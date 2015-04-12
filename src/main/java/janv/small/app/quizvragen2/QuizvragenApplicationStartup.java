package janv.small.app.quizvragen2;

import janv.small.app.quizvragen2.domain.QuizvragenViewPresenter;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class QuizvragenApplicationStartup extends Application
implements StageContainer {
    
    private Stage stage;
    
    private Map<QuizvragenViewPresenter, Scene> scenes = new HashMap<QuizvragenViewPresenter, Scene>(); 
    
    private final FileChooser fileChooser = new FileChooser();

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Quizvragen.getInstance().getQuizvragenApplicationController().setStageContainer(this);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                Quizvragen.getInstance().getQuizvragenApplicationController().saveBuffer();
            }
        });
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public QuizvragenViewPresenter createScene(String url) {
        QuizvragenViewPresenter presenter = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = (Parent) fxmlLoader.load(getClass().getResource(url).openStream());
            presenter = (QuizvragenViewPresenter) fxmlLoader.getController();

            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            scenes.put(presenter, scene);
        } catch (IOException ex) {
            Logger.getLogger(QuizvragenApplicationStartup.class.getName()).log(Level.SEVERE, null, ex);
        }
        return presenter;
    }
    
    @Override
    public void showScene(String title, QuizvragenViewPresenter presenter) {
        if (scenes.containsKey(presenter)) {
            stage.setTitle(title);
            stage.setScene(scenes.get(presenter));
            stage.show();
        }
    }
    
    @Override
    public File onSelectFile() {
        return fileChooser.showOpenDialog(stage);
    }
}
