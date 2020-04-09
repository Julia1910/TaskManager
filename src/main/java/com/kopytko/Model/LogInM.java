package com.kopytko.Model;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Scanner;

public class LogInM {
    protected static MongoClient mongoClient;
    protected static DB database;
    protected static DBCollection Users;

    public static boolean logIn (String login, String password) {
        connect();
        boolean status = false;
        String pass = "";
        try {
            DBCursor cursor = Users.find(new BasicDBObject("username", login));
            pass = (String)cursor.one().get("password");
            if (pass.equals(password)) {
                status = true;
            }
        } catch (Exception e){
        }
        return status;
    }

    protected static void connect() {
        try {
            mongoClient = new MongoClient();
            database = mongoClient.getDB("TaskManager");
            Users = database.getCollection("Users");
        } catch (UnknownHostException e) {

        }
    }


}
