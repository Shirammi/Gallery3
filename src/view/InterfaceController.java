package view;


import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import controller.GalleryController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
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
		//this.observablePhotoList = new ObservableList<PhotoProperty>();
	}

	@FXML
	void initialize() {
		Gson gson = new Gson();
		String json = galleryControler.showPhotos();
		ArrayList<PhotoProperty> photoList = gson.fromJson(json, new TypeToken<ArrayList<PhotoProperty>>(){}.getType());
		observablePhotoList = FXCollections.observableArrayList(photoList);
		observablePhotoList.add(new PhotoProperty(23, 15, "PIEKNE", "ogorka 2", "2312", "C:/sadfa.jpg", new ArrayList<String> ()));


        // Initialize the person table with the two columns.
    	photoNameColumn.setCellValueFactory(cellData -> cellData.getValue().photoNameProperty());
    	localizationColumn.setCellValueFactory(cellData -> cellData.getValue().localizationProperty());
    	languagesColumn.setCellValueFactory(cellData -> cellData.getValue().languagesProperty());

    	photoTable.setItems(observablePhotoList);

	}


	/**
     * Returns the data as an observable list of Persons.
     * @return
     */
    public ObservableList<PhotoProperty> getPhotoData() {
        return observablePhotoList;
    }

}
