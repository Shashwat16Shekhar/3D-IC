/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Vinay,Priya,Shashwat
 */
public class Statistics implements SetPrecision{
    TreeMap<Integer, Integer> tm;
    String directory;
    String file;
    int no_of_execution;
    public Statistics(String directory,String file) {
        this.directory=directory;
        this.file=file;
        this.no_of_execution=0;
        this.directory+=file+"\\";
        tm=new TreeMap<>();
    }
    public void add(int mod){
        int d=0;
        if(tm.containsKey(mod)){
            d=tm.get(mod)+1;
        }
        else
        {
            d=1;
        }
        tm.put(mod,d);
    }
    public void add(int mod,int n){
        tm.put(mod,n);
    }
    
    public void prepareReport(){
        
        try{            
            FileOutputStream fos=new FileOutputStream(directory+"Report.txt");
            PrintWriter pw=new PrintWriter(fos);
            //System.out.println("In the file: "+this.no_of_execution);
            //System.out.println("Writing into File");
            pw.println("Number of Stimulations : "+this.no_of_execution);
            pw.println("# Number of TSV\tNumber of Stimulation\tPercentage");
            for(Map.Entry<Integer, Integer> i:tm.entrySet()){
                double percentage=(double)(i.getValue()*100.0)/(double)(this.no_of_execution);
                percentage=formatDecimal(percentage);
                pw.println("  "+i.getKey()+"\t\t\t"+i.getValue()+"\t\t"+percentage+"%");
            }
            pw.close();
            fos.close();
        }
        catch(Exception e){
            throw new IllegalArgumentException("Unable to Create Report");
        }
    }
    public void prepareInitialMap(int no_of_execution){
        try{
            File file=new File(directory+"Report.txt");
            tm.clear();
            this.no_of_execution=0;
            //System.out.println("Preparing initial List");
            if(file.exists()){
                FileInputStream fis=new FileInputStream(directory+"Report.txt");
                InputReader in=new InputReader(fis);
                in.readString();
                in.readString();
                in.readString();
                in.readString();
                int temp=in.nextInt();
                this.no_of_execution=temp;
                int num=0;
                while(num<temp){
                    int tsv=in.nextInt();
                    int stimulation=in.nextInt();
                    in.readString();
                    num+=stimulation;
                    add(tsv,stimulation);
                }
                in=null;
                fis.close();
                file=null;
            }
            else{
                //System.out.println("File do not exist");
            }
            this.no_of_execution+=no_of_execution;
        }
        catch(Exception e){
            System.out.println(e);
            throw new IllegalArgumentException("Unable to Prepare Statistical Report");
        }
    }
    public double formatDecimal(double d){
        return Double.parseDouble(decformat.format(d));
    }
    public void display(){
        for(Map.Entry<Integer, Integer> i:tm.entrySet()){
                double percentage=(double)(i.getValue()*100.0)/(double)(this.no_of_execution);
                percentage=formatDecimal(percentage);
                System.out.println("  "+i.getKey()+"\t\t\t"+i.getValue()+"\t\t"+percentage+"%");
            }
    }
}
