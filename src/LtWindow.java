import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
//import littletext.*;

public class LtWindow extends Application {
	
	private String[] args;
		
	public void start(Stage primaryStage) {

		Stage window = primaryStage;
		window.setTitle("LittleText");

		TextArea textArea = new TextArea();
		TextField tField = new TextField();

		// menu bar entries
		Menu fileMenu = new LtFileMenu(window, textArea).getFileMenu();
		Menu editMenu = new LtEditMenu(textArea).getEditMenu();
		Menu helpMenu = new LtHelpMenu().getHelpMenu();
		// menu bar
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);
/*		
		// tab pane tabs
		Tab tab1 = new LtTab("1.Tab").getTab();
		// tab pane
		TabPane tabPane = new TabPane();
		tabPane.getTabs().add(tab1);
*/
		// layout
	    VBox layout = new VBox();
	    VBox.setVgrow(textArea, Priority.ALWAYS);
		layout.getChildren().addAll(menuBar, textArea, tField);

		Scene scene = new Scene(layout, 600, 500);

		window.setScene(scene);
		window.show();
	}
}

