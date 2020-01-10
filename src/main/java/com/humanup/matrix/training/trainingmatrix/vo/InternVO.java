package com.humanup.matrix.training.trainingmatrix.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(of={"id","emailPerson","reviewList"})
public class InternVO {
    long id;
    String emailPerson;
    List<ReviewVO> reviewList;
}
