package controller;

import business.BOFactory;
import business.BOType;
import business.custom.LoginBO;
import entity.Login;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserLogInController implements Initializable {
    public CheckBox pass_toggle;
    public Label lblShowPassword;
    public TextField txtShowPassword;
    public Button btnSave;
    @FXML
    private Pane pnSignUp;

    @FXML
    private TextField txtRegname;

    @FXML
    private TextField txtRegFirstname;

    @FXML
    private TextField txtRegPassword;

    @FXML
    private TextField txtRegEmail;

    @FXML
    private TextField txtRegPhoneNo;

    @FXML
    private RadioButton female;

    @FXML
    private ToggleGroup Gender;

    @FXML
    private RadioButton male;

    @FXML
    private Label controlRegLabel;

    @FXML
    private Label success;

    @FXML
    private Label goBack;

    @FXML
    private Button getStarted;

    @FXML
    private ImageView btnBack;

    @FXML
    private Pane pnSignIn;

    @FXML
    private Button btnSignUp;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    private Label loginNotifier;


    public Label nameExists;
    public Label checkEmail;


    public static String username, password;
    public static ArrayList<Login> loggedInUser = new ArrayList<>();
    public static ArrayList<Login> users = new ArrayList<Login>();

    private final LoginBO loginBO = BOFactory.getInstance().getBO(BOType.LOGIN);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.togglevisiblePassword(null);
        passwordValue();
    }

    @FXML
    void handleButtonAction(ActionEvent event) {
        if (event.getSource().equals(btnSignUp)) {
            new FadeTransition().play();
            pnSignUp.toFront();
        }
        if (event.getSource().equals(getStarted)) {
            new FadeTransition().play();
            pnSignIn.toFront();
        }
        loginNotifier.setOpacity(0);
        txtUsername.setText("");
        txtPassword.setText("");

    }
    @FXML
    void handleMouseEvent(MouseEvent event) {
        if (event.getSource() == btnBack) {

            new FadeTransition().play();
            pnSignIn.toFront();
        }
        txtRegname.setText("");
        txtRegPassword.setText("");
        txtRegEmail.setText("");

    }
    private void makeDefault() {
        txtRegname.setText("");
        txtRegPassword.setText("");
        txtRegEmail.setText("");
        txtRegFirstname.setText("");
        txtRegPhoneNo.setText("");
        male.setSelected(true);

    }

    @FXML
    public void login(ActionEvent event) throws IOException {
//        username = txtUsername.getText();
//        password = txtPassword.getText();
//        boolean login = false;
//        for (Login u : users) {
//            if (u.getUsername().equalsIgnoreCase(username) && u.getPassword().equalsIgnoreCase(password)) {
//                login = true;
//                loggedInUser.add(u);
//                System.out.println(u.getUsername());
//                break;
//            }
//        }
//        if (login) {
//            changeWindow();
//        } else {
//            loginNotifier.setOpacity(1);
//        }

        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();

        if (username.length()>0 && password.length()>0){

            if (username.equalsIgnoreCase("a")
                    && password.equalsIgnoreCase("1")){


                URL resource = this.getClass().
                        getResource("/view/DashBoard.fxml");
                Parent load = FXMLLoader.load(resource);// ALt + Enter
                Scene scene= new Scene(load);
                Stage stage= new Stage();
                stage.setScene(scene);
                stage.show();
                stage.setTitle("Royal Institute");


            }else{
                new Alert(Alert.AlertType.WARNING,"Try Again !!!!",
                        ButtonType.OK,ButtonType.NO).show();
            }
        }else{
            new Alert(Alert.AlertType.WARNING,"Empty !!!!",
                    ButtonType.OK,ButtonType.NO).show();
        }

    }

    private void changeWindow() {
        try {
            Stage stage = (Stage) txtUsername.getScene().getWindow();
            URL resource = this.getClass().getResource("/view/DashBoard.fxml");
            Parent load = FXMLLoader.load(resource);
            Scene scene= new Scene(load);

            stage.setScene(scene);
            stage.show();
            stage.setTitle("Royal Institute");
            stage.setOnCloseRequest(event -> {
                System.exit(0);
            });
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void registration(ActionEvent event) {
//        if (!txtUsername.getText().equalsIgnoreCase("")
//                || !txtPassword.getText().equalsIgnoreCase("")) {
//            if (checkUser(txtUsername.getText())) {
//                if (checkPassword(txtPassword.getText())) {
//                        try {
//                            loginBO.saveUser(txtUsername.getText(),txtPassword.getText());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                }
//            }
//        }

//----------------------------------------------------------------------------------------
//        String un = txtUsername.getText();
//        String ps = txtPassword.getText();
//
//        // Validation
//        if (un.trim().length() == 0 || ps.trim().length() == 0) {
//            new Alert(Alert.AlertType.ERROR, "username & password can't be empty", ButtonType.OK).show();
//            return;
//        }
//
//        if (btnSave.getText().equals("Save")) {
//
//            try {
//                loginBO.saveUser(txtUsername.getText(),txtPassword.getText());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }else {
//            new Alert(Alert.AlertType.ERROR, "Failed to Create acount", ButtonType.OK).show();
//
//        }
//----------------------------------------------------------------------------------------
    }

    private boolean checkPassword(String password) {
        for(Login user : users) {
            if(user.getPassword().equalsIgnoreCase(password)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkUser(String username) {
        for(Login user : users) {
            if(user.getUsername().equalsIgnoreCase(username)) {
                return false;
            }
        }
        return true;
    }



    public void handleMouseEvent(javafx.scene.input.MouseEvent mouseEvent) {
    }

    public void togglevisiblePassword(ActionEvent actionEvent) {
        if (pass_toggle.isSelected()) {
            txtShowPassword.setText(txtPassword.getText());
            txtShowPassword.setVisible(true);
            txtPassword.setVisible(false);
            return;
        }
        txtPassword.setText(txtShowPassword.getText());
        txtPassword.setVisible(true);
        txtShowPassword.setVisible(false);
    }
    private String passwordValue() {
        return pass_toggle.isSelected()?
                txtShowPassword.getText(): txtPassword.getText();
    }
}
