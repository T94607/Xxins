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
    //  检查两个点的情况是否符合要求
    
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
    	assertEquals("顶点名为A\n所有入边为{B=3}\n所有出边为{B=5}\n"+"顶点名为B\n所有入边为{A=5}\n所有出边为{A=3}\n",graph.toString());
    }
       /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    //  测试每一个函数
    
    // TODO tests for operations of Vertex
    
    /*
     * 加入前不存在这条边，加入后存在这条边
     * */
    @Test
    public void testaddOutedge() {
    	Vertex<String> one=new Vertex<String>("A");
    	assertEquals(false,one.getoutEdge().containsKey("B"));
    	one.addOutedge("B",3);
    	assertEquals(true,one.getoutEdge().containsKey("B"));
    }
    /* Testing strategy
     * 加入前不存在这条边，加入后存在这条边
     * */
    @Test
    public void testaddInedge() {
    	Vertex<String> one=new Vertex<String>("A");
    	assertEquals(false,one.getinEdge().containsKey("B"));
    	one.addInedge("B",3);
    	assertEquals(true,one.getinEdge().containsKey("B"));
    }
    
    /* Testing strategy
     * 删除前存在这条边，删除后不存在这条边
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
     * 删除前存在这条边，删除后不存在这条边
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
     *  得到正确的返回值
     * */
    @Test
    public void testgetname() {
    	Vertex<String> one=new Vertex<String>("A");
    	assertEquals("A",one.getname());
    	
    }
    /*  Testing strategy
     *  两种情况 ：空集和非空集
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
     *  两种情况 ：空集和非空集
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
