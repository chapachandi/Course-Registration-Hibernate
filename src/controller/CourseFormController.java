package controller;

import business.BOFactory;
import business.BOType;
import business.custom.CourseBO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.CourseTM;
import model.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CourseFormController implements Initializable {
    public TableColumn clmcName;
    public TableColumn clmcFee;
    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtFee;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtProgram;

    @FXML
    private TableView<CourseTM> tblCourse;

    @FXML
    private TableColumn<?, ?> clmCode;

    @FXML
    private TableColumn<?, ?> clmProgram;

    @FXML
    private TableColumn<?, ?> clmDuration;

    @FXML
    private TableColumn<?, ?> clmFee;

    @FXML
    private Button btnDelete;

    @FXML
    private TextField txtDuration;

    private final CourseBO courseBO = BOFactory.getInstance().getBO(BOType.COURSE);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        clmCode.setCellValueFactory(new PropertyValueFactory("code"));
        clmcName.setCellValueFactory(new PropertyValueFactory("cName"));
        clmcFee.setCellValueFactory(new PropertyValueFactory("cFee"));
        clmDuration.setCellValueFactory(new PropertyValueFactory("duration"));



        tblCourse.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData((CourseTM) newValue);
                });

        txtCode.setText(generateNewProgramCode());
        loadAllCourses();




    }

    private void setData(CourseTM tm) {
        txtCode.setText(tm.getCode());
        txtProgram.setText(tm.getcName());
        txtDuration.setText(tm.getDuration());
        txtFee.setText(tm.getcFee()+"");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this Program?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            CourseTM selectedItem = (CourseTM) tblCourse.getSelectionModel().getSelectedItem();

            try {
                courseBO.deleteCourse(selectedItem.getCode());
                tblCourse.getItems().remove(selectedItem);
//                tblCourse.getSelectionModel().clearSelection();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to delete the Program", ButtonType.OK).show();
            }
        }

    }

    @FXML
    void btnNewProgramOnAction(ActionEvent event) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("../lk/ijse/hibernate/view/CourseForm.fxml")));


    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String cName = txtProgram.getText();
        String duration = txtDuration.getText();
        String cFee = txtFee.getText();

        // Validation
        if (cName.trim().length() == 0 || duration.trim().length() == 0 || cFee.trim().length() == 0) {
            new Alert(Alert.AlertType.ERROR, "Program Name, Duration,Fee can't be empty", ButtonType.OK).show();
            return;
        }

        if (btnSave.getText().equals("Save")) {

            try {
                courseBO.saveCourse(txtCode.getText(),
                        txtProgram.getText(),
                        Double.parseDouble(txtFee.getText()),
                        txtDuration.getText()
                );
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save the program", ButtonType.OK).show();

        }
        loadAllCourses();
        clearMethode();

    }

    private void clearMethode() {
    }

    private void loadAllCourses() {
        tblCourse.getItems().clear();
        List<CourseTM> allCourse = null;
        try {
            allCourse =courseBO.getAllCourses();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<CourseTM> courses = FXCollections.observableArrayList(allCourse);
        tblCourse.setItems(courses);

    }
    public String generateNewProgramCode(){
        String s = null;
        try {
            s = courseBO.getNewProgramCodeNo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        CourseTM selectedItem = (CourseTM) tblCourse.getSelectionModel().getSelectedItem();
        try {
            courseBO.updateCourse(txtProgram.getText(), Double.parseDouble(txtFee.getText()),
                    txtDuration.getText(), selectedItem.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to update the program", ButtonType.OK).show();
        }
        tblCourse.refresh();
    }

}
