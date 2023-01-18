package com.nguyen1207.vishbe.entities.review;

import com.nguyen1207.vishbe.entities.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
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
}
