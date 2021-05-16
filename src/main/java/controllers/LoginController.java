package controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import exceptions.UsernameOrPasswordIncorrectException;
import services.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    public Label closeButtonLogin;

    @FXML
    public Label signUpButtonLogin;

    @FXML
    public Button logInButton;

    @FXML
    public Label emptyFieldMessage;

    @FXML
    public TextField usernameLogin;

    @FXML
    public PasswordField passwordLogin;

    @FXML
    public void handleLogInButton(MouseEvent event) throws IOException
    {
        try{
            if(User.loginCheckStudent(usernameLogin.getText(), passwordLogin.getText()).equals("Student"))
            {
                Parent HomePage = FXMLLoader.load(getClass().getResource("HomePageStudent.fxml"));
                Scene HomeScene = new Scene(HomePage);
                Stage appStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                appStage.setScene(HomeScene);
                appStage.show();
            }
            else {
                if (User.loginCheckProvider(usernameLogin.getText(), passwordLogin.getText()).equals("Provider"))
                {
                    Parent HomePage = FXMLLoader.load(getClass().getResource("HomePageProvider.fxml"));
                    Scene HomeScene = new Scene(HomePage);
                    Stage appStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    appStage.setScene(HomeScene);
                    appStage.show();
                }
            }
            User.checkIncorrect();
        }catch(UsernameOrPasswordIncorrectException e)
        {
            emptyFieldMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleSignUpButton(MouseEvent event) throws IOException
    {
        Parent signUpPage = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene signUpScene = new Scene(signUpPage);
        Stage appStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(signUpScene);
        appStage.show();
    }

    @FXML
    public void handleCloseButtonAction(MouseEvent event)
    {
        if(event.getSource() == closeButtonLogin)
        {
            System.exit(0);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}
