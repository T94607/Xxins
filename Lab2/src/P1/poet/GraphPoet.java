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
    // AF(graph)=文章中所有字符串构成的图
    // Representation invariant:
    // 必须保存从语料库文件生成的图
    // Safety from rep exposure:
    // 设置graph为private
    
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
				String mytemp2= mytemp1.toLowerCase();//图中顶点必须小写
				content1.append(mytemp2);//将读入内容放到content1中
				content1.append(" ");//多放一个空格便于分隔单词
			}
			String content2 = new String(content1);//Stringbuffer转化为string
			content = content2.split(" ");//将读到的内容用空格分隔
			
			 get.close();
			 if(content==null) {
				 return;
			 }
		    for(int i=0;i<content.length-1;i++) {//遍历已经构建好的顶点字符串集合，将顶点和边加入graph中
		    	//放入边时讨论，是否已经存在这条边
		    	if(this.graph.targets(content[i]).containsKey(content[i+1])) {//如果已经存在，则set时将权值在原来基础上加一
		    			//删除原来的边，得到返回值，再重新设置
		    			this.graph.set(content[i], content[i+1], this.graph.set(content[i], content[i+1], 0)+1);
		    	}
		    	else {//如果不存在，直接将权重设置为1
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
       // lower和 word 唯一区别就是大小写 
        String[] lower= temp.split(" "); //小写的输入串，用于查找  
        String[] word = input.split(" "); //原版的输入串，用于赋值
        output.append(word[0]);
        for(int i=1;i<lower.length;i++)
        {
        	//判断是否需要插入单词
        	if(graph.vertices().contains(lower[i-1])&&graph.vertices().contains(lower[i])) {
        		Map<String,Integer> targets = this.graph.targets(lower[i-1]);//前一个点的出边集
        		Map<String,Integer> sources = this.graph.sources(lower[i]);//后一个点的入边集
        		Set<String> intersection = sources.keySet();
        		intersection.retainAll(targets.keySet());
        		//如果有交集
        		if(!intersection.isEmpty()) {
        			//找到权重最大的边,将对应点放入output
        			Integer max = new Integer(0);
        			String temp1 = "";
        			for(String temp2 : intersection)
        			{
        				if(targets.get(temp2)+sources.get(temp2)> max) {
        					max = targets.get(temp2)+sources.get(temp2);
        					temp1 = temp2;
        				}
        				
        			}
        			//循环结束后temp1中保存了权重最大的桥对应的单词,把它放入输出中
        			output.append(" ");
        			output.append(temp1);
        		}
        		
        	}
        	
        	//把当前单词放入输出中
        		output.append(" ");
        		output.append(word[i]);
        
        }
        	checkRep();
          return output.toString();
        
    }
    
}
