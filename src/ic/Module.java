/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ic;

/**
 *
 * @author Vinay,Priya,Shashwat
 */
public class Module implements SetPrecision{
    int number;
    String name;
    double length;
    double breadth;
    double area;
    public Module(int number,String name,double length,double breadth){
        this.name=name;
        this.number=number;
        this.length=formatDecimal(length);
        this.breadth=formatDecimal(breadth);
        this.area=formatDecimal(length*breadth);
        
    }
    public Module(int number,String name, int area){
        this.name=name;
        this.number=number;
        this.length=0;
        this.breadth=0;
        this.area=formatDecimal(area);
    }
    public String toString(){
        return number + "\t" + name + "\t" + length + "\t" + breadth + "\t" + area;
    }
    
    public boolean equals(Object ob){
        if(ob instanceof String){
            String S=(String)ob;
            if(S.equalsIgnoreCase(name))
            {
                return true;
            }
        }
        else if(ob instanceof Integer){
            int n=(Integer)ob;
            if(n==number){
                return true;
            }
        }
        else if(ob instanceof Module){
            Module m=(Module)ob;
            if(m.hashCode()==hashCode()){
                return true;
            }
        }
        return false;
    }
    public int hashCode(){
        return number;
    }
    
    public double formatDecimal(double d){
        return Double.parseDouble(decformat.format(d));
    }
}
