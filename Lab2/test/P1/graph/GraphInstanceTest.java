/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
 
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    /*
     * ��ʼ�������ǳ���ģ���Ҫ������д�ſ��Խ��в���  
     * ������������˲�ͬ����д��ʵ�ֶԲ�ͬ��Ĳ���
     * ������ֻ��Ҫ��Graph�ӿ��еķ���дһ�����Կ�ܼ���
     * ������ʵ�ָ���Ĳ��ԣ�����ӶԲ�ͬ��ʵ����Ĳ���
     * */
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    // TODO other tests for instance methods of Graph
    
    /* Testing strategy
     * �����������ͻ��֣����Ѵ��ڣ��㲻����
     */
    @Test
    public void testAdd() {
    	Graph<String> graph =emptyInstance();
    	String a="A";
    	assertEquals(true,graph.add(a));
    	assertEquals(false,graph.add(a));
    }
    
    
    /* Testing strategy
     * ������ߵ����ͻ��֣����Ѿ����ڣ��߲�����
     * ������ߵ�Ȩֵ���֣�ȨֵΪ0��Ȩֵ����0��ȨֵС��0
     */
    @Test
    public void testSet() {
    	Graph<String> graph =emptyInstance();
    	assertEquals(0,graph.set("A", "B", 5));
    	assertEquals(0,graph.set("B", "C", 4));
    	assertEquals(5,graph.set("A", "B", 6));
    	assertEquals(6,graph.set("A", "B", 0));
    	assertEquals(0,graph.set("A", "C", 0));
    	assertEquals(-1,graph.set("A", "B", -3));	
    }
    
    /* Testing strategy
     * �����������ͻ��֣����Ѿ����ڣ��㲻����
     */
    @Test
    public void testRemove() {
    	Graph<String> graph =emptyInstance();
    	String a="A";
    	String b="B";
    	String c="C";
    	graph.add(a);
    	graph.add(b);
    	graph.add(c);
    	graph.set(a, b, 5);
    	graph.set(b, c, 4);
    	assertEquals(true,graph.remove(a));
    	assertEquals(false,graph.remove(a));  	
    }
    
    
    /* Testing strategy
     * �����������ͼ�����ǿ�ͼ
     */
    @Test
    public void testVertices() {
    	Graph<String> graph =emptyInstance();
    	String a="A";
    	String b="B";
    	String c="C";
    	assertEquals(false,graph.vertices().contains(a));
    	assertEquals(false,graph.vertices().contains(b));
    	assertEquals(false,graph.vertices().contains(c));
    	graph.add(a);
    	graph.add(b);
    	graph.add(c);
    	assertEquals(true,graph.vertices().contains(a));
    	assertEquals(true,graph.vertices().contains(b));
    	assertEquals(true,graph.vertices().contains(c));
    }
    
    /* Testing strategy
     * ������������Ѿ����ڣ��߲�����
     */
    @Test
    public void testSources() {
    	Graph<String> graph =emptyInstance();
    	String a="A";
    	String b="B";
    	String c="C";
    	graph.add(a);
    	graph.add(b);
    	graph.add(c);
    	graph.set(a, b, 5);
    	graph.set(b, c, 4);
    	graph.set(a, c, 3);
    	Map<String,Integer> map=new HashMap<String,Integer>();
    	map.put(a,3);
    	map.put(b,4);
    	assertEquals(map,graph.sources(c)); 	
    }
    
    /* Testing strategy
     *  ������������Ѿ����ڣ��߲�����
     */
    @Test
    public void testTargets() {
    	Graph<String> graph =emptyInstance();
    	String a="A";
    	String b="B";
    	String c="C";
    	graph.add(a);
    	graph.add(b);
    	graph.add(c);
    	graph.set(a, b, 5);
    	graph.set(b, c, 4);
    	graph.set(a, c, 3);
    	Map<String,Integer> map=new HashMap<String,Integer>();
    	map.put(c,3);
    	map.put(b,5);
    	assertEquals(map,graph.targets(a)); 	
    }
    
    
}
