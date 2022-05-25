/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package filewatcher;
import watcher.*;
import excel.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author efrai
 */
public class FileWatcher {

    public static void main(String[] args) {
               
        System.out.println("Hello World!");
        
        
        ui.App mainApp = new ui.App();
        
        try {
            mainApp.startApp();
        } catch (IOException ex) {
            System.out.println("Error: "+ex);
        }
            /*
            try {
            Path temp = Files.move(Paths.get("C:\\Users\\efrai\\Desktop\\PRUEBAS\\FILES\\uno.xlsx"), Paths.get("C:\\Users\\efrai\\Desktop\\PRUEBAS\\uno.xlsx"));
            }
            catch (IOException ex) {
            Logger.getLogger(Watcher.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            
            //ui.App panel = new ui.App();
            //panel.setVisible(true);
            
            
            /*
            String filePath = "C:\\Users\\efrai\\Desktop\\PRUEBAS\\FILES\\";
            
            //MASTER FILE
            //FileInputStream streamMaster = new FileInputStream(filePath+"Master.xlsx");
            ExcelWorkbook masterFile = new ExcelWorkbook(filePath+"Master.xlsx");
            
            //SLAVE FILE
            //FileInputStream streamSlave = new FileInputStream(filePath+"uno.xlsx");
            ExcelWorkbook slaveFile = new ExcelWorkbook(filePath+"uno.xlsx");
            
            masterFile.copySheetsFromWorkbook("uno", slaveFile.getWorkbook().getWorksheets());
            
            masterFile.saveFile();
            slaveFile.closeStream();
            */
        
        
    }
    
}
