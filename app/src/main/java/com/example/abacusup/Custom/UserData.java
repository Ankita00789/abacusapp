package com.example.abacusup.Custom;

public class UserData {

    private String Id;
    private String Name;
    private String Mobile;
    private String Email;
    private String Address;

    public UserData(){}
    public UserData(String id, String name, String mobile, String email, String address) {
        this.Id = id;
        this.Name = name;
        this.Mobile = mobile;
        this.Email = email;
        this.Address = address;
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

    public void setMobile(String mobile) {
        Mobile = mobile;
    }
    public String getMobile() {
        return Mobile;
    }

    public void setEmail(String email) {
        Email = email;
    }
    public String getEmail() {
        return Email;
    }

    public void setAddress(String address) {
        Address = address;
    }
    public String getAddress() {
        return Address;
    }

}
