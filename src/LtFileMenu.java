//package littletext;

import java.io.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LtFileMenu {

	Menu fileMenu;
	TextArea textArea;
	Stage window;
	
	public LtFileMenu(Stage window, TextArea textArea) {

		this.fileMenu = new Menu("File");
		this.textArea = textArea;


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
		
		// add items to the menu
		fileMenu.getItems().addAll(newFile, openFile, saveFile, saveFileAs);
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

	public Menu getFileMenu() {

		return this.fileMenu;
	}
}
