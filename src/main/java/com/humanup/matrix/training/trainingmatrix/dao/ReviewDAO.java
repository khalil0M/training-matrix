package com.humanup.matrix.training.trainingmatrix.dao;

import com.humanup.matrix.training.trainingmatrix.dao.entities.InternCourseId;
import com.humanup.matrix.training.trainingmatrix.dao.entities.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewDAO extends CrudRepository<Review, InternCourseId> {
    List<Review> findAllByScore(int score);
    @Query("SELECT r FROM Review r WHERE lower(r.course.title) = :courseTitle")
    List<Review> findAllByCourseTitle(String courseTitle);
    @Query("SELECT r FROM Review r WHERE lower(r.intern.emailPerson) = :internEmail")
    List<Review> findAllByInternEmail(String internEmail);
}
