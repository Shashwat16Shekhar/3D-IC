/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ic;

import static ic.Project.edge;
import static ic.Project.mod;
import static ic.Project.pw;

import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Vinay,Priya,Shashwat
 */
public class Graph {
    Vertex adjacency_list[];
    int no_of_vertices;
    int deg[];
    int offset;   
    int no_of_tsv;
    static final double THRESHOLD=0.001;
    public Graph(int no_of_vertices,int no_of_nets)  {
        adjacency_list=new Vertex[no_of_vertices+1];
        this.no_of_vertices=no_of_vertices;
        deg=new int[no_of_nets+1];
        offset=0;
        no_of_tsv=0;
    }
    public void constructGraph(){
        System.out.println("Constructing Graph");
        
         /*adjacency_list is an array of type Vertex which is instantiated here 
        with the vertex id,so adjacency_list[1] will have vertex number 1 and a hashmap,
        hashmap containing adjacent list and edge weight*/
        for(int i=1;i<=no_of_vertices;i++){
            adjacency_list[i]=new Vertex(i);
        }
        
        /*Modules and terminals of each net is stored in edge_list,so now each net(having modules and terminals) is iterated*/
        Iterator<HyperEdge> it=edge.iterator();
        while(it.hasNext()){
            HyperEdge ed=it.next();
            
            /*each net's module and terminal will now be feeded to completeSubGraph method*/
            completeSubGraph(ed);
        }
        System.out.println("Graph Completed");
    }
    public void completeSubGraph(HyperEdge ed){
        
        /*Modules of that net is iterated*/
        Iterator<Module> i=ed.mod.iterator();
        while(i.hasNext()){
            Module mod1=i.next();
            Iterator<Module> j=ed.mod.iterator();
            /*Modules of that net is iterated*/
            while(j.hasNext()){
                Module mod2=j.next();
                
                /*For every i-th module,if j-th module is different this block will execute*/
                if(!mod1.equals(mod2)){
                    
                     /*At i-th module id's index in adjacency list,*/
                    int d=adjacency_list[mod1.number].add(mod2);
                    
                     /*d is the index where the edge weight is changed,so if d becomes 5,
                    then the position containing no. of edges having edge weight 5,is increased and
                    position containing no. of edges having edge weight 4, is decreased*/
                     
                    /*deg[k]=x shows that no. of edges having edge weight k in this net is x*/
                    deg[d-1]--;
                    deg[d]++;
                }
            }
        }
        /*there is no edge having edge weight 0 for a net,so deg[0] is 0*/
        deg[0]=0;
    }
    public void findOffset(){
        for(int i=offset+1;i<deg.length;i++){
            if(deg[i]!=0)
            {
                offset=i;
                break;
            }
        }
    }    
    public void display(){
        for(int i=1;i<=no_of_vertices;i++){
            System.out.println(adjacency_list[i]);
        }
    }
    public void prepareInitialSet(Sets set){
        for(int i=1;i<=no_of_vertices;i++){
            
            /*For Each module of whole circuit,add that module in set,
            increase area of that set by modules area and increse no_of_modules in that set*/
            set.addModule(mod.get(new Integer(i)));
            
            /*For each module of whole circuit,adjacency list of that module, v here, i.e.,module number and 
            a hashmap(having adjacent modules and edge weight) is added to set*/
            Vertex v=adjacency_list[i];
            
            /*v's hashmap's entries is iterated and module ( j.getKey() ) and d( j.getValue()[edge weight between v/i-th module and j]) 
            is added into HashMap of v and thus to set.Also,if v/i-th module doesn't exist,then add that module into set*/
            for(Map.Entry<Module, Integer> j:v.hm.entrySet()){
                set.addVertex(i, j.getKey(), j.getValue());
            }
        }
    }
    public void compute(){
        //System.out.println("Starting Computation");
        MyList<Sets> dis=new MyList<>();
        
        /*instantiation of Sets class will instantiate arraylist of Module and Vertex(one Vertex has id of mudule and ahshmap),
        and will instantiate area and no_of_modules to 0*/
        
        Sets set=new Sets();
        
        /*For Each module of whole circuit,add that module in set,
            increase area of that set by modules area and increse no_of_modules in that set.
        Also,adjacency list of that module,i.e.,that module number and 
            a hashmap(having adjacent modules and edge weight) is added to set*/
        prepareInitialSet(set);
        
        /*Whole "set" arraylist is added to dis*/
        dis.add(set); 
        int t;
        PartitionSet psets[];
        Partition part;
        do{
            int l=dis.size();            
            //System.out.println("Offset: "+offset);
            for(int j=0;j<l;j++){
                new SetGraph(dis.get(0), offset, dis);
                dis.remove(0);
            }
            //System.out.println("Subsets");
            //dis.display();
            //System.out.println("Starting Partiton ");
            part=new Partition(dis);
            psets=part.psets;
            
            //System.out.println("End Partition");
            no_of_tsv=(new TSV(psets)).findTSV();
            t=offset;
            findOffset();
        }while(!threshold(psets) && t!=offset);
        pw.println("Offset: "+offset);  
        for(int j=0;j<part.n;j++){
            pw.println(psets[j]);
        }
        pw.println("Number Of TSV: "+no_of_tsv);
        //System.out.println("End Computation");
    }
    
    public boolean threshold(PartitionSet psets[]){
        double diff=Math.abs(psets[0].area - psets[1].area);
        double total=psets[0].area + psets[1].area;
        if(diff/total <= THRESHOLD){
            return true;
        }
        return false;
    }
    public void reset(){
        
        offset=0;
        no_of_tsv=0;
    }
}
