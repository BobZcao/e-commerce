package com.e_commerce.common;

/**
 * Created by code on 3/25/18.
 */
public class Const {
    public static final String CURRENT_USER = "currentUser";
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";

    public interface Role{
        int ROLE_CUSTOMER = 0; // normal user
        int ROLE_ADMIN = 1; //Adimn
    }
}
