package model;

import java.util.ArrayList;
import  java.sql.*;

public class PhotoModel {

	private String DB_URL = "jdbc:mysql://localhost/photos";

	public ArrayList<Photo> loadPhotos(){
		ArrayList<Photo> toReturn = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    Connection conn = DriverManager.getConnection(DB_URL, "root", "");
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery("SELECT * FROM interwar");
		    while (rs.next()){
		        int id = rs.getInt("idinterwar");
		        String photoName = rs.getString("pname");
		        String localization = rs.getString("localization");
		        String classified = rs.getString("classified");
		        String path = rs.getString("path");
		        toReturn.add(new Photo(id, photoName, classified));
		    }

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return toReturn;
	}

	public void savePhoto(Photo photo){
		throw new UnsupportedOperationException();
	}
}
