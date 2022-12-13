/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ic;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Vinay,Priya,Shashwat
 */
public class Project {

    /**
     * @param args the command line arguments
     */
    public static  ReadModule rm;
    public static ReadHyperEdge ed;
    public static Graph graph;
    public static MyList<Module> mod;
    public static MyList<Terminals> term;
    public static MyList<HyperEdge> edge;
    public static Vertex adj[];    
    public static PrintWriter pw;
    public static Statistics st;
    public static FileManipulation fm;
    public static String folder;
    public static String file;
    
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        new Project().init();
        //System.out.println("THANK YOU");
    }
    public void init(){
        //int no_of_execution=10;
		
       
        folder="C:\\Project1\\thermal_benchmark\\";
        file="ami33";
        try{
            
            fm=new FileManipulation(folder,file);
            //fm.deleteDirectory();
            st=new Statistics(folder, file);
            
            //pw=new PrintWriter(new FileOutputStream(folder+file+".txt"));
            
            /*ReadModule class is reading no. of blocks and terminals;block/module name,length,breadth and terminal name*/
            rm=new ReadModule(folder+file+".blocks");
            
            /*all modules and terminals are added in "list" data member(an arraylist of type MyList) of rm(reference of ReadModule)*/
            rm.input();
            //rm.list.display();
        
            /*all the modules are stored in mod thus mod have id,name,length,breadth,area of each module*/
            mod=rm.list.mod;
            
            /*all the terminals are stored in term thus term have id,name of each terminal*/
            term=rm.list.term;
        
            /*ReadHyperEdge class is reading hyperedges i.e. no.of nets,number of pins;blocks and terminals in each net degree*/
            ed=new ReadHyperEdge(folder+file+".nets");
            
            /*Terminals as well as modules for one particular net is added to "list" data member(an arraylist of type MyList) of ed(reference of ReadHyperEdge)*/
            ed.input();
            //ed.list.display(); 
            
            /*all the modules and terminals are stored in edge thus edge have modules and terminals of each net*/
            edge=ed.list.edge;
        
            /*instantiate graph with no_of_modules/no_of_vertices and no_of_nets in circuit*/
            graph=new Graph(rm.list.no_of_modules,ed.list.no_of_nets);
            
            /*It will construct adjacency list array,containing vertex number 
            and a hashmap(having adjacent module and edge weight)*/
            graph.constructGraph();
            //graph.display();
            
            /*adjacency_list is an array of type Vertex where each element of array contains
            vertex id and a hashmap,so adjacency_list[1] will have vertex number 1 and a hashmap(adjacent modules with edge weight),
            thus adj will contain the same*/
            adj=graph.adjacency_list;
            //execution(no_of_execution);
            
            new Display(this);
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void execution(int no_of_execution)throws Exception{
        //System.out.println("New Execution: "+no_of_execution);
        st.prepareInitialMap(no_of_execution);
        //st.display();
        for(int i=0;i<no_of_execution;i++){
                //System.out.println("Computing: "+i);
                FileOutputStream fos=new FileOutputStream(folder+file+".txt");
                pw=new PrintWriter(fos);
                graph.compute();
                int no_of_tsv=graph.no_of_tsv;   
                
                /*It will reset offset to 0 and "no_of_tsv" data member of Graph class to 0*/
                graph.reset(); 
                
                //System.out.println("Number of TSVs is "+no_of_tsv);
                pw.println("............................");
                pw.close();
                fos.close();
                fm.write(no_of_tsv);
                st.add(no_of_tsv);
                //pw=new PrintWriter(new FileOutputStream(folder+file+".txt"));
            }
            pw.close();
            st.prepareReport();
    }
}
