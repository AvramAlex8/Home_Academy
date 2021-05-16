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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import static services.Student.nameCourse;

public class SeeCommentsStudentController implements Initializable {


    public Label closeButton;
    public Label homePageButton;
    public TextArea commentField;

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
        JSONParser parser = new JSONParser();
        Object p;
        JSONArray arrayClient = new JSONArray();
        try {
            FileReader readFile = new FileReader("src/main/comments.json");
            BufferedReader read = new BufferedReader(readFile);
            p = parser.parse(read);
            if (p instanceof JSONArray) {
                arrayClient = (JSONArray) p;
            }
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        Iterator<JSONObject> iterator = arrayClient.iterator();
        while (iterator.hasNext())
        {
            JSONObject obj = iterator.next();
            if (obj.get("Name of the course:").equals(nameCourse))
            {
                if(commentField.getText().isEmpty())
                {
                    commentField.setText((String) obj.get("Comment:"));
                }
                else
                {
                    String actual = commentField.getText();
                    commentField.setText(actual + "\n-----\n" + (String) obj.get("Comment:"));
                }
            }
        }
    }
}
