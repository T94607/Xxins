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
    // 没有重复的Person
    // Safety from rep exposure:
    // 将personGraph设置为private
    // 由于personGraph是immutable，所以在返回时不需要进行defensive copies
	
	/**
	 * 初始化构造方法
	 */
    FriendshipGraph(){
    	personGraph = new ConcreteEdgesGraph<Person>();
    	
    }

	/**
	 * 返回ConcreteVerticesGraph图
	 * @return ConcreteVerticesGraph图
	 */
    public ConcreteEdgesGraph<Person> getgraph(){
    	return personGraph;
    }

	/**
	 * 在图中增加新人
	 * @param x 待加入的新Person
	 */
	public void addVertex(Person x) 
	{
		for(Person temp:this.getgraph().vertices())
		{
			if(temp.toString().equals(x.toString()))
			{
				System.out.println("存在同名的人\n");
				System.exit(0);
			}
		}
		personGraph.add(x);
		  
	}

	/**
	 * 为某个人增加朋友
	 * @param x 这个人
	 * @param y 增加的朋友
	 */
	 public void addEdge(Person x,Person y)
	 {
		 
		 personGraph.set(x, y, 1);

	 }
	 
		/**
		 * 得到两个人之间的最短距离
		 * @param x 第一个人
		 * @param y 第二个人
		 * @return 两个人之间的最短距离
		 */
	public int getDistance(Person x,Person y)//每次计算距离都从x出发进行一次广搜，同时计算路径上各点距离
	{
		
		if(x.toString()==y.toString())//如果计算的是自己到自己的距离，返回0
		{
			return 0;
		}
		//计算的不是自己到自己的距离
		else {
		Queue<Person> queue = new LinkedList<>();
		queue.clear();
		for(Person temp : personGraph.vertices())//所有人tag,distance清零
		{
			temp.settag(0);
			temp.setdistance(0); 
		}
		x.setdistance(0);//x到自己的距离为0
		queue.add(x);
		x.settag(1);//tag表示距离是否已经确定
		while(!queue.isEmpty())//广度优先搜索，计算距离
		{
		    Person head = queue.remove(); 
		    Map<Person,Integer> outedgemap = personGraph.targets(head);
		    Set<Person> outedgeset = outedgemap.keySet();
		    for(Person temp : outedgeset)//未确定距离的元素 对它的距离进行修改
		    {
		    	if(temp.gettag()==0){temp.setdistance(head.getdistance() + 1);}
		    }
		    if(outedgeset.contains(y))//如果当前队首元素的出边中包含y，直接返回已经计算好的距离
		    {
		    	return y.getdistance();
		    }
		    else {
		    	 for(Person temp : outedgeset)//广度优先搜索的思想，将所有未确定距离的顶点入队
		    	{
		    		if(temp.gettag()==0)
		    		{
		    			queue.add(temp);
		    			temp.settag(1);
		    		}
		    	}
		    }
		}
		//x的所有可达路径距离都计算完毕，也未找到y，说明x不可达y，返回-1
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
