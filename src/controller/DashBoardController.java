package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashBoardController {

    public Pane root;
    public Button btnProgram;
    public Button btnStudentReg;
    public Label lblTime;
    public Label lblDate;
    public Button btnRegistration;

    public void initialize() throws IOException {
        initUi("StudentForm.fxml");

    }

    private void initUi(String location) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/" +location)));
        lblDate.setText((String.valueOf(LocalDate.now())));
        setLBLTime();
    }
    private void setLBLTime() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, event -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
            lblTime.setText(LocalDateTime.now().format(formatter));
        }),new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void btnProgramOnAction(ActionEvent actionEvent) throws IOException {
        initUi("CourseForm.fxml");

    }

    public void btnStudentOnAction(ActionEvent actionEvent) throws IOException {
        initUi("StudentForm.fxml");
    }

    public void btnRegistrationOnAction(ActionEvent actionEvent) throws IOException {
        initUi("RegistrationForm.fxml");
    }

    public void settingOnAction(MouseEvent mouseEvent) throws IOException {
//        initUi("ChangeUserName.fxml");
        URL resource = this.getClass().
                getResource("/view/ChangeUserName.fxml");
        Parent load = FXMLLoader.load(resource);// ALt + Enter
        Scene scene= new Scene(load);
        Stage stage= new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setTitle("User Login Setting");

    }
}
