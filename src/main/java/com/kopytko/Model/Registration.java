package com.kopytko.Model;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Registration extends LogInM {

    public boolean Register (String login, String pass, String passAgain) {
        connect();
        boolean status = false;
        String[] array = new String[1];
        array[0] = "New task";
        if (pass.equals(passAgain)) {
            try {
                DBObject newUser = new BasicDBObject()
                        .append("username", login)
                        .append("password", pass )
                        .append("tasks", array);
                 Users.insert(newUser);
                 status = true;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return status;
    }

}
