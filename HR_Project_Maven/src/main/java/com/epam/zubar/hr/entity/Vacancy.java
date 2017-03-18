package com.epam.zubar.hr.entity;

/**
 * * Basic entity class stores info about Vacancy.
 * @author Mikalay Zubar
 *
 */
public class Vacancy extends Entity{


    private static final long serialVersionUID = 1L;

    private int id;
    private String date;
    private String name;
    private String info;
    private double minSalary;
    private double maxSalary;
    private String status;
    private int recruterId;

    public Vacancy() {
    }

    public Vacancy(String date, String name, String info, double minSalary, double maxSalary, String status,
                   int recruterId) {
        super();
        this.date = date;
        this.name = name;
        this.info = info;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.status = status;
        this.recruterId = recruterId;
    }

    public Vacancy(int id, String date, String name, String info, double minSalary, double maxSalary, String status,
                   int recruterId) {
        super();
        this.id = id;
        this.date = date;
        this.name = name;
        this.info = info;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.status = status;
        this.recruterId = recruterId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(double minSalary) {
        this.minSalary = minSalary;
    }

    public double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(double maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRecruterId() {
        return recruterId;
    }

    public void setRecruterId(int recruterId) {
        this.recruterId = recruterId;
    }

    @Override
    public String toString() {
        return "Vacancy [id=" + id + ", date=" + date + ", name=" + name + ", info=" + info + ", minSalary=" + minSalary
                + ", maxSalary=" + maxSalary + ", status=" + status + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vacancy other = (Vacancy) obj;
        if (id != other.id)
            return false;
        return true;
    }

}
