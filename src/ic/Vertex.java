/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ic;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Vinay,Priya,Shashwat
 */
public class Vertex {
    int n;
    HashMap<Module, Integer> hm;
    public Vertex(int n){
        this.n=n;
        hm=new HashMap<>();
    }
    public int add(Module mod){
        int d=0;
        
        /*HashMap of module/block which is calling add method,if contains the Module mod(which is called inside argument),
        then value at that mod key is incremented and is stored in d*/
        if(hm.containsKey(mod)){
            d=hm.get(mod)+1;
        }
        
        /*HashMap of module/block which is calling add method,if does not contains the Module mod(which is called inside argument),
        then value of key  of calling module is set to 1 and is stored in d*/
        else
        {
            d=1;
        }
        
        /*For module/block calling this add function let it be m,it will add mod and d(edge weight between m and mod)
        into HashMap of m*/
        hm.put(mod,d);
        
        /*d is the index where the net degree is changed*/
        return d;
    }
    public void add(Module mod,int d){
        
        /*For module/block calling this add functionlet it be m,it will add mod and d(edge weight between m and mod)
        into HashMap of m*/
            hm.put(mod,d);
            
    }
    public String toString(){
        String S=""+n+" ->";
        for(Map.Entry<Module, Integer> i:hm.entrySet()){
            S+=" ( "+i.getKey().number+" , "+i.getValue()+" ) ";
        }
        return S;
    }
    public boolean equals(Object ob){
        //System.out.print("Vertex");
        if(ob instanceof Integer){
            int v=(Integer)ob;
            if(v==n){
                return true;
            }
        }
        return false;
    }
    public int hashCode(){
        return n;
    }
}
