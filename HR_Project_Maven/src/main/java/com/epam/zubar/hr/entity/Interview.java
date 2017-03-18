package com.epam.zubar.hr.entity;

/**
 * * Basic entity class stores info about Interview.
 * @author Mikalay Zubar
 *
 */
public class Interview extends Entity{

    private static final long serialVersionUID = 1L;

    private String date;
    private int candidateId;
    private int recruterId;
    private String status;
    private String number;
    private String comment;
    private int vacancyId;

    public Interview(){}

    public Interview(String date, int candidateId, int recruterId, String status, String number, String comment,
                     int vacancyId) {
        super();
        this.date = date;
        this.candidateId = candidateId;
        this.recruterId = recruterId;
        this.status = status;
        this.number = number;
        this.comment = comment;
        this.vacancyId = vacancyId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public int getRecruterId() {
        return recruterId;
    }

    public void setRecruterId(int recruterId) {
        this.recruterId = recruterId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(int vacancyId) {
        this.vacancyId = vacancyId;
    }

    @Override
    public String toString() {
        return "Interview [date=" + date + ", candidateId=" + candidateId + ", recruterId=" + recruterId + ", status="
                + status + ", number=" + number + ", comment=" + comment + "]";
    };

}
