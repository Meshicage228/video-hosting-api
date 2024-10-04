package ru.clevertec.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.clevertec.enums.Category;
import ru.clevertec.enums.Language;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "channels")
public class ChannelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;

    @Column(name = "short_description")
    private String shortDescription;

    @ManyToOne
    @JoinColumn(
        name = "author_id"
    )
    private UserEntity author;

//    @ManyToMany
//    private Set<UserEntity> subscribers;

    @DateTimeFormat
    @Column(name = "date_of_creation")
    private Date dateOfCreation;

    @Enumerated(value = EnumType.STRING)
    private Language language;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    @Lob
    private Byte[] avatar;
}
