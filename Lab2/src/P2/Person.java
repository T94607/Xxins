package P2;

public class Person {

	private final String name ;
	private int tag;
	private int distance;
	// Abstraction function:
    // AF(name)=人的名字
	// AF(tag)=distance是否已经确定
	// AF(distance)=到源点的距离
    // Representation invariant:
    // 没有重复名字
    // Safety from rep exposure:
    // 将myname设置为private
    // 由于myname是immutable，所以在返回时不需要进行defensive copies
	
	
	
	//构造函数
	public Person(String string) {
		// TODO Auto-generated constructor stub
		this.name = string;
	}
	
	/**
	 * @param tag 距离是否确定 确定为1 未确定为0
     * @return  tag
     */
	public int gettag() {  
		return this.tag;
	}
	/**
	 * @param distance 起点Person到当前Person的距离
     * @return distance
     */
	public int getdistance() {  
		return this.distance;
	}
	/**
	 * 
     * 修改tag
     */
	public void settag(int tag) {  
		 this.tag = tag;
	}
	/**
	 * 
     * 修改distance 
     */
	public void setdistance(int distance) {  
			this.distance = distance;
	}
	
	/**
	 * 
     * @return 人的名字
     */
	public String toString() {  
				return name;
	}
	
	
}
