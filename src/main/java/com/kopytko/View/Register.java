package com.kopytko.View;

import com.kopytko.Controller.Controller;
import com.kopytko.Controller.ControllerImpl;
import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Register extends Application {

    private Controller controller;
    private Group layout;
    private TextField login;
    private PasswordField password;
    private PasswordField passwordAgain;
    private Button btnRegister;

    @Override
    public void start(Stage primaryStage) {
        controller = new ControllerImpl();

        primaryStage.setTitle("Task Manager");
        Text text = new Text();
        text.setText("Registration");
        text.setLayoutX(200);
        text.setLayoutY(100);

        login = new TextField();
        login.setLayoutX(150);
        login.setLayoutY(150);
        login.setText("Enter your login");

        password = new PasswordField();
        password.setLayoutX(150);
        password.setLayoutY(200);

        passwordAgain = new PasswordField();
        passwordAgain.setLayoutX(150);
        passwordAgain.setLayoutY(250);


        btnRegister = new Button();
        btnRegister.setText("Register");
        btnRegister.setLayoutX(200);
        btnRegister.setLayoutY(300);
        btnRegister.setOnAction(this::Register);


        layout = new Group();
        layout.getChildren().addAll(text,login, password, passwordAgain, btnRegister);
        Scene scene = new Scene(layout, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void Register(Event event) {
        String log = login.getText();
        String pass = password. getText();
        String passAgain = passwordAgain.getText();
        if (controller.register(log, pass, passAgain)) {
            layout.getChildren().clear();
            Text text1 = new Text();
            text1.setText("Registration is done");
            text1.setLayoutX(200);
            text1.setLayoutY(200);

            btnRegister.setText("Come back to LogIn");
            btnRegister.setLayoutX(200);
            btnRegister.setLayoutY(250);
            btnRegister.setOnAction(event1 -> {
                Stage stage = (Stage) btnRegister.getScene().getWindow();
                stage.close();
                Stage newStage = new Stage();
                new LogInV().start(newStage);
            });
            layout.getChildren().addAll(text1, btnRegister);
        }
        else {
            Text text2 = new Text();
            text2.setText("Your passwords don't match");
            text2.setLayoutX(180);
            text2.setLayoutY(350);
            layout.getChildren().addAll(text2);
        }

    }
}
