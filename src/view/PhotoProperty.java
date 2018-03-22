package view;

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

//	private SimpleStringProperty classified ;
//	private SimpleStringProperty path ;
//



	public PhotoProperty(int id, int pairID, String photoName, String localization,
			String  classified, String  path) {
		super();
		this.photoName = photoName;
		this.localization = localization;
		this.classified = classified;
		this.path =  path;
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

}
