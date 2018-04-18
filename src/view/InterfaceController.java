package view;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import controller.GalleryController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Photo;

public class InterfaceController {
	@FXML
	private ObservableList<PhotoProperty> observablePhotoList;

	@FXML
	private TableView<PhotoProperty> photoTable;

	@FXML
	private TableColumn<PhotoProperty, String> photoNameColumn;
	@FXML
	private TableColumn<PhotoProperty, String> localizationColumn;
	@FXML
	private TableColumn<PhotoProperty, String> languagesColumn;

	private GalleryController galleryControler;

	public InterfaceController() {
		this.galleryControler = new GalleryController();
		// this.observablePhotoList = new ObservableList<PhotoProperty>();
	}

	@FXML
	void initialize() {
		Gson gson = new Gson();
		String json = galleryControler.showPhotos();
		ArrayList<PhotoProperty> photoList = gson.fromJson(json, new TypeToken<ArrayList<PhotoProperty>>() {
		}.getType());
		observablePhotoList = FXCollections.observableArrayList(photoList);
		observablePhotoList
				.add(new PhotoProperty(23, 15, "PIEKNE", "ogorka 2", "2312", "C:/sadfa.jpg", new ArrayList<String>()));

		// Initialize the person table with the two columns.
		photoNameColumn.setCellValueFactory(cellData -> cellData.getValue().photoNameProperty());
		localizationColumn.setCellValueFactory(cellData -> cellData.getValue().localizationProperty());
		languagesColumn.setCellValueFactory(cellData -> cellData.getValue().languagesProperty());

		photoTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPhotoDetails(newValue));

		photoTable.setItems(observablePhotoList);

	}

	/**
	 * Returns the data as an observable list of Persons.
	 *
	 * @return
	 */
	public ObservableList<PhotoProperty> getPhotoData() {
		return observablePhotoList;
	}

	private boolean showPhotoDetails(PhotoProperty photo) {

		//find second photo
		PhotoProperty photo2 = observablePhotoList.stream().filter(a -> a.id == photo.pairID).findFirst().get();
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(InterfaceController.class.getResource("PhotoDetails.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Show Photo");
			dialogStage.initModality(Modality.WINDOW_MODAL);
		//	dialogStage.initOwner(???);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			PhotoDetailsController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPhotos(photo, photo2);
			controller.showPhotos();

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean showPhotoEditDialog(PhotoProperty photo) {
	    try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(InterfaceController.class.getResource("EditPhotoDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Edit Person");
	        dialogStage.initModality(Modality.WINDOW_MODAL);

	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Set the person into the controller.
	        EditPhotoDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setPhoto(photo);

	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();

	        return controller.isOkClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

}
