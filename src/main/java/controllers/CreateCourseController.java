package controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import exceptions.CourseNameExistsException;
import exceptions.EmptyFieldException;
import services.Provider;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateCourseController implements Initializable {

    public Label closeButton;
    public Button createButton;
    public Label homePageButton;
    public TextArea descriptionField;
    public TextField nameField;
    public TextField priceField;
    public TextArea contentField;
    public Label emptyFieldMessage;

    @FXML
    public void handleHomePageButton(MouseEvent event) throws IOException
    {

        Parent HomePage = FXMLLoader.load(getClass().getResource("HomePageProvider.fxml"));
        Scene HomeScene = new Scene(HomePage);
        Stage appStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(HomeScene);
        appStage.show();
    }

    @FXML
    public void handleCreateButton(MouseEvent event)
    {
        try{
            Provider.createCourse(nameField.getText(), priceField.getText(), descriptionField.getText(), contentField.getText());
            emptyFieldMessage.setText("                         Course created!");
        } catch (EmptyFieldException | CourseNameExistsException e)
        {
            emptyFieldMessage.setText(e.getMessage());
        }
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
