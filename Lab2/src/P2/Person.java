package P2;

public class Person {

	private final String name ;
	private int tag;
	private int distance;
	// Abstraction function:
    // AF(name)=�˵�����
	// AF(tag)=distance�Ƿ��Ѿ�ȷ��
	// AF(distance)=��Դ��ľ���
    // Representation invariant:
    // û���ظ�����
    // Safety from rep exposure:
    // ��myname����Ϊprivate
    // ����myname��immutable�������ڷ���ʱ����Ҫ����defensive copies
	
	
	
	//���캯��
	public Person(String string) {
		// TODO Auto-generated constructor stub
		this.name = string;
	}
	
	/**
	 * @param tag �����Ƿ�ȷ�� ȷ��Ϊ1 δȷ��Ϊ0
     * @return  tag
     */
	public int gettag() {  
		return this.tag;
	}
	/**
	 * @param distance ���Person����ǰPerson�ľ���
     * @return distance
     */
	public int getdistance() {  
		return this.distance;
	}
	/**
	 * 
     * �޸�tag
     */
	public void settag(int tag) {  
		 this.tag = tag;
	}
	/**
	 * 
     * �޸�distance 
     */
	public void setdistance(int distance) {  
			this.distance = distance;
	}
	
	/**
	 * 
     * @return �˵�����
     */
	public String toString() {  
				return name;
	}
	
	
}
