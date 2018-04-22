package model;

import java.util.ArrayList;
import java.sql.*;

public class PhotoModel {

	private String DB_URL = "jdbc:mysql://localhost/photos";

	public ArrayList<Photo> loadPhotos() {
		ArrayList<Photo> toReturn = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection(DB_URL, "root", "");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM interwar");
			while (rs.next()) {
				int id = rs.getInt("idinterwar");
				int pairID = rs.getInt("idcontemp");
				String photoName = rs.getString("pname");
				String localization = rs.getString("localization");
				String classified = rs.getString("classified");
				String path = rs.getString("path");

				Connection conn2 = DriverManager.getConnection(DB_URL, "root", "");
				Statement st2 = conn2.createStatement();
				ResultSet langSet = st2
						.executeQuery("SELECT * FROM languages WHERE idPhoto = " + Integer.toString(id) + " ;");
				langSet.next();
				/// get metadata for colums name
				ResultSetMetaData rsmd = langSet.getMetaData();
				String name = rsmd.getColumnName(1);
				System.out.println(name);
				final int columnCount = rsmd.getColumnCount();

				/// iterate over columns
				final ArrayList<String> langList = new ArrayList<String>();

				for (int column = 2; column <= columnCount; ++column) {
					final Object value = langSet.getObject(column);
					if ((boolean) value) {
						langList.add(rsmd.getColumnName(column));
					}
				}
				langSet.close();

				toReturn.add(new Photo(id, pairID, photoName, localization, classified, path, langList, "interwar"));
			}
			rs.close();

			/// load contemporary photos
			rs = st.executeQuery("SELECT * FROM contemporary");
			while (rs.next()) {
				int id = rs.getInt("idcontemporary");
				int pairID = rs.getInt("idinterwar");
				String photoName = rs.getString("pname");
				String localization = rs.getString("localization");
				String classified = rs.getString("classified");
				String path = rs.getString("path");

				Connection conn2 = DriverManager.getConnection(DB_URL, "root", "");
				Statement st2 = conn2.createStatement();
				ResultSet langSet = st2
						.executeQuery("SELECT * FROM languages WHERE idPhoto = " + Integer.toString(id) + " ;");
				langSet.next();
				/// get metadata for colums name
				ResultSetMetaData rsmd = langSet.getMetaData();
				String name = rsmd.getColumnName(1);
				System.out.println(name);
				final int columnCount = rsmd.getColumnCount();

				/// iterate over columns
				final ArrayList<String> langList = new ArrayList<String>();

				for (int column = 2; column <= columnCount; ++column) {
					final Object value = langSet.getObject(column);
					if ((boolean) value) {
						langList.add(rsmd.getColumnName(column));
					}
				}
				langSet.close();

				toReturn.add(new Photo(id, pairID, photoName, localization, classified, path, langList, "contemporary"));
			}
			rs.close();

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return toReturn;
	}

	public void savePhoto(Photo photo) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection(DB_URL, "root", "");
			Statement st = conn.createStatement();

			String maxIdQuery = "";
			String query = "INSERT INTO ";
			query += photo.getTimes() + " ";
			if (photo.getTimes() == "interwar"){
				query += "(idcontemp, ";
				maxIdQuery = "SELECT MAX(idinterwar) as maxid  FROM interwar";
			}
			if (photo.getTimes() == "contemporary"){
				query += "(idinterwar, ";
				maxIdQuery = "SELECT MAX(idcontemporary) as maxid  FROM contemporary";
			}
			query += "pname, localization, classified, path) VALUES (";
			query += photo.getPairID() + ", ";
			query += photo.getPhotoName() + ", ";
			query += photo.getLocalization() + ", ";
			query += photo.getClassified() + ", ";
			query += photo.getPath() + "); ";



			st.executeQuery(query);

			ResultSet idSet = st.executeQuery(maxIdQuery);
			idSet.next();
			int maxID = idSet.getInt("maxid");

			String query2 = "INSERT INTO languages VALUES (idPhoto, " + String.join(", ", photo.getLanguages()) + ") VALUES (" + maxID + ", ";
			for (int i = 0; i < photo.getLanguages().size() - 1; i++ ){
				query2 += "1, ";
			}
			query2 += "1)";

			st.executeQuery(query2);

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updatePhoto(Photo photo) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection(DB_URL, "root", "");
			Statement st = conn.createStatement();

			String query = "UPDATE ";
			query += photo.getTimes() + " SET";
			if (photo.getTimes() == "interwar"){
				//query += "idinterwar =  " + photo.getId() + ", ";
				query += "idcontemp =  " + photo.getPairID() + ", ";
			}
			if (photo.getTimes() == "contemporary"){
				//query += "idcontemporary =  " + photo.getId() + ", ";
				query += "idinterwar =  " + photo.getPairID() + ", ";
			}

			query += "pname =  " + photo.getPhotoName() + ", ";
			query += "localization =  " + photo.getLocalization() + ", ";
			query += "path =  " + photo.getPath() + ", ";
			query += "classified =  " + photo.getClassified() + " ";
			query += "WHERE id = " + photo.getId();

			st.executeQuery(query);

			String query2 = "UPDATE languages SET ";
			for (String s : photo.getLanguages()){
				query2 += s + " = 1, ";
			}
			query2 = query2.substring(0, query2.length() - 1);
			query2 += "WHERE idPhoto = " + photo.getId();

			st.executeQuery(query2);

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
