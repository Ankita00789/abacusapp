package com.example.abacusup.Custom;

public class GenResult {
    private String Status;
    private String Message;
    public void set(com.example.abacusup.Custom.GenResult g){
        this.Status=g.Status;
        this.Message=g.Message;
    }

    public String getStatus() {
        return Status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
