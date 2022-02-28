package uz.binaryorient.appwebhookbot.cache;

import uz.binaryorient.appwebhookbot.enums.BotStateEnum;

public interface DataCache {
    void setUserBotState(long userId, BotStateEnum botState);

    BotStateEnum getCurrentBotState(long userId);


}
