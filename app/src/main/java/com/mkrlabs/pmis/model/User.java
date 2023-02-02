package com.mkrlabs.pmis.model;

public class User {





    private  String name;
    private  String email;
    private  String phone;
    private  String versity_id;
    private  String image;
    private  String batch_id;
    private  String dept;
    private  String program;
    private  String password;
    private  ProjectType projectType;


    public User(String name, String email, String phone, String versity_id, String image, String batch_id, String dept, String program, ProjectType projectType,String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.versity_id = versity_id;
        this.image = image;
        this.batch_id = batch_id;
        this.dept = dept;
        this.program = program;
        this.projectType = projectType;
        this.password= password;
    }


    public User() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVersity_id() {
        return versity_id;
    }

    public void setVersity_id(String versity_id) {
        this.versity_id = versity_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(String batch_id) {
        this.batch_id = batch_id;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }
}
