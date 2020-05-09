package com.humanup.matrix.training.trainingmatrix.dao.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(of = {"id", "typeTitle", "courses"})
@Entity
@Table(name = "course_type")
public class CourseType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    Long id;
    @Column(name = "type_title")
    String typeTitle;
    @OneToMany(mappedBy="courseType",fetch=FetchType.LAZY)
    List<Course> courses;
}
