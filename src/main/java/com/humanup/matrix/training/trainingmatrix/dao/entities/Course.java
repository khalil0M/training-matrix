package com.humanup.matrix.training.trainingmatrix.dao.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(of= {"id","courseType","trainer","title","description","startDate","endDate","reviewList"})
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    Long id;
    @ManyToOne
    @JoinColumn(name = "idType")
    CourseType courseType;
    @ManyToOne
    @JoinColumn(name = "idTrainer")
    Trainer trainer;
    String title;
    String description;
    Date startDate;
    Date endDate;
    @OneToMany(
            mappedBy = "course",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<Review> reviewList = new ArrayList<>();
}
