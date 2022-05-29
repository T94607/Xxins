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
    // AF(vertices)=ͼ�����еĵ㣬ÿ��������б����������ı�ǩ������Լ�����
    // Representation invariant:
    // vertices�в������ظ���
    // Safety from rep exposure:
    // ����verticesΪprivate
    // ����vertices��mutable�������ڷ�������ʱ������defensive copies
    
    
    // TODO constructor
    public ConcreteVerticesGraph() {
    	
    }
    // TODO checkRep
    /**
	 * ���Ա�ʾ������
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
    	for(Vertex<L> temp : vertices)//����Ƿ��Ѵ���
    	{
    		if(temp.getname().equals(vertex)) {
    			System.out.println("��������Ѿ�����\n");
    			return false;
    		}
    	}
    	//�����ڸõ㣬����һ��Vertex�࣬���뵽������ 
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
    		System.out.println("ȨֵС��0�����벻�Ϸ�");
    		back = -1;
    	}
    	else if(weight==0){
    		
    		for(Vertex<L> temp1: vertices)//�ж��������ڲ���ͼ��
    		{
    			if(temp1.getname().equals(source)) {//Դ����ͼ��
    			
    				Map<L,Integer> a =new HashMap<>();
    				a.putAll(temp1.getoutEdge());;//aΪ���ߵ�Map����
    				for(L temp2 : a.keySet())
       			   {
       				     if(temp2.equals(target))//�յ���ͼ��
       				      {
       				          back = a.get(temp2).intValue();
       				    	
       				          temp1.deleteOutedge(target);//Դ���г��߼�ɾ������
       		            		for(Vertex<L> temp3 : vertices)//�յ�����߼�ɾ�����
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
    		
    		//��������߲���ͼ�У�����0������Ҫ�������
    				
    		}
    	else {
    		int flag1 = 0;
    		int flag2 = 0;
    		//�ж�Դ���յ��Ƿ���ͼ��
    		for(Vertex<L> temp1: vertices)
    		{
    			if(temp1.getname().equals(source)) {//Դ����ͼ��
    				flag1 = 1;
    				for(Vertex<L> temp2: vertices) {
    					if(temp2.getname().equals(target)) {
    						flag2 = 1;//�յ���ͼ��
    					}
    					
    				}
    			}
    		}	
    		//�������������
    		/*Դ���յ㶼��ͼ�� flag1=1,flag2=1
    		 * Դ���յ㶼����ͼ�� flag1=0,flag2=0
    		 * Դ����ͼ��,�յ㲻��ͼ�� flag1=1,flag2=0
    		 * Դ�㲻��ͼ��,�յ���ͼ�� flag1=0,flag2=1
    		 * */
    		if(flag1==1&&flag2==1)
    		{
    			//�ж���������ͼ�У���ͼ�����޸ıߵ�Ȩֵ
    			//����ͼ��������һ����
    			int flag3 = 0;
    			for(Vertex<L> temp1: vertices)
        		{
        			if(temp1.getname().equals(source)) {//�ҵ�Դ��
        				 Map<L,Integer> a =new HashMap<>();
         				a.putAll(temp1.getoutEdge());//aΪ���ߵ�Map����
         				
         				for(L temp2 : a.keySet()) {
         		            if(temp2.equals(target)) {//�ҵ��յ�
         		            	//����ͼ�У��޸�Ȩֵ
         		            	flag3 = 1;
         		            	back = a.get(temp2).intValue();
    		            		temp1.deleteOutedge(target);//Դ���г��߼�ɾ������,����µĳ���
    		            		temp1.addOutedge(target,weight);
    		            		for(Vertex<L> temp3 : vertices)//�յ�����߼�ɾ����ߣ�����µ����
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
    			//�߲���ͼ�У�����һ����
    			if(flag3==0) {
    				for(Vertex<L> temp1: vertices)
            		{
            			if(temp1.getname().equals(source)) {//�ҵ�Դ��
            				temp1.addOutedge(target,weight);
            			}
            		}
    				
    				for(Vertex<L> temp1: vertices)
            		{
            			if(temp1.getname().equals(target)) {//�ҵ��յ�
            				temp1.addInedge(source,weight);
            			}
            		}
    				
    			}
    			
    			
    		}
    		
    		if(flag1==0&&flag2==0) {//Դ���յ㶼����ͼ��
    			Vertex<L> s = new Vertex<L>(source);
    			Vertex<L> t = new Vertex<L>(target); 
    			s.addOutedge(target, weight);
    			t.addInedge(source, weight);
    			vertices.add(s);
    			vertices.add(t);
    		}
    		if(flag1==0&&flag2==1) {//Դ�㲻��ͼ���յ���ͼ��
    			Vertex<L> s = new Vertex<L>(source);
    			s.addOutedge(target, weight);
    			vertices.add(s);
    			for(Vertex<L> temp3 : vertices)//�յ�����߼�ɾ����ߣ�����µ����
        		{
        			if(temp3.getname().equals(target))
        			{
        				temp3.addInedge(source,weight);
        				break;
        			}
        		}
    			
    		}
    		if(flag1==1&&flag2==0) {//Դ����ͼ�У��յ㲻��ͼ��
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
    	for(Vertex<L> temp : vertices)//����Ƿ��Ѵ���
    	{
    		if(temp.getname().equals(vertex)) {
    			vertices.remove(temp);//�ڶ��㼯����ɾ���ö���
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
     * @return ��������ͼ���ɵ��ַ���
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
    private final Map<L,Integer> inEdge;//�����������ߣ��ö������ͱ�Ȩֵ��ʾ��
    private final  Map<L,Integer> outEdge;//��������г��ߣ��ö������ͱ�Ȩֵ��ʾ��
    private final L name;//��������
    
    // Abstraction function:
    // AF(name)=��ı�ǩ
	// AF(inEdge)=�����������ߣ��ö������ͱ�Ȩֵ��ʾ��
	// AF(alltarget)=��������г��ߣ��ö������ͱ�Ȩֵ��ʾ��
    // Representation invariant:
    // ÿ���ߵ�ȨֵӦ�ô���0
    // Safety from rep exposure:
    // ��outEdge,inEdge,name����Ϊprivate
	
    
    // TODO constructor
    /**
	 * ��ʼ�����췽�����õ�����ִ���
	 * @param name �����ǩ
	 */
    public Vertex (L name) {
    	
    	this.name = name;
    	this.outEdge = new HashMap<>();
    	this.inEdge = new HashMap<>();
    	
    }
    // TODO checkRep
    /**
	 * ����ʾ������
	 * @param one ��߼���
	 * @param two ���߼���
	 * @param value1 ÿ����ߵ�Ȩֵ 
	 * @param value2 ÿ�����ߵ�Ȩֵ
	 */

    public void checkRep() {
    		Set<L> one = this.inEdge.keySet();//��߼���
    		if(one!=null)
    		{
    			for(L temp : one) {
    				Integer value1 = this.inEdge.get(temp);
    				assert  value1>0 ;
    				
    			}
    		
    		}
    		Set<L> two = this.outEdge.keySet();//��߼���
    		if(two!=null)
    		{
    			for(L temp : two) {
    				Integer value2 = this.outEdge.get(temp);
    				assert  value2>0 ;
    				
    			}
    		
    		}
    		
    	
    }
   
    /**
     * @param target ��ʶ����Ҫɾ���ĳ���
     * 
     * 
     */
    
    public void deleteOutedge(L target) {
    	this.outEdge.remove(target);//�Ƴ�outEdge��ָ��keyֵ��key-value��
    	
    }
    /**
     * @param source ��ʶ����Ҫɾ�������
     * 
     * 
     */
    public void deleteInedge(L source) {
    	this.inEdge.remove(source);
    }
    
    /**
     * @param target ��ʶ����Ҫ���ӵĳ���
     * @param weight �����ĳ����ϵ�Ȩֵ
     * 
     */
    public void addOutedge(L target,int weight) {
    	Integer temp = new Integer(weight);
    	this.outEdge.put(target, temp);
    }
    /**
     * @param source ��ʶ����Ҫ���ӵ����
     * @param weight ����������ϵ�Ȩֵ
     * 
     */
    public void addInedge(L source,int weight) {
    	Integer temp = new Integer(weight);
    	this.inEdge.put(source, temp);
    }
    /**
     * @return ����һ��������ַ�����ʾ
     * 
     */
    public String toString() {
		
		return  "������Ϊ"+this.name.toString()+"\n"+"�������Ϊ"+this.inEdge.toString()+"\n"+"���г���Ϊ"+this.outEdge.toString()+"\n";
		
		
	}
	
    /**
	 * ���ص������name
	 * @return �������
	 */
	public L getname() {
		return this.name;
	}
	
	/**
	 * �����ܵ���õ�����е�ͱ߹��ɵ�Map
	 * @return ����ĳ��������е�ͱ߹��ɵ�Map
	 */
	public Map<L,Integer> getinEdge(){
		return this.inEdge;
	}
	
	/**
	 * ����ĳ�����ܵ�������е�ͱ߹��ɵ�Map
	 * @return ����ĳ��������е�ͱ߹��ɵ�Map
	 */
	public Map<L,Integer> getoutEdge(){
		return this.outEdge;
	}
    
    
}
