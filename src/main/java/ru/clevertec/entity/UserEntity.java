package ru.clevertec.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "actual_name")
    private String actualName;
    private String email;

    @ManyToMany(mappedBy = "subscribers", fetch = FetchType.LAZY)
    private Set<ChannelEntity> subscriptions = new HashSet<>();

    @PreRemove
    private void preRemove() {
        subscriptions.forEach(subscription -> subscription.getSubscribers().remove(this));
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
