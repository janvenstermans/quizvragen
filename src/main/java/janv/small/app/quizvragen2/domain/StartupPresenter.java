/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package janv.small.app.quizvragen2.domain;

import janv.small.app.quizvragen2.view.StartupViewController;

/**
 *
 * @author Jan Venstermans
 */
public interface StartupPresenter {

    public QuizvragenViewPresenter getView();
    
    public interface View extends QuizvragenViewPresenter {

        void setHandler(Handler startupHandler);
        
        void setFileName(String fileName);
        
    }
    
    public interface Handler {

        public void onSelectFile();
        
        public void onLoadFile();

    }
}
