package view;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PhotoProperty {

	 SimpleIntegerProperty idProperty;
	 SimpleStringProperty titleProperty ;
	 SimpleStringProperty description ;
	 String title;
	 int id;

//	private SimpleStringProperty classified ;
//	private SimpleStringProperty path ;
//



	public PhotoProperty(int id, String photoName, String localization,
			String  classified, String  path) {
		super();
		this.idProperty = new SimpleIntegerProperty( id);
		this.titleProperty = new SimpleStringProperty( photoName);
		this.description = new SimpleStringProperty( localization);
//		this.classified = new SimpleStringProperty( classified);
//		this.path = new SimpleStringProperty( path);
	}


	public int getId() {
		return idProperty.get();
	}
	public void setId(int id) {
		this.idProperty.set(id);
	}
	public String getPhotoName() {
		return titleProperty.get();
	}
	public void setTitle(String photoName) {
		this.titleProperty.set(photoName);
	}
	public String getDescription() {
		return description.get();
	}
	public void setDescription(String localization) {
		this.description.set(localization);
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
		return new SimpleStringProperty( title);
	}


	public StringProperty localizationProperty() {
		// TODO
		return new SimpleStringProperty() ;
	}



}
