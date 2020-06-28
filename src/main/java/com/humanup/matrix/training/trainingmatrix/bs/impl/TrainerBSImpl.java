package com.humanup.matrix.training.trainingmatrix.bs.impl;

import com.humanup.matrix.training.trainingmatrix.aop.dto.CourseException;
import com.humanup.matrix.training.trainingmatrix.aop.dto.TrainerException;
import com.humanup.matrix.training.trainingmatrix.bs.TrainerBS;
import com.humanup.matrix.training.trainingmatrix.bs.impl.sender.RabbitMQTrainerSender;
import com.humanup.matrix.training.trainingmatrix.dao.TrainerDAO;
import com.humanup.matrix.training.trainingmatrix.dao.entities.Trainer;
import com.humanup.matrix.training.trainingmatrix.vo.TrainerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional(readOnly = true)
public class TrainerBSImpl implements TrainerBS {
  @Autowired private TrainerDAO trainerDAO;
  @Autowired private RabbitMQTrainerSender rabbitMQTrainerSender;

  @Override
  @Transactional(
      transactionManager = "transactionManagerWrite",
      rollbackFor = TrainerException.class)
  public boolean createTrainer(final TrainerVO trainer) throws TrainerException {
    if (null == trainer) {
      throw new TrainerException();
    }
    rabbitMQTrainerSender.send(trainer);
    return true;
  }

  @Override
  public List<TrainerVO> getListTrainer() {
    return StreamSupport.stream(trainerDAO.findAll().spliterator(), false)
        .map(
            trainer ->
                TrainerVO.builder()
                    .name(trainer.getName())
                    .email(trainer.getEmail())
                    .address(trainer.getAddress())
                    .phone(trainer.getPhone())
                    .build())
        .collect(Collectors.toList());
  }

  @Override
  public TrainerVO getTrainerById(final long id) {
    final Optional<Trainer> trainerFound = trainerDAO.findById(id);
    return trainerFound
        .map(
            trainer ->
                TrainerVO.builder()
                    .name(trainer.getName())
                    .email(trainer.getEmail())
                    .address(trainer.getAddress())
                    .phone(trainer.getPhone())
                    .build())
        .orElse(null);
  }

  @Override
  public TrainerVO getTrainerByEmail(final String email) {
    final Optional<Trainer> trainerFound = trainerDAO.findByEmail(email);
    return trainerFound
        .map(
            trainer ->
                TrainerVO.builder()
                    .name(trainer.getName())
                    .email(trainer.getEmail())
                    .address(trainer.getAddress())
                    .phone(trainer.getPhone())
                    .build())
        .orElse(null);
  }

  @Override
  public TrainerVO getTrainerByPhone(final String phone) {
    final Optional<Trainer> trainerFound = trainerDAO.findByPhone(phone);
    return trainerFound
        .map(
            trainer ->
                TrainerVO.builder()
                    .name(trainer.getName())
                    .email(trainer.getEmail())
                    .address(trainer.getAddress())
                    .phone(trainer.getPhone())
                    .build())
        .orElse(null);
  }

  @Override
  public List<TrainerVO> getListTrainerByName(final String name) {
    final List<Trainer> trainerList = trainerDAO.findAllByName(name);
    return trainerList.stream()
        .map(
            trainer ->
                TrainerVO.builder()
                    .name(trainer.getName())
                    .email(trainer.getEmail())
                    .address(trainer.getAddress())
                    .phone(trainer.getPhone())
                    .build())
        .collect(Collectors.toList());
  }
}
