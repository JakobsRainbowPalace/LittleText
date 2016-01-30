import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import java.io.*;


public class LtWindow extends Application {
	
	Stage window;
	TextArea textArea;
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) {

		window = primaryStage;
		window.setTitle("LittleText");

		textArea = new TextArea();
		
		// menu items fileMenu
		MenuItem newFile = new MenuItem("New");
		MenuItem openFile = new MenuItem("Open...");
		MenuItem saveFile = new MenuItem("Save");
		MenuItem saveFileAs = new MenuItem("Save As...");
		// actions --> defined below
		newFile.setOnAction(e -> newFileDo());
		openFile.setOnAction(e -> openFileDo());
		saveFile.setOnAction(e -> saveFileDo("TestFile.txt"));
		saveFileAs.setOnAction(e -> saveFileAsDo());
		
		// menu items editMenu
		MenuItem undoAction = new MenuItem("Undo");
		MenuItem redoAction = new MenuItem("Redo");
		MenuItem copyData = new MenuItem("Copy");
		MenuItem cutData = new MenuItem("Cut");
		MenuItem pasteData = new MenuItem("Paste");
		// actions
		undoAction.setOnAction(e -> System.out.println("undoooooing!"));
		redoAction.setOnAction(e -> System.out.println("redoing :("));
		copyData.setOnAction(e -> System.out.println("cooooopiiiing"));
		cutData.setOnAction(e -> System.out.println("cuting :("));
		pasteData.setOnAction(e -> System.out.println("pasting!"));

		// menu items helpMenu
		MenuItem documentation = new MenuItem("Documentation");
		MenuItem about = new MenuItem("About LittleText");
		MenuItem credits = new MenuItem("Credits");
		//actions
		documentation.setOnAction(e -> System.out.println(""));
		about.setOnAction(e -> System.out.println(""));
		credits.setOnAction(e -> System.out.println("A small texteditor programmed in Java\nby Jakob Rieke"));

		// menu bar entries
		Menu fileMenu = new Menu("File");
		Menu editMenu = new Menu("Edit");
		Menu helpMenu = new Menu("Help");
		// add items to menu bar entries
		fileMenu.getItems().addAll(newFile, openFile, saveFile, saveFileAs);
		editMenu.getItems().addAll(undoAction, redoAction, copyData, cutData, pasteData);
		helpMenu.getItems().addAll(documentation, about, credits);
		
		// menu bar
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);
		
		// layout
	    VBox layout = new VBox();
	    VBox.setVgrow(textArea, Priority.ALWAYS);
		layout.getChildren().addAll(menuBar,textArea);

		Scene scene = new Scene(layout, 600, 500);
		
		window.setScene(scene);
		window.show();

	}
	
	private String newFileDo() {

		return "New file created succesfully.";
	}

	private String openFileDo() {

		// let the user choose a file to open
		FileChooser fChooser = new FileChooser();
		fChooser.setTitle("Open File");
		File inputFile = fChooser.showOpenDialog(window);

		// read the choosen file to String
		if (inputFile != null) {
			
			String outputText = "";
			String line = null;
			
			try {
					
				FileReader reader = new FileReader(inputFile);
				BufferedReader bufferedReader = new BufferedReader(reader);

				while ((line = bufferedReader.readLine()) != null) {
					
					outputText += line + "\n";
				}

				textArea.clear();
				textArea.appendText(outputText);
			}
			
			catch (Exception err) {
				System.err.println(err.getMessage());
			}

			return "File was opened successuflly!";
		}

		else {

			return "No file was choosen.";
		}
	}

	private String saveFileDo(String filePath) {

		String[] output = textArea.getText().split("\n");
		File file = new File(filePath);
		FileWriter writer = null;
		BufferedWriter bWriter = null;

		try {
			
			writer = new FileWriter(file);
			bWriter = new BufferedWriter(writer);
			
			for(String i : output) {

				bWriter.write(i);
				bWriter.newLine();
			}
		}
		
		catch (Exception err) {
			System.err.println(err.getMessage());
		}

		finally {
			try {

				bWriter.close();
			}
			
			catch (Exception err) {
				System.err.println(err.getMessage());
			}
		}
		
		return "File saved.";		
	}

	private String saveFileAsDo() {

		FileChooser fChooser = new FileChooser();
		fChooser.setTitle("Save File As");
		String filePath = fChooser.showSaveDialog(window).getAbsolutePath();
		
		if (filePath != null) {
			
			saveFileDo(filePath);
			return "File was saved succesfully!";
		}
		else {

			return "No file was saved.";
		}

	}
}
