/**
 * The controller class for littletext.fxml
 */
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

import javafx.scene.control.Tab;
import javafx.geometry.Side;

import java.io.*;

import javafx.stage.FileChooser;
import javafx.scene.Node;
import javafx.stage.Window;
import javafx.scene.Parent;

import java.util.List;

import javafx.stage.Stage;


public class ControllerMain
{
    @FXML // fx:id="vBox"
    private VBox vBox; // Value injected by FXMLLoader
    @FXML // fx:id="menuBar"
    private MenuBar menuBar; // Value injected by FXMLLoader
    @FXML // fx:id="warningBar"
    private HBox warningBar; // Value injected by FXMLLoader
    @FXML // fx:id="treeView"
    private TreeView<?> treeView; // Value injected by FXMLLoader
    @FXML // fx:id="tabPane"
    private TabPane tabPane; // Value injected by FXMLLoader
    @FXML // fx:id="statusBar"
    private HBox statusBar; // Value injected by FXMLLoader
    @FXML // fx:id="leftStatus"
    private Label leftStatus; // Value injected by FXMLLoader
    @FXML // fx:id="rightStatus"
    private Label rightStatus; // Value injected by FXMLLoader
    @FXML // fx:id="x41"
    private Color x41; // Value injected by FXMLLoader
    @FXML // fx:id="x31"
    private Font x31; // Value injected by FXMLLoader
    @FXML // fx:id="x3"
    private Font x3; // Value injected by FXMLLoader
    @FXML // fx:id="x4"
    private Color x4; // Value injected by FXMLLoader

    private ArrayList<String> recentList = new ArrayList<>();
    private Tab currentSelection;
    private static ControllerMain instance;
    private String tempFilePath = ".littletext.temp";

    //Constructor
    public ControllerMain()
    {
        instance = this;
    }

    public static ControllerMain getInstance()
    {
        return instance;
    }

    public TabPane getTabPane()
    {
        return tabPane;
    }

    public void start()
    {
        try
        {

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tempFilePath));
            TabPane tempFile = (TabPane) ois.readObject();
        }
        catch (Exception err)
        {
            System.err.println(err.getMessage());
        }
    }

    //Menu File
    @FXML
    void newFile(ActionEvent event)
    {

        Tab tab = new Tab("Untitled");
        tab.setContent(new TextArea());
        tab.setOnSelectionChanged(e ->
        {
            currentSelection = tabPane.getSelectionModel().getSelectedItem();
        });

        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);
    }

    /**
     * creats and initalizes a FileChooser to open a File
     * takes the File and calls openFile()
     */
    @FXML
    void openFile(ActionEvent event)
    {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");

        File file = chooser.showOpenDialog(vBox.getScene().getWindow());
        openFile(file);
    }

    /**
     * creats and initalizes a FileChooser to open multiple Files
     * takes the File List and calls openFile() for each item
     */
    @FXML
    void openMultiple(ActionEvent event)
    {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Multiple Files");

        List<File> fileList = chooser.showOpenMultipleDialog(vBox.getScene().getWindow());
        for (File file : fileList)
        {
            openFile(file);
        }
    }

    /**
     * opens a File and reads it's data to a TextArea
     * the textArea is set as content of a tab
     * the tab is added to the TabPane and then selected
     */
    void openFile(File input)
    {

        if (input != null)
        {
            String inputText = "";
            String line = null;

            try
            {
                FileReader reader = new FileReader(input);
                BufferedReader bufferedReader = new BufferedReader(reader);

                while ((line = bufferedReader.readLine()) != null)
                {
                    inputText += line + "\n";
                }
                TextArea textArea = new TextArea();
                textArea.appendText(inputText);

                Tab tab = new Tab(input.getName());
                tab.setContent(textArea);
                tab.setOnSelectionChanged(e ->
                {
                    currentSelection = tabPane.getSelectionModel().getSelectedItem();
                });

                tabPane.getTabs().add(tab);
                tabPane.getSelectionModel().select(tab);
            }
            catch (Exception err)
            {
                System.err.println(err.getMessage());
            }
        }
    }

    @FXML
    void clearRecent(ActionEvent event)
    {
    }

    @FXML
    void close(ActionEvent event)
    {
        tabPane.getTabs().remove(currentSelection);
        currentSelection = tabPane.getSelectionModel().getSelectedItem();
    }

    /**
     * calls saveTextFile(currentSelection)
     * if the tab id is null, calls saveAs(ActionEvent e)
     */
    @FXML
    void save(ActionEvent event)
    {

        if (tabPane.getTabs().isEmpty())
        {
        } else if (currentSelection.getId() != null)
        {
            saveTextFile(currentSelection);
        } else
        {
            saveAs(null);
        }
    }

    /**
     * let's the user choose a path
     * saves path in the tab id of currentSelection
     * calls save(ActionEvent e)
     */
    @FXML
    void saveAs(ActionEvent event)
    {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save As");

        File file = fileChooser.showSaveDialog(vBox.getScene().getWindow());
        if (file != null)
        {
            currentSelection.setId(file.getAbsolutePath());
            save(null);
        }
    }

    /*
        * get's a tab
        * examines the tab id (memory adress)
        * examines the tab content and writes it to a textfile
        * saves the textfile at the id (memory adress)
    */
    void saveTextFile(Tab tab)
    {

        TextArea textArea = (TextArea) tab.getContent();
        String[] output = textArea.getText().split("\n");

        try
        {
            FileWriter writer = new FileWriter(tab.getId());
            BufferedWriter bWriter = new BufferedWriter(writer);

            for (String i : output)
            {
                bWriter.write(i);
                bWriter.newLine();
            }
            bWriter.close();
        }
        catch (Exception err)
        {
            System.err.println(err.getMessage());
            leftStatus.setText("Sorry, your file could not be saved.");
        }
    }

    @FXML
    void revertToSaved(ActionEvent event)
    {
    }

    @FXML
    void quit(ActionEvent event)
    {

        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tempFilePath));
            oos.writeObject(tabPane);
            oos.flush();
        }
        catch (Exception err)
        {
            System.err.println(err.getMessage());
        }
        Stage stage = (Stage) vBox.getScene().getWindow();
        stage.close();
    }

    //Menu Preferences
    @FXML
    void setSyncPref(ActionEvent event)
    {
    }

    @FXML
    void setShortcutPref(ActionEvent event)
    {
    }

    @FXML
    void disablePack(ActionEvent event)
    {
    }

    @FXML
    void installPack(ActionEvent event)
    {
    }

    @FXML
    void uninstallPack(ActionEvent event)
    {
    }

    @FXML
    void enablePack(ActionEvent event)
    {
    }

    @FXML
    void updatePack(ActionEvent event)
    {
    }

    //Menu Edit
    @FXML
    void editUndo(ActionEvent event)
    {
    }

    @FXML
    void editRedo(ActionEvent event)
    {
    }

    @FXML
    void editCut(ActionEvent event)
    {
        if (!tabPane.getTabs().isEmpty())
        {
            TextArea textArea = (TextArea) currentSelection.getContent();
            textArea.cut();
        }
    }

    @FXML
    void editCopy(ActionEvent event)
    {
        if (!tabPane.getTabs().isEmpty())
        {
            TextArea textArea = (TextArea) currentSelection.getContent();
            textArea.copy();
        }
    }

    @FXML
    void editPaste(ActionEvent event)
    {
        if (!tabPane.getTabs().isEmpty())
        {
            TextArea textArea = (TextArea) currentSelection.getContent();
            textArea.paste();
        }
    }

    @FXML
    void editDelete(ActionEvent event)
    {
        if (!tabPane.getTabs().isEmpty())
        {
            TextArea textArea = (TextArea) currentSelection.getContent();
            textArea.deleteNextChar();
        }
    }

    @FXML
    void editSelectAll(ActionEvent event)
    {
        if (!tabPane.getTabs().isEmpty())
        {
            TextArea textArea = (TextArea) currentSelection.getContent();
            textArea.selectAll();
        }
    }

    @FXML
    void editDeselect(ActionEvent event)
    {
        if (!tabPane.getTabs().isEmpty())
        {
            TextArea textArea = (TextArea) currentSelection.getContent();
            textArea.deselect();
        }
    }

    //Menu View
    @FXML
    void viewMenuBar(ActionEvent event)
    {
        if (menuBar.isVisible())
        {
            menuBar.setVisible(false);
            menuBar.setManaged(false);
        } else
        {
            menuBar.setVisible(true);
            menuBar.setManaged(true);
        }
    }

    @FXML
    void viewTabPane(ActionEvent event)
    {
        if (tabPane.isVisible())
        {
            tabPane.setVisible(false);
            tabPane.setManaged(false);
        } else
        {
            tabPane.setVisible(true);
            tabPane.setManaged(true);
        }
    }

    @FXML
    void viewTreeView(ActionEvent event)
    {
        if (treeView.isVisible())
        {
            treeView.setVisible(false);
            treeView.setManaged(false);
        } else
        {
            treeView.setVisible(true);
            treeView.setManaged(true);
        }
    }

    @FXML
    void viewStatusBar(ActionEvent event)
    {
        if (statusBar.isVisible())
        {
            statusBar.setVisible(false);
            statusBar.setManaged(false);
        } else
        {
            statusBar.setVisible(true);
            statusBar.setManaged(true);
        }
    }

    @FXML
    void viewFullscreen(ActionEvent event)
    {

        Stage stage = (Stage) vBox.getScene().getWindow();
        if (stage.isFullScreen())
        {
            stage.setFullScreen(false);
        } else
        {
            stage.setFullScreen(true);
        }
    }

    public void viewWarningBar()
    {
        if (warningBar.isVisible())
        {
            warningBar.setVisible(false);
            warningBar.setManaged(false);
        } else
        {
            warningBar.setVisible(true);
            warningBar.setManaged(true);
        }
    }

    //Menu Help
    @FXML
    void helpSubmitFeedback(ActionEvent event)
    {
    }

    @FXML
    void helpIntroduce(ActionEvent event)
    {
    }

    @FXML
    void helpReportBug(ActionEvent event)
    {
    }

    @FXML
    void helpAbout(ActionEvent event)
    {
    }
}
