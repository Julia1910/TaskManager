package com.kopytko.View;

import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class Edit extends Application {
    private TextField edit;
    private Button save;
    public static String newTask;

    @Override
    public void start(Stage primaryStage) {
        edit = new TextField();
        edit.setLayoutX(10);
        edit.setLayoutY(10);
        edit.setText(Tasks.changeTask);

        save = new Button();
        save.setLayoutX(200);
        save.setLayoutY(100);
        save.setText("Save");
        save.setOnAction(this::setSave);

        primaryStage.setTitle("Edit Task");
        Group layout = new Group();
        layout.getChildren().addAll(edit,save);
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setSave(Event event) {
        newTask = edit.getText();
        Tasks.changeTask();
        Stage stage = (Stage) save.getScene().getWindow();
        stage.close();
    }
}
