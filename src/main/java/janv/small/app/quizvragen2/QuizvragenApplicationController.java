/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package janv.small.app.quizvragen2;

import java.util.Set;

/**
 *
 * @author janv
 */
public interface QuizvragenApplicationController {

    void setStageContainer(StageContainer stageContainer);

    public void createSelection(Set<String> selectedCategories,
            Set<String> selectedTypes, Set<Integer> selectedStatus);

    public void showSelectionView();
    
    public void showStartupView();

    public void updateStatus(Integer currentQuestionId, boolean improve);

    public void onSelectFile();
    
}
