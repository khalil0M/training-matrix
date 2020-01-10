package com.humanup.matrix.training.trainingmatrix.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(of={"courseId","internId","course","intern","createdOn","score"})
public class ReviewVO {
    long courseId;
    long internId;
    String courseTitle;
    String internEmail;
    Date createdOn;
    int score;
}
