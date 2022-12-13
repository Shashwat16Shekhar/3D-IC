/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ic;

import static ic.Project.mod;
import static ic.Project.term;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
/**
 *
 * @author Vinay,Priya,Shashwat
 */
public class ReadHyperEdge {
    private InputReader in;
    HyperEdgeList list;
    public ReadHyperEdge(String file_name) throws FileNotFoundException{
        in=new InputReader(new FileInputStream(file_name));  
    }
     public void input() {
        try{
            String line;
            int no_of_nets;
            int no_of_pins=0;
            System.out.println("Reading Hyper Edge");
            
            while(!(line=in.readString()).equalsIgnoreCase("NumNets"));
            in.readString();
            no_of_nets=Integer.parseInt(in.readString().trim());
            
            while(!(line=in.readString()).equalsIgnoreCase("Numpins"));
            in.readString();
            no_of_pins=Integer.parseInt(in.readString().trim());
            
            list=new HyperEdgeList(no_of_nets, no_of_pins);
            
            /*total no. of nets are read so outer loop will run to number of nets*/
            for(int i=1;i<=no_of_nets;i++){
                in.readString();
                in.readString();
                
                 /*For each net,loop will run to no of degree*/
                int net_degree=Integer.parseInt(in.readString());
                
                /*ed(Reference variable of HyperEdge is collecting modules and terminals for present net_degree*/
                HyperEdge ed=new HyperEdge(net_degree);
                
                for(int j=1;j<=net_degree;j++){
                    String block=in.readString();
                    in.readString();
                    Terminals t;
                    Module m;
                    if(j==1 && (t=term.get(block))!=null){
                        
                        /*Terminal is added to "term" data member of ed(term is of type Terminals)*/
                        ed.add(t);
                    }
                    else if((m=mod.get(block))!=null){
                        
                        /*Module is added to arraylist of modules of type MyList,i.e. added to "mod" data member of ed*/
                        ed.add(m);
                        in.readString();
                        in.readString();
                        in.readString();
                    }
                }
                
                /*whole ed object having terminals as well as modules 
                for one particular net is added to "list" data member(an arraylist of type MyList) of ReadHyperEdge class*/
                list.add(ed);
            }
            
            System.out.println("HyperEdge Read Complete");
            
        }
        catch(Exception e){
            throw new IllegalArgumentException("Error Reading Hyperedge");
        }
    }
    
}
