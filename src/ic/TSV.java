/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ic;

import static ic.Project.edge;

import java.util.Iterator;
/**
 *
 * @author Vinay,Priya,Shashwat
 */
public class TSV {
    PartitionSet psets[];
    int no_of_tsv;
    public TSV(PartitionSet psets[]){
        this.psets=psets;
        no_of_tsv=0;
        
    }
    public int findTSV(){
        Iterator<HyperEdge> it=edge.iterator();
        while(it.hasNext()){
            HyperEdge ed=it.next();
            Iterator<Module> it1=ed.mod.iterator();
            boolean set1=false,set2=false;
            while(it1.hasNext() && (!set1 || !set2)){
                Module mod=it1.next();
                Module m;
                if(!set1 && (m=psets[0].mod.get(mod))!=null){
                    set1=true;
                }
                else if(!set2 &&(m=psets[1].mod.get(mod))!=null){
                    set2=true;
                }
            }
            if(set1 && set2){
                no_of_tsv++;
            }
        }
        return no_of_tsv;
    }
    
}
