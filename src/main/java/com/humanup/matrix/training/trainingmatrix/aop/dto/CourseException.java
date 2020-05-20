package com.humanup.matrix.training.trainingmatrix.aop.dto;

public class CourseException extends HttpException {
    public CourseException(String message) {
        super(message);
    }

    public CourseException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Cannot Create Course";
    }
}
