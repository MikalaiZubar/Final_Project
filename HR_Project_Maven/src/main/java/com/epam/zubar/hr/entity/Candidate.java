package com.epam.zubar.hr.entity;

/**
 * Basic entity class stores info about Candidate.
 * @author Mikalay Zubar
 *
 */
public class Candidate extends Entity {

    private static final long serialVersionUID = 1L;
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String sex;
    private String birthDate;
    private String phone;
    private String info;
    private String photo; // ??? url to image

    public Candidate(){}

    public Candidate(int id, String firstName, String lastName, String email, String sex, String birthDate,
                     String phone, String info, String photo) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.sex = sex;
        this.birthDate = birthDate;
        this.phone = phone;
        this.info = info;
        this.photo = photo;
    }



    public Candidate(int id, String firstName, String lastName, String email, String sex, String birthDate,
                     String phone, String info) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.sex = sex;
        this.birthDate = birthDate;
        this.phone = phone;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Candidate [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", sex=" + sex + ", birthDate=" + birthDate + ", phone=" + phone + "]";
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
