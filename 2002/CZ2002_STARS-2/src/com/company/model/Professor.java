package com.company.model;

import java.io.Serializable;
import java.util.StringTokenizer;

import com.company.utils.PasswordUtil;

public class Professor implements Serializable {

    private String name;
    private String email;
    private int contact;
    private String password;

    public Professor(String name, String email, int contact, String password) {
    	this.name = name;
    	this.email = email;
    	this.contact = contact;
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getContact() {
        return this.contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
