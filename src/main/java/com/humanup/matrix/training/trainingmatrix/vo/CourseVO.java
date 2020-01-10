package com.humanup.matrix.training.trainingmatrix.vo;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(of={"id","courseType","trainerEmail","title","description","startDate","endDate","reviewList"})
public class CourseVO {
    long id;
    String courseTypeTitle;
    String trainerEmail;
    String title;
    String description;
    Date startDate;
    Date endDate;
    List<ReviewVO> reviewList;
}
