package com.kopytko.Controller;

import com.kopytko.Model.*;
import javafx.collections.ObservableList;

public class ControllerImpl implements  Controller {
    ModelI model;

    public ControllerImpl() {
        model = new Model();
    }

    @Override
    public boolean register(String login, String pass, String passAgain) {
        return model.register(login, pass, passAgain);
    }

    @Override
    public boolean logIn(String login, String pass) {
        return model.logIn(login, pass);
    }

    @Override
    public ObservableList<String> getTasks(String login) {
        return model.getTasks(login);
    }

    @Override
    public ObservableList<String> addTask(String task) {
        return model.addTask(task);
    }

    @Override
    public ObservableList<String> deleteTask(String task) {
        return model.deleteTask(task);
    }

    @Override
    public ObservableList<String> editTask(String task, String newTask) {
        return model.editTask(task, newTask);
    }

    @Override
    public void shareTask(String user, String task) {
        model.shareTask(user, task);
    }

    @Override
    public ObservableList<String> getUsers() {
        return model.getUsers();
    }
}
