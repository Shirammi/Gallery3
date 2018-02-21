package model;

import java.sql.Date;

public class Photo {
	private Date shootDate;
	private String title;
	private String description;
	private int id;

	public Photo(int id, Date shootDate, String title, String description) {
		super();
		this.id = id;
		this.shootDate = shootDate;
		this.title = title;
		this.description = description;

	}


}
