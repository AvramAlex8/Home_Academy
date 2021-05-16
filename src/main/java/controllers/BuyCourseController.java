package controllers;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import exceptions.EmptyFieldException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static services.Student.nameCourse;
import static services.User.userNameStudent;

public class BuyCourseController implements Initializable {

    @FXML
    public Label closeButton;
    public Label homePageButton;
    public Button buyButton;
    public TextField holderCard;
    public TextField numberCard;
    public TextField MM;
    public TextField YY;
    public Label emptyField;
    public TextField CVC;

    @FXML
    public void handleBuyButton(MouseEvent event) throws IOException
    {
        try{
            if(holderCard.getText().isEmpty() | numberCard.getText().isEmpty() | MM.getText().isEmpty() | YY.getText().isEmpty() | CVC.getText().isEmpty())
            {
                throw new EmptyFieldException();
            }
            else
            {
                Student.buyCourse(userNameStudent, nameCourse);
                Parent HomePage = FXMLLoader.load(getClass().getResource("HomePageStudent.fxml"));
                Scene HomeScene = new Scene(HomePage);
                Stage appStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                appStage.setScene(HomeScene);
                appStage.show();
            }
        } catch(EmptyFieldException e)
        {
            emptyField.setText(e.getMessage());
        }
    }

    @FXML
    public void handleHomePageButton(MouseEvent event) throws IOException
    {

        Parent HomePage = FXMLLoader.load(getClass().getResource("HomePageStudent.fxml"));
        Scene HomeScene = new Scene(HomePage);
        Stage appStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(HomeScene);
        appStage.show();
    }

    @FXML
    public void handleCloseButtonAction(MouseEvent event)
    {
        if(event.getSource() == closeButton)
        {
            System.exit(0);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
