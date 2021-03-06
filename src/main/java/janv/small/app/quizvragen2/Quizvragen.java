/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package janv.small.app.quizvragen2;

/**
 *
 * @author janv
 */
public class Quizvragen {
    
    private String fileName;
    
    private QuizvragenApplicationController quizvragenApplicationController;
    
    private QuizvragenProperties quizvragenProperties 
            = new QuizvragenPropertiesImpl();
    
    private static Quizvragen application = new Quizvragen();

    private Quizvragen() {
    }

    public static Quizvragen getInstance() {
        return application;
    }

    public QuizvragenApplicationController getQuizvragenApplicationController() {
        if (quizvragenApplicationController == null) {
            quizvragenApplicationController = new QuizvragenApplicationControllerImpl();
        } 
        return quizvragenApplicationController;
    }
    
    public QuizvragenProperties getQuizvragenProperties() {
        return quizvragenProperties;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    

    
}
