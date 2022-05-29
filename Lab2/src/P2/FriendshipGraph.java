package P2;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import P1.graph.ConcreteEdgesGraph;

public class FriendshipGraph {

	private final ConcreteEdgesGraph<Person> personGraph;
	// Abstraction function:
    // AF(personFraph)=Social NetWork
    // Representation invariant:
    // û���ظ���Person
    // Safety from rep exposure:
    // ��personGraph����Ϊprivate
    // ����personGraph��immutable�������ڷ���ʱ����Ҫ����defensive copies
	
	/**
	 * ��ʼ�����췽��
	 */
    FriendshipGraph(){
    	personGraph = new ConcreteEdgesGraph<Person>();
    	
    }

	/**
	 * ����ConcreteVerticesGraphͼ
	 * @return ConcreteVerticesGraphͼ
	 */
    public ConcreteEdgesGraph<Person> getgraph(){
    	return personGraph;
    }

	/**
	 * ��ͼ����������
	 * @param x ���������Person
	 */
	public void addVertex(Person x) 
	{
		for(Person temp:this.getgraph().vertices())
		{
			if(temp.toString().equals(x.toString()))
			{
				System.out.println("����ͬ������\n");
				System.exit(0);
			}
		}
		personGraph.add(x);
		  
	}

	/**
	 * Ϊĳ������������
	 * @param x �����
	 * @param y ���ӵ�����
	 */
	 public void addEdge(Person x,Person y)
	 {
		 
		 personGraph.set(x, y, 1);

	 }
	 
		/**
		 * �õ�������֮�����̾���
		 * @param x ��һ����
		 * @param y �ڶ�����
		 * @return ������֮�����̾���
		 */
	public int getDistance(Person x,Person y)//ÿ�μ�����붼��x��������һ�ι��ѣ�ͬʱ����·���ϸ������
	{
		
		if(x.toString()==y.toString())//�����������Լ����Լ��ľ��룬����0
		{
			return 0;
		}
		//����Ĳ����Լ����Լ��ľ���
		else {
		Queue<Person> queue = new LinkedList<>();
		queue.clear();
		for(Person temp : personGraph.vertices())//������tag,distance����
		{
			temp.settag(0);
			temp.setdistance(0); 
		}
		x.setdistance(0);//x���Լ��ľ���Ϊ0
		queue.add(x);
		x.settag(1);//tag��ʾ�����Ƿ��Ѿ�ȷ��
		while(!queue.isEmpty())//��������������������
		{
		    Person head = queue.remove(); 
		    Map<Person,Integer> outedgemap = personGraph.targets(head);
		    Set<Person> outedgeset = outedgemap.keySet();
		    for(Person temp : outedgeset)//δȷ�������Ԫ�� �����ľ�������޸�
		    {
		    	if(temp.gettag()==0){temp.setdistance(head.getdistance() + 1);}
		    }
		    if(outedgeset.contains(y))//�����ǰ����Ԫ�صĳ����а���y��ֱ�ӷ����Ѿ�����õľ���
		    {
		    	return y.getdistance();
		    }
		    else {
		    	 for(Person temp : outedgeset)//�������������˼�룬������δȷ������Ķ������
		    	{
		    		if(temp.gettag()==0)
		    		{
		    			queue.add(temp);
		    			temp.settag(1);
		    		}
		    	}
		    }
		}
		//x�����пɴ�·�����붼������ϣ�Ҳδ�ҵ�y��˵��x���ɴ�y������-1
			return -1;
		}
	}
	
	
	public static void main(String[] args)
	{
		 FriendshipGraph graph = new FriendshipGraph();
		 Person rachel = new Person("Rachel");
		 Person ross = new Person("Ross");
		 Person ben = new Person("Ben");
		 Person kramer = new Person("Kramer");
		 graph.addVertex(rachel);
		 graph.addVertex(ross);
		 graph.addVertex(ben);
		 graph.addVertex(kramer);
		 graph.addEdge(rachel, ross);
		 graph.addEdge(ross, rachel);
		 graph.addEdge(ross, ben);
		 graph.addEdge(ben, ross);
		 System.out.println(graph.getDistance(rachel, ross));
		 //should print 1
		 System.out.println(graph.getDistance(rachel, ben));
		 //should print 2
		 System.out.println(graph.getDistance(rachel, rachel));
		 //should print 0
		 System.out.println(graph.getDistance(rachel, kramer));
		 //should print -1
		
	}
}
