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
public class Terminals {
    String name;
    int number;
    public Terminals(int number, String name){
        this.name=name;
        this.number=number;
    
    }
    
    public String toString(){
        return name + "\t" + number;
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
        else if(ob instanceof Terminals){            
            Terminals t=(Terminals)ob;
            if(t.hashCode()==hashCode()){
                return true;
            }
        }
        return false;
    }
    public int hashCode(){
        return number;
    }
}
