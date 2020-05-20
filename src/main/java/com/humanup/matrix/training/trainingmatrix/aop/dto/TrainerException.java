package com.humanup.matrix.training.trainingmatrix.aop.dto;

public class TrainerException extends HttpException {
    public TrainerException(String message) {
        super(message);
    }

    public TrainerException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Cannot Create Trainer";
    }
}
