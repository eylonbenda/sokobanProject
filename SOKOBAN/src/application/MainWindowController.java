package application;

import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.data.Level;
import model.dataBase.Record;
import view.View;


/**
 * 
 * 
 * @author eylon ben david
 * 
 *
 */
public class MainWindowController extends Observable implements View, Initializable {


	private Level level = null;
	private Stage stage;
	private String command;
	private int timerCounter = 0;
	private Timer t;
	private RecordWindowController rwc;
	private String fileName;

	@FXML
	private SokobanDisplayer sokoDisplayer;

	@FXML
	Text stepCount;

	@FXML
	Text timer;

	@FXML
	TableView<Record> recordstable;

	// music members
	private String mp3path = "./resorces/sia.mp3";
	private Media mp3 = new Media(new File(mp3path).toURI().toString());
	private MediaPlayer player = new MediaPlayer(mp3);

	public MainWindowController() {
		this.level = new Level();
	}

	public void setCommand(String command) {
		this.command = command;

		String[] arr = command.split(" ");
		List<String> params = new LinkedList<String>();

		for (String s : arr) {
			params.add(s);
		}

		setChanged();
		notifyObservers(params);
	}

	public void saveFile() {

		if (level != null) {

			FileChooser fc = new FileChooser();
			fc.setTitle("save sokoban file");
			fc.setInitialDirectory(new File("./resorces"));

			fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files", "*.txt"));
			fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files", "*.xml"));
			fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Object files", "*.obj"));

			File chosen = fc.showSaveDialog(null);

			if (chosen != null) {

				List<String> params = new LinkedList<String>();
				params.add("Save");
				params.add(chosen.getPath());

				this.setChanged();
				this.notifyObservers(params);

			}
		}
	}

	public void openFile() {

		FileChooser fc = new FileChooser();
		fc.setTitle("open sokoban file");
		fc.setInitialDirectory(new File("./levels"));

		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files", "*.txt"));
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files", "*.xml"));
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Object files", "*.obj"));

		File f = fc.showOpenDialog(null);
		if (f != null) {

			List<String> params = new LinkedList<>();
			params.add("Load");
			params.add(f.getPath());

			setTimerCounter(0);
			startTimer();
			player.play();

			// getting level name
			 fileName = f.getPath();
			 
			 int index1 = fileName.indexOf(".");
			int index2 = fileName.indexOf("level");
			String name = fileName.substring(index2, index1);
			

			this.setChanged();
			this.notifyObservers(params);
			level.setLevelName(name);
			System.out.println(level.getLevelName());
			//level.setLevelName(f.getPath().substring(index2, index1));
		}
	}

	public void closeFile() {
		setCommand("Exit");
	}

	public void showRecordTable() {

		rwc.displayRecords();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		setTimerCounter(0);
		sokoDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> sokoDisplayer.requestFocus());
		sokoDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				if (level != null) {
					
					if (!level.isWin()) {

						if (event.getCode() == KeyCode.UP)
							setCommand("move up");
						if (event.getCode() == KeyCode.DOWN)
							setCommand("move down");
						if (event.getCode() == KeyCode.RIGHT)
							setCommand("move right");
						if (event.getCode() == KeyCode.LEFT)
							setCommand("move left");
						if (event.getCode() == KeyCode.ESCAPE)
							setCommand("Exit");

					}
				}

				if (event.getCode() == KeyCode.ESCAPE)
					setCommand("Exit");
			}

		});

	}

	@Override
	public void displayLevel(Level lvl) {
		
		updateNameLevel(fileName);
		
		String temp = String.valueOf(lvl.getCountSteps());
		stepCount.setText(temp);

		this.level = lvl;
		sokoDisplayer.setLevel(level);
		sokoDisplayer.redraw();

		if (lvl.isWin()) {
			t.cancel();
			player.stop();
			this.askUserInsertDetails(); // ask user if he wants to enroll the
											// record list
			sokoDisplayer.drawLevelCompleted();
			// this.level = null;

		}

	}

	public void showSolution() {
		
		this.setChanged();
		this.notifyObservers("solve");
	}

	public void startTimer() {
		t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				setTimerCounter(getTimerCounter() + 1);

			}
		}, 0, 1000);

	}

	public int getTimerCounter() {
		return timerCounter;
	}

	public void setTimerCounter(int timerCounter) {
		this.timerCounter = timerCounter;
		String temp = String.valueOf(this.timerCounter);
		timer.setText(temp);

	}

	public RecordWindowController getRwc() {
		return rwc;
	}

	public void setRwc(RecordWindowController rwc) {
		this.rwc = rwc;
	}

	public void askUserInsertDetails() { // pop up window show about record

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("record list");
				alert.setContentText("would you like to add your details to the world record list?");
				Optional<ButtonType> result = alert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.OK) {
					// add record to list records
					rwc.insertDetails(level, getTimerCounter());

				}
			}
		});

	}

	@Override
	public void displayError(String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	public void stopMusic() {
		player.stop();
	}
	
	public void updateNameLevel(String str){
		
		int index1 = str.indexOf(".");
		int index2 = str.indexOf("Level");
		 level.setLevelName(str.substring(index2, index1));
	}

}
