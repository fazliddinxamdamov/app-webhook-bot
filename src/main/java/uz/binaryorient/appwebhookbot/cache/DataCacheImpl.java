package uz.binaryorient.appwebhookbot.cache;

import org.springframework.stereotype.Component;
import uz.binaryorient.appwebhookbot.enums.BotStateEnum;

import java.util.HashMap;
import java.util.Map;

@Component
public class DataCacheImpl implements DataCache {
    private Map<Long, BotStateEnum> usersBotState = new HashMap<>();

    @Override
    public void setUserBotState(long userId, BotStateEnum botState) {
        usersBotState.put(userId, botState);
    }

    @Override
    public BotStateEnum getCurrentBotState(long userId) {
        BotStateEnum botStateEnum = usersBotState.get(userId);
        if (botStateEnum == null) {
            return BotStateEnum.START;
        }
        return botStateEnum;
    }
}
