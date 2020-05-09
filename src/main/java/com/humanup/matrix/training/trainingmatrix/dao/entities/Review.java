package com.humanup.matrix.training.trainingmatrix.dao.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(of = {"id", "course", "intern", "createdOn", "score"})
@Entity
@Table(name = "review")
public class Review implements Serializable {
  @EmbeddedId InternCourseId id;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("courseId")
  Course course;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("internId")
  Intern intern;

  @Column(name = "created_on")
  Date createdOn = new Date();

  @Column(name = "score")
  int score;
}
