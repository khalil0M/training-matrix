package com.humanup.matrix.training.trainingmatrix.dao.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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
      "id",
      "courseType",
      "trainer",
      "title",
      "description",
      "startDate",
      "endDate",
      "reviewList"
    })
@Entity
@Table(name = "course")
public class Course implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  Long id;

  @ManyToOne
  @JoinColumn(name = "id_type")
  CourseType courseType;

  @ManyToOne
  @JoinColumn(name = "id_trainer")
  Trainer trainer;

  @Column(name = "title")
  String title;

  @Column(name = "description")
  String description;

  @Column(name = "start_date")
  Date startDate;

  @Column(name = "end_date")
  Date endDate;

  @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
  List<Review> reviewList = new ArrayList<>();
}
