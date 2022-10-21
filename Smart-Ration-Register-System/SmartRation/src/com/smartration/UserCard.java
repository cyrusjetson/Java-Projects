package com.smartration;

public class UserCard {
    String rationID;
    String familyHeadName;
    float age;
    String DOB;
    String an;
    String address;
    int familyMemberCount;
    int noOfChildren;

    public String getAn() {
        return an;
    }

    public void setAn(String an) {
        this.an = an;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }

    public UserCard(String rationID, float age, String familyHeadName, String DOB, String address, int familyMemberCount, int noOfChildren) {
        this.rationID = rationID;
        this.age = age;
        this.familyHeadName = familyHeadName;
        this.DOB = DOB;
        this.address = address;
        this.familyMemberCount = familyMemberCount;
        this.noOfChildren = noOfChildren;
    }

    public UserCard() {
        rationID = "";
        familyHeadName = "";
        DOB = "";
        address = "";
        familyMemberCount = 0;
        noOfChildren = 0;
    }

    public String getRationID() {
        return rationID;
    }

    public void setRationID(String rationID) {
        this.rationID = rationID;
    }

    public String getFamilyHeadName() {
        return familyHeadName;
    }

    public void setFamilyHeadName(String familyHeadName) {
        this.familyHeadName = familyHeadName;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFamilyMemberCount() {
        return familyMemberCount;
    }

    public void setFamilyMemberCount(int familyMemberCount) {
        this.familyMemberCount = familyMemberCount;
    }

    public int getNoOfChildren() {
        return noOfChildren;
    }

    public void setNoOfChildren(int noOfChildren) {
        this.noOfChildren = noOfChildren;
    }
}
