//package littletext;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class LtHelpMenu {
	
	Menu helpMenu;

	// Constructor
	public LtHelpMenu(){

		this.helpMenu = new Menu("Help");

		// menu items helpMenu
		MenuItem documentation = new MenuItem("Documentation");
		MenuItem about = new MenuItem("About LittleText");
		MenuItem credits = new MenuItem("Credits");
		//actions
		documentation.setOnAction(e -> System.out.println(""));
		about.setOnAction(e -> System.out.println(""));
		credits.setOnAction(e -> System.out.println("A small texteditor programmed in Java\nby Jakob Rieke"));	
		
		helpMenu.getItems().addAll(documentation, about, credits);
	}
	
	public Menu getHelpMenu() {

		return this.helpMenu;
	}
}
