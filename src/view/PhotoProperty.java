package view;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PhotoProperty {

	private SimpleIntegerProperty id;
	private SimpleStringProperty title ;
	private SimpleStringProperty description ;
//	private SimpleStringProperty classified ;
//	private SimpleStringProperty path ;
//	
	
	
	
	public PhotoProperty(int id, String photoName, String localization,
			String  classified, String  path) {
		super();
		this.id = new SimpleIntegerProperty( id);
		this.title = new SimpleStringProperty( photoName);
		this.description = new SimpleStringProperty( localization);
//		this.classified = new SimpleStringProperty( classified);
//		this.path = new SimpleStringProperty( path);
	}
	
	
	public int getId() {
		return id.get();
	}
	public void setId(int id) {
		this.id.set(id);
	}
	public String getPhotoName() {
		return title.get();
	}
	public void setTitle(String photoName) {
		this.title.set(photoName);
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
		return title;
	}


	public StringProperty localizationProperty() {
		return description;
	}
	
	
		
}
