package com.humanup.matrix.training.trainingmatrix.dao.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(of= {"id","typeTitle","courses"})
@Entity
public class CourseType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    Long id;
    String typeTitle;
    @OneToMany(mappedBy="courseType",fetch=FetchType.LAZY)
    List<Course> courses;
}
