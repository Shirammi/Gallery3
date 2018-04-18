package view;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PhotoDetailsController {

	private Stage dialogStage;
	private PhotoProperty photo1, photo2;

	@FXML
	private Text title1;
	@FXML
	private Text title2;
	@FXML
	private ImageView imageView1;
	@FXML
	private ImageView imageView2;

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;

	}

	public void setPhotos(PhotoProperty photo1, PhotoProperty photo2) {
		this.photo1 = photo1;
		this.photo2 = photo2;
	}

	public void showPhotos(){
		title1.setText(photo1.photoName);
		Image image1 = new Image("file:"+photo1.path);
		imageView1.setImage(image1);


		title2.setText(photo2.photoName);
		Image image2 = new Image("file:"+photo2.path);
		imageView2.setImage(image2);


	}





}
