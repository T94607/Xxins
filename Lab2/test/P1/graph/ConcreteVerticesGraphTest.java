/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<String>();
    }
   
    // Testing strategy for ConcreteVerticesGraph.toString()
    //  ��������������Ƿ����Ҫ��
    
    // TODO tests for ConcreteVerticesGraph.toString()
    @Test
    public void testConcreteEdgesGraphtoString() {
    	Graph<String> graph =emptyInstance();
    	String a="A";
    	String b="B";
    	graph.add(a);
    	graph.add(b);
    	graph.set("A", "B", 5);
    	graph.set("B", "A", 3);
    	assertEquals("������ΪA\n�������Ϊ{B=3}\n���г���Ϊ{B=5}\n"+"������ΪB\n�������Ϊ{A=5}\n���г���Ϊ{A=3}\n",graph.toString());
    }
       /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    //  ����ÿһ������
    
    // TODO tests for operations of Vertex
    
    /*
     * ����ǰ�����������ߣ���������������
     * */
    @Test
    public void testaddOutedge() {
    	Vertex<String> one=new Vertex<String>("A");
    	assertEquals(false,one.getoutEdge().containsKey("B"));
    	one.addOutedge("B",3);
    	assertEquals(true,one.getoutEdge().containsKey("B"));
    }
    /* Testing strategy
     * ����ǰ�����������ߣ���������������
     * */
    @Test
    public void testaddInedge() {
    	Vertex<String> one=new Vertex<String>("A");
    	assertEquals(false,one.getinEdge().containsKey("B"));
    	one.addInedge("B",3);
    	assertEquals(true,one.getinEdge().containsKey("B"));
    }
    
    /* Testing strategy
     * ɾ��ǰ���������ߣ�ɾ���󲻴���������
     * */
    @Test
    public void testdeleteOutedge() {
    	Vertex<String> one=new Vertex<String>("A");
    	one.addOutedge("B",3);
    	assertEquals(true,one.getoutEdge().containsKey("B"));
    	one.deleteOutedge("B");
    	assertEquals(false,one.getoutEdge().containsKey("B"));
    	
    }
    
    /* Testing strategy
     * ɾ��ǰ���������ߣ�ɾ���󲻴���������
     * */
    @Test
    public void testdeleteInedge() {
    	Vertex<String> one=new Vertex<String>("A");
    	one.addInedge("B",3);
    	assertEquals(true,one.getinEdge().containsKey("B"));
    	one.deleteInedge("B");
    	assertEquals(false,one.getinEdge().containsKey("B"));
    	
    }
    /*  Testing strategy
     *  �õ���ȷ�ķ���ֵ
     * */
    @Test
    public void testgetname() {
    	Vertex<String> one=new Vertex<String>("A");
    	assertEquals("A",one.getname());
    	
    }
    /*  Testing strategy
     *  ������� ���ռ��ͷǿռ�
     * */
    @Test
    public void testgetoutEdge() {
    	Vertex<String> one=new Vertex<String>("A");
    	Map<String,Integer> back=new HashMap<String,Integer>();
    	assertEquals(back, one.getoutEdge());
    	one.addOutedge("B",3);
    	one.addOutedge("C",2);
    	back.put("B", 3);
    	back.put("C", 2);
    	assertEquals(back, one.getoutEdge());
    }
    /*  Testing strategy
     *  ������� ���ռ��ͷǿռ�
     * */
    @Test
    public void testgetinEdge() {
    	Vertex<String> one=new Vertex<String>("A");
    	Map<String,Integer> back=new HashMap<String,Integer>();
    	assertEquals(back, one.getinEdge());
    	one.addInedge("B",3);
    	one.addInedge("C",2);
    	back.put("B", 3);
    	back.put("C", 2);
    	assertEquals(back, one.getinEdge());
    }
    
    
}
