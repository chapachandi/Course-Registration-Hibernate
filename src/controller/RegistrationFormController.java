package controller;

import business.BOFactory;
import business.BOType;
import business.custom.CourseBO;
import business.custom.RegistrationBO;
import business.custom.StudentBO;
import entity.Registration;
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
import model.RegistrationTM;
import model.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class RegistrationFormController implements Initializable {
    public TextField txtStudentName;
    public TextField txtCName;
    public TableColumn clmRegNo;
    public TableColumn clmSId;
    public TableColumn clmPCode;
    public TableColumn clmRegDate;
    public TableColumn clmRegFee;
    public TextField txtPName;
    public AnchorPane root;
    @FXML
    private TextField txtRegNo;

    @FXML
    private TextField txtRegFee;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnSave;

    @FXML
    private TableView<RegistrationTM> tblRegistration;

    @FXML
    private TableColumn<?, ?> clmCode;

    @FXML
    private TableColumn<?, ?> clmProgram;

    @FXML
    private TableColumn<?, ?> clmDuration;

    @FXML
    private TableColumn<?, ?> clmFee;

    @FXML
    private TableColumn<?, ?> clmFee1;

    @FXML
    private Button btnDelete;

    @FXML
    private DatePicker regDate;

    @FXML
//    private ComboBox<StudentTM> cmbStudentId;
    public ComboBox cmbStudentId;

    @FXML
    private ComboBox cmbCode;

    static ArrayList<Registration> registrations = new ArrayList<>();
    ObservableList<String> observableList = FXCollections.observableArrayList();

    private final RegistrationBO registrationBO = BOFactory.getInstance().getBO(BOType.REGISTRATION);
    private final StudentBO studentBO = BOFactory.getInstance().getBO(BOType.STUDENT);
    private final CourseBO courseBO = BOFactory.getInstance().getBO(BOType.COURSE);

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        clmRegNo.setCellValueFactory(new PropertyValueFactory("regNo"));
        clmRegDate.setCellValueFactory(new PropertyValueFactory("regDate"));
        clmRegFee.setCellValueFactory(new PropertyValueFactory("regFee"));
        clmPCode.setCellValueFactory(new PropertyValueFactory("code"));
        clmSId.setCellValueFactory(new PropertyValueFactory("id"));


        tblRegistration.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData((RegistrationTM) newValue);
                });
        try {
            getAllStudents();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            getAllPrograms();
        } catch (Exception e) {
            e.printStackTrace();
        }

        loadAllRegistrations();
        txtRegNo.setText(generateNewRegNo());


    }

    private void setData(RegistrationTM tm) {

    }


    public void getAllStudents() throws Exception {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        List<StudentTM> arrayList = studentBO.getAllStudents();

        for (StudentTM s:arrayList){
            observableList.add((s.getId()));

        }
        cmbStudentId.setItems(observableList);
    }
    public void getAllPrograms() throws Exception {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        List<CourseTM> arrayList = courseBO.getAllCourses();

        for (CourseTM c:arrayList){
            observableList.add((c.getCode()));

        }
        cmbCode.setItems(observableList);
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete this Registration?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            RegistrationTM selectedItem = (RegistrationTM) tblRegistration.getSelectionModel().getSelectedItem();

            try {
                registrationBO.deleteRegistration(selectedItem.getRegNo());
                tblRegistration.getItems().remove(selectedItem);

            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to delete the Registration", ButtonType.OK).show();
            }
        }

    }

    @FXML
    void btnNewRegistrationOnAction(ActionEvent event) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("../lk/ijse/hibernate/view/RegistrationForm.fxml")));


    }

    @FXML
    void btnNewStudentOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String rd = regDate.getValue().toString();
        String rf = txtRegFee.getText();
        String cmSI = cmbStudentId.getValue().toString();
        String cmPC = cmbCode.getValue().toString();


        // Validation
        if (rd.trim().length() == 0 || rf.trim().length() == 0 || cmSI.trim().length() == 0 || cmPC.trim().length() == 0) {
            new Alert(Alert.AlertType.ERROR, "Refisration date, fee, Student id Program code  can't be empty", ButtonType.OK).show();
            return;
        }
        if (btnSave.getText().equals("Save")) {

            try {
                registrationBO.saveRegistration(txtRegNo.getText(),
                        regDate.getValue().toString(),
                        Double.parseDouble(txtRegFee.getText()),
                        cmbCode.getValue().toString(),
                        cmbStudentId.getValue().toString()
                );

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to Save the registration", ButtonType.OK).show();
        }
        loadAllRegistrations();
        clearMethode();

    }
    public String generateNewRegNo(){
        String r = null;
        try {
            r = registrationBO.getNewRegistrationId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    private void clearMethode() {
        txtRegNo.setText("");
        cmbStudentId.setValue("");
        cmbCode.setValue("");
        txtRegFee.setText("");

    }

    private void loadAllRegistrations() {
        tblRegistration.getItems().clear();
        List<RegistrationTM> allRegistration = null;
        try {
            allRegistration = registrationBO.getAllRegistrations();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<RegistrationTM> registrations = FXCollections.observableArrayList(allRegistration);
        tblRegistration.setItems(registrations);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

//        RegistrationTM selectedItem = (RegistrationTM) tblRegistration.getSelectionModel().getSelectedItem();
//            try {
//                registrationBO.updateRegistration(
//                        regDate.getValue().toString(),
//                        Double.parseDouble(txtRegFee.getText()),
//                        cmbCode.getValue().toString(),
//                        cmbStudentId.getValue().toString(),
//                        selectedItem.getRegNo());
//


//            } catch (Exception e) {
//                e.printStackTrace();
//                new Alert(Alert.AlertType.ERROR, "Failed to update the registration", ButtonType.OK).show();
//            }
//            tblRegistration.refresh();

    }

    @FXML
    void cmbCodeOnAction(ActionEvent event) {

    }

    @FXML
    void cmbStudentOnAction(ActionEvent event) {

    }
}
