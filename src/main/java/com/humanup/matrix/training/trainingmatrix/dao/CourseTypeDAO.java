package com.humanup.matrix.training.trainingmatrix.dao;

import com.humanup.matrix.training.trainingmatrix.dao.entities.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseTypeDAO extends JpaRepository<CourseType, Long> {
  Optional<CourseType> findByTypeTitle(String typeTitle);
}
