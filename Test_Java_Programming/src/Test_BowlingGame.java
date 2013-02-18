import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;


public class Test_BowlingGame {
	BowlingGame testGame;
	
	@Before
	public void init(){
		testGame = new BowlingGame();		// BowlingGame 인스턴스 생성
	}
	
	@Test
	public void test_GameStarted(){
		assertEquals("게임시작확인", Boolean.TRUE, testGame.gameStatus);
	}
	
	@Test
	public void test_roll(){
		assertEquals("roll(Integer) 파라미터 값 확인", Integer.valueOf(8), testGame.roll(8));
	}
	
	@Test
	public void test_getNowFrame(){
		testGame.roll(1);
		testGame.roll(7);
		testGame.roll(2);
		testGame.roll(6);
		// 1Frame : 8-1  2Frame : 7-2  3Frame : 6-
		HashMap<BowlingFrame, Integer> gameFrame = new HashMap<>();
		gameFrame.put(BowlingFrame.getInstanceof(1, 1), 8);
		gameFrame.put(BowlingFrame.getInstanceof(1, 2), 1);
		gameFrame.put(BowlingFrame.getInstanceof(2, 1), 7);
		gameFrame.put(BowlingFrame.getInstanceof(2, 2), 2);
		gameFrame.put(BowlingFrame.getInstanceof(3, 1), 6);
		assertEquals(Boolean.TRUE, BowlingFrame.getInstanceof(3, 2).equals(testGame.getNowFrame()));
		gameFrame.put(BowlingFrame.getInstanceof(3, 2), 4);
		gameFrame.put(BowlingFrame.getInstanceof(4, 1), 10);
		assertEquals("Strike Frame check", Boolean.TRUE, BowlingFrame.getInstanceof(5, 1).equals(testGame.getNowFrame()));
	}
}
