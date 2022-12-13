/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ic;

import java.io.*;

/**
 *
 * @author Vinay,Priya,Shashwat
 */
public class FileManipulation {
    String directory;
    String file;
    String outputDirectory;
    public FileManipulation(String directory,String file){
        this.directory=directory;
        this.file=file;             
        outputDirectory=directory+file+"\\";
    }
    public void write(int no_of_tsv){        
        createDirectory();
        //System.out.println("Directory Created");
        try{
            File oldFile=new File(directory+file+".txt");
            File newFile=new File(outputDirectory+file+"_"+no_of_tsv+".txt");
            if(newFile.exists())
            {
                 FileOutputStream out=new FileOutputStream(newFile,true);
                 FileInputStream in=new FileInputStream(oldFile);
                 byte[] buffer = new byte[1024];
                 int length;
                 while((length=in.read(buffer)) >0){
                     out.write(buffer, 0, length);

                 }
                 in.close();
                 out.close();
                 oldFile.delete();
            }
            else
            {
                oldFile.renameTo(newFile);
            }
        }
        catch(Exception e){
            throw new IllegalArgumentException("File Not Found");
        }
    }
    public void createDirectory(){
        try{
            File file = new File(outputDirectory);
            if (!file.exists()) {
                file.mkdir();
            }
        }
        catch(Exception e){
            throw new IllegalArgumentException("Unable to create directory");
        }
    }
    public void deleteDirectory(){   
        File file=new File(outputDirectory);
        try{
            if(file.exists()){
                if(file.list().length==0){
                   file.delete();
                }
                else{
                    String files[] = file.list();
                    for (String temp : files) {
                        File fileDelete = new File(file,temp);
                        if(fileDelete.delete())
                        {
                            //System.out.println("File Deleted: "+fileDelete.getAbsolutePath());
                        }
                        else{
                            System.out.println("Unable to Delete");
                            
                        }
                    }
                    if(file.list().length==0){
                        file.delete();
                    }
                }
            }
        }
       catch(Exception e){
           throw new IllegalArgumentException("Unable to delete directory");               
       } 
    }
    
}
