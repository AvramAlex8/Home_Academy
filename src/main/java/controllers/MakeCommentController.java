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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import exceptions.EmptyCommentException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import static services.Student.nameCourse;

public class MakeCommentController implements Initializable {

    @FXML
    public Label closeButton;
    public Label homePageButton;
    public Button postCommentButton;
    public TextArea commentField;
    public Label emptyComment;

    @FXML
    public void handlePostCommentButton(MouseEvent event) throws IOException {
        try{
            if(commentField.getText().isEmpty())
            {
                throw new EmptyCommentException();
            }
            JSONObject obj = new JSONObject();
            JSONArray arrayClient = new JSONArray();
            JSONParser jp = new JSONParser();
            Object p;
            try {
                FileReader readFile = new FileReader("src/main/comments.json");
                BufferedReader read = new BufferedReader(readFile);
                p = jp.parse(read);
                if (p instanceof JSONArray) {
                    arrayClient = (JSONArray) p;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Iterator<JSONObject> iterator = arrayClient.iterator();
            JSONArray array= new JSONArray();
            obj.put("Name of the course:", nameCourse);
            obj.put("Comment:", commentField.getText());
            arrayClient.add(obj);
            try {
                File file = new File("src/main/comments.json");
                FileWriter fisier = new FileWriter(file.getAbsoluteFile());
                fisier.write(arrayClient.toJSONString());
                fisier.flush();
                fisier.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent HomePage = FXMLLoader.load(getClass().getResource("HomePageStudent.fxml"));
            Scene HomeScene = new Scene(HomePage);
            Stage appStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            appStage.setScene(HomeScene);
            appStage.show();
        }catch(EmptyCommentException e)
        {
            emptyComment.setText(e.getMessage());
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
