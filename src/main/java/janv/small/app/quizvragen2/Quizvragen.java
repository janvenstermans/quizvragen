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
    
    //public String fileName = "C:\\Users\\janv\\Documents\\AlgemeneInfo.xlsx";
    private static String fileName;
    
    private QuizvragenApplicationController quizvragenApplicationController;
    
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

    public static String getFileName() {
        return fileName;
    }

    public static void setFileName(String fileName) {
        Quizvragen.fileName = fileName;
    }
    

    
}
