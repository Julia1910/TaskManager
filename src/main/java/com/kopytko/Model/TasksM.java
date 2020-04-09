package com.kopytko.Model;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class TasksM extends LogInM {
    private ObservableList<String> tasks;
    private String username;


    public ObservableList<String> getTasks(String login) {
        connect();
        BasicDBList list = new BasicDBList();
        username = login;
        DBCursor cursor = Users.find(new BasicDBObject("username", login));
        list = (BasicDBList) cursor.one().get("tasks");
        tasks = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
            tasks.add((String)list.get(i));
        }
        return tasks;
    }

    public ObservableList<String> addTask(String task) {
        tasks.add(task);
        Users.update(new BasicDBObject("username", username),
                new BasicDBObject("$set", new BasicDBObject("tasks", tasks)));
        return tasks;
    }

    public ObservableList<String> deleteTask(String task) {
        tasks.remove(task);
        BasicDBObject update = new BasicDBObject().append("$pull",
                new BasicDBObject("tasks",task));
        Users.update(new BasicDBObject("username", username), update);
        return tasks;
    }

    public ObservableList<String> editTask(String task, String newTask) {
        int index = 0;
        for (int i =0; i< tasks.size(); i++) {
            if (tasks.get(i).equals(task)) {
                index = i;
            }
        }
        tasks.set(index, newTask);
        Users.update(new BasicDBObject("username", username),
                new BasicDBObject("$set", new BasicDBObject("tasks", tasks)));
        return tasks;
    }

    public void shareTask(String user, String task) {
        BasicDBList list = new BasicDBList();
        DBCursor cursor = Users.find(new BasicDBObject("username", user));
        list = (BasicDBList) cursor.one().get("tasks");
        list.add(task);
        Users.update(new BasicDBObject("username", user),
                new BasicDBObject("$set", new BasicDBObject("tasks", list)));
    }

    public static ObservableList<String> getUsers() {
        connect();
        ObservableList<String> users = FXCollections.observableArrayList();
        for (DBObject user : Users.find()) {
            String username = (String)user.get("username");
            users.add(username);
        }
        return users;
    }

}
