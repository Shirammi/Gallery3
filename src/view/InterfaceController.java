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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
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
	@FXML
	private TableColumn<PhotoProperty, String> classificationColumn;
	@FXML
	private Text photoNameInfo;
	@FXML
	private Text addressInfo;
	@FXML
	private Text llTypeInfo;
	@FXML
	private Text unitStatusInfo;
	@FXML
	private Text languagesInfo;
	@FXML
	private Text sourceInfo;
	@FXML
	private ImageView viewInfo;


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
				.add(new PhotoProperty(23, 15, "PIEKNE", "ogorka 2", "2312", "C:/sadfa.jpg", new ArrayList<String>(), "contemporary"));

		// Initialize the person table with the two columns.
		photoNameColumn.setCellValueFactory(cellData -> cellData.getValue().photoNameProperty());
		localizationColumn.setCellValueFactory(cellData -> cellData.getValue().localizationProperty());
		languagesColumn.setCellValueFactory(cellData -> cellData.getValue().languagesProperty());
		classificationColumn.setCellValueFactory(cellData -> cellData.getValue().classificationProperty());

		photoTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPhotoInfo(newValue));

		photoTable.setRowFactory( tv -> {
		    TableRow<PhotoProperty> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		        	PhotoProperty rowData = row.getItem();
		        	showPhotoDetails(rowData);
		        }
		    });
		    return row ;
		});

		photoTable.setItems(observablePhotoList);

	}

	private void showPhotoInfo(PhotoProperty newValue) {
		this.photoNameInfo.setText(newValue.photoName);

		Image image1 = new Image("file:" + newValue.path);
		this.viewInfo.setImage(image1);


	}

	@FXML
	private void addPhoto(){
		PhotoProperty photo = new PhotoProperty(0, 0, null, null, null, null, null, null);
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


			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
			if (controller.isOkClicked()){
				galleryControler.createPhoto(new Gson().toJson(photo));
			};
		} catch (IOException e) {
			e.printStackTrace();

		}

	}


	/**
	 * Returns the data as an observable list of Photos.
	 *
	 * @return
	 */
	public ObservableList<PhotoProperty> getPhotoData() {
		return observablePhotoList;
	}

	private boolean showPhotoDetails(PhotoProperty photo) {

		//find second photo
		PhotoProperty photo2 = observablePhotoList.stream().filter(a -> a.id == photo.pairID && a.times != photo.times ).findFirst().get();
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

			//check if any changes were made
			if(controller.isPhoto1Changed()){
				galleryControler.updatePhoto(new Gson().toJson(photo));
			}
			if(controller.isPhoto2Changed()){
				galleryControler.updatePhoto(new Gson().toJson(photo2));
			}

			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}


}
