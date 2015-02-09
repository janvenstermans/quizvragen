/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package janv.small.app.quizvragen2.domain;

import janv.small.app.quizvragen2.QuizvragenApplicationController;
import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author Jan Venstermans
 */
public class SelectionPresenterImpl implements SelectionPresenter, 
        SelectionPresenter.Handler {
    
    private final QuizvragenApplicationController applicationController;
    
    private View view;
    
    public SelectionPresenterImpl(QuizvragenApplicationController applicationController,
            View view) {
        this.applicationController = applicationController;
        this.view = view;
        view.setHandler(this);
    }

    @Override
    public void onCreateSelection(Set<String> selectedCategories,
            Set<String> selectedTypes, Set<Integer> selectedStatus) {
        applicationController.createSelection(selectedCategories, 
                selectedTypes, selectedStatus);
    }

    @Override
    public QuizvragenViewPresenter getView() {
        return view;
    }

    @Override
    public void setCategoryChoice(Set<String> categories) {
        view.setCategoryChoice(new ArrayList<String>(categories));
    }

    @Override
    public void setTypeChoice(Set<String> types) {
        view.setTypeChoice(new ArrayList<String>(types));
    }

    @Override
    public void setStatusChoice(Set<Integer> statusses) {
        view.setStatusChoice(new ArrayList<Integer>(statusses));
    }

}
