import javafx.scene.control.Tab;
import javafx.scene.shape.Rectangle;

public class LtTab {
	
	private Tab tab;

	// Constructor
	public LtTab(String name){
		
		tab = new Tab();
		tab.setText(name);
		tab.setContent(new Rectangle());
	}

	public Tab getTab() {

		return this.tab;
	}
	
}
