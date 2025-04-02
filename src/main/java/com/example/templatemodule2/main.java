package com.example.templatemodule2;

import com.example.templatemodule2.essential.course;
import com.example.templatemodule2.essential.facuilty;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import com.example.templatemodule2.essential.subject;
import java.util.List;
import java.util.ArrayList;



public class main extends Application {
    static public char userType = 'u';
    public static facuilty[] facuilties = new facuilty[100];
    public static course[] courses = new course[100];
    public static int facCounter = 0;

    public static List<subject> subjectList = new ArrayList<>();

    public static int mainCounter = 0;
    public static void switchScene (Stage stage,  String s) throws IOException {
        FXMLLoader subManagment = new FXMLLoader(main.class.getResource("subModule.fxml"));
        FXMLLoader courManagment = new FXMLLoader(main.class.getResource("courseModule.fxml"));
        FXMLLoader studManagment = new FXMLLoader(main.class.getResource("studModule.fxml"));
        FXMLLoader facManagment = new FXMLLoader(main.class.getResource("facModule.fxml"));
        FXMLLoader evenManagment = new FXMLLoader(main.class.getResource("mergedEventModule.fxml"));
       // FXMLLoader logModule = new FXMLLoader(main.class.getResource("logModule.fxml"));

        Scene subManagmentScene = new Scene(subManagment.load());
        Scene courManagmentScene = new Scene(courManagment.load());
        //Scene studManagmentScene = new Scene(studManagment.load());
        Scene facManagmentScene = new Scene(facManagment.load());
        Scene evenManagmentScene = new Scene(evenManagment.load());

        //Scene logModuleScene = new Scene(logModule.load());

        if (s.equals("subjectManagment"))
        {
            stage.setTitle("subjectManagment");
            stage.setScene(subManagmentScene);
            stage.show();
        }
        if (s.equals("courseManagment"))
        {
            stage.setTitle("courseManagment");
            stage.setScene(courManagmentScene);
            stage.show();
        }
       /* if (s == "studentManagement")
        {
            stage.setTitle("studentManagement");
            stage.setScene(studManagment);
            stage.show();
        }*/
        if (s.equals("facuiltyManagment"))
        {
            stage.setTitle("facuiltyManagment");
            stage.setScene(facManagmentScene);
            stage.show();
        }
        if (s.equals("eventManagment"))
        {
            stage.setTitle("eventManagment");
            stage.setScene(evenManagmentScene);
            stage.show();
        }
//testing
    }


    @Override
    public void start(Stage stage) throws IOException {

        for (int i = 0; i < 100; i++)
        {
            course c = new course("", "", "", "", "", "", "", "");
            courses[i] = c;
            facuilty f = new facuilty();
            courses[i].name = i+"";
            facuilties[i] = f;
            facuilties[i].setName("");
            facuilties[i].setEmail("");
            facuilties[i].researchInterest = "";
            facuilties[i].officeLocation = "";
            facuilties[i].degree = "";
            facuilties[i].intital();
        }
        courses[0].name = "calc";

        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("logModule.fxml"));
        facuiltyController controller = new facuiltyController();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("moduleName");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}