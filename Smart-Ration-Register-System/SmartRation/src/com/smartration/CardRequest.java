package com.smartration;

public class CardRequest {
    private String id;
    private int status;

    //Getters and Setter
    public CardRequest(String id, int status) {
        this.id = id;
        this.status = status;
    }

    public CardRequest() {
        id = "";
        status = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void deleteAll(){
        this.id = "";
        this.id = "";
    }
}
