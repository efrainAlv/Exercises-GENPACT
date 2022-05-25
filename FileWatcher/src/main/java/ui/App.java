/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import watcher.*;
/**
 *
 * @author efrai
 */
public class App {
    
    
    public void startApp() throws IOException{
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
 
        String path = "C:\\Users\\efrai\\Desktop\\PRUEBAS\\FILES";
        int option=-1;
        
        while(option!=3){
        
            System.out.println("===================================================");
            System.out.println("======================= FILE WATCHER ==============");
            System.out.println("===================================================\n");

            System.out.print("- Enter the directory path to watch: ");
            //path = reader.readLine();
            option=-1;
            
            while(option!=0 && option!=3){
            
                System.out.println("---------------------------------------------------\n");
                System.out.println("- Are you sure you want to watch the following directory? -> "+path);
                System.out.println("- 1. Yes. ");
                System.out.println("- 2. No.");
                System.out.println("- 3. Exit.");
                System.out.print("- Enter a number: ");

                try{
                    
                    option = Integer.parseInt(reader.readLine());

                    switch(option){

                        case 1:

                            System.out.println("\n---------------------------------------------------");
                            System.out.println(" Watching "+path);
                            System.out.println("---------------------------------------------------\n");

                            Watcher w = new Watcher(path);
                            
                            if(w.prepareEnviroment()){
                                w.setService();
                                w.setPath();
                                w.initWatcher();   
                            }
                            else{
                                System.out.println("********* Error preparing the work enviroment *********\n");
                            }
                            break;
                        
                        case 2:
                            option=0;
                           break;
                    }
                } 
                catch(NumberFormatException e){
                    System.out.println("********* Error: Only type numbers *********\n");
                    System.out.println(e+"\n");
                    option =0;
                }
                catch (InterruptedException ex) {
                    System.out.println("\n Watcher was interrupted. Please dont open the master file. ");
                    System.out.println("Please dont open the master file while the watcher is running. \n");
                }

            }
            
        }
        
    }
    
}
