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
     * 初始化方法是抽象的，需要子类重写才可以进行测试  
     * 子类对它进行了不同的重写，实现对不同类的测试
     * 父类中只需要对Graph接口中的方法写一个测试框架即可
     * 子类中实现父类的测试，再添加对不同的实现类的测试
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
     * 按加入点的类型划分：点已存在，点不存在
     */
    @Test
    public void testAdd() {
    	Graph<String> graph =emptyInstance();
    	String a="A";
    	assertEquals(true,graph.add(a));
    	assertEquals(false,graph.add(a));
    }
    
    
    /* Testing strategy
     * 按加入边的类型划分：边已经存在，边不存在
     * 按输入边的权值划分：权值为0，权值大于0，权值小于0
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
     * 按加入点的类型划分：点已经存在，点不存在
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
     * 两种情况：空图，不是空图
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
     * 两种情况：边已经存在，边不存在
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
     *  两种情况：边已经存在，边不存在
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
