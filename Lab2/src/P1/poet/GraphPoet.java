/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import P1.graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
    private final Graph<String> graph = Graph.<String>empty();
    
    // Abstraction function:
    // AF(graph)=�����������ַ������ɵ�ͼ
    // Representation invariant:
    // ���뱣������Ͽ��ļ����ɵ�ͼ
    // Safety from rep exposure:
    // ����graphΪprivate
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
       try (
    		   // throw new RuntimeException("not implemented");
    		   BufferedReader get = new BufferedReader(new FileReader(corpus))
    		   )
       
       {
    	   String mytemp1 = null;
		   String[] content = null;
		   StringBuffer content1 = new StringBuffer();
		   
			while((mytemp1=get.readLine())!=null)
			{
				String mytemp2= mytemp1.toLowerCase();//ͼ�ж������Сд
				content1.append(mytemp2);//���������ݷŵ�content1��
				content1.append(" ");//���һ���ո���ڷָ�����
			}
			String content2 = new String(content1);//Stringbufferת��Ϊstring
			content = content2.split(" ");//�������������ÿո�ָ�
			
			 get.close();
			 if(content==null) {
				 return;
			 }
		    for(int i=0;i<content.length-1;i++) {//�����Ѿ������õĶ����ַ������ϣ�������ͱ߼���graph��
		    	//�����ʱ���ۣ��Ƿ��Ѿ�����������
		    	if(this.graph.targets(content[i]).containsKey(content[i+1])) {//����Ѿ����ڣ���setʱ��Ȩֵ��ԭ�������ϼ�һ
		    			//ɾ��ԭ���ıߣ��õ�����ֵ������������
		    			this.graph.set(content[i], content[i+1], this.graph.set(content[i], content[i+1], 0)+1);
		    	}
		    	else {//��������ڣ�ֱ�ӽ�Ȩ������Ϊ1
		    		this.graph.set(content[i], content[i+1], 1);
		    	}
		    }
		    checkRep();
		    return;
	}
       
        

        
    }
    
    // TODO checkRep
    private void checkRep() {
  		assert graph != null;
  	}
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
        //throw new RuntimeException("not implemented");
        StringBuffer output = new StringBuffer();
        String temp = input.toLowerCase();
       // lower�� word Ψһ������Ǵ�Сд 
        String[] lower= temp.split(" "); //Сд�����봮�����ڲ���  
        String[] word = input.split(" "); //ԭ������봮�����ڸ�ֵ
        output.append(word[0]);
        for(int i=1;i<lower.length;i++)
        {
        	//�ж��Ƿ���Ҫ���뵥��
        	if(graph.vertices().contains(lower[i-1])&&graph.vertices().contains(lower[i])) {
        		Map<String,Integer> targets = this.graph.targets(lower[i-1]);//ǰһ����ĳ��߼�
        		Map<String,Integer> sources = this.graph.sources(lower[i]);//��һ�������߼�
        		Set<String> intersection = sources.keySet();
        		intersection.retainAll(targets.keySet());
        		//����н���
        		if(!intersection.isEmpty()) {
        			//�ҵ�Ȩ�����ı�,����Ӧ�����output
        			Integer max = new Integer(0);
        			String temp1 = "";
        			for(String temp2 : intersection)
        			{
        				if(targets.get(temp2)+sources.get(temp2)> max) {
        					max = targets.get(temp2)+sources.get(temp2);
        					temp1 = temp2;
        				}
        				
        			}
        			//ѭ��������temp1�б�����Ȩ�������Ŷ�Ӧ�ĵ���,�������������
        			output.append(" ");
        			output.append(temp1);
        		}
        		
        	}
        	
        	//�ѵ�ǰ���ʷ��������
        		output.append(" ");
        		output.append(word[i]);
        
        }
        	checkRep();
          return output.toString();
        
    }
    
}
