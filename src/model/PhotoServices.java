package model;

import java.util.ArrayList;
import com.google.gson.*;

public class PhotoServices {

	/**
	 *
	 * @param photoList
	 * List of photos to serialize
	 * @return
	 * String with serialized in JSON photos.
	 */
	public static String PhotosToJSON(ArrayList<Photo> photoList) {
		Gson gson = new Gson();
		return gson.toJson(photoList);
	}

	/**
	 * @param photo
	 * Photo to serialize
	 * @return
	 * Serialized photo string.
	 */

	public static String PhotoToJSON(Photo photo) {
		Gson gson = new Gson();
		return gson.toJson(photo);
	}

	public static Photo JSONtoPhoto(String json){
		Gson gson = new Gson();
		return gson.fromJson(json, Photo.class);
	}

}
