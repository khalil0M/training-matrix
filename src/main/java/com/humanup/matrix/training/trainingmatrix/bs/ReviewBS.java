package com.humanup.matrix.training.trainingmatrix.bs;

import com.humanup.matrix.training.trainingmatrix.vo.ReviewVO;

import java.util.List;

public interface ReviewBS {
    boolean createReview(ReviewVO review);
    List<ReviewVO> getListReview();
    ReviewVO getReviewById(long courseId, long internId);
    List<ReviewVO> getListReviewByScore(int score);
    List<ReviewVO> getListReviewByCourseTitle(String courseTitle);
    List<ReviewVO> getListReviewByInternEmail(String internEmail);
}
