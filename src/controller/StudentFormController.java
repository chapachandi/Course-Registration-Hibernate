package controller;

import business.BOFactory;
import business.BOType;
import business.custom.StudentBO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class StudentFormController implements Initializable {
    public AnchorPane root;
    public TextField txtStudentId;
    public RadioButton rdbtnMale;
    public RadioButton rdbtnFemale;
    public TextField txtAddress;
    public TextField txtFullName;
    public DatePicker date;
    public TableColumn clmId;
    public TableColumn clmFullName;
    public TableColumn clmAddress;
    public TableColumn clmGender;
    public TableColumn clmDob;
    public TableColumn clmMobile;

    public TextField txtMobile;
    public Label labaleGender;
    public Button btnSave;
    public TableView tblStudent;

//    private TableView<StudentTM> tblStudent;
//    @FXML
//    private TableView<StudentTM> tblStudent;

    private final StudentBO studentBO = BOFactory.getInstance().getBO(BOType.STUDENT);
    public Button btnDelete;
    public Button btnUpdate;
    public ToggleGroup GndrGroup;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        clmId.setCellValueFactory(new PropertyValueFactory("id"));
        clmFullName.setCellValueFactory(new PropertyValueFactory("sName"));
        clmAddress.setCellValueFactory(new PropertyValueFactory("address"));
        clmMobile.setCellValueFactory(new PropertyValueFactory("contact"));
        clmDob.setCellValueFactory(new PropertyValueFactory("dob"));
        clmGender.setCellValueFactory(new PropertyValueFactory("gender"));
//        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
//        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("sName"));
//        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
//        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact"));
//        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("dob"));
//        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("gender"));

        //------------------------------ set Listener-------------

        tblStudent.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData((StudentTM) newValue);
                });
        loadAllStudents();
        txtStudentId.setText(generateNewID());

    }

    private void setData(StudentTM tm) {
        txtStudentId.setText(tm.getId());
        txtFullName.setText(tm.getsName());
        txtAddress.setText(tm.getAddress());
        date.setValue(LocalDate.parse(tm.getDob()));
        labaleGender.setText(tm.getGender());
        txtMobile.setText(tm.getContact()+"");
    }

    public void radioBtnOnAction(ActionEvent actionEvent) {
        String message = "";
        if(rdbtnMale.isSelected()){
            message += rdbtnMale.getText()+"\n";
        }
        if(rdbtnFemale.isSelected()){
            message += rdbtnFemale.getText()+"\n";
        }
        labaleGender.setText(message);
    }
    private void getGender(String gender) {
        switch(gender){
            case "Female":
                rdbtnFemale.setSelected(true);

                break;
            case "Male":
                rdbtnMale.setSelected(true);
                break;
            default:
                break;
        }
    }

    public void btnSaveOnAction(ActionEvent event) {
        String sName = txtFullName.getText();
        String address = txtAddress.getText();
        String contact = txtMobile.getText();
        String dob = date.getValue().toString();
        String gender = labaleGender.getText();


        // Validation
        if (sName.trim().length() == 0 || address.trim().length() == 0 || contact.trim().length() == 0 || dob.trim().length() == 0 || gender.trim().length() == 0) {
            new Alert(Alert.AlertType.ERROR, "Student Name, Address, Contact, Dob, Gender can't be empty", ButtonType.OK).show();
            return;
        }

        if (btnSave.getText().equals("Save")) {

            try {
                studentBO.saveStudent(txtStudentId.getText(),
                        txtFullName.getText(),
                        txtAddress.getText(),
                        txtMobile.getText(),
                        date.getValue().toString(),
                        labaleGender.getText()
                );

            } catch (Exception e) {
                e.printStackTrace();

            }
//            loadAllStudents();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save the student", ButtonType.OK).show();
        }
        loadAllStudents();
        clearMethode();
    }

    public void btnNewStudentOnAction(ActionEvent actionEvent) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("../lk/ijse/hibernate/view/StudentForm.fxml")));

    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        StudentTM selectedItem = (StudentTM) tblStudent.getSelectionModel().getSelectedItem();
        try {
            studentBO.updateStudent(txtFullName.getText(), txtAddress.getText(),txtMobile.getText(),date.getValue().toString(),labaleGender.getText(), selectedItem.getId());
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to update the student", ButtonType.OK).show();
        }
        tblStudent.refresh();
//        loadAllStudents();

    }

    private void loadAllStudents() {
        tblStudent.getItems().clear();
        List<StudentTM> allStudent = null;
        try {
            allStudent = studentBO.getAllStudents();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<StudentTM> students = FXCollections.observableArrayList(allStudent);
        tblStudent.setItems(students);
    }

    public String generateNewID(){
        String s = null;
        try {
            s = studentBO.getNewStudentId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this Student?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            StudentTM selectedItem = (StudentTM) tblStudent.getSelectionModel().getSelectedItem();

            try {
                studentBO.deleteStudent(selectedItem.getId());
                tblStudent.getItems().remove(selectedItem);
                tblStudent.getSelectionModel().clearSelection();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to delete the Student", ButtonType.OK).show();
            }
        }
//        loadAllStudents();
        clearMethode();
    }

    private void clearMethode(){
        txtStudentId.setText("");
        txtFullName.setText("");
        txtAddress.setText("");
        txtMobile.setText("");

    }
}
