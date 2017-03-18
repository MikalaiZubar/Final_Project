package com.epam.zubar.hr.entity;

/**
 * artificial class for supplementary logic
 * @author Mikalay Zubar
 */


public class VacCandConnector extends Entity{

    private static final long serialVersionUID = 7898092717031133058L;
    private String vacancyName;
    private int candidateId;
    private String vacDateOfSign;
    private String candLastName;
    private String result;
    private int vacancyId;


    public VacCandConnector() {}

    public VacCandConnector( int vacancyId, String vacancyName, String vacDateOfSign, String result) {
        this.vacancyId = vacancyId;
        this.vacancyName = vacancyName;
        this.vacDateOfSign = vacDateOfSign;
        this.result = result;
    }

    public VacCandConnector( int vacancyId, String vacancyName, int candidateId, String candLastName, String result) {
        this.vacancyId = vacancyId;
        this.vacancyName = vacancyName;
        this.candidateId = candidateId;
        this.candLastName = candLastName;
        this.result = result;
    }


    public VacCandConnector(int vacancyId, int candidateId, String vacDateOfSign, String result) {
        this.vacancyId = vacancyId;
        this.candidateId = candidateId;
        this.vacDateOfSign = vacDateOfSign;
        this.result = result;
    }



    public int getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(int vacancyId) {
        this.vacancyId = vacancyId;
    }

    public String getVacancyName() {
        return vacancyName;
    }

    public void setVacancyName(String vacancyName) {
        this.vacancyName = vacancyName;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public String getVacDateOfSign() {
        return vacDateOfSign;
    }

    public void setVacDateOfSign(String vacDateOfSign) {
        this.vacDateOfSign = vacDateOfSign;
    }

    public String getCandLastName() {
        return candLastName;
    }

    public void setCandLastName(String candLastName) {
        this.candLastName = candLastName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
