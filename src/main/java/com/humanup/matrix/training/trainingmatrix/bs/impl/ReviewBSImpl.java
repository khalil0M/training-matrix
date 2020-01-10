package com.humanup.matrix.training.trainingmatrix.bs.impl;

import com.humanup.matrix.training.trainingmatrix.bs.ReviewBS;
import com.humanup.matrix.training.trainingmatrix.dao.CourseDAO;
import com.humanup.matrix.training.trainingmatrix.dao.InternDAO;
import com.humanup.matrix.training.trainingmatrix.dao.ReviewDAO;
import com.humanup.matrix.training.trainingmatrix.dao.entities.Course;
import com.humanup.matrix.training.trainingmatrix.dao.entities.Intern;
import com.humanup.matrix.training.trainingmatrix.dao.entities.InternCourseId;
import com.humanup.matrix.training.trainingmatrix.dao.entities.Review;
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
public class ReviewBSImpl implements ReviewBS {
    @Autowired
    private ReviewDAO reviewDAO;
    @Autowired
    private InternDAO internDAO;
    @Autowired
    private CourseDAO courseDAO;

    @Override
    @Transactional
    public boolean createReview(final ReviewVO review) {
        final Optional<Intern> intern =  internDAO.findByEmailPerson(review.getInternEmail());
        final Optional<Course> course =  courseDAO.findByTitle(review.getCourseTitle());
        final Review reviewToSave = Review.builder()
                .intern(intern.get())
                .course(course.get())
                .score(review.getScore())
                .createdOn(review.getCreatedOn())
                .build();
        reviewDAO.save(reviewToSave);
        return true;
    }

    @Override
    public List<ReviewVO> getListReview() {
        return StreamSupport.stream(reviewDAO.findAll().spliterator(), false)
                .map(review -> ReviewVO.builder()
                        .courseId(review.getId().getCourseId())
                        .internId(review.getId().getInternId())
                        .score(review.getScore())
                        .createdOn(review.getCreatedOn())
                        .internEmail(review.getIntern().getEmailPerson())
                        .courseTitle(review.getCourse().getTitle())
                        .build())
                        .collect(Collectors.toList());
    }

    @Override
    public ReviewVO getReviewById(final long courseId, final long internId) {
        final Optional<Review> reviewFound = reviewDAO.findById(new InternCourseId(internId, courseId));
        return reviewFound.map(review -> ReviewVO.builder()
                .courseId(review.getId().getCourseId())
                .internId(review.getId().getInternId())
                .score(review.getScore())
                .createdOn(review.getCreatedOn())
                .internEmail(review.getIntern().getEmailPerson())
                .courseTitle(review.getCourse().getTitle())
                .build()).orElse(null);
    }

    @Override
    public List<ReviewVO> getListReviewByScore(final int score) {
        final List<Review> reviewListFound = reviewDAO.findAllByScore(score);
        return reviewListFound.stream()
                .map(review -> ReviewVO.builder()
                        .courseId(review.getId().getCourseId())
                        .internId(review.getId().getInternId())
                        .score(review.getScore())
                        .createdOn(review.getCreatedOn())
                        .internEmail(review.getIntern().getEmailPerson())
                        .courseTitle(review.getCourse().getTitle())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public List<ReviewVO> getListReviewByCourseTitle(final String courseTitle) {
        final List<Review> reviewList = reviewDAO.findAllByCourseTitle(courseTitle);
        return reviewList.stream()
                .map(review -> ReviewVO.builder()
                        .courseId(review.getId().getCourseId())
                        .internId(review.getId().getInternId())
                        .score(review.getScore())
                        .createdOn(review.getCreatedOn())
                        .internEmail(review.getIntern().getEmailPerson())
                        .courseTitle(review.getCourse().getTitle())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public List<ReviewVO> getListReviewByInternEmail(final String internEmail) {
        final List<Review> reviewList = reviewDAO.findAllByInternEmail(internEmail);
        return reviewList.stream()
                .map(review -> ReviewVO.builder()
                        .courseId(review.getId().getCourseId())
                        .internId(review.getId().getInternId())
                        .score(review.getScore())
                        .createdOn(review.getCreatedOn())
                        .internEmail(review.getIntern().getEmailPerson())
                        .courseTitle(review.getCourse().getTitle())
                        .build()).collect(Collectors.toList());
    }
}
