package com.example.healthyway.ui.dietician.clientsList;

public class RecyclerData {

    private String clientFirstName;
    private String clientLastName;
    private int imgid;

    public String getClientFirstName() {
        return clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public int getImgid() {
        return imgid;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public RecyclerData(String clientFirstName, String clientLastName, int imgid) {
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.imgid = imgid;
    }
}
