/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excel;

/**
 *
 * @author efrai
 */
import java.io.*;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.WorksheetCollection;

public class ExcelWorkbook {
    
    private String filePath = null;
    private Exception error = null;
    private Workbook workbook = null;
    private FileInputStream stream = null;
    
    public ExcelWorkbook(){}
    
    public ExcelWorkbook(String filePath){
        try {
            this.filePath = filePath;
            this.stream = new FileInputStream(filePath);
            this.workbook = new Workbook(this.stream);
        }
        catch (Exception ex) {
            this.error = ex;
            System.out.println("- File couldn't be processed");
        }
    }   
    
    public Workbook getWorkbook(){
        return this.workbook;
    }
    
    public String getFilePath(){
        return this.filePath;
    }
    
    public void addSheet(String sheetName){
        int sheetNumber = this.workbook.getWorksheets().add();
        this.workbook.getWorksheets().get(sheetNumber).setName(sheetName);
    }
    
    public void copySheetsFromWorkbook(String workbookName, WorksheetCollection sheets){
        
        for (int i = 0; i < sheets.getCount(); i++) {
            Worksheet tempSheet = sheets.get(i);
            int sheetNumber = this.workbook.getWorksheets().add();
            this.workbook.getWorksheets().get(sheetNumber).setName(sheetNumber+"-"+workbookName+"-"+tempSheet.getName());
            try {
                this.workbook.getWorksheets().get(sheetNumber).copy(tempSheet);
            } catch (Exception ex) {
                System.out.println("- File couldn't be processed");
                this.error = ex;
            }
        }
    }
    
    
    public void saveFile(){
        try {
            this.workbook.save(this.filePath);
            this.stream.close();
        } catch (Exception ex) {
            this.error = ex;
            System.out.println("- File couldn't be processed");
        }
    }
    
    public void closeStream(){
        try {
            this.stream.close();
        } catch (IOException ex) {
            this.error = ex;
        }
    }
    
    public Exception getError(){
        return this.error;
    }
}
