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

public class LogInV extends Application {

    private Group layout;
    private Controller controller;
    private TextField login;
    private PasswordField password;
    private Button btnLogIn;
    private Button btnRegister;
    public static String log;


    public void start(Stage primaryStage)  {
        controller = new ControllerImpl();

        primaryStage.setTitle("Task Manager");
        Text text = new Text();
        text.setText("Please login into system");
        text.setLayoutX(200);
        text.setLayoutY(100);

        Text textL = new Text();
        textL.setText("Enter your login");
        textL.setLayoutX(150);
        textL.setLayoutY(140);

        login = new TextField();
        login.setLayoutX(150);
        login.setLayoutY(150);
        login.setText("login");

        Text textP = new Text();
        textP.setText("Enter your password");
        textP.setLayoutX(150);
        textP.setLayoutY(200);

        password = new PasswordField();
        password.setLayoutX(150);
        password.setLayoutY(210);
        password.setText("pass");


        btnLogIn = new Button();
        btnLogIn.setText("Log In");
        btnLogIn.setLayoutX(250);
        btnLogIn.setLayoutY(260);
        btnLogIn.setOnAction(this::LogIn);

        Text text1 = new Text();
        text1.setText("If you don't have account, you can ");
        text1.setLayoutX(150);
        text1.setLayoutY(330);

        btnRegister = new Button();
        btnRegister.setText("Register");
        btnRegister.setLayoutX(350);
        btnRegister.setLayoutY(310);
        btnRegister.setOnAction(this::Register);


        layout = new Group();
        layout.getChildren().addAll(text, textL, textP, login, password, btnLogIn, text1, btnRegister);
        Scene scene = new Scene(layout, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void LogIn (Event event) {
        log = login.getText();
        String pass = password.getText();
        if (controller.logIn(log, pass)) {
            Stage stage = (Stage) btnLogIn.getScene().getWindow();
            stage.close();
            Stage newStage = new Stage();
            new Tasks().start(newStage);
        }
        else {
            Text text = new Text();
            text.setText("Your login or password is incorrect");
            text.setLayoutX(150);
            text.setLayoutY(400);
            layout.getChildren().addAll(text);
        }
    }

    public void Register (Event event) {
        Stage stage = (Stage) btnRegister.getScene().getWindow();
        stage.close();
        Stage stage1 = new Stage();
        new Register().start(stage1);
    }

}
