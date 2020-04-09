package com.kopytko.Model;

import javafx.collections.ObservableList;

import java.net.UnknownHostException;

public interface ModelI {
    boolean register(String login, String pass, String passAgain);
    boolean logIn(String login, String pass);
    ObservableList<String> getTasks(String login);
    ObservableList<String> addTask(String task);
    ObservableList<String> deleteTask(String task);
    ObservableList<String> editTask(String task, String newTask);
    void shareTask(String user, String task);
    ObservableList<String> getUsers();
}
