package com.example.quiz.model;

public class User {
    // success login
    private int id;
    private String email;
    private String username;
    private String password;
    private String token;
    private String lease;
    private String role;
    private int is_active;
    private String secret;

    public User(){
        id = 0;
        email = "";
        username = "";
        password = "";
        token = "";
        lease = "";
        role = "";
        is_active = 0;
        secret = "";
    }

    public User(int id, String email, String username, String password, String token, String lease, String role, int is_active, String secret) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.token = token;
        this.lease = lease;
        this.role = role;
        this.is_active = is_active;
        this.secret = secret;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLease() {
        return lease;
    }

    public void setLease(String lease) {
        this.lease = lease;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", lease='" + lease + '\'' +
                ", role='" + role + '\'' +
                ", is_active='" + is_active + '\'' +
                ", secret='" + secret + '\'' +
                '}';
    }
}

