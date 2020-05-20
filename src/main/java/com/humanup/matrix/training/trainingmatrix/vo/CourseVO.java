package com.humanup.matrix.training.trainingmatrix.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(
    of = {
      "courseTypeTitle",
      "trainerEmail",
      "title",
      "description",
      "startDate",
      "endDate",
      "reviewList"
    })
public class CourseVO implements Serializable {
  String courseTypeTitle;
  String trainerEmail;
  String title;
  String description;
  Date startDate;
  Date endDate;
  List<ReviewVO> reviewList;
}
