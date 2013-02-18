import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class Test_BowlingGame {
	BowlingGame testGame;
	@Before
	public void init() {
		testGame = new BowlingGame(); // BowlingGame �ν��Ͻ� ����
	}

	@Test
	public void test_GameStarted() {
		assertEquals("���ӽ���Ȯ��", Boolean.TRUE, testGame.gameStatus);
	}
	
	// test_Roll��, test_getNowFrame�� ���������� ����Ǿ�� �ϴµ�, Test Method �� ������ ������ �������־�� �ϰڴ�!
	@Test
	public void testInOrder_roll_getNowFrame_getScoreBoard(){
		test_roll();
		test_getNowFrame();
		test_getScoreBoard();
	}
	
	public void test_roll() {
		assertEquals("roll(Integer) �Ķ���� �� Ȯ��", Integer.valueOf(8),
				testGame.roll(8));
	}

	public void test_getNowFrame() {
		testGame.roll(1);
		testGame.roll(7);
		testGame.roll(2);
		testGame.roll(6);
		
		try {
			assertEquals(Boolean.TRUE,
					BowlingFrame.getInstanceof(3, 2).equals(testGame.getNowFrame()));
		
		testGame.roll(4);
		testGame.roll(10);
		assertEquals("Strike Frame check", Boolean.TRUE, BowlingFrame
				.getInstanceof(5, 1).equals(testGame.getNowFrame()));
		} catch (Exception exception) {
			// TODO Auto-generated catch block
			assertEquals(Boolean.TRUE, exception.getMessage().equals("GameOverException")); // GameOverException�� �߻��ϸ� ���� Exception
		}
	}
	
	public void test_getScoreBoard() {
		testGame.roll(5);
		testGame.roll(2);
		testGame.roll(10);
		testGame.roll(10);
		testGame.roll(8);
		testGame.roll(2);
		testGame.roll(4);
		testGame.roll(4);
		testGame.roll(10);
		testGame.roll(10);
		testGame.roll(10);
		try{
			assertEquals(Boolean.TRUE, ScoreFrame.getInstanceof(1,"81",9).equals(testGame.requireFrame(1)));
			assertEquals(Boolean.TRUE, ScoreFrame.getInstanceof(2,"72",9).equals(testGame.requireFrame(2)));
			assertEquals(Boolean.TRUE, ScoreFrame.getInstanceof(3,"6/",20).equals(testGame.requireFrame(3)));
			assertEquals(Boolean.TRUE, ScoreFrame.getInstanceof(4,"X",17).equals(testGame.requireFrame(4)));
			assertEquals(Boolean.TRUE, ScoreFrame.getInstanceof(5,"52",7).equals(testGame.requireFrame(5)));	
			assertEquals(Boolean.TRUE, ScoreFrame.getInstanceof(6,"X",28).equals(testGame.requireFrame(6)));
			assertEquals(Boolean.TRUE, ScoreFrame.getInstanceof(7,"X",20).equals(testGame.requireFrame(7)));
			assertEquals(Boolean.TRUE, ScoreFrame.getInstanceof(8,"8/",14).equals(testGame.requireFrame(8)));
			assertEquals(Boolean.TRUE, ScoreFrame.getInstanceof(9,"44",8).equals(testGame.requireFrame(9)));
			assertEquals(Boolean.TRUE, ScoreFrame.getInstanceof(10,"XXX",30).equals(testGame.requireFrame(10)));
			
		}catch(Exception exception){
			assertEquals(Boolean.TRUE, exception.getMessage().equals("GameOverException")); 		// exception �� GameOverExcpetion�� ��� �������� ���� �߻��̹Ƿ� ���� ó�� ���־�� ��.
		}
	}
	
	
}
