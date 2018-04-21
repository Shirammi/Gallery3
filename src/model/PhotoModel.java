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
		throw new UnsupportedOperationException();
	}
}
