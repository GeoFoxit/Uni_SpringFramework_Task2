package models;

import java.util.Date;

public class Client {

    private String passportNumber;
    private String fullName;
    private Date birthDate;
    private Integer taxNumber;
    private Byte rate;
    private String phoneNumber;

    public Client() {
    }

    public Client(String passportNumber,
                  String fullName,
                  Date birthDate,
                  Integer taxNumber,
                  Byte rate,
                  String phoneNumber) {
        this.passportNumber = passportNumber;
        this.fullName = fullName;
        this. birthDate = birthDate;
        this.taxNumber = taxNumber;
        this.rate = rate;
        this.phoneNumber = phoneNumber;
    }

    public String getPassportNumber() {
        return passportNumber;
    }
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getTaxNumber() {
        return taxNumber;
    }
    public void setTaxNumber(Integer taxNumber) {
        this.taxNumber = taxNumber;
    }

    public Byte getRate() {
        return rate;
    }
    public void setRate(Byte rate) {
        this.rate = rate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}