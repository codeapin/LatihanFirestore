package com.codeapin.latihanfirestore;


import java.util.Date;

public class User {

    public static final String ADDRESS_FIELD = "address";
    public static final String BIRTHDAY_FIELD = "birthDay";
    public static final String FULLNAME_FIELD = "fullName";
    public static final String GENDER_FIELD = "address";
    public static final String JOIN_DATE_FIELD = "joinDate";
    public static final String LAST_MODIFIED_FIELD = "lastModified";
    public static final String PHONE_NUMBER_FIELD = "phoneNumber";
    public static final String PHOTO_URL_FIELD = "photoUrl";

    private String address;
    private Date birthDay;
    private String fullName;
    private String gender;
    private Date joinDate;
    private Date lastModified;
    private String phoneNumber;
    private String photoUrl;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
