package view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PhotoDetailsController {

	private Stage dialogStage;
	private PhotoProperty photo1, photo2;
	private boolean isPhoto1Changed, isPhoto2Changed;

	@FXML
	private Text title1;
	@FXML
	private Text title2;
	@FXML
	private ImageView imageView1;
	@FXML
	private ImageView imageView2;
	@FXML
	private Button editButton1;
	@FXML
	private Button editButton2;

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;

	}

	public void setPhotos(PhotoProperty photo1, PhotoProperty photo2) {
		this.photo1 = photo1;
		this.photo2 = photo2;
	}

	public void showPhotos() {
		title1.setText(photo1.getPhotoName());
		Image image1 = new Image("file:" + photo1.path);
		imageView1.setImage(image1);

		editButton1.setOnAction((event) -> {
			isPhoto1Changed = showPhotoEditDialog(photo1);
		});

		title2.setText(photo2.getPhotoName());
		Image image2 = new Image("file:" + photo2.path);
		imageView2.setImage(image2);

		editButton2.setOnAction((event) -> {
			isPhoto2Changed = showPhotoEditDialog(photo2);
		});

	}

	public boolean showPhotoEditDialog(PhotoProperty photo) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(PhotoDetailsController.class.getResource("EditPhotoDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Photo");
			dialogStage.initModality(Modality.WINDOW_MODAL);

			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the photo into the controller.
			EditPhotoDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPhoto(photo);
			controller.disableTimesChoice();
			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
			showPhotos();
			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean isPhoto1Changed() {
		return isPhoto1Changed;
	}

	public boolean isPhoto2Changed() {
		return isPhoto2Changed;
	}

}
