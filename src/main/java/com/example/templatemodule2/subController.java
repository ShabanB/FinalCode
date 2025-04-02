package com.example.templatemodule2;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.example.templatemodule2.essential.subject;


public class subController implements Initializable {

    @FXML private TextField searchTextField;
    @FXML private TextField subjectCodeField;
    @FXML private TextField subjectNameField;
    @FXML private Label statusLabel;
    @FXML private Label userTypeLabel;
    @FXML private MenuItem adminMenuItem;
    @FXML private Button addSubjectButton;
    @FXML private Button editSubjectButton;
    @FXML private Button deleteSubjectButton;

    // Labels
    @FXML public Label labelOne;
    @FXML public Label labelTwo;
    @FXML public Label labelThree;
    @FXML public Label labelFour;
    @FXML public Label labelFive;
    @FXML public Label labelSix;
    @FXML public Label labelSeven;
    @FXML public Label labelEight;
    @FXML public Label labelNine;
    @FXML public Label labelTen;
    @FXML public Label labelEleven;
    @FXML public Label labelTweleve;
    @FXML public Label labelThirteen;
    @FXML public Label labelFourteen;


    @FXML public Label[] infoList = new Label[14];

    private boolean subjectsInitialized = false;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // array of labels
        infoList[0] = labelOne;
        infoList[1] = labelTwo;
        infoList[2] = labelThree;
        infoList[3] = labelFour;
        infoList[4] = labelFive;
        infoList[5] = labelSix;
        infoList[6] = labelSeven;
        infoList[7] = labelEight;
        infoList[8] = labelNine;
        infoList[9] = labelTen;
        infoList[10] = labelEleven;
        infoList[11] = labelTweleve;
        infoList[12] = labelThirteen;
        infoList[13] = labelFourteen;

        // Hide Admin Buttons by default
        addSubjectButton.setVisible(false);
        editSubjectButton.setVisible(false);
        deleteSubjectButton.setVisible(false);
        subjectCodeField.setVisible(false);
        subjectNameField.setVisible(false);

        // Placeholder text
        subjectCodeField.setText("Enter Subject Code");
        subjectCodeField.setOnMouseClicked(e -> {
            if (subjectCodeField.getText().equals("Enter Subject Code")) subjectCodeField.clear();
        });

        subjectNameField.setText("Enter Subject Name");
        subjectNameField.setOnMouseClicked(e -> {
            if (subjectNameField.getText().equals("Enter Subject Name")) subjectNameField.clear();
        });

        // Hide Admin menu for student users
        if (main.userType == 's') {
            adminMenuItem.setVisible(false);
        }
    }

    // Shows initial subjects. Subjects are added to the list and labels are updated using a loop
    @FXML
    protected void onFeatureButtonOne() {
        if (!subjectsInitialized) {
            subject s1 = new subject(); s1.code = "MATH001"; s1.name = "Mathematics"; main.subjectList.add(s1);
            subject s2 = new subject(); s2.code = "ENG101"; s2.name = "English"; main.subjectList.add(s2);
            subject s3 = new subject(); s3.code = "ENGG1420"; s3.name = "Programming"; main.subjectList.add(s3);
            subject s4 = new subject(); s4.code = "CS201"; s4.name = "Computer Science"; main.subjectList.add(s4);
            subject s5 = new subject(); s5.code = "CHEM200"; s5.name = "Chemistry"; main.subjectList.add(s5);
            subject s6 = new subject(); s6.code = "BIO300"; s6.name = "Biology"; main.subjectList.add(s6);
            subject s7 = new subject(); s7.code = "ENGG402"; s7.name = "Engineering"; main.subjectList.add(s7);
            subject s8 = new subject(); s8.code = "HIST101"; s8.name = "History"; main.subjectList.add(s8);
            subject s9 = new subject(); s9.code = "MUSIC102"; s9.name = "Music"; main.subjectList.add(s9);
            subject s10 = new subject(); s10.code = "PSYCHO100"; s10.name = "Psychology"; main.subjectList.add(s10);
            subject s11 = new subject(); s11.code = "PHYS1010"; s11.name = "Physics"; main.subjectList.add(s11);
            subject s12 = new subject(); s12.code = "ENGG1210"; s12.name = "Mechanics"; main.subjectList.add(s12);
            subject s13 = new subject(); s13.code = "MATH1210"; s13.name = "Calculus Two"; main.subjectList.add(s13);
            subjectsInitialized = true;
        }
        updateLabelsFromSubjectList();
    }


    @FXML
    protected void onAdminSelected() {
        addSubjectButton.setVisible(true);
        editSubjectButton.setVisible(true);
        deleteSubjectButton.setVisible(true);
        subjectCodeField.setVisible(true);
        subjectNameField.setVisible(true);
        onFeatureButtonOne(); // show subjects too
    }
    // method to update the labels with data from subject list
    private void updateLabelsFromSubjectList() {
        for (int i = 0; i < infoList.length; i++) { //loop through infolist
            if (i < main.subjectList.size()) { // if index is within range of subjectlist
                subject s = main.subjectList.get(i);
                infoList[i].setText(s.code + " - " + s.name);
                infoList[i].setVisible(true);
            } else { //if fewer subjects than labels it clears and hides the labels
                infoList[i].setText("");
                infoList[i].setVisible(false);
            }
        }
    }

    @FXML
    protected void onAddSubject() {
        String code = subjectCodeField.getText().trim().toUpperCase();
        String name = subjectNameField.getText().trim();

        if (code.isEmpty() || name.isEmpty()) {
            statusLabel.setText("Please enter both Subject Code and Name");
            return;
        }

        for (subject s : main.subjectList) {
            if (s.code.equalsIgnoreCase(code)) {
                statusLabel.setText("Subject Code already exists");
                return;
            }
        }

        if (main.subjectList.size() >= 14) {
            statusLabel.setText("No more space for new subjects");
            return;
        }

        subject newSubject = new subject();
        newSubject.code = code;
        newSubject.name = name;
        main.subjectList.add(newSubject);

        statusLabel.setText("Subject Added Successfully");
        updateLabelsFromSubjectList();

        subjectCodeField.clear();
        subjectNameField.clear();
    }

    @FXML
    protected void onEditSubject() {
        String searchText = searchTextField.getText().trim().toLowerCase();
        if (searchText.isEmpty()) {
            statusLabel.setText("Enter a Subject Code to Edit");
            return;
        }

        for (subject s : main.subjectList) {
            if (s.code.toLowerCase().contains(searchText)) {
                s.code = subjectCodeField.getText().trim().toUpperCase();
                s.name = subjectNameField.getText().trim();
                statusLabel.setText("Subject Edited Successfully");
                updateLabelsFromSubjectList();
                return;
            }
        }

        statusLabel.setText("Subject Not Found");

        subjectCodeField.clear();
        subjectNameField.clear();
    }

    @FXML
    protected void onDeleteSubject() {
        String code = subjectCodeField.getText().trim().toUpperCase();
        if (code.isEmpty()) {
            statusLabel.setText("Enter a Subject Code to Delete");
            return;
        }

        for (int i = 0; i < main.subjectList.size(); i++) {
            if (main.subjectList.get(i).code.equalsIgnoreCase(code)) {
                main.subjectList.remove(i);
                statusLabel.setText("Subject Deleted Successfully");
                updateLabelsFromSubjectList();
                return;
            }
        }

        statusLabel.setText("Subject Not Found");

        subjectCodeField.clear();
        subjectNameField.clear();
    }

    @FXML
    protected void onSearchButton() {
        String search = searchTextField.getText().trim().toLowerCase();
        if (search.isEmpty()) {
            updateLabelsFromSubjectList();
            return;
        }

        for (int i = 0; i < infoList.length; i++) {
            if (i < main.subjectList.size()) {
                subject s = main.subjectList.get(i);
                if (s.code.toLowerCase().contains(search) || s.name.toLowerCase().contains(search)) {
                    infoList[i].setText(s.code + " - " + s.name);
                    infoList[i].setVisible(true);
                } else {
                    infoList[i].setVisible(false);
                }
            } else {
                infoList[i].setVisible(false);
            }
        }
    }

    @FXML
    protected void onSubjectManagmentButton() throws IOException {
        Stage currentStage = (Stage) userTypeLabel.getScene().getWindow();
        main.switchScene(currentStage, "subjectManagment");
    }

    @FXML
    protected void onCourseManagmentButton() throws IOException {
        Stage currentStage = (Stage) userTypeLabel.getScene().getWindow();
        main.switchScene(currentStage, "courseManagment");
    }

    @FXML
    protected void onStudentManagmentButton() throws IOException {
        Stage currentStage = (Stage) userTypeLabel.getScene().getWindow();
        main.switchScene(currentStage, "studentManagment");
    }

    @FXML
    protected void onFacuiltyManagmentButton() throws IOException {
        Stage currentStage = (Stage) userTypeLabel.getScene().getWindow();
        main.switchScene(currentStage, "facuiltyManagment");
    }

    @FXML
    protected void onEventManagmentButton() throws IOException {
        Stage currentStage = (Stage) userTypeLabel.getScene().getWindow();
        main.switchScene(currentStage, "eventManagment");
    }
}
