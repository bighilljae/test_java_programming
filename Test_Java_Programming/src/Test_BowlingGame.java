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
		testGame = new BowlingGame(); // BowlingGame 인스턴스 생성
		gameFrame = new HashMap<>();
	}

	@Test
	public void test_GameStarted() {
		assertEquals("게임시작확인", Boolean.TRUE, testGame.gameStatus);
	}
	
	// test_Roll과, test_getNowFrame이 순차적으로 진행되어야 하는데, Test Method 의 순서를 엄밀히 지정해주어야 하겠다!
	@Test
	public void testInOrder_roll_getNowFrame_getScoreBoard(){
		test_roll();
		test_getNowFrame();
		test_getScoreBoard();
	}
	
	public void test_roll() {
		assertEquals("roll(Integer) 파라미터 값 확인", Integer.valueOf(8),
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
