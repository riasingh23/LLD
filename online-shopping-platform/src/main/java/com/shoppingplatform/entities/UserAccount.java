package com.shoppingplatform.entities;

public class UserAccount {
    private String userName;
    private String password;

    private UserAccount(UserAccountBuilder userAccountBuilder){
        this.userName = userAccountBuilder.userName;
        this.password = userAccountBuilder.password;
    }

    public static class UserAccountBuilder {
        private final String userName;
        private final String password;

        public UserAccountBuilder (String userName, String password){
            this.userName = userName;
            this.password = password;
        }

        public UserAccount build() {
            return new UserAccount(this);
        }
    }

}
