package uz.binaryorient.appwebhookbot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultWebhook;
import uz.binaryorient.appwebhookbot.MySpringTelegramBot;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Slf4j
@Configuration
public class BotConfig {

    @Value("${bot.token}")
    private String TOKEN;

    @Value("${bot.name}")
    private String BOT_USERNAME;

    @Value("${bot.path}")
    private String BOT_PATH;

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(BOT_PATH).build();
    } // public address, now it is ngrok, in the future it will (i think) be the server address
    // Create it as

    @Bean
    public MySpringTelegramBot mySpringTelegramBot(SetWebhook setWebhookInstance) throws TelegramApiException {

        MySpringTelegramBot mySpringTelegram = new MySpringTelegramBot(setWebhookInstance);
        mySpringTelegram.setBOT_USERNAME(BOT_USERNAME);
        mySpringTelegram.setTOKEN(TOKEN);
        mySpringTelegram.setBOT_PATH("adam");
        DefaultWebhook defaultWebhook = new DefaultWebhook();

        defaultWebhook.setInternalUrl(
                "http://localhost:80"); // the port to start the server, on the localhost computer, on the server it
        // be the server address
        //   defaultWebhook.registerWebhook(adamSmithBot);

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class, defaultWebhook);

        log.info("SetWebHook from AdamSmith bot {}", setWebhookInstance);
        mySpringTelegram.getBotUsername();
        telegramBotsApi.registerBot(mySpringTelegram, setWebhookInstance);
        return mySpringTelegram;

    }

}
