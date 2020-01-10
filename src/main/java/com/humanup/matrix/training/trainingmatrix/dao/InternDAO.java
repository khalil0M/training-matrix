package com.humanup.matrix.training.trainingmatrix.dao;

import com.humanup.matrix.training.trainingmatrix.dao.entities.Intern;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InternDAO extends CrudRepository<Intern, Long> {
    Optional<Intern> findByEmailPerson(String emailPerson);
    @Query("SELECT i FROM Intern as i, Course c, Review r WHERE lower(c.title) = :courseTitle" +
            " AND r.id.courseId = c.id AND r.id.internId = i.id")
    List<Intern> findAllByCourseTitle(String courseTitle);
}
