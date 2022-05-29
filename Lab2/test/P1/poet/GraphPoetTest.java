/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // TODO tests
    
    /* Testing strategy
     * ����Ҫ������ļ����֣����ļ����ǿ��ļ�
     * ������֮��ָ���ʽ���֣��ո�ָ������з��ָ�
     * ����ÿ��ȡֵ���£�
     */
    @Test
    public void testGraphPoet() throws IOException{
    	 final GraphPoet nimoy1 = new GraphPoet(new File("test/P1/poet/null.txt"));
         final String input1 = "You are a good man.";
         assertEquals("You are a good man.",nimoy1.poem(input1));
         final GraphPoet nimoy2 = new GraphPoet(new File("test/P1/poet/1.txt"));
         final String input2 = "You are a good man.";
         assertEquals("You are really a good man.",nimoy2.poem(input2));
         final GraphPoet nimoy3 = new GraphPoet(new File("test/P1/poet/11.txt"));
         final String input3 = "You are a good man.";
         assertEquals("You are really a good man.",nimoy3.poem(input3));  
    }
    
    /* Testing strategy
     * ��ͼ�б�Ȩֵ���֣�ȨֵȫΪ1,Ȩֵ��ȫ��1
     * ����ÿ��ȡֵ���£�
     */
    @Test
    public void testPoem() throws IOException{
    	 final GraphPoet nimoy1 = new GraphPoet(new File("test/P1/poet/2.txt"));
         final String input1 = "You are a good man.";
         assertEquals("You are really a good man.",nimoy1.poem(input1));
         final GraphPoet nimoy2 = new GraphPoet(new File("test/P1/poet/22.txt"));
         final String input2 = "You are a good man.";
         assertEquals("You are truely a good man.",nimoy2.poem(input2));  
    }
    
}
