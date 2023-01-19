package com.nguyen1207.vishbe.entities.review;

import com.nguyen1207.vishbe.entities.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vote {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String voteId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reviewId", nullable = false)
    private Review review;

    private boolean isUpvote;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Vote vote = (Vote) o;
        return voteId != null && Objects.equals(voteId, vote.voteId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
