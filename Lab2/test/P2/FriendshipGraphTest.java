package P2;

import static org.junit.Assert.*;

import org.junit.Test;

public class FriendshipGraphTest {
	FriendshipGraph graph=new FriendshipGraph();
	
	/*
	 * Testing strategy
	 * �ж�����޶���
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
	 * ����������бߺ��ޱ�
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
		 assertEquals("a->bȨ����1\n" + 
		 		"a->cȨ����1\n" + 
		 		"b->dȨ����1\n" + 
		 		"b->eȨ����1\n" + 
		 		"c->fȨ����1\n" + 
		 		"e->bȨ����1\n" + 
		 		"f->cȨ����1\n",graph.getgraph().toString());
		 
	}
	
	/*
	 * Testing strategy
	 * ����������֮��ľ��뻮�֣�����Ϊ0������Ϊ-1������Ϊ����1
	 * �����Ƿ�ͬһ���˻��֣�ͬһ����֮����룬������֮�����
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
	 * �����ص�personGraph�Ƿ��������ͼ�е�Ԫ�ؼ���
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
