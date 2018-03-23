package view;

import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PhotoProperty {


	 int id;
	 int pairID;
	 String photoName;
	 String localization;
	 String classified;
	 String path;
	 ArrayList<String> languages;
//	private SimpleStringProperty classified ;
//	private SimpleStringProperty path ;
//



	public PhotoProperty(int id, int pairID, String photoName, String localization,
			String  classified, String  path, ArrayList<String> languages) {
		super();
		this.photoName = photoName;
		this.localization = localization;
		this.classified = classified;
		this.path =  path;
		this.languages = languages;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public String getLocalization() {
		return localization;
	}
	public void setLocalization(String localization) {
		this.localization = localization;
	}
//	public String getClassified() {
//		return classified.get();
//	}
//	public void setClassified(String classified) {
//		this.classified.set(classified);
//	}
//	public String getPath() {
//		return path.get();
//	}
//	public void setPath(String path) {
//		this.path.set(path);
//	}


	public StringProperty photoNameProperty() {
		return new SimpleStringProperty(photoName);
	}


	public StringProperty localizationProperty() {
		return new SimpleStringProperty(localization) ;
	}

	public StringProperty languagesProperty(){
		return new SimpleStringProperty(this.languages.toString());
	}

}
