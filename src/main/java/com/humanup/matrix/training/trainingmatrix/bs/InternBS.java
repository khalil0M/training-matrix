package com.humanup.matrix.training.trainingmatrix.bs;

import com.humanup.matrix.training.trainingmatrix.aop.dto.InternException;
import com.humanup.matrix.training.trainingmatrix.vo.InternVO;

import java.util.List;

public interface InternBS {
  boolean createIntern(InternVO intern) throws InternException;

  List<InternVO> getListIntern();

  InternVO getInternById(long id);

  InternVO getInternByEmail(String email);

  List<InternVO> getListInternByCourseTitle(String courseTitle);
}
