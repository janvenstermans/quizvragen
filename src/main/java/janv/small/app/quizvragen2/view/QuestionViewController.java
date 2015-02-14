package janv.small.app.quizvragen2.view;

import janv.small.app.quizvragen2.domain.QuestionPresenter;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class QuestionViewController implements Initializable, QuestionPresenter.View {
    
    @FXML
    private Button showAnswerButton;
    
    @FXML
    private Button okButton;
    
    @FXML
    private Button notOkButton;
    
    @FXML
    private Label questionLabel;
    
    @FXML
    private Label aswerLabel;
    
    @FXML
    private Label questionCount;
    
    @FXML
    private ChoiceBox incrementChoiceBox;
    
    private QuestionPresenter.Handler vragenHandler;
    
    @FXML
    private void handleShowAnswerButtonAction(ActionEvent event) {
        // toggle visibility
        setAnswerVisible(!aswerLabel.isVisible());
    }
    
    @FXML
    private void handleOkButtonAction(ActionEvent event) {
        vragenHandler.onOk(getIncrementChoiceBoxValue());
    }
    
    @FXML
    private void handleNotOkButtonAction(ActionEvent event) {
        vragenHandler.onNotOk(getIncrementChoiceBoxValue());
    }
    
    @FXML
    private void handleNewSelectionButtonAction(ActionEvent event) {
        vragenHandler.onNewSelection();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        questionLabel.setWrapText(true);
        aswerLabel.setWrapText(true);
    }  

    @Override
    public void setHandler(QuestionPresenter.Handler vragenHandler) {
        this.vragenHandler = vragenHandler;
    }
    
    @Override
    public void setAnswerVisible(boolean visible) {
        aswerLabel.setVisible(visible);
    }

    @Override
    public void setQuestionAnswer(String question, String answer) {
        questionLabel.setText(question);
        aswerLabel.setText(answer);
    }

    @Override
    public void setAllQuestionsAsked(boolean allAsked) {
        if (allAsked) {
            aswerLabel.setVisible(false);
            questionLabel.setText("alle vragen gesteld, maak een nieuwe selectie");
            setCountLabel(null);
            notOkButton.setVisible(false);
            okButton.setVisible(false);
            showAnswerButton.setVisible(false);
        } else {
            setAnswerVisible(false);
        }
    }

    @Override
    public void setCountLabel(String countLabel) {
        questionCount.setText(countLabel);
    }
    
    @Override
    public void setIncrementValues(List<Integer> incrementValues) {
        incrementChoiceBox.setItems(FXCollections.observableArrayList(incrementValues));
    }
    
    @Override
    public void setShownIncrementValue(Integer incrementValue) {
        incrementChoiceBox.setValue(incrementValue);
    }
    
    private int getIncrementChoiceBoxValue() {
        return (Integer) incrementChoiceBox.getValue();
    }
}
