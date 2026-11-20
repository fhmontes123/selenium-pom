package com.selenium.pom.model;

// Clase que representa a cada fila del excel
public class LoginData {

    private String username;
    private String password;

    public LoginData() {
    }

    public LoginData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}