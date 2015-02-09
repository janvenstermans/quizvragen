package janv.small.app.quizvragen2;

import janv.small.app.quizvragen2.domain.QuizvragenViewPresenter;
import java.io.File;


public interface StageContainer {
    
    /**
     * 
     * @param url
     * @return controller
     */
    public QuizvragenViewPresenter createScene(String url);
    
    void showScene(String title, QuizvragenViewPresenter presenter);
    
    File onSelectFile();
}
