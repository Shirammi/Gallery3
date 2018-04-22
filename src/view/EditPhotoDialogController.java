package view;



import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditPhotoDialogController {


    @FXML
    private TextField photoNameField;
    @FXML
    private TextField photoAdressField;
    @FXML
    private TextField photoLanguagesField;
    @FXML
    private TextField secondPhotoIDField;
    @FXML
    private TextField photoClassificationField;
    @FXML
    private TextField pathField;
    @FXML
    private CheckMenuItem polish; 
    @FXML
    private CheckMenuItem english; 
    @FXML
    private CheckMenuItem german; 
    @FXML
    private CheckMenuItem french;
    @FXML
    private CheckMenuItem spanish; 
    @FXML
    private CheckMenuItem italian; 
    @FXML
    private CheckMenuItem chineese; 
    @FXML
    private CheckMenuItem japaneese;
    @FXML
    private CheckMenuItem arab; 
    @FXML
    private CheckMenuItem russian;
    @FXML
    private CheckMenuItem yddish;
    
    private Stage dialogStage;
    private PhotoProperty photo;
    private boolean okClicked = false;




    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the photo to be edited in the dialog.
     *
     * @param photo
     */
    public void setPhoto(PhotoProperty photo) {
        this.photo = photo;

         photoNameField.setText(photo.getPhotoName());
         System.out.println(photo.languages);
         secondPhotoIDField.setText(String.valueOf(photo.getPairID()));
         photoClassificationField.setText(photo.getClassified());
         pathField.setText(photo.getPath());
         photoAdressField.setText(photo.getLocalization());
         checkmenuChoice(photo.languages);

    }


    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     * 
     */
    public boolean isOkClicked() {
        return okClicked;
    }


    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            photo.setPhotoName(photoNameField.getText());
            photo.setLocalization(photoAdressField.getText());
            photo.setClassification(photoClassificationField.getText());
            photo.setLanguages(photoLanguagesField.getText());
            photo.setPath(pathField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    private void checkmenuChoice( ArrayList<String> languages){
    	
    	for (int i =0; i<(languages.size()); i++){
    		if (languages.get(i).equals("polish")){
    				polish.setSelected(true);
    		}
    		if (languages.get(i).equals("english")){
				english.setSelected(true);
    		}
    		if (languages.get(i).equals("german")){
				german.setSelected(true);
    		}
    		if (languages.get(i).equals("french")){
				french.setSelected(true);
    		}
    		if (languages.get(i).equals("spanish")){
				spanish.setSelected(true);
    		}
    		if (languages.get(i).equals("italian")){
				italian.setSelected(true);
    		}
    		if (languages.get(i).equals("chineese")){
				chineese.setSelected(true);
    		}
    		if (languages.get(i).equals("japaneese")){
				japaneese.setSelected(true);
    		}
    		if (languages.get(i).equals("arab")){
				arab.setSelected(true);
    		}
    		
    		if (languages.get(i).equals("russian")){
				russian.setSelected(true);
    		}
    		if (languages.get(i).equals("yddish")){
				yddish.setSelected(true);
    		}
    	}
    }
    
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }


    
    
    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (photoNameField.getText() == null || photoNameField.getText().length() == 0) {
            errorMessage += "No valid name!\n";
        }
        if (photoAdressField.getText() == null || photoAdressField.getText().length() == 0) {
            errorMessage += "No valid address!\n";
        }
        if (photoLanguagesField.getText() == null || photoLanguagesField.getText().length() == 0) {
            errorMessage += "No valid languages!\n";
        }

        if (secondPhotoIDField.getText() == null || secondPhotoIDField.getText().length() == 0) {
            errorMessage += "No valid second photo id!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(secondPhotoIDField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid second photo id (must be an integer)!\n";
            }
        }

        if (photoClassificationField.getText() == null || photoClassificationField.getText().length() == 0) {
            errorMessage += "No valid classification!\n";
        }

        if (pathField.getText() == null || pathField.getText().length() == 0) {
            errorMessage += "No valid path!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }



}
