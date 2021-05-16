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

public class SignUpStudentController {
    @FXML
    public Label closeButtonSignUp;

    @FXML
    public Label signInButtonRegister;

    @FXML
    public Button providerButton;

    @FXML
    public TextField firstName;

    @FXML
    public TextField lastName;

    @FXML
    public TextField userName;

    @FXML
    public PasswordField password;

    @FXML
    public Button signUpStudentButton;

    @FXML
    public Label emptyFieldMessage;

    @FXML
    public void handleSignUpStudentButton(MouseEvent event) throws EmptyFieldException, ShortPasswordException{
        try{
            User.addUserStudent(firstName.getText(), lastName.getText(), userName.getText(), password.getText());
            emptyFieldMessage.setText("                     Sign up complete!");
        } catch (EmptyFieldException | ShortPasswordException | UsernameAlreadyExistsException e)
        {
            emptyFieldMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleProviderButton(MouseEvent event) throws IOException {
        Parent providerPage = FXMLLoader.load(getClass().getResource("SignUpProvider.fxml"));
        Scene providerScene = new Scene(providerPage);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(providerScene);
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