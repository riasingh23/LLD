package com.shoppingplatform.dal;

// Singleton, so that only one instance/object can be created 
public class UserDatabase {

    private static UserDatabase instance;

    public UserDatabase() {

    }

    public static synchronized UserDatabase getInstance() {
        if (instance == null) {
            instance = new UserDatabase();
        }
        return instance;
    }

}
