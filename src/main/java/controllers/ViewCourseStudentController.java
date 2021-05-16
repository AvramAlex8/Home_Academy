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
import exceptions.CourseNameIncorrectException;
import services.Student;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static services.Student.courseListStudent;
import static services.Student.courseNameStudent;
import static services.User.userNameStudent;

public class ViewCourseStudentController implements Initializable {
    private static boolean empty = true;

    @FXML
    public Label closeButton;
    public Label homePageButton;
    public TextArea courseList;
    public TextField courseName;
    public Button seeCourseButton;
    public Label emptyField;

    @FXML
    public void handleSeeCourseButton(MouseEvent event)
    {

        try{
            if(Student.findCourse(courseName.getText()))
            {
                Parent afterPage = FXMLLoader.load(getClass().getResource("InformationAfterPurchase.fxml"));
                Scene afterScene = new Scene(afterPage);
                Stage appStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                appStage.setScene(afterScene);
                appStage.show();
            }
            else
            {
                throw new CourseNameIncorrectException();
            }
        }catch(CourseNameIncorrectException | IOException e)
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
        if(empty)
        {
            Student.viewCourseList(userNameStudent);
            courseList.setText(courseListStudent);
            empty = false;
        }
        else {
            courseList.setText(courseListStudent);
        }
    }
}
