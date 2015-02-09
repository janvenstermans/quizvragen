/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package janv.small.app.quizvragen2.persistence;

import janv.small.app.quizvragen2.domain.CategoryUtil;
import janv.small.app.quizvragen2.domain.Question;
import java.io.File;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


/**
 * Implementation of QuizvragenPersistenceService for Excel file.
 * @author Jan Venstermans
 */
public class ExcelPersistenceRowAsIdImpl extends AbstractExcelPersistenceImpl {

    public ExcelPersistenceRowAsIdImpl(File excelFile, ExcelVersion version) {
       super(excelFile, version);
    }
    
    @Override
    protected Question getVraagFromRow(Row row) {
        if (row != null) {
            // get values from the cells
            String vraagText = getCellString(row, 0);
            String antwoordText = getCellString(row, 1);
            String categoryText = getCellString(row, 2);
            String typeText = getCellString(row, 3);
            Integer status = getCellInteger(row, 4);
            Integer id = getCellInteger(row, 5);
            if (id != null) {
                //create question
                return new Question(id, vraagText, antwoordText, 
                        CategoryUtil.toUpperCase(categoryText), 
                        CategoryUtil.toUpperCase(typeText),
                        row.getRowNum(),
                        status != null ? status : 0);
            }
            //, Integer rowId, Integer status
        }
        return null;
    }

    @Override
    protected void saveStatus(Row row, int status) {
        Cell cell = row.getCell(4);
        if (cell == null) {
            cell = row.createCell(4, Cell.CELL_TYPE_NUMERIC);
        }
        cell.setCellValue(status);
    }
    
    private String getCellString(Row row, int column) {
        Cell cell = row.getCell(column);
        if (cell != null) {
            int type = cell.getCellType();
            if (Cell.CELL_TYPE_STRING == type) {
                return cell.getStringCellValue();
            }
            if (Cell.CELL_TYPE_NUMERIC == type) {
                return cell.getNumericCellValue() + "";
            }
        }
        return null;
    }
    
    private Double getCellDouble(Row row, int column) {
        Cell cell = row.getCell(column);
        if (cell != null) {
            int type = cell.getCellType();
            if (Cell.CELL_TYPE_NUMERIC == type) {
                return cell.getNumericCellValue();
            }
        }
        return null;
    }
    
    private Integer getCellInteger(Row row, int column) {
        Cell cell = row.getCell(column);
        if (cell != null) {
            int type = cell.getCellType();
            if (Cell.CELL_TYPE_NUMERIC == type) {
                return (int) cell.getNumericCellValue();
            }
        }
        return null;
    }
}
