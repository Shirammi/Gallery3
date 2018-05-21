package view;

import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PhotoProperty {


	 private int id;
	 private int pairID;
	 private String photoName;
	 private String localization;



	 public String getTimes() {
		return times;
	}


	public void setTimes(String times) {
		this.times = times;
	}




	String classified;
	 String path;
	 ArrayList<String> languages;
	 String times;
//	private SimpleStringProperty classified ;
//	private SimpleStringProperty path ;
//




	public PhotoProperty(int id, int pairID, String photoName, String localization,
			String  classified, String  path, ArrayList<String> languages, String times) {
		super();
		this.photoName = photoName;
		this.pairID = pairID;
		this.localization = localization;
		this.classified = classified;
		this.path =  path;
		this.languages = languages;
		this.times = times;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getPairID(){
		return pairID;
	}

	public void setPairID(int pairID){
		this.pairID = pairID;
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
	public String getClassified() {
		return classified;
	}
	public void setClassified(String classified) {
		this.classified = classified;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path.replace("\\", "/");
	}


	public StringProperty photoNameProperty() {
		return new SimpleStringProperty(photoName);
	}


	public StringProperty localizationProperty() {
		return new SimpleStringProperty(localization) ;
	}

	public StringProperty languagesProperty(){
		return new SimpleStringProperty(String.join(", ", this.languages) );
	}


	public void setClassification(String text) {
		this.classified = text;

	}


	public void setLanguages(String text) {
		// TODO Auto-generated method stub

	}


	public StringProperty classificationProperty() {
		return new SimpleStringProperty(classified) ;
	}


	public void setLanguages(ArrayList<String> listLanguages) {
		this.languages = listLanguages;

	}


//	public void setPath(String text) {
		// TODO Auto-generated method stub

//	}

}
