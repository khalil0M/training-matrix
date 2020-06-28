package com.humanup.matrix.training.trainingmatrix.aop.dto;

public class ReviewException extends HttpException {
    public ReviewException(String message) {
        super(message);
    }

    public ReviewException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Cannot Create Review";
    }
}
