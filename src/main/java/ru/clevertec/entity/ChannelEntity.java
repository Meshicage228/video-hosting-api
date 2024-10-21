package ru.clevertec.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.clevertec.enums.Language;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@FieldNameConstants
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "channels")
public class ChannelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "channel_gen")
    @SequenceGenerator(name = "channel_gen", schema = "public", allocationSize = 1, sequenceName = "channel_seq")
    private Long id;

    private String title;

    @Column(name = "short_description")
    private String shortDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "author_id"
    )
    private UserEntity author;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "subscription_user",
            joinColumns = @JoinColumn(name = "channel_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<UserEntity> subscribers = new HashSet<>();

    @Column(name = "date_of_creation")
    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime dateOfCreation;

    @Enumerated(value = EnumType.STRING)
    private Language language;

    @OneToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @Lob
    private byte[] avatar;

    @PreRemove
    private void preRemove() {
        subscribers.forEach(subscriber -> subscriber.getSubscriptions().remove(this));
    }
}
