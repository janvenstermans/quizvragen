/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package janv.small.app.quizvragen2.domain;

import janv.small.app.quizvragen2.Quizvragen;
import janv.small.app.quizvragen2.QuizvragenApplicationController;

/**
 *
 * @author Jan Venstermans
 */
public class StartupPresenterImpl implements StartupPresenter, 
        StartupPresenter.Handler {
    
    private final QuizvragenApplicationController applicationController;

    private View view;

    public StartupPresenterImpl(QuizvragenApplicationController applicationController,
            StartupPresenter.View view) {
        this.applicationController = applicationController;
        this.view = view;
        view.setHandler(this);
    }

    @Override
    public QuizvragenViewPresenter getView() {
        return view;
    }

    @Override
    public void onSelectFile() {
        applicationController.onSelectFile();
        view.setFileName(Quizvragen.getInstance().getFileName());
    }

    @Override
    public void onLoadFile() {
        applicationController.onLoadFileContent();
    }

    @Override
    public void setFileName(String fileName) {
        view.setFileName(fileName);
    }
}
