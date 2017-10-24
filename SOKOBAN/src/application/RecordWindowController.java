package application;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.hibernate.SessionFactory;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;
import model.data.Level;
import model.dataBase.Dbmaneger;
import model.dataBase.Record;
import model.dataBase.User;

/**
 * 
 * 
 * @author eylon ben david
 * 
 *
 */
public class RecordWindowController implements Initializable {
	
	private Dbmaneger db;
	
	@FXML
	private TableView<Record> table;
	@FXML
	private TextField userSearch;
	@FXML
	private TextField levelSearch;
	
	private ObservableList<Record> list;
	private Stage recordstage;
	private String userName;
	private User user;
	private Record record;
	private String searchName;
	
	
	
	public RecordWindowController() {
		// TODO Auto-generated constructor stub
		
		db=new Dbmaneger();
		recordstage=new Stage();
		user=null;
		record=null;
		
	}
	
	
	
	
	// add user game results to world record table.
	public void insertDetails(Level level,int time){
		
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				

				TextInputDialog dialog1 = new TextInputDialog("UserName");
				dialog1.setTitle("connection");
				dialog1.setContentText("Please enter your username:");

				Optional<String> res = dialog1.showAndWait();

				res.ifPresent(name ->{
					userName=name;
				});
				
				
				user=db.checkIfUserExist(userName);
				if(user==null){
			
					Dialog<Pair<String, String>> dialog = new Dialog<>();
					dialog.setTitle("Registration");

					// Set the icon (must be included in the project).
					//dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

					// Set the button types.
					ButtonType addButtonType = new ButtonType("add", ButtonData.OK_DONE);
					dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

					// Create the username  labels and fields.
					GridPane grid = new GridPane();
					grid.setHgap(10);
					grid.setVgap(10);
					grid.setPadding(new Insets(20, 150, 10, 10));

					TextField firstname = new TextField();
					firstname.setPromptText("First Name");
					TextField lastname = new TextField();
					lastname.setPromptText("Last Name");

					grid.add(new Label("First Name:"), 0, 0);
					grid.add(firstname, 1, 0);
					grid.add(new Label("Last Name:"), 0, 1);
					grid.add(lastname, 1, 1);

					dialog.getDialogPane().setContent(grid);



					// Convert the result to a firstname-lastname-pair when the add button is clicked.
					dialog.setResultConverter(dialogButton -> {
						if (dialogButton == addButtonType) {
							return new Pair<>(firstname.getText(), lastname.getText());
						}
						return null;
					});

					Optional<Pair<String, String>> result = dialog.showAndWait();

					result.ifPresent(name -> {
			
						user=new User(name.getKey(), name.getValue(),userName);
						db.addUser(user);
					});   
				}
				
				record=new Record(level.getCountSteps(), level.getLevelName(),time, user);
				db.addRecord(record);
				
			}

		});

	}




	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
 
		list=FXCollections.observableArrayList();
		table.setEditable(true);
		
		
		
		// username column
		TableColumn<Record,User> uNameCol=new TableColumn<>("UserName");
		uNameCol.setMinWidth(100);
		uNameCol.setCellValueFactory(new PropertyValueFactory<>("user"));
		
		

		// step column
		TableColumn<Record,Integer> stepscol=new TableColumn<>("steps");
		stepscol.setMinWidth(80);
		stepscol.setCellValueFactory(new PropertyValueFactory<>("steps"));
		
		// time column
		TableColumn<Record,Float> timecol=new TableColumn<>("time");
		timecol.setMinWidth(80);
		timecol.setCellValueFactory(new PropertyValueFactory<>("time"));

		
		// level name column
		TableColumn<Record,String> levelcol=new TableColumn<>("Level Name");
		levelcol.setMinWidth(100);
		levelcol.setCellValueFactory(new PropertyValueFactory<>("levelName"));

		
		table.getColumns().addAll(levelcol,uNameCol,stepscol,timecol);
		
		table.setItems(list);
		
		
		// open in a new window record table of some user.
		table.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				
				Record r=table.getSelectionModel().getSelectedItem();
				if(r!=null)
					displayUserRecord(r);
				
			}
		});
		
		//search user name 
		userSearch.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				displayAccordingUserSearch(newValue);	
			}
		});
		
		//search level name
		levelSearch.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				displayAccordingLevelName(newValue);
				
			}
		});
		
		

	}
	
	
	public void displayRecords(){
		
		list.clear();
		recordstage.show();
		  list.addAll(db.selectAllRecords());
		
	}
	
	public void displayUserRecord(Record record){
		
		list.clear();
		List<Record> userRecordList=record.getUser().getRecordlist();
		
		list.addAll(userRecordList);
		
	}
	
	public void displayAccordingUserSearch(String newval){
		
		list.clear();
		list.addAll(db.displayAllRecordsAccordingUserName(newval));
		
	}
	
	public void displayAccordingLevelName(String levelname){
		
		list.clear();
		list.addAll(db.displayRecordsAccordingLevelName(levelname));
		
	}


	public Stage getRecordstage() {
		return recordstage;
	}




	public void setRecordstage(Stage recordstage) {
		this.recordstage = recordstage;
		
	}
	
	
	
	
}
