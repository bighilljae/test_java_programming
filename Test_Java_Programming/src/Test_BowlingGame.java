import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class Test_BowlingGame {
	BowlingGame testGame;
	
	@Before
	public void init(){
		testGame = new BowlingGame();
	}
	
	@Test
	public void test_GameStarted(){
		assertEquals(Boolean.TRUE, testGame.gameStatus);
	}
}
