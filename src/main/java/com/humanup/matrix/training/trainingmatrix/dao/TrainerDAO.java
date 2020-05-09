package com.humanup.matrix.training.trainingmatrix.dao;

import com.humanup.matrix.training.trainingmatrix.dao.entities.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainerDAO extends JpaRepository<Trainer, Long> {
  List<Trainer> findAllByName(String name);

  Optional<Trainer> findByPhone(String phone);

  Optional<Trainer> findByEmail(String email);
}
