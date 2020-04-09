package com.kopytko;

import com.kopytko.View.LogInV;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.stage.Stage;
import java.io.FileInputStream;

public class App extends Application {

    private Button btnStart;

    @Override
    public void start(Stage primaryStage) {
        Image image;
        try {
            image = new Image(new FileInputStream("D:\\Study\\Java course\\TaskManager\\welcome.jpg"));
            ImageView imageView = new ImageView(image);
        imageView.setX(50);
        imageView.setY(25);
        imageView.setFitHeight(400);
        imageView.setFitWidth(400);
        imageView.setPreserveRatio(true);


        btnStart = new Button();
        btnStart.setText("Start");
        btnStart.setLayoutX(200);
        btnStart.setLayoutY(350);
        btnStart.setOnAction(this::StartNew);

        Group root = new Group(imageView, btnStart);
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Task Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
        } catch (Exception e) {
        }
    }

    public void StartNew(Event event)  {
        closeButtonAction();
        Stage stage = new Stage();
        new LogInV().start(stage);
    }

    private void closeButtonAction(){
        Stage stage = (Stage) btnStart.getScene().getWindow();
        stage.close();
    }
}
