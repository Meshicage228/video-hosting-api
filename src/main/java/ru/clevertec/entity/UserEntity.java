package ru.clevertec.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "actual_name")
    private String actualName;

    private String email;

    @ManyToMany(mappedBy = "subscribers")
    private Set<ChannelEntity> subscriptions = new HashSet<>();

    @OneToMany(mappedBy = "author")
    private Set<ChannelEntity> createdChannels = new HashSet<>();

    @PreRemove
    private void preRemove() {
        subscriptions.forEach(subscription -> subscription.getSubscribers().remove(this));
        createdChannels.forEach(createdChannels -> createdChannels.setAuthor(null));
    }

    public void addSubscription(ChannelEntity channelEntity) {
        subscriptions.add(channelEntity);
        channelEntity.getSubscribers().add(this);
    }

    public void removeSubscription(ChannelEntity channelEntity) {
        subscriptions.remove(channelEntity);
        channelEntity.getSubscribers().remove(this);
    }
}
