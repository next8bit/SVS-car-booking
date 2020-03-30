package com.example.orbitz;

public class User {

    //String userID;
    String userName;
    String email;
    String password;
    String conPassword;

    public User() {
    }
    public User(String userName, String password){

        this.userName = userName;
        this.password = password;
    }

    public User(String userame,String email,String password){

        this.userName = userame;
        this.email = email;
        this.password = password;

    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getConPassword()
    {
        return conPassword;
    }

    public void setConPassword(String conPassword) {
        this.conPassword = conPassword;
    }
}
