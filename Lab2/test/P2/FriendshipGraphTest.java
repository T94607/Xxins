package P2;

import static org.junit.Assert.*;

import org.junit.Test;

public class FriendshipGraphTest {
	FriendshipGraph graph=new FriendshipGraph();
	
	/*
	 * Testing strategy
	 * 有顶点和无顶点
	 */
	@Test
	public void  addVertexTest() {
		Person A = new Person("a");
		Person B = new Person("b");
		Person C = new Person("c");
		Person D = new Person("d");
		Person E = new Person("e");
		Person F = new Person("f");
		
		 assertFalse(graph.getgraph().vertices().contains(A));
		 
		 graph.addVertex(A);
		 graph.addVertex(B);
		 graph.addVertex(C);
		 graph.addVertex(D);
		 graph.addVertex(E);
		 graph.addVertex(F);
		 assertTrue(graph.getgraph().vertices().contains(A));
		 assertTrue(graph.getgraph().vertices().contains(B));
		 assertTrue(graph.getgraph().vertices().contains(C));
		 assertTrue(graph.getgraph().vertices().contains(D));
		 assertTrue(graph.getgraph().vertices().contains(E));
		 assertTrue(graph.getgraph().vertices().contains(F));
		 
	}
	
	/*
	 * Testing strategy
	 * 两种情况：有边和无边
	 */
	@Test
	public void  addEdgeTest() {
		Person A = new Person("a");
		Person B = new Person("b");
		Person C = new Person("c");
		Person D = new Person("d");
		Person E = new Person("e");
		Person F = new Person("f");
		
		 assertFalse(graph.getgraph().sources(B).containsKey(A));
		
		 graph.addVertex(A);
		 graph.addVertex(B);
		 graph.addVertex(C);
		 graph.addVertex(D);
		 graph.addVertex(E);
		 graph.addVertex(F);
		 graph.addEdge(A,B);
		 graph.addEdge(A,C);
		 graph.addEdge(B,D);
		 graph.addEdge(B,E);
		 graph.addEdge(C,F);
		 graph.addEdge(E,B);
		 graph.addEdge(F,C);
		 assertEquals("a->b权重是1\n" + 
		 		"a->c权重是1\n" + 
		 		"b->d权重是1\n" + 
		 		"b->e权重是1\n" + 
		 		"c->f权重是1\n" + 
		 		"e->b权重是1\n" + 
		 		"f->c权重是1\n",graph.getgraph().toString());
		 
	}
	
	/*
	 * Testing strategy
	 * 按照两个人之间的距离划分：距离为0，距离为-1，距离为大于1
	 * 按照是否同一个人划分：同一个人之间距离，两个人之间距离
	 */
	
	@Test
	public void  getDistanceTest() {
	
		Person A = new Person("a");
		Person B = new Person("b");
		Person C = new Person("c");
		Person D = new Person("d");
		Person E = new Person("e");
		Person F = new Person("f");
		 graph.addVertex(A);
		 graph.addVertex(B);
		 graph.addVertex(C);
		 graph.addVertex(D);
		 graph.addVertex(E);
		 graph.addVertex(F);
		 graph.addEdge(A,B);
		 graph.addEdge(A,C);
		 graph.addEdge(B,D);
		 graph.addEdge(B,E);
		 graph.addEdge(C,F);
		 graph.addEdge(E,B);
		 graph.addEdge(F,C);
		 assertEquals(0, graph.getDistance(A, A));
		 assertEquals(1, graph.getDistance(A, B));
		 assertEquals(2, graph.getDistance(A, F));
		 assertEquals(-1, graph.getDistance(E, F));
		
	}
	
	/*
	 * Testing strategy
	 * 看返回的personGraph是否包含所有图中的元素即可
	 */
	@Test
	public void getgraphtest() {
		Person A = new Person("a");
		Person B = new Person("b");
		graph.addVertex(A);
		graph.addVertex(B);
		graph.addEdge(A, B);
		assertTrue(graph.getgraph().targets(A).containsKey(B));
	}
	
	

}
