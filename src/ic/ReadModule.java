/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ic;

import java.io.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;


/**
 *
 * @author Vinay,Priya,Shashwat
 */
public class ReadModule {
    
    /*InputReader class is user-defined class to read thermal benchmark files of certain format*/
    private InputReader in;
    ModuleList list;
    public ReadModule(String file_name) throws FileNotFoundException{
        in=new InputReader(new FileInputStream(file_name));  
    }
    public void input() {
        try{
            String line;
            int no_of_modules=0;
            int no_of_terminals=0;
            System.out.println("Reading Modules");
            
            while(!(line=in.readString()).equalsIgnoreCase("NumSoftRectangularBlocks"));
            in.readString();
            no_of_modules=Integer.parseInt(in.readString().trim());
            
            while(!(line=in.readString()).equalsIgnoreCase("NumTerminals"));
            in.readString();
            no_of_terminals=Integer.parseInt(in.readString().trim());
            
            /*ModuleList class will just add modules and terminals 
            in two different generic user-defined arraylist of type MyList and can display them*/
            list=new ModuleList(no_of_modules, no_of_terminals);
            
            for(int i=1;i<=no_of_modules;i++){
                String name=in.readString();
                in.readString();
                int area=in.nextInt();
                double length=Double.parseDouble(in.readString());
                double breadth=Double.parseDouble(in.readString());
                //Module m=new Module(i ,name, length, breadth);
                Module m=new Module(i,name,area);
                /*adding modules in list having module id,name,length,breadth and area*/
                this.list.add(m);
            }
            
            
            for(int i=1;i<=no_of_terminals;i++){
                String name=in.readString();
                in.readString();
                
                /*adding terminals in list having terminal id and name*/
                this.list.add(new Terminals(i ,name));
            }
            
            System.out.println("Modules Read Complete");
        }
        catch(Exception e){
            //System.out.println("Invalid Input\n"+e.getMessage());
            throw (new IllegalArgumentException("Error Reading Module"));
            
        }
    }
}
