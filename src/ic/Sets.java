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

/*This class implements an Interface SetPrecision which will set DecimalFormat*/

public class Sets implements SetPrecision{
    MyList<Module> mod;
    double area;
    int no_of_modules;
    MyList<Vertex> adj;
    public Sets(){
        mod=new MyList<>();
        adj=new MyList<>();
        area=0;
        no_of_modules=0;
    }
    public void addModule(Module m){
        
        /*Add Module m into Module generic ArrayList of type MyList*/
        mod.add(m);
        
        Vertex v;
        /*Fetch Module m's id typecast it to Wrapper class Integer search for that id in Vertex generic ArrayList,
        if it's not there,then add that module into it*/
        if((v=adj.get(new Integer(m.number)))==null){
            v=new Vertex(m.number);
            adj.add(v);
        }
        /*area of set is increased by module's area*/
        area+=m.area;
        area=formatDecimal(area);
        /*no. of modules in that set increases*/
        no_of_modules++;
    }
    public void addVertex(int i,Module m,int d){
        
        Vertex v;
        /*Search for i-th module id (typecasted to Wrapper class Integer) in Vertex generic ArrayList,
        if it's not there,then add that module into it*/
        if((v=adj.get(new Integer(i)))==null){
            v=new Vertex(i);
            adj.add(v);            
        }
         /*For i-th module calling this add function,it will add module mod and d(edge weight between i and mod)
        into HashMap of i*/
        v.add(m, d);
    }
    public double formatDecimal(double d){
        return Double.parseDouble(decformat.format(d));
    }
    public String toString(){
        String S="("+no_of_modules+" , "+area+" )-> ";
        Iterator<Module> it=mod.iterator();
        while(it.hasNext()){
            Module m=it.next();
            S+=" "+m.number+" | ";
        }
        return S;
    }
    public void displayEdgeList(){
        System.out.println("SET EDGE LIST: "+adj.size());
        Iterator<Vertex> it=adj.iterator();
        while(it.hasNext()){
            Vertex v=it.next();
            System.out.println(v);
        }
    }
}
