package application;
	

import controller.MySokobanController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.MyModel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

/**
 * 
 * 
 * @author eylon ben david
 * main class - start the game
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			// main window sokoban game
			FXMLLoader fxl=new FXMLLoader();
			BorderPane root = fxl.load(getClass().getResource("MainWindow.fxml").openStream());
			
			// Records Table Window Loading
			FXMLLoader rtw = new FXMLLoader();
			BorderPane recordsTableWindowroot = (BorderPane)rtw.load(getClass().getResource("RecordWindow.fxml").openStream());
			
			MainWindowController mwc=fxl.getController();
			RecordWindowController rwc = (RecordWindowController)rtw.getController();

			
			MyModel m=new MyModel();
			MySokobanController c=new MySokobanController(m, mwc);
			
			m.addObserver(c);
			mwc.addObserver(c);
			mwc.setRwc(rwc);
			
			Scene scene = new Scene(root,900,900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			// Record Table Window
			Stage recordsTableWindowStage = new Stage();

			Scene recordsTableWindowScene = new Scene(recordsTableWindowroot); // Scene scene = new Scene(root,900,800); 
			recordsTableWindowScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			recordsTableWindowStage.setScene(recordsTableWindowScene);
			
			
			rwc.setRecordstage(recordsTableWindowStage);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
