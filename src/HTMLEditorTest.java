import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;


public class HTMLEditorTest extends Application {

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
    public void start(Stage window) throws Exception{

        HTMLEditor editor = new HTMLEditor();
        VBox vBox = new VBox();
        vBox.getChildren().add(editor);

        Scene scene = new Scene(vBox, 1000, 700);


        window.setTitle("HTML Editor Test");
        //window.initStyle(StageStyle.UTILITY);
        window.centerOnScreen();
        window.setScene(scene);
        window.show();
    }
}
