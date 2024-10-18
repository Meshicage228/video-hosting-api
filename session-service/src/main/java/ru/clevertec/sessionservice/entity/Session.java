package ru.clevertec.sessionservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Document(collection = "sessions")
public class Session {
    @Id
    private String id;

    @Field("Login")
    private String login;

    @Field("creation_date")
    private LocalDate creationDate;
}
