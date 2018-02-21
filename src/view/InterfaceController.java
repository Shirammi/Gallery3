package view;


import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import controller.GalleryController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import model.Photo;

public class InterfaceController {
	@FXML
	ObservableList<Photo> observablePhotoList; 
	
	private GalleryController galleryControler;

	public InterfaceController() {
		this.galleryControler = new GalleryController();
	}

	@FXML
	void initialize() {
		Gson gson = new Gson();
		ArrayList<Photo> photoList = gson.fromJson(galleryControler.showPhotos(), new TypeToken<ArrayList<Photo>>(){}.getType());
		observablePhotoList = FXCollections.observableArrayList(photoList);
		
	}

}
