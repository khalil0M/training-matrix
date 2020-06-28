package com.humanup.matrix.training.trainingmatrix.bs.impl;

import com.humanup.matrix.training.trainingmatrix.aop.dto.CourseException;
import com.humanup.matrix.training.trainingmatrix.aop.dto.InternException;
import com.humanup.matrix.training.trainingmatrix.bs.InternBS;
import com.humanup.matrix.training.trainingmatrix.bs.impl.sender.RabbitMQInternSender;
import com.humanup.matrix.training.trainingmatrix.dao.InternDAO;
import com.humanup.matrix.training.trainingmatrix.dao.entities.Intern;
import com.humanup.matrix.training.trainingmatrix.vo.InternVO;
import com.humanup.matrix.training.trainingmatrix.vo.ReviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional(readOnly = true)
public class InternBSImpl implements InternBS {
  @Autowired private InternDAO internDAO;
  @Autowired private RabbitMQInternSender rabbitMQInternSender;

  @Override
  @Transactional(
      transactionManager = "transactionManagerWrite",
      rollbackFor = InternException.class)
  public boolean createIntern(final InternVO intern) throws InternException {
    if (null == intern) {
      throw new InternException();
    }
    rabbitMQInternSender.send(intern);
    return true;
  }

  @Override
  public List<InternVO> getListIntern() {
    return StreamSupport.stream(internDAO.findAll().spliterator(), false)
        .map(
            intern ->
                InternVO.builder()
                    .emailPerson(intern.getEmailPerson())
                    .reviewList(
                        intern.getReviewList().stream()
                            .map(
                                review ->
                                    ReviewVO.builder()
                                        .courseTitle(review.getCourse().getTitle())
                                        .internEmail(review.getIntern().getEmailPerson())
                                        .createdOn(review.getCreatedOn())
                                        .score(review.getScore())
                                        .build())
                            .collect(Collectors.toList()))
                    .build())
        .collect(Collectors.toList());
  }

  @Override
  public InternVO getInternById(final long id) {
    final Optional<Intern> internFound = internDAO.findById(id);
    return internFound
        .map(
            intern ->
                InternVO.builder()
                    .emailPerson(intern.getEmailPerson())
                    .reviewList(
                        intern.getReviewList().stream()
                            .map(
                                review ->
                                    ReviewVO.builder()
                                        .courseTitle(review.getCourse().getTitle())
                                        .internEmail(review.getIntern().getEmailPerson())
                                        .createdOn(review.getCreatedOn())
                                        .score(review.getScore())
                                        .build())
                            .collect(Collectors.toList()))
                    .build())
        .orElse(null);
  }

  @Override
  public InternVO getInternByEmail(final String email) {
    final Optional<Intern> internFound = internDAO.findByEmailPerson(email);
    return internFound
        .map(
            intern ->
                InternVO.builder()
                    .emailPerson(intern.getEmailPerson())
                    .reviewList(
                        intern.getReviewList().stream()
                            .map(
                                review ->
                                    ReviewVO.builder()
                                        .courseTitle(review.getCourse().getTitle())
                                        .internEmail(review.getIntern().getEmailPerson())
                                        .createdOn(review.getCreatedOn())
                                        .score(review.getScore())
                                        .build())
                            .collect(Collectors.toList()))
                    .build())
        .orElse(null);
  }

  @Override
  public List<InternVO> getListInternByCourseTitle(final String courseTitle) {
    final List<Intern> internList = internDAO.findAllByCourseTitle(courseTitle);
    return internList.stream()
        .map(
            intern ->
                InternVO.builder()
                    .emailPerson(intern.getEmailPerson())
                    .reviewList(
                        intern.getReviewList().stream()
                            .map(
                                review ->
                                    ReviewVO.builder()
                                        .courseTitle(review.getCourse().getTitle())
                                        .internEmail(review.getIntern().getEmailPerson())
                                        .createdOn(review.getCreatedOn())
                                        .score(review.getScore())
                                        .build())
                            .collect(Collectors.toList()))
                    .build())
        .collect(Collectors.toList());
  }
}
