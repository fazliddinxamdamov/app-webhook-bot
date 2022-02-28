package uz.binaryorient.appwebhookbot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.binaryorient.appwebhookbot.model.BotUser;

/**
 * @author Fazliddin Xamdamov
 * @date 28.02.2022  19:38
 * @project app-webhook-bot
 */
public interface UserRepo extends JpaRepository<BotUser , Long> {

}
