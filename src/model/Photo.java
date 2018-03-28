package model;

import java.util.ArrayList;

public class Photo {

	 private int id;
	 private int pairID;
	 private String photoName;
	 private String localization;
	 private String classified;
	 private String path;
	 private ArrayList<String> languages;






	public Photo(int id, int pairID, String photoName, String localization, String classified, String path, ArrayList<String> langs) {
		super();
		this.id = id;
		this.pairID = pairID;
		this.photoName = photoName;
		this.localization = localization;
		this.classified = classified;
		this.path = path;
		this.languages = langs;
	}

}

