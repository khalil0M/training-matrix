package com.humanup.matrix.training.trainingmatrix.dao.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(of = {"internId", "courseId"})
@EqualsAndHashCode
@Embeddable
public class InternCourseId implements Serializable {
  @Column(name = "intern_id")
  long internId;

  @Column(name = "course_id")
  long courseId;
}
