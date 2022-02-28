package uz.binaryorient.appwebhookbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Fazliddin Xamdamov
 * @date 28.02.2022  18:43
 * @project app-webhook-bot
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
