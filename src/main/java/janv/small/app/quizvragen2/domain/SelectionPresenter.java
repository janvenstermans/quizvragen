/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package janv.small.app.quizvragen2.domain;

import java.util.List;
import java.util.Set;

/**
 *
 * @author Jan Venstermans
 */
public interface SelectionPresenter {

    public QuizvragenViewPresenter getView();
    
    public interface View extends QuizvragenViewPresenter {

        void setHandler(Handler selectionHandler);
        
        void setCategoryChoice(List<String> categories);
        
        void setTypeChoice(List<String> types);
       
        void setStatusChoice(List<Integer> statusses);
    }
    
    public interface Handler {

        public void onCreateSelection(Set<String> selectedCategories, 
                Set<String> selectedTypes, Set<Integer> selectedStatus);

    }

    void setCategoryChoice(Set<String> categories);
    
    void setTypeChoice(Set<String> types);

    void setStatusChoice(Set<Integer> statusses);
}
