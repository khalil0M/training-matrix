package com.humanup.matrix.training.trainingmatrix.controllers;

import com.humanup.matrix.training.trainingmatrix.aop.dto.TrainerException;
import com.humanup.matrix.training.trainingmatrix.bs.TrainerBS;
import com.humanup.matrix.training.trainingmatrix.vo.TrainerVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TrainerController {
  @Autowired private TrainerBS trainerBS;

  @Operation(
      summary = "Create new trainer",
      description = "Create new trainer",
      tags = {"trainer"})
  @RequestMapping(
      value = "/trainer",
      method = RequestMethod.POST,
      consumes = {"application/json"})
  @ResponseBody
  public ResponseEntity createTrainer(@RequestBody final TrainerVO trainer)
      throws TrainerException {
    final Optional<Object> trainerFound =
        Optional.ofNullable(trainerBS.getTrainerByEmail(trainer.getEmail()));
    if (trainerFound.isPresent()) {
      return ResponseEntity.status(HttpStatus.FOUND).body("Trainer already exists.");
    }
    trainerBS.createTrainer(trainer);
    return ResponseEntity.status(HttpStatus.CREATED).body(trainer);
  }

  @Operation(
      summary = "Find trainer by id",
      description = "Find trainer by id.",
      tags = {"trainer"})
  @RequestMapping(value = "/trainer", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity getTrainerById(@RequestParam(value = "id") final long id) {
    final Optional<TrainerVO> trainerFound = Optional.ofNullable(trainerBS.getTrainerById(id));
    if (trainerFound.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(trainerFound.get());
  }

  @Operation(
      summary = "Find trainer by email",
      description = "Find trainer by email.",
      tags = {"trainer"})
  @RequestMapping(value = "/trainer/email", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity getTrainerByEmail(
      @RequestParam(value = "email", defaultValue = "robot@sqli.com") final String email) {
    final Optional<TrainerVO> trainerFound =
        Optional.ofNullable(trainerBS.getTrainerByEmail(email));
    if (trainerFound.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(trainerFound.get());
  }

  @Operation(
      summary = "Find trainer by phone",
      description = "Find trainer by phone.",
      tags = {"trainer"})
  @RequestMapping(value = "/trainer/phone", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity getTrainerByPhone(@RequestParam(value = "phone") final String phone) {
    final Optional<TrainerVO> trainerFound =
        Optional.ofNullable(trainerBS.getTrainerByPhone(phone));
    if (trainerFound.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(trainerFound.get());
  }

  @Operation(
      summary = "Find all trainers",
      description = "Find all trainers",
      tags = {"trainer"})
  @RequestMapping(value = "/trainer/all", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity getAllTrainer() {
    final List<TrainerVO> trainerListFound = trainerBS.getListTrainer();
    if (trainerListFound.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(trainerListFound);
  }

  @Operation(
      summary = "Find all trainers by name",
      description = "Find all trainers by name",
      tags = {"trainer"})
  @RequestMapping(value = "/trainer/all/name", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity getAllTrainerByName(@RequestParam(value = "name") final String name) {
    final List<TrainerVO> trainerListFound = trainerBS.getListTrainerByName(name);
    if (trainerListFound.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(trainerListFound);
  }
}
