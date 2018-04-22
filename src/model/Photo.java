package model;

import java.util.ArrayList;

public class Photo {

	 private int id;
	 public int getId() {
		return id;
	}


	public int getPairID() {
		return pairID;
	}


	public String getPhotoName() {
		return photoName;
	}


	public String getLocalization() {
		return localization;
	}


	public String getClassified() {
		return classified;
	}


	public String getPath() {
		return path;
	}


	public ArrayList<String> getLanguages() {
		return languages;
	}


	public String getTimes() {
		return times;
	}


	private int pairID;
	 private String photoName;
	 private String localization;
	 private String classified;
	 private String path;
	 private ArrayList<String> languages;
	 private String times;


	public Photo(int id, int pairID, String photoName, String localization, String classified, String path, ArrayList<String> langs, String times) {
		super();
		this.id = id;
		this.pairID = pairID;
		this.photoName = photoName;
		this.localization = localization;
		this.classified = classified;
		this.path = path;
		this.languages = langs;
		this.times = times;
	}

}

