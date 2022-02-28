package uz.binaryorient.appwebhookbot;

import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.starter.SpringWebhookBot;
import uz.binaryorient.appwebhookbot.service.MainServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Setter
public class MySpringTelegramBot extends SpringWebhookBot {
    @Value("${bot.token}")
    private String TOKEN;

    @Value("${bot.name}")
    private String BOT_USERNAME;

    @Value("${bot.path}")
    private String BOT_PATH;

    @Autowired
    private MainServiceImpl mainService;

    public MySpringTelegramBot(SetWebhook setWebhook) {
        super(setWebhook);
    }

    public MySpringTelegramBot(DefaultBotOptions options, SetWebhook setWebhook) {
        super(options, setWebhook);
    }

    @Override
    public String getBotUsername() {

        log.info("BOT_USERNAME FROM ADAM BOT {}", BOT_USERNAME);
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    @SneakyThrows
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {

        String chat_id = ""; // todo
        String first_name = "";
        if (update.hasMessage()) {
            chat_id = update.getMessage().getChatId().toString();
            first_name = update.getMessage().getFrom().getFirstName();
        } else if (update.hasCallbackQuery()) {
            chat_id = update.getCallbackQuery().getMessage().getChatId().toString();
            first_name = update.getCallbackQuery().getMessage().getFrom().getFirstName();
        }

        if (update.hasMessage()){
            Message message = update.getMessage();
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId().toString());
            String text = message.getText();

            if (message.hasText()){

                if (text.equals("/start")){
                    sendMessage.setText("Assalomu alaykum. " + currentUser.getFullName());
                    sendMessage.setText("Ro'yxatdan o'tish uchun telefon raqamingizni jo'nating.");
                    currentUser.setRound(1);
                }

            } else if (message.hasContact()){

            }

        }

        return null;
    }

    

    public void sendMessage(String chatId, String textMessage) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(textMessage);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendAnswerCallbackQuery(String callbackId, String message) {
        AnswerCallbackQuery answer = new AnswerCallbackQuery();
        answer.setCallbackQueryId(callbackId);
        answer.setText(message);
        answer.setShowAlert(true);

        try {
            execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }



    @Override
    public String getBotPath() {
        return "adam"; //any other path here
    }
}
