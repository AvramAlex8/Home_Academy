package controllers;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {
    @FXML
    public Label closeButtonSignUp;

    @FXML
    public Label signInButtonRegister;

    @FXML
    public Button studentButton;

    @FXML
    public Button providerButton;

    @FXML
    public void handleStudentButton(MouseEvent event) throws IOException {
        Parent studentPage = FXMLLoader.load(getClass().getResource("SignUpStudent.fxml"));
        Scene studentScene = new Scene(studentPage);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(studentScene);
        appStage.show();
    }

    @FXML
    public void handleProviderButton(MouseEvent event) throws IOException {
        Parent providerPage = FXMLLoader.load(getClass().getResource("SignUpProvider.fxml"));
        Scene providerScene = new Scene(providerPage);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(providerScene);
        appStage.show();
    }

    @FXML
    public void handleSignInButton(MouseEvent event) throws IOException {
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