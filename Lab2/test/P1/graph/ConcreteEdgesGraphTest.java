/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<String>();
    }
   
    // Testing strategy for ConcreteEdgesGraph.toString()
    /*
     * 两种情况：没有边，有边
     * 
     * */
    
    // TODO tests for ConcreteEdgesGraph.toString()
    @Test
    public void testConcreteEdgesGraphtoString() {
    	Graph<String> graph =emptyInstance();
    	String a="A";
    	String b="B";
    	graph.add(a);
    	graph.add(b);
    	assertEquals("",graph.toString());
    	graph.set("A", "B", 5);
    	graph.set("B", "A", 3);
    	assertEquals("A->B权重是5\n"+"B->A权重是3\n",graph.toString());
    }
    
    /*
     * Testing Edge...
     */
    /* Testing strategy
     * 测试终点的返回值
     */
    @Test
    public void testgetsource() {
    	Edge<String> one=new Edge<String>("A","B",5);
    	assertEquals("A", one.getsource());
    	
    }
    
    /* Testing strategy
     * 测试源点的返回值
     */
    @Test
    public void testgettarget() {
    	Edge<String> one=new Edge<String>("A","B",5);
    	assertEquals("B", one.gettarget());
    }
    
    /* Testing strategy
     * 测试权重的返回值
     */
    @Test
    public void testgetweight() {
    	Edge<String> one=new Edge<String>("A","B",5);
    	assertEquals(5, one.getweight());
    }
    
}
