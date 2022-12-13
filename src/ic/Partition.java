/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ic;

import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author Vinay,Priya,Shashwat
 */
public class Partition implements SetPrecision{
    MyList<Sets> dis;
    PartitionSet psets[];
    int n=2;
    double diff;
    public Partition(MyList<Sets> dis){
        this.dis=dis;
        diff=Double.MAX_VALUE;
        psets=new PartitionSet[n];
        for(int i=0;i<n;i++){
            psets[i]=new PartitionSet();
        }
        randomAllocate();
        equallyAllocate();
        for(int i=0;i<n;i++){
            psets[i].prepareFinalSet();
        }
    }
    public void randomAllocate(){
        Random r=new Random();
        Iterator<Sets> it=dis.iterator();
        while(it.hasNext()){
            Sets s=it.next();
            int i=r.nextInt(n);
            psets[i].addSet(s);
        }
    }
    public void equallyAllocate(){
        double minDiff=Double.MAX_VALUE;
        while(true){
            //System.out.println("Inside Equally Allocate");
            PartitionSet max=(psets[0].area>psets[1].area)?psets[0]:psets[1];
            PartitionSet min=(psets[0].area<psets[1].area)?psets[0]:psets[1];
            double diff=(max.area-min.area)/2;
            /*System.out.println("BEFORE: "+(diff*2));
            System.out.println("SET 1: "+ max.displayDisjointSet());
            System.out.println("SET 2: "+ min.displayDisjointSet());
            /*if(diff>=this.diff){
                break;                
            }
            
            System.out.println(diff);
            this.diff=diff;*/
            //System.out.print(diff+"\t");
            double minValue=Double.MAX_VALUE;
            Sets s=null;
            Iterator<Sets> it=max.adj.iterator();
            while(it.hasNext()){
                Sets temp=it.next();
                double diff1=Math.abs(temp.area-diff);
                if(diff1 < minValue){
                    minValue=diff1;
                    s=temp;
                }
            }
            max.removeSet(s);
            min.addSet(s);
            double tempDiff1=(Math.abs(max.area - min.area))/2;
            tempDiff1=formatDecimal(tempDiff1);
            //System.out.println(tempDiff);
            /*System.out.println("AFTER: "+(tempDiff1*2));
            System.out.println("SET 1: "+ max.displayDisjointSet());
            System.out.println("SET 2: "+ min.displayDisjointSet());
            /*if(tempDiff1 == tempDiff){
                min.removeSet(s);
                max.addSet(s);
                break;
            }*/
            
            if(tempDiff1<minDiff){
                minDiff=tempDiff1;
            }
            else if(minDiff==tempDiff1){
                break;
            }
            //System.out.println("MIN DIFF:"+(minDiff*2));
        }
        
    }
    public double formatDecimal(double d){
        return Double.parseDouble(decformat.format(d));
    }
    
}
