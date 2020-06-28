package com.humanup.matrix.training.trainingmatrix.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(of = {"emailPerson", "reviewList"})
public class InternVO implements Serializable {
  String emailPerson;
  List<ReviewVO> reviewList;
}
