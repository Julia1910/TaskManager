package com.kopytko.Model;

import javafx.collections.ObservableList;

import java.net.UnknownHostException;

public class Model implements ModelI {
    private TasksM tasks;
    @Override
    public boolean register(String login, String pass, String passAgain)  {
        Registration register = new Registration();
        return register.Register(login, pass, passAgain);
    }

    @Override
    public boolean logIn(String login, String pass) {
        LogInM logInM = new LogInM();
        return logInM.logIn(login, pass);
    }

    @Override
    public ObservableList<String> getTasks(String login) {
        tasks = new TasksM();
        return tasks.getTasks(login);
    }

    @Override
    public ObservableList<String> addTask(String task) {
        return tasks.addTask(task);
    }

    @Override
    public ObservableList<String> deleteTask(String task) {
        return tasks.deleteTask(task);
    }

    @Override
    public ObservableList<String> editTask(String task, String newTask) {
        return tasks.editTask(task,newTask);
    }

    @Override
    public void shareTask(String user, String task) {
        tasks.shareTask(user, task);
    }

    @Override
    public ObservableList<String> getUsers() {
        tasks = new TasksM();
        return tasks.getUsers();
    }
}
