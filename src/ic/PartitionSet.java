/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ic;

import java.util.Iterator;

/**
 *
 * @author Vinay,Priya,Shashwat
 */
public class PartitionSet implements SetPrecision{
    MyList<Module> mod;
    double area;
    int no_of_modules;
    MyList<Sets> adj;
    public PartitionSet(){
        mod=new MyList<>();
        area=0;
        no_of_modules=0;
        adj=new MyList<>();
    }
    public void addSet(Sets s){
        area+=s.area;
        no_of_modules+=s.no_of_modules;
        adj.add(s);
        //area=formatDecimal(area);
    }
    public void removeSet(Sets s){
        area-=s.area;
        no_of_modules-=s.no_of_modules;
        adj.remove(s);
        //area=formatDecimal(area);
    }
    public void prepareFinalSet(){
        area=0;
        no_of_modules=0;
        Iterator<Sets> it=adj.iterator();
        while(it.hasNext()){
            Sets s=it.next();
            Iterator<Module> it1=s.mod.iterator();
            while(it1.hasNext()){
                Module m=it1.next();
                mod.add(m);
                area+=m.area;
                no_of_modules++;
            }
        }
        area=formatDecimal(area);
    }
   
    public String toString(){
        String S="("+no_of_modules+" , "+area+" )-> ";
        Iterator<Module> it=mod.iterator();
        while(it.hasNext()){
            Module m=it.next();
            S+=" "+m.name+" | ";
        }
        return S;
    }
    public String displayDisjointSet(){
        String S="("+no_of_modules+" , "+formatDecimal(area)+" )-> ";
        Iterator<Sets> it=adj.iterator();
        while(it.hasNext()){
            Sets set=it.next();
            Iterator<Module> it1=set.mod.iterator();
            while(it1.hasNext()){
                Module m=it1.next();
                S+=" "+m.number+" | ";
            }
        }
        return (S);
    }
    public double formatDecimal(double d){
        return Double.parseDouble(decformat.format(d));
    }
}
