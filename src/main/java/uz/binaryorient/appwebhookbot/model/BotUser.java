package uz.binaryorient.appwebhookbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Fazliddin Xamdamov
 * @date 28.02.2022  18:31
 * @project app-webhook-bot
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BotUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String chatId;
    private String FullName;
    private String phoneNumber;
    private Integer round;
    private String role;
    private String section;
    private String position;
}
