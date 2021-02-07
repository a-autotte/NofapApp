package com.example.nofapmainactivity;

public class NofapServer {
    private int imageProfile;
    private String nofapservername;
    private String nofapserverDescription;
    public int getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(int imageProfile) {
        this.imageProfile = imageProfile;
    }

    public String getUsername() {
        return nofapservername;
    }

    public void setUsername(String username) {
        this.nofapservername = username;
    }

    public String getUserDescription() {
        return nofapserverDescription;
    }

    public void setUserDescription(String userDescription) {
        this.nofapserverDescription = userDescription;
    }



    public NofapServer(int imageProfile, String nofapservername, String nofapserverDescription)
    {
        this.imageProfile = imageProfile;
        this.nofapservername = nofapservername;
        this.nofapserverDescription = nofapserverDescription;
    }

}
