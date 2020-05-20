package com.humanup.matrix.training.trainingmatrix.aop.dto;

public class CourseTypeException extends HttpException {
    public CourseTypeException(String message) {
        super(message);
    }

    public CourseTypeException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Cannot Create Course Type";
    }
}
