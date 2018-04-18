package view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PhotoOverviewController {
	@FXML
	private TableView<PhotoProperty> photoTable;

    @FXML
    private TableColumn<PhotoProperty, String> photoNameColumn;
    @FXML
    private TableColumn<PhotoProperty, String> localizationColumn;


    // Reference to the main application.
    private InterfaceController interfaceController;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PhotoOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
    	photoNameColumn.setCellValueFactory(cellData -> cellData.getValue().photoNameProperty());
    	localizationColumn.setCellValueFactory(cellData -> cellData.getValue().localizationProperty());


    	photoTable.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> showPhotoDetails(newValue));
    }

    private void showPhotoDetails(PhotoProperty photo) {


	}

	/**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(InterfaceController mainApp) {
        this.interfaceController = mainApp;

        // Add observable list data to the table
        photoTable.setItems(interfaceController.getPhotoData());
    }


}
