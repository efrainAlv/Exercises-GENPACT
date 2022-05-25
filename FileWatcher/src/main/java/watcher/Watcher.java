/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package watcher;

import com.aspose.cells.Workbook;
import excel.ExcelWorkbook;
import java.io.File;
import java.io.IOException;
import static java.nio.file.StandardWatchEventKinds.*;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author efrai
 */
public class Watcher {
    
    private Path path=null;
    private WatchService service=null;
    private WatchKey key=null;
    private Exception error = null;
    private String pathDirectory = null;
    
    public Watcher(){}
    
    public Watcher(String pathDirectory){
        this.pathDirectory = pathDirectory;
    }
    
    public void initWatcher() throws InterruptedException, IOException{

        this.key = this.service.take();
        
        for(;;){
            
            for(WatchEvent<?> event: this.key.pollEvents()){
                WatchEvent.Kind<?> kind = event.kind();
                if (kind == OVERFLOW) {
                    continue;
                }

                System.out.println("--- New file detected: " + event.context() + ".");
                if(this.processFile(event.context().toString())){
                    System.out.println("- File processed successfully: " + event.context() + ".");
                    Path temp = Files.move(Paths.get(this.pathDirectory+"\\"+event.context().toString()), Paths.get(this.pathDirectory+"\\Processed\\"+event.context().toString()));
                }
                else{
                    System.out.println("- File couldn't be processed or was not applicable: " + event.context() + ".\n");
                    Path temp = Files.move(Paths.get(this.pathDirectory+"\\"+event.context().toString()), Paths.get(this.pathDirectory+"\\NotApplicable\\"+event.context().toString()));
                }
            }
        }
    }

    public void setPath() throws IOException {
        this.path = Paths.get(this.pathDirectory);
        this.path.register(this.service, ENTRY_CREATE);
    }

    public void setService() throws IOException {
        this.service = FileSystems.getDefault().newWatchService();
    }

    public void setKey() throws InterruptedException {
        this.key = this.service.take();
    }
        
    public String getError(){
        return "Error: "+ this.error;
    }
    
    public boolean prepareEnviroment(){
        
        File mF = new File(this.pathDirectory+"\\Master.xlsx");
        File p = new File(this.pathDirectory+"\\Processed");
        File np = new File(this.pathDirectory+"\\NotApplicable");
        
        try {
            if (!mF.exists()){
                Workbook wb = new Workbook();
                wb.save(this.pathDirectory+"\\Master.xlsx");
            }
            if (!p.exists()){
                p.mkdirs();
            }
            if (!np.exists()){
                np.mkdirs();
            }
            return true;
        } 
        catch (Exception ex) {
            return false;
        }
    }

    private boolean processFile(String workbookName){
        
        String []temp = workbookName.split("\\.");
        
        if("xlsx".equals(temp[temp.length-1])|| "xls".equals(temp[temp.length-1])){
            
            try{
                //MASTER FILE
                ExcelWorkbook masterFile = new ExcelWorkbook(this.pathDirectory+"\\Master.xlsx");

                //SLAVE FILE
                ExcelWorkbook slaveFile = new ExcelWorkbook(this.pathDirectory+"\\"+workbookName);

                //COPYING SLAVE SHEETS INTO MASTER WORKBOOK
                masterFile.copySheetsFromWorkbook(workbookName, slaveFile.getWorkbook().getWorksheets());

                masterFile.saveFile();
                slaveFile.closeStream();
                return true;
            }
            catch(Exception e){
                System.out.println("\n Either the file does not exists or the Watcher was interrupted.");
                System.out.println("Please do not open the master file while the watcher is running. \n");
                return false;
            }
        }
        else{
            return false;
        }
    }
    
    
}
