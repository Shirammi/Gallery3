package view;



import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
     * @param person
     */
    public void setPhoto(PhotoProperty photo) {
        this.photo = photo;

         photoNameField.setText(photo.getPhotoName());
       //  photoLanguagesField.setText(photo.get);
//       secondPhotoIDField;
         photoClassificationField.setText(photo.getClassified());
         pathField.setText(photo.getPath());
//        postalCodeField.setText(Integer.toString(person.getPostalCode()));
//        cityField.setText(person.getCity());
//        birthdayField.setText(DateUtil.format(person.getBirthday()));
//        birthdayField.setPromptText("dd.mm.yyyy");
    }


    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
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
            errorMessage += "No valid first name!\n";
        }
        if (photoAdressField.getText() == null || photoAdressField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (photoLanguagesField.getText() == null || photoLanguagesField.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }

        if (secondPhotoIDField.getText() == null || secondPhotoIDField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(secondPhotoIDField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n";
            }
        }

        if (photoClassificationField.getText() == null || photoClassificationField.getText().length() == 0) {
            errorMessage += "No valid city!\n";
        }

        if (pathField.getText() == null || pathField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
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
