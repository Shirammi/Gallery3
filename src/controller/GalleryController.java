package controller;

import java.util.ArrayList;

import model.Photo;
import model.PhotoModel;
import model.PhotoServices;

public class GalleryController {

	private PhotoModel photoModel;

	public GalleryController(){
		this.photoModel = new PhotoModel();
	};

	public String showPhotos(){
		ArrayList<Photo> photoList = photoModel.loadPhotos();
		return PhotoServices.PhotosToJSON(photoList);
	};

	public void createPhoto(String json){
		Photo photo = PhotoServices.JSONtoPhoto(json);
		photoModel.savePhoto(photo);

	};

	public void updatePhoto(String json){
		Photo photo = PhotoServices.JSONtoPhoto(json);
		photoModel.updatePhoto(photo);
	};




}
