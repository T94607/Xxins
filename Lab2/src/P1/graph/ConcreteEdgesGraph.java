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
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    // AF(vertices)=ͼ�еĵ�
    // AF(edges)=ͼ�еı�
    // Representation invariant:
    // edges�����Ǵ���0��ʵ������Դ����յ�
    // vertex����������vertices��
    // ������֮�����ֻ��һ����
    // Safety from rep exposure:
    // ��vertices��edges����Ϊprivate
    // ����vertices��edges��mutable�������ڷ�������ʱ������defensive copies
    
    // TODO constructor
    public ConcreteEdgesGraph() {	//���췽��
   	}
    // TODO checkRep
    
    private void checkRep() {
        for (Edge<L> edge : edges) {
            assert vertices.contains(edge.getsource());
            assert vertices.contains(edge.gettarget());
            assert edge.getweight()>0;
        }
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
    	
    	if(vertices.contains(vertex)) {
    		System.out.println("��������Ѿ�����\n");
    		return false;
    	}
    	
    	vertices.add(vertex);
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
    	//ԭ��û���������򷵻�0��ԭ�����������򷵻�ԭ����Ȩֵ
        //weightΪ0��ɾ�������ߣ�weight����0�����ã�weightС��0���ش�����������׳��쳣���ߴ�ӡ
    	//throw new RuntimeException("not implemented");
    	int back = 0;
    	if(weight<0) {
    		System.out.println("ȨֵС��0�����벻�Ϸ�");
    		back = -1;
    	}
    	else if (weight == 0) {
    		for(Edge<L> temp : edges )//final�ֶ�ֻ�ܸ�ֵһ��
    		{
    			if(temp.getsource().equals(source)&&temp.gettarget().equals(target))
    			{
    				back = temp.getweight();
    				edges.remove(temp);
    				break;
    			}
    			
    		}
    		
    		
    	}
    	else {
    		for(Edge<L> temp : edges )//�����������������������Ȩֵ��finalֻ�ܸ�ֵһ�Σ����ֱ��ɾ���������
    		{
    			if(temp.getsource().equals(source)&&temp.gettarget().equals(target))
    			{
    				back = temp.getweight();
    				edges.remove(temp);
    				break;
    			}
    			
    		}
    		//����±�
    		vertices.add(source); 
			vertices.add(target);
			Edge<L> one=new Edge<L>(source,target,weight);
			edges.add(one);
    		
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
        if(vertices.contains(vertex)) {//���������ڣ�ɾ�������Լ��Ͷ������ӵı�
        	for(Edge<L> temp : edges)
        	{
        		if(temp.getsource().equals(vertex)||temp.gettarget().equals(vertex))
        		{
        			edges.remove(temp);
        		}
        		
        	}
        	vertices.remove(vertex);
        	checkRep();
        	return true;
        }
        else {
        	
        	return false;
        	
        }
        
        
    }
    /**
     * Get all the vertices in this graph.
     * 
     * @return the set of labels of vertices in this graph
     */
    @Override public Set<L> vertices() {
      //  throw new RuntimeException("not implemented");
        
        return vertices;
        
        
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
       // throw new RuntimeException("not implemented");
        Map<L,Integer> back = new HashMap<>();
       
        	for(Edge<L> temp : edges)
        	{
        		if(temp.gettarget().equals(target))
        		{
        			back.put(temp.getsource(), temp.getweight());
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
         
     	for(Edge<L> temp : edges)
     	{
     		if(temp.getsource().equals(source))
     		{
     			back.put(temp.gettarget(), temp.getweight());
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
		for (Edge<L> temp : edges) {
			back += temp.toString();
		}
		checkRep();
		return back;
    }
   
    
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
 class Edge<L> {

		// Abstraction function:
		// AF(source) = �ߵ����
	    // AF(target) = �ߵ��յ�
	    // AF(weight) = �ߵ�Ȩֵ
	    // Representation invariant:
	    // weight >0 source��target��Ϊ��
	    // Safety from rep exposure:
	    // ��source,target,weight����Ϊprivate 
   
  
    private final L source,target;
	private final int weight;
	
	/**
	 * ��ʼ�����췽������ʼ���±ߵ�������ͱߵ�Ȩֵ
	 * @param source2  �±ߵ�һ����
	 * @param target2  �±ߵ�����һ����
	 * @param wei  �±ߵ�Ȩֵ
	 */
	public Edge(L source,L target,int weight) {
			this.source = source;
			this.target = target;
			this.weight = weight;
			checkRep();
	}
	
    /**
     * ����ʾ������,�߲�Ϊ����Ȩֵ����0
     */
    public void checkRep(){
    	assert source!=null;
    	assert target!=null;
    	assert weight>0;
    }
    /**
     * 
     * @return �ߵ�һ����source
     */
    public L getsource() {
    	return source;
    }
    
    /**
     * 
     * @return �ߵ������target
     */
    public L gettarget() {
    	return target;
    }
    
    /**
	 * 
	 * @return ���رߵ�weight
	 */
	public int getweight() {
		checkRep();
		return weight;
	}
	
	/**
     * @return ����һ���ߵ��ַ���
     */
    // TODO toString()
	public String toString() {
		
		return source.toString() + "->" + target.toString() + "Ȩ����"+ weight + "\n";
		
	}
	
	
}
