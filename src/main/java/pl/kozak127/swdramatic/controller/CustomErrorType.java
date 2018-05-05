package pl.kozak127.swdramatic.controller;

public class CustomErrorType {

    private final String errorMessage;

    public CustomErrorType(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}