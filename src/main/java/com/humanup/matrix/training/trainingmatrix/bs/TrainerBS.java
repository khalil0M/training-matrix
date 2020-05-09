package com.humanup.matrix.training.trainingmatrix.bs;

import com.humanup.matrix.training.trainingmatrix.aop.dto.TrainerException;
import com.humanup.matrix.training.trainingmatrix.vo.TrainerVO;

import java.util.List;

public interface TrainerBS {
  boolean createTrainer(TrainerVO trainer) throws TrainerException;

  List<TrainerVO> getListTrainer();

  TrainerVO getTrainerById(long id);

  TrainerVO getTrainerByEmail(String email);

  TrainerVO getTrainerByPhone(String phone);

  List<TrainerVO> getListTrainerByName(String name);
}
