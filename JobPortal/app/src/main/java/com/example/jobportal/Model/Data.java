package com.example.jobportal.Model;

public class Data {

    String title;
    String description;
    String skills;
    String salary;
String email;
    String id ;
    String Date;
    String Companyname;

    public Data(){}

    public Data(String title, String description, String skills, String salary, String email, String id, String date, String companyname) {
        this.title = title;
        this.description = description;
        this.skills = skills;
        this.salary = salary;
        this.email = email;
        this.id = id;
        Date = date;
        Companyname = companyname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCompanyname() {
        return Companyname;
    }

    public void setCompanyname(String companyname) {
        Companyname = companyname;
    }

    public class Myviewholder {
    }
}
