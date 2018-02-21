import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;


public class Main extends Application {

    public static void main(String[] args) {
        try {
            launch(args);
        }
        catch (Exception err) {
            System.err.println(err.getMessage());
            System.out.println("Sorry, there was an error in the main method.");
        }
    }
    @Override
    public void init() throws Exception {}
    @Override
    public void start(Stage window) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));

        //Load Setup e.g. preferences, ... 
        ControllerMain.getInstance().viewWarningBar();
        ControllerMain.getInstance().start();

        window.setTitle("LittleText");
        //window.initStyle(StageStyle.UTILITY);
        window.centerOnScreen();
        window.getIcons().add(new Image("/icons/littletext.png"));
        window.setScene(new Scene(root, 800, 600));
        window.show();
    }
}
