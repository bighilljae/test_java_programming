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
	HashMap<BowlingFrame, Integer> gameFrame;
	@Before
	public void init() {
		testGame = new BowlingGame(); // BowlingGame �ν��Ͻ� ����
		gameFrame = new HashMap<>();
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
		// 1Frame : 8-1 2Frame : 7-2 3Frame : 6-
		
		gameFrame.put(BowlingFrame.getInstanceof(1, 1), 8);
		gameFrame.put(BowlingFrame.getInstanceof(1, 2), 1);
		gameFrame.put(BowlingFrame.getInstanceof(2, 1), 7);
		gameFrame.put(BowlingFrame.getInstanceof(2, 2), 2);
		gameFrame.put(BowlingFrame.getInstanceof(3, 1), 6);
		assertEquals(Boolean.TRUE,
				BowlingFrame.getInstanceof(3, 2).equals(testGame.getNowFrame()));
		testGame.roll(4);
		testGame.roll(10);
		gameFrame.put(BowlingFrame.getInstanceof(3, 2), 4);
		gameFrame.put(BowlingFrame.getInstanceof(4, 1), 10);
		assertEquals("Strike Frame check", Boolean.TRUE, BowlingFrame
				.getInstanceof(5, 1).equals(testGame.getNowFrame()));
	}
	
	public void test_getScoreBoard(){
		testGame.roll(5);
		testGame.roll(2);
		
		assertEquals(ScoreFrame.getInstanceof(1,"81",9), testGame.requireFrame(1));
		assertEquals(ScoreFrame.getInstanceof(2,"72",9), testGame.requireFrame(2));
	}
	
	
}
