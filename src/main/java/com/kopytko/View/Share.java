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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Share extends Application {
    private Group root;
    private Button share;
    private Controller controller;
    public static String user;
    private ListView listView;

    @Override
    public void start(Stage primaryStage){
        controller = new ControllerImpl();
        ObservableList<String> data = FXCollections.observableArrayList();
        data.add("user");
        ObservableList<String> users = controller.getUsers();
        listView = new ListView();
        listView.setPrefSize(150, 80);
        listView.setEditable(true);
        listView.setLayoutX(80);
        listView.setLayoutY(5);
        listView.setItems(data);
        listView.setCellFactory(ComboBoxListCell.forListView(users));

        share = new Button();
        share.setText("Share");
        share.setLayoutX(120);
        share.setLayoutY(100);
        share.setOnAction(this::setShare);

        primaryStage.setTitle("Choose user");
        root = new Group();
        root.getChildren().addAll(listView, share);
        Scene scene = new Scene(root, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setShare(Event event) {
        user = (String)listView.getSelectionModel().getSelectedItem();
        root.getChildren().removeAll(listView, share);
        Text text = new Text();
        text.setText("Your task is shared");
        text.setLayoutX(120);
        text.setLayoutY(20);

        Button close = new Button();
        close.setText("Close");
        close.setLayoutX(150);
        close.setLayoutY(70);
        close.setOnAction(event1 -> {
            Stage stage = (Stage)close.getScene().getWindow();
            stage.close();
        });

        Tasks.confirmSharing();
        root.getChildren().addAll(text, close);
    }


}
