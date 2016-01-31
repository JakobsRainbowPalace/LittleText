//package littletext;

import javafx.scene.control.TextArea;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class LtEditMenu {
	
	Menu editMenu;
	TextArea textArea;

	// Constructor
	public LtEditMenu(TextArea textArea){
	
		this.editMenu = new Menu("Edit");

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

		editMenu.getItems().addAll(undoAction, redoAction, copyData, cutData, pasteData);
	}

	public Menu getEditMenu() {

		return this.editMenu;
	}
	
}
