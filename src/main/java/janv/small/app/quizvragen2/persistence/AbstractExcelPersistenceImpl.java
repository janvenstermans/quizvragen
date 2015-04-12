/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package janv.small.app.quizvragen2.persistence;

import janv.small.app.quizvragen2.domain.Question;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Implementation of QuizvragenPersistenceService for Excel file.
 * @author Jan Venstermans
 */
public abstract class AbstractExcelPersistenceImpl implements 
        QuizvragenPersistenceService {

    public enum ExcelVersion {
        XLS, XLSX;
    }
    
    private File excelFile;
    private ExcelVersion version;
    
    private int buffer = 10;

    private Map<Integer, Integer> rowStatusMap = new HashMap<Integer, Integer>();

    public AbstractExcelPersistenceImpl(File excelFile, ExcelVersion version) {
       this.excelFile = excelFile;
       this.version = version;
    }

    @Override
    public Map<Integer, Question> getVragen() {
        Sheet sheet1 = getSheetFromFile(0);
        if (sheet1 != null) {
            Map<Integer, Question> vragen = new HashMap<>();
            int rows = sheet1.getPhysicalNumberOfRows();

            // don't use first row, is heading
            for (int i = 1; i < rows; i++) {
                Question vraag = getVraagFromRow(sheet1.getRow(i));
                // make sure no duplication of id
                if (vraag != null && !vragen.containsKey(vraag.getId())) {
                    vragen.put(vraag.getId(), vraag);
                }
            }
            return vragen;
        }
        return null;          
    }
    
    @Override
    public void setBufferAmountQuestions(int amountOfQuestions) {
        if (amountOfQuestions < 1) {
            buffer = 10;
        }
        buffer = amountOfQuestions;
    }

    @Override
    public void saveBuffer() {
        if (rowStatusMap.size() > 0) {
            BufferSaver bufferSaver = new BufferSaver(rowStatusMap);
            new Thread(bufferSaver).start();
            rowStatusMap.clear();
        }
    }
    
    @Override
    public void saveCategory(int rowId, int status) {
        rowStatusMap.put(rowId, status);
        if (rowStatusMap.size() >= buffer) {
           saveBuffer();
        }
    }
    
    private void saveCategoryBuffer(Map<Integer, Integer> rowStatusBuffer) {
        FileInputStream fis = null;
        Workbook wb = null;
        try {
            fis = new FileInputStream(excelFile);
            switch (version) {
                case XLS:
                    POIFSFileSystem fs = new POIFSFileSystem(fis);
                    wb = new HSSFWorkbook(fs);
                    break;
                case XLSX:
                    wb = new XSSFWorkbook(fis);
                    break;
            }
            if (wb != null) {
                Sheet sheet1 = wb.getSheetAt(0);
                if (sheet1 != null) {
                    for (Map.Entry<Integer, Integer> entry 
                            : rowStatusBuffer.entrySet()) {
                        Row row = sheet1.getRow(entry.getKey());
                        saveStatus(row, entry.getValue());
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AbstractExcelPersistenceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AbstractExcelPersistenceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
                FileOutputStream fos = new FileOutputStream(excelFile);
                wb.write(fos);
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(AbstractExcelPersistenceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private Sheet getSheetFromFile(int sheetNr) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(excelFile);
            Workbook wb = null;
            switch(version) {
                case XLS:
                    POIFSFileSystem fs = new POIFSFileSystem(fis);
                    wb = new HSSFWorkbook(fs);
                    break;
                case XLSX:
                    wb = new XSSFWorkbook(fis);
                    break;
            }
            if (wb != null) {
                return wb.getSheetAt(sheetNr);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AbstractExcelPersistenceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AbstractExcelPersistenceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(AbstractExcelPersistenceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    protected abstract Question getVraagFromRow(Row row);
    protected abstract void saveStatus(Row row, int status);
    
    private class BufferSaver implements Runnable {
        
        private Map<Integer, Integer> rowStatusBuffer;

        public BufferSaver(Map<Integer, Integer> rowStatusBuffer) {
            this.rowStatusBuffer = new HashMap<Integer, Integer>(rowStatusBuffer);
        }

        @Override
        public void run() {
            saveCategoryBuffer(rowStatusBuffer);
        }
        
    }
}
