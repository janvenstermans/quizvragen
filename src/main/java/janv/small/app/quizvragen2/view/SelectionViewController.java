package janv.small.app.quizvragen2.view;

import janv.small.app.quizvragen2.domain.SelectionPresenter;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class SelectionViewController implements Initializable, SelectionPresenter.View {
    
    private SelectionPresenter.Handler selectionHandler;
    
    @FXML
    private ListView categoryList;
    
    @FXML
    private ListView typeList;
    
    @FXML
    private ListView statusList;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoryList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        typeList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        statusList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    
    @FXML
    private void handleCreateSelectionButtonAction(ActionEvent event) {
        ObservableList<String> selectedCategoryObservableList 
                = categoryList.getSelectionModel().getSelectedItems();
        Set<String> selectedCategories = new HashSet<String>(selectedCategoryObservableList); 
        
        ObservableList<String> selectedTypeObservableList
                = typeList.getSelectionModel().getSelectedItems();
        Set<String> selectedTypes = new HashSet<String>(selectedTypeObservableList);
        
        ObservableList<Integer> selectedStatusObservableList
                = statusList.getSelectionModel().getSelectedItems();
        Set<Integer> selectedStatusses = new HashSet<Integer>(selectedStatusObservableList);
        
        selectionHandler.onCreateSelection(selectedCategories, 
                selectedTypes, selectedStatusses);
    }
    
    @Override
    public void setHandler(SelectionPresenter.Handler selectionHandler) {
        this.selectionHandler = selectionHandler;
    }

    @Override
    public void setCategoryChoice(List<String> categories) {
        categoryList.setItems(FXCollections.observableArrayList(categories));
    }

    @Override
    public void setTypeChoice(List<String> types) {
        typeList.setItems(FXCollections.observableArrayList(types));
    }

    @Override
    public void setStatusChoice(List<Integer> statusses) {
        statusList.setItems(FXCollections.observableArrayList(statusses));
    }
    
}
