package uz.binaryorient.appwebhookbot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.binaryorient.appwebhookbot.model.BotUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MainServiceImpl{

    public List<PartialBotApiMethod<Message>> receiveUpdate(Update update) {
        String  chatId = update.getMessage().getChatId().toString();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Assalomalekum Inflex-Lunch botga xush kelibsiz");

        BotUser botUser = new BotUser();
        botUser.setChatId(chatId);
        ReplyKeyboardMarkup replyKeyboardMarkup = contactButton(botUser);
        message.setReplyMarkup(replyKeyboardMarkup);
        return Collections.singletonList(message);
    }

    public ReplyKeyboardMarkup contactButton(BotUser currentUser) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> rowList = new ArrayList<>();
        replyKeyboardMarkup.setKeyboard(rowList);

        if (currentUser.getRound() == 1) {
            KeyboardRow row = new KeyboardRow();
            KeyboardButton button = new KeyboardButton();
            button.setRequestContact(true);
            button.setText("📞Share Contact📞");
            row.add(button);
            rowList.add(row);
        }
        return replyKeyboardMarkup;
    }
}
