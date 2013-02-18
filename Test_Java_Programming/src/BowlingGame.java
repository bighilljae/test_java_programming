
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


public class BowlingGame {

	public static final Exception GameOverException = new Exception("GameOverException");
	public Boolean gameStatus = Boolean.FALSE;
	BowlingFrame gameFrame = new BowlingFrame();
	ArrayList<Integer> gameScoreBoard = new ArrayList<Integer>();
	
	public BowlingGame() {
		gameStatus = Boolean.TRUE;			// ���� ������ �˸�.
	}

	/**
	 * 
	 * @param pins �����߸� pin ��
	 * @return parmeter �״�� ����.
	 */
	public Integer roll(Integer pins) {
		
		gameScoreBoard.add(pins);			// List�� �����߸� pin�� �߰�
		gameFrame.FrameAddTry(pins);		// ���� Frame ��ġ ����
		if( gameFrame.Frame == 10 ){		// 10��° frame����
			Integer LastPrevious = gameScoreBoard.get(gameScoreBoard.size()-2);		// ������ �����߸� pin���� �� ���� �����߸� pin���� �����ͼ�
			Integer Last = pins;
			if(gameFrame.Tried == 4 || gameFrame.Tried==3 && Last + LastPrevious < 10 ){ 		// 10��° Frame���� ����° �õ��� �̹� �����߰ų�, 2��° ������ �̷������, ��Ʈ����ũ�� ����� ó���� �ȵǸ� ���� ����
				gameStatus = Boolean.FALSE;
			}
		}
		return pins;
	}

	/**
	 * 
	 * @return ���� �Է��� �� Frame�� ���� Ƚ��
	 * @throws Exception GameOverException
	 */
	public BowlingFrame getNowFrame() throws Exception {		// �۾��߿� �����غ��� getNowFrame�� getter �Լ� ���� ���̳׿�...
		if( gameStatus.equals(Boolean.FALSE)) throw GameOverException;
		return gameFrame;
	}
	
	/**
	 * 
	 * @param Framenum ���ϰ��� �ϴ� Frame number
	 * @return ��� Frame
	 * @throws Exception GameOverException
	 */
	public ScoreFrame requireFrame(Integer Framenum) throws Exception {		// Frame ��ȣ�� �־��ָ� Frame ������ (5,"52",7) ���·� ������.
		if( gameStatus.equals(Boolean.FALSE) ) throw GameOverException;
		HashMap<Integer, Integer> map = explore(Framenum);

		Integer Score = 0;
		BowlingInteger First = new BowlingInteger(map.get(1));			// BowlingInteger : 10 �ϰ�� X�� , 0�ϰ��� -�� toString���� �Ѱ���
		BowlingInteger Second = new BowlingInteger(map.get(2));
		Integer lastIndex = map.get(0);
		
		String Result = "";
		try{									// First�� null�� �ƴ϶�� Result String�� First�� �߰�����.

			Result = First.toString();
			
			if( !(First.isStrike()) && First.sum(Second) == 10 ) Result += "/";		// ����� ó�� ������
			else Result += Second.toString();								// ����� ó�� ���н� 
			
			if( Framenum == 10 && (First.isStrike() || First.sum(Second) == 10) ){		// 10�� Frame���� ��Ʈ����ũ��, ����� �߻��� �ѹ� �� roll �� �� ����.
				
				BowlingInteger LastPin = new BowlingInteger(gameScoreBoard.get(lastIndex + 1)); 		// ������ �����͸� �������µ� �õ��Ѵ�. ������ NullPointExcpetion���� �˾Ƽ� ��������.
				if(First.isStrike() && !Second.isStrike() && Second.sum(LastPin) == 10 ) Result += "/"; // 10�� Frame���� ù��° ��Ʈ����ũ ġ�� 2,3��° �ļ� ����� ó����.
				else Result += LastPin.toString();							// ��Ʈ����ũ��, ���� �ƴϸ� ���� ���� ������.
				
				Score = First.sum(Second.sum(LastPin));						// LastPin�� ������ ��� ���� ��� �ٷ� �ؼ� Return. �Ʒ� �ڵ忡�� ������� ����.
				return new ScoreFrame(Framenum, Result, Score);
			}
			
		}catch(Exception e){}

		try {
			if (First.isStrike()) {// ��Ʈ����ũ ������ ���� �� ���ڰ� �ִ� ��� ���Ѵ�. ���� ������ ����� �� ����.
				Score = 10 + gameScoreBoard.get(lastIndex + 1) + gameScoreBoard.get(lastIndex + 2);
			}
			else if (First.sum(Second) == 10) { // ����� ó�� ������ ���� ���� �ϳ��� �ִ� �������ش�.
				Score = 10 + gameScoreBoard.get(lastIndex + 1);
			} 
			else {	// ����� ���н� �� ���ڸ� ���Ѵ�.
				Score = First.sum(Second); 
			}
		} catch (Exception e) {
			Score = 0;			// Exception �߻� : ���ڰ� ����
		}
		return new ScoreFrame(Framenum, Result, Score); // Score�� �Ի� �ȵǴ� ��� �׻�
														// 0�̰�, Result ���� �߰���
														// ����� �߰��� ����� ��ŭ�� �����.
	}

	/**
	 * 
	 * @param framenum Ž���ϰ��� �ϴ� frame �ѹ�
	 * @return key-value�� ����� HaspMap ������
	 */
	private HashMap<Integer, Integer> explore(Integer framenum) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int frame = 1, tried = 1;									// Frame �����ϴ� ���� �ΰ�
		for( int i = 0 ; i < gameScoreBoard.size() ; i ++ ){
			if( frame == framenum ){
				map.put(tried, gameScoreBoard.get(i));
				map.put(0, i); 			// Key 0 : Endframe ����� �߻� �Ǵ� ��Ʈ����ũ �߻��� ���� ���ڸ� ã�� ���� Frame�� �� �κ�.
			}
			
			if( gameScoreBoard.get(i) == 10 && frame != 10 ){		// 10���� Frame�� �ƴ϶�� ��Ʈ����ũ �߻��� ���� �ϳ� �ǳʶ�.
				frame++; tried = 1;
				continue;
			}
			frame += tried / 2; tried = tried % 2 + 1;			// ���� �ѹ� ���Ŀ� �� ��° Frame�̰�, ���° �������� 10��° Frame���� tried�� 3���� �����ϸ� ���ʿ��� ó���� ���쳪 ������ ������ ����
		}
		return map;
	}
}

