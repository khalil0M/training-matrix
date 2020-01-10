package com.humanup.matrix.training.trainingmatrix.dao.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(of= {"id","emailPerson","reviewList"})
@Entity
public class Intern {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    Long id;
    String emailPerson;
    @OneToMany(
            mappedBy = "intern",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<Review> reviewList = new ArrayList<>();
}
