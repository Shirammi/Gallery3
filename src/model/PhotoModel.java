package model;

import java.util.ArrayList;
import  java.sql.*;

public class PhotoModel {

	public ArrayList<Photo> loadPhotos(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return null;
	}

	public void savePhoto(Photo photo){
		throw new UnsupportedOperationException();
	}
}
