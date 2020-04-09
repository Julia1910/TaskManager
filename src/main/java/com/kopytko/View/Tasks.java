package com.kopytko.View;

import com.kopytko.Controller.Controller;
import com.kopytko.Controller.ControllerImpl;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Tasks extends Application {
    private static Controller controller;
    private TextField task;
    private static ListView<String> listView;
    private static ObservableList<String> tasks;
    private Button delete;
    private static Group root;
    private Button add;
    private Button edit;
    public static String changeTask;
    private Button share;
    private static String shareTask;

    @Override
    public void start(Stage primaryStage) {
        controller = new ControllerImpl();
        String login = LogInV.log;

        Text user = new Text();
        user.setText("User: " + login);
        user.setLayoutX(400);
        user.setLayoutY(40);

        Button logOut = new Button();
        logOut.setText("LogOut");
        logOut.setLayoutX(400);
        logOut.setLayoutY(60);
        logOut.setOnAction(event -> {
            Stage stage = new Stage();
            new LogInV().start(stage);
            Stage stage1 = (Stage)logOut.getScene().getWindow();
            stage1.close();
        });

        Text text = new Text();
        text.setText("Your tasks");
        text.setLayoutX(150);
        text.setLayoutY(100);

        listView = new ListView<>();
        tasks = controller.getTasks(login);
        listView.setItems(tasks);
        listView.setLayoutX(100);
        listView.setLayoutY(110);
        listView.setPrefSize((200),(200));

        task = new TextField();
        task.setLayoutX(100);
        task.setLayoutY(350);

        delete = new Button();
        delete.setText("Delete");
        delete.setLayoutX(360);
        delete.setLayoutY(130);
        delete.setOnAction(this::deleteTask);

        add = new Button();
        add.setText("Add");
        add.setLayoutX(300);
        add.setLayoutY(350);
        add.setOnAction(this::addTask);

        edit = new Button();
        edit.setText("Edit");
        edit.setLayoutX(360);
        edit.setLayoutY(180);
        edit.setOnAction(this::editTask);

        share = new Button();
        share.setText("Share");
        share.setLayoutX(360);
        share.setLayoutY(230);
        share.setOnAction(this::shareTask);

        primaryStage.setTitle("Task Manager");
        root = new Group();
        root.getChildren().addAll(user, text, logOut, listView, task, delete, add, edit, share);
        Scene scene = new Scene(root,500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void deleteTask(Event event) {
        root.getChildren().remove(listView);
        String deletedTask = listView.getSelectionModel().getSelectedItem();
        tasks = controller.deleteTask(deletedTask);
        listView.setItems(tasks);
        root.getChildren().add(listView);
    }

    private void addTask(Event event) {
        root.getChildren().remove(listView);
        String newTask = task.getText();
        tasks = controller.addTask(newTask);
        task.clear();
        listView.setItems(tasks);
        root.getChildren().add(listView);
    }

    private void editTask(Event event) {
        changeTask = listView.getSelectionModel().getSelectedItem();
        Stage stage = new Stage();
        new Edit().start(stage);
    }

    public static void changeTask() {
        root.getChildren().remove(listView);
        tasks = controller.editTask(changeTask, Edit.newTask);
        listView.setItems(tasks);
        root.getChildren().add(listView);
    }

    private void shareTask(Event event) {
        shareTask = listView.getSelectionModel().getSelectedItem();
        Stage stage = new Stage();
        new Share().start(stage);
    }

    public static void confirmSharing() {
        controller.shareTask(Share.user, shareTask);
    }


}
