package com.humanup.matrix.training.trainingmatrix.aop.dto;

public class InternException extends HttpException {
    public InternException(String message) {
        super(message);
    }

    public InternException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Cannot Create Intern";
    }
}
