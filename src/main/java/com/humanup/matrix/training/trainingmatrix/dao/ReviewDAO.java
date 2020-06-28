package com.humanup.matrix.training.trainingmatrix.dao;

import com.humanup.matrix.training.trainingmatrix.dao.entities.InternCourseId;
import com.humanup.matrix.training.trainingmatrix.dao.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewDAO extends JpaRepository<Review, InternCourseId> {
  List<Review> findAllByScore(int score);

  @Query("SELECT r FROM Review r WHERE r.course.title like %:courseTitle%")
  List<Review> findAllByCourseTitle(String courseTitle);

  @Query("SELECT r FROM Review r WHERE r.intern.emailPerson like %:internEmail%")
  List<Review> findAllByInternEmail(String internEmail);
}
