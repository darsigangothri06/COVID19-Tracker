package com.example.trackercovid_19.Model;

public class District {

    private String districtNAME;
    private String activeSent;
    private String recoveredSent;
    private String confirmedSent;
    private String deathsSent;

    public District(String districtName, String active, String recovered, String confirmed, String deaths) {
        this.districtNAME = districtName;
        this.activeSent = active;
        this.recoveredSent = recovered;
        this.confirmedSent = confirmed;
        this.deathsSent = deaths;
    }

    public String getDistrictName() {
        return districtNAME;
    }
    public void setDistrictName(String districtName) {
        this.districtNAME = districtName;
    }
    public String getActive() {
        return activeSent;
    }
    public void setActive(String active) {
        this.activeSent = active;
    }
    public String getRecovered() {
        return recoveredSent;
    }
    public void setRecovered(String recovered) {
        this.recoveredSent = recovered;
    }
    public String getConfirmed() {
        return confirmedSent;
    }
    public void setConfirmed(String confirmed) {
        this.confirmedSent = confirmed;
    }
    public String getDeaths() {
        return deathsSent;
    }
    public void setDeaths(String deaths) {
        this.deathsSent = deaths;
    }
}
