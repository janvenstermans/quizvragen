package janv.small.app.quizvragen2.view;

import janv.small.app.quizvragen2.domain.StartupPresenter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class StartupViewController implements Initializable, StartupPresenter.View {
    
    @FXML
    private Button showAnswerButton;

    @FXML
    private Label title;
    
    @FXML
    private Label fileNameLabel;
    
    private StartupPresenter.Handler startupHandler;
    
    @FXML
    private void handleSelectFileButtonAction(ActionEvent event) {
        startupHandler.onSelectFile();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        title.setText("Quizvragen");
        setFileName(null);
    }  

    @Override
    public void setHandler(StartupPresenter.Handler startupHandler) {
        this.startupHandler = startupHandler;
    }

    @Override
    public void setFileName(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            fileName = "No file selected";
        }
        fileNameLabel.setText(fileName);
    }
    
    
}
