package com.humanup.matrix.training.trainingmatrix.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(of={"id","typeTitle"})
public class CourseTypeVO implements Serializable {
    long id;
    String typeTitle;
}
