package com.example.abacusup.Custom;

import com.example.abacusup.CollegeReport;

public class FacultyDetailModel {

    private String Id;
    private String Name;
    private String Mobile_no;
    private String Email_id;
    private String University_name;
    private String College_name;
    private String Father_name;
    private String Dob;
    private String District_name;
    private String Total;
    public FacultyDetailModel(){}

    public FacultyDetailModel(String id, String name, String mobile_no, String email_id, String university_name, String college_name, String father_name,String dob, String district_name, String total) {
        this.Id = id;
        this.Name = name;
        this.Mobile_no = mobile_no;
        this.Email_id = email_id;
        this.University_name = university_name;
        this.College_name = college_name;
        this.Father_name=father_name;
        this.Dob=dob;
        this.District_name=district_name;
        this.Total=total;

    }


    public void setId(String id) {
        Id = id;
    }
    public String getId() {
        return Id;
    }

    public void setName(String name) {
        Name = name;
    }
    public String getName() {
        return Name;
    }

    public void setMobile(String mobile_no) {
        Mobile_no = mobile_no;
    }
    public String getMobile() {
        return Mobile_no;
    }

    public void setEmail(String email_id) {
        Email_id = email_id;
    }
    public String getEmail() {
        return Email_id;
    }

    public void setUnivName(String university_name) {
        University_name = university_name;
    }
    public String getUnivName() {
        return University_name;
    }

    public void setCollName(String college_name) {
        College_name = college_name;
    }
    public String getCollName() {
        return College_name;
    }

    public void setFatherName(String father_name) { Father_name = father_name; }
    public String getFatherName() {
        return Father_name;
    }

    public void setDOB(String dob) { Dob = dob; }
    public String getDOB() {
        return Dob;
    }

    public void setDistName(String district_name) {
        District_name = district_name;
    }
    public String getDistName() {
        return District_name;
    }


    public void setTotal(String total) {
        Total = total;
    }
    public String getTotal() {
        return Total;
    }

}
