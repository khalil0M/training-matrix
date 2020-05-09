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
@ToString(of = {"id", "name", "address", "phone", "email", "courses"})
@Entity
@Table(name = "trainer")
public class Trainer implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  Long id;

  @Column(name = "name")
  String name;

  @Column(name = "address")
  String address;

  @Column(name = "phone")
  String phone;

  @Column(name = "email")
  String email;

  @OneToMany(mappedBy = "trainer", fetch = FetchType.EAGER)
  List<Course> courses;
}
