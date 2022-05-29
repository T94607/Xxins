/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    // Abstraction function:
    // AF(vertices)=图中所有的点，每个点的类中保存了这个点的标签，入边以及出边
    // Representation invariant:
    // vertices中不能有重复点
    // Safety from rep exposure:
    // 设置vertices为private
    // 由于vertices是mutable，所以在返回他们时进行了defensive copies
    
    
    // TODO constructor
    public ConcreteVerticesGraph() {
    	
    }
    // TODO checkRep
    /**
	 * 测试表示不变性
	 */
	private void checkRep() {
		  Set<Vertex<L>> testvertices = new HashSet<>();
	      testvertices.addAll(vertices);
	      assert testvertices.size() == vertices.size();
	}
	
	 /**
     * Add a vertex to this graph.
     * 
     * @param vertex label for the new vertex
     * @return true if this graph did not already include a vertex with the
     *         given label; otherwise false (and this graph is not modified)
     */
    @Override public boolean add(L vertex) {
        //throw new RuntimeException("not implemented");
    	for(Vertex<L> temp : vertices)//检查是否已存在
    	{
    		if(temp.getname().equals(vertex)) {
    			System.out.println("待加入点已经存在\n");
    			return false;
    		}
    	}
    	//不存在该点，新增一个Vertex类，加入到数组中 
    	Vertex<L> one = new Vertex<L>(vertex);
    	vertices.add(one);
    	checkRep();
    	return true;
    	
    }
    /**
     * Add, change, or remove a weighted directed edge in this graph.
     * If weight is nonzero, add an edge or update the weight of that edge;
     * vertices with the given labels are added to the graph if they do not
     * already exist.
     * If weight is zero, remove the edge if it exists (the graph is not
     * otherwise modified).
     * 
     * @param source label of the source vertex
     * @param target label of the target vertex
     * @param weight nonnegative weight of the edge
     * @return the previous weight of the edge, or zero if there was no such
     *         edge
     */
    @Override public int set(L source, L target, int weight) {
        //throw new RuntimeException("not implemented");
    	int back = 0;
    	if(weight<0) {
    		System.out.println("权值小于0，输入不合法");
    		back = -1;
    	}
    	else if(weight==0){
    		
    		for(Vertex<L> temp1: vertices)//判断这条边在不在图中
    		{
    			if(temp1.getname().equals(source)) {//源点在图中
    			
    				Map<L,Integer> a =new HashMap<>();
    				a.putAll(temp1.getoutEdge());;//a为出边的Map集合
    				for(L temp2 : a.keySet())
       			   {
       				     if(temp2.equals(target))//终点在图中
       				      {
       				          back = a.get(temp2).intValue();
       				    	
       				          temp1.deleteOutedge(target);//源点中出边集删除出边
       		            		for(Vertex<L> temp3 : vertices)//终点中入边集删除入边
       		            		{
       		            			if(temp3.getname().equals(target))
       		            			{
       		            				temp3.deleteInedge(source);
       		            				break;
       		            			}
       		            		}
       		            	    break;
       				      }
       				    
       			    }
    		        
    		            
    		            
    		      }
    
    			
    			
    		    }
    		
    		//如果这条边不在图中，返回0，不需要额外操作
    				
    		}
    	else {
    		int flag1 = 0;
    		int flag2 = 0;
    		//判断源点终点是否在图中
    		for(Vertex<L> temp1: vertices)
    		{
    			if(temp1.getname().equals(source)) {//源点在图中
    				flag1 = 1;
    				for(Vertex<L> temp2: vertices) {
    					if(temp2.getname().equals(target)) {
    						flag2 = 1;//终点在图中
    					}
    					
    				}
    			}
    		}	
    		//分四种情况讨论
    		/*源点终点都在图中 flag1=1,flag2=1
    		 * 源点终点都不在图中 flag1=0,flag2=0
    		 * 源点在图中,终点不在图中 flag1=1,flag2=0
    		 * 源点不在图中,终点在图中 flag1=0,flag2=1
    		 * */
    		if(flag1==1&&flag2==1)
    		{
    			//判断这条边在图中，在图中则修改边的权值
    			//不在图中则新增一条边
    			int flag3 = 0;
    			for(Vertex<L> temp1: vertices)
        		{
        			if(temp1.getname().equals(source)) {//找到源点
        				 Map<L,Integer> a =new HashMap<>();
         				a.putAll(temp1.getoutEdge());//a为出边的Map集合
         				
         				for(L temp2 : a.keySet()) {
         		            if(temp2.equals(target)) {//找到终点
         		            	//边在图中，修改权值
         		            	flag3 = 1;
         		            	back = a.get(temp2).intValue();
    		            		temp1.deleteOutedge(target);//源点中出边集删除出边,添加新的出边
    		            		temp1.addOutedge(target,weight);
    		            		for(Vertex<L> temp3 : vertices)//终点中入边集删除入边，添加新的入边
    		            		{
    		            			if(temp3.getname().equals(target))
    		            			{
    		            				temp3.deleteInedge(source);
    		            				temp3.addInedge(source,weight);
    		            			}
    		            		}
         		            }
         				}
        				
        			}
        		}
    			//边不在图中，新增一条边
    			if(flag3==0) {
    				for(Vertex<L> temp1: vertices)
            		{
            			if(temp1.getname().equals(source)) {//找到源点
            				temp1.addOutedge(target,weight);
            			}
            		}
    				
    				for(Vertex<L> temp1: vertices)
            		{
            			if(temp1.getname().equals(target)) {//找到终点
            				temp1.addInedge(source,weight);
            			}
            		}
    				
    			}
    			
    			
    		}
    		
    		if(flag1==0&&flag2==0) {//源点终点都不在图中
    			Vertex<L> s = new Vertex<L>(source);
    			Vertex<L> t = new Vertex<L>(target); 
    			s.addOutedge(target, weight);
    			t.addInedge(source, weight);
    			vertices.add(s);
    			vertices.add(t);
    		}
    		if(flag1==0&&flag2==1) {//源点不在图中终点在图中
    			Vertex<L> s = new Vertex<L>(source);
    			s.addOutedge(target, weight);
    			vertices.add(s);
    			for(Vertex<L> temp3 : vertices)//终点中入边集删除入边，添加新的入边
        		{
        			if(temp3.getname().equals(target))
        			{
        				temp3.addInedge(source,weight);
        				break;
        			}
        		}
    			
    		}
    		if(flag1==1&&flag2==0) {//源点在图中，终点不在图中
    			Vertex<L> t = new Vertex<L>(target); 
    			t.addInedge(source, weight);
    			vertices.add(t);
    			for(Vertex<L> temp1: vertices)
        		{
        			if(temp1.getname().equals(source)) {
        				temp1.addOutedge(target,weight);
        				break;
        			}
        		}	
    		}
    		
    	}
    		
    		
    	checkRep();
        
        return back;
    }
    /**
     * Remove a vertex from this graph; any edges to or from the vertex are
     * also removed.
     * 
     * @param vertex label of the vertex to remove
     * @return true if this graph included a vertex with the given label;
     *         otherwise false (and this graph is not modified)
     */
    @Override public boolean remove(L vertex) {
        //throw new RuntimeException("not implemented");
    	for(Vertex<L> temp : vertices)//检查是否已存在
    	{
    		if(temp.getname().equals(vertex)) {
    			vertices.remove(temp);//在顶点集合中删除该顶点
    			checkRep();
    			return true;
    		}
    	}
    	return false;
    }
    /**
     * Get all the vertices in this graph.
     * 
     * @return the set of labels of vertices in this graph
     */
    @Override public Set<L> vertices() {
        //throw new RuntimeException("not implemented");
        Set<L> back = new HashSet<>();
        for(Vertex<L> temp:vertices)
        {
        	back.add(temp.getname());
        }
        checkRep();
        return back;
    }
    /**
     * Get the source vertices with directed edges to a target vertex and the
     * weights of those edges.
     * 
     * @param target a label
     * @return a map where the key set is the set of labels of vertices such
     *         that this graph includes an edge from that vertex to target, and
     *         the value for each key is the (nonzero) weight of the edge from
     *         the key to target
     */
    @Override public Map<L, Integer> sources(L target) {
        //throw new RuntimeException("not implemented");
    	Map<L,Integer> back = new HashMap<>();
        for(Vertex<L> temp:vertices)
        {
        	if(temp.getname().equals(target)) {
        		back.putAll(temp.getinEdge());
        	}
        }
        checkRep();
        return back;
        
    }
    /**
     * Get the target vertices with directed edges from a source vertex and the
     * weights of those edges.
     * 
     * @param source a label
     * @return a map where the key set is the set of labels of vertices such
     *         that this graph includes an edge from source to that vertex, and
     *         the value for each key is the (nonzero) weight of the edge from
     *         source to the key
     */
    @Override public Map<L, Integer> targets(L source) {
        //throw new RuntimeException("not implemented");
    	 Map<L,Integer> back = new HashMap<>();
         
      	for(Vertex<L> temp : vertices)
      	{
      		if(temp.getname().equals(source))
      		{
      			back.putAll(temp.getoutEdge());
      		}
      		
      	}
      	checkRep();
      	return back;
         
     }
        
    
    // TODO toString()
    /**
     * @return 返回整个图连成的字符串
     */
    public String toString() {
    	String back = "";
		for (Vertex<L> temp : vertices) {
			back += temp.toString();
		}
		checkRep();
		return back;
    	
    }


    
}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex<L>{
    
    // TODO fields
    private final Map<L,Integer> inEdge;//顶点的所有入边（用顶点名和边权值表示）
    private final  Map<L,Integer> outEdge;//顶点的所有出边（用顶点名和边权值表示）
    private final L name;//顶点名字
    
    // Abstraction function:
    // AF(name)=点的标签
	// AF(inEdge)=顶点的所有入边（用顶点名和边权值表示）
	// AF(alltarget)=顶点的所有出边（用顶点名和边权值表示）
    // Representation invariant:
    // 每个边的权值应该大于0
    // Safety from rep exposure:
    // 将outEdge,inEdge,name设置为private
	
    
    // TODO constructor
    /**
	 * 初始化构造方法，用点的名字创建
	 * @param name 顶点标签
	 */
    public Vertex (L name) {
    	
    	this.name = name;
    	this.outEdge = new HashMap<>();
    	this.inEdge = new HashMap<>();
    	
    }
    // TODO checkRep
    /**
	 * 检查表示不变性
	 * @param one 入边集合
	 * @param two 出边集合
	 * @param value1 每条入边的权值 
	 * @param value2 每条出边的权值
	 */

    public void checkRep() {
    		Set<L> one = this.inEdge.keySet();//入边集合
    		if(one!=null)
    		{
    			for(L temp : one) {
    				Integer value1 = this.inEdge.get(temp);
    				assert  value1>0 ;
    				
    			}
    		
    		}
    		Set<L> two = this.outEdge.keySet();//入边集合
    		if(two!=null)
    		{
    			for(L temp : two) {
    				Integer value2 = this.outEdge.get(temp);
    				assert  value2>0 ;
    				
    			}
    		
    		}
    		
    	
    }
   
    /**
     * @param target 标识出想要删除的出边
     * 
     * 
     */
    
    public void deleteOutedge(L target) {
    	this.outEdge.remove(target);//移除outEdge中指定key值的key-value对
    	
    }
    /**
     * @param source 标识出想要删除的入边
     * 
     * 
     */
    public void deleteInedge(L source) {
    	this.inEdge.remove(source);
    }
    
    /**
     * @param target 标识出想要增加的出边
     * @param weight 新增的出边上的权值
     * 
     */
    public void addOutedge(L target,int weight) {
    	Integer temp = new Integer(weight);
    	this.outEdge.put(target, temp);
    }
    /**
     * @param source 标识出想要增加的入边
     * @param weight 新增的入边上的权值
     * 
     */
    public void addInedge(L source,int weight) {
    	Integer temp = new Integer(weight);
    	this.inEdge.put(source, temp);
    }
    /**
     * @return 返回一个顶点的字符串表示
     * 
     */
    public String toString() {
		
		return  "顶点名为"+this.name.toString()+"\n"+"所有入边为"+this.inEdge.toString()+"\n"+"所有出边为"+this.outEdge.toString()+"\n";
		
		
	}
	
    /**
	 * 返回点的名字name
	 * @return 点的名字
	 */
	public L getname() {
		return this.name;
	}
	
	/**
	 * 返回能到达该点的所有点和边构成的Map
	 * @return 到达某个点的所有点和边构成的Map
	 */
	public Map<L,Integer> getinEdge(){
		return this.inEdge;
	}
	
	/**
	 * 返回某个点能到达的所有点和边构成的Map
	 * @return 到达某个点的所有点和边构成的Map
	 */
	public Map<L,Integer> getoutEdge(){
		return this.outEdge;
	}
    
    
}
