package com.humanup.matrix.training.trainingmatrix.dao;

import com.humanup.matrix.training.trainingmatrix.dao.entities.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseDAO extends CrudRepository<Course, Long> {
    Optional<Course> findByTitle(String courseTitle);
    @Query("SELECT c FROM Course as c, Intern i, Review r WHERE lower(i.emailPerson) = :internEmail" +
            " AND r.id.courseId = c.id AND r.id.internId = i.id")
    List<Course> findAllByInternEmail(String internEmail);
    @Query("SELECT c FROM Course c WHERE lower(c.courseType.typeTitle) = :typeTitle")
    List<Course> findAllByTypeTitle(String typeTitle);
    @Query("SELECT c FROM Course c WHERE lower(c.trainer.email) = :trainerEmail")
    List<Course> findAllByTrainerEmail(String trainerEmail);
}
