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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static services.Student.nameCourse;
import static services.Student.price;
import static services.Student.description;

public class InformationCourseStudentController implements Initializable {
    @FXML
    public Label closeButton;
    public Label homePageButton;
    public Button buyCourseButton;
    public Button seeCommentsButton;
    public TextField seeName;
    public TextField seePrice;
    public TextArea seeDescription;

    @FXML
    public void handleSeeCommentsButton(MouseEvent event) throws IOException {
        Parent commentPage = FXMLLoader.load(getClass().getResource("SeeCommentsStudent.fxml"));
        Scene commentScene = new Scene(commentPage);
        Stage appStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(commentScene);
        appStage.show();
    }

    @FXML
    public void handleBuyCourseButton(MouseEvent event) throws IOException {
        Parent buyPage = FXMLLoader.load(getClass().getResource("BuyCourse.fxml"));
        Scene buyScene = new Scene(buyPage);
        Stage appStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(buyScene);
        appStage.show();
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
        seeName.setText(nameCourse);
        seePrice.setText(price);
        seeDescription.setText(description);
    }
}
