package com.humanup.matrix.training.trainingmatrix.dao;

import com.humanup.matrix.training.trainingmatrix.dao.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseDAO extends JpaRepository<Course, Long> {
  Optional<Course> findByTitle(String courseTitle);

  @Query(
      "SELECT c FROM Course as c, Intern i, Review r WHERE i.emailPerson like %:internEmail%"
          + " AND r.id.courseId = c.id AND r.id.internId = i.id")
  List<Course> findAllByInternEmail(String internEmail);

  @Query("SELECT c FROM Course c WHERE c.courseType.typeTitle like %:typeTitle%")
  List<Course> findAllByTypeTitle(String typeTitle);

  @Query("SELECT c FROM Course c WHERE c.trainer.email like %:trainerEmail%")
  List<Course> findAllByTrainerEmail(String trainerEmail);
}
