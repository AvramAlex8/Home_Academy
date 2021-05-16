package controllers;
import javafx.fxml.FXMLLoader;
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
import exceptions.EmptyFieldException;
import exceptions.ShortPasswordException;
import exceptions.UsernameAlreadyExistsException;
import services.User;

import java.io.IOException;

public class SignUpProviderController {
    @FXML
    public Label closeButtonSignUp;

    @FXML
    public Label signInButtonRegister;

    @FXML
    public Button studentButton;

    @FXML
    public Button signUpProviderButton;

    @FXML
    public TextField businessName;

    @FXML
    public TextField userName;

    @FXML
    public PasswordField password;

    @FXML
    public Label emptyFieldMessage;

    @FXML
    public void handleSignUpProviderButton(MouseEvent event) throws EmptyFieldException, ShortPasswordException {
        try{
            User.addUserProvider(businessName.getText(), userName.getText(), password.getText());
            emptyFieldMessage.setText("                     Sign up complete!");
        } catch (EmptyFieldException | ShortPasswordException | UsernameAlreadyExistsException e)
        {
            emptyFieldMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleStudentButton(MouseEvent event) throws IOException {
        Parent studentPage = FXMLLoader.load(getClass().getResource("SignUpStudent.fxml"));
        Scene studentScene = new Scene(studentPage);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(studentScene);
        appStage.show();
    }

    @FXML public void handleSignInButton(MouseEvent event) throws IOException {
        Parent signInPage = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene signInScene = new Scene(signInPage);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(signInScene);
        appStage.show();
    }

    @FXML
    public void handleCloseButtonAction(MouseEvent event)
    {
        if(event.getSource() == closeButtonSignUp)
        {
            System.exit(0);
        }

    }
}