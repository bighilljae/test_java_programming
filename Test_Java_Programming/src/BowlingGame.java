
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

	public BowlingFrame getNowFrame() throws Exception {		// �۾��߿� �����غ��� getNowFrame�� getter �Լ� ���� ���̳׿�...
		if( gameStatus.equals(Boolean.FALSE)) throw GameOverException;
		return gameFrame;
	}

	public ScoreFrame requireFrame(Integer Framenum) throws Exception {		// Frame ��ȣ�� �־��ָ� Frame ������ (5,"52",7) ���·� ������.
		if( gameStatus.equals(Boolean.FALSE) ) throw GameOverException;
		HashMap<Integer, Integer> map = explore(Framenum);

		Integer Score = 0;
		Integer First = map.get(1);
		Integer Second = map.get(2);
		Integer lastIndex = map.get(0);
		String Result = "";
		try{									// First�� null�� �ƴ϶�� Result String�� First�� �߰�����.
			if( First == 10 ) Result = "X";		// ù��° �õ��� 10 �̶�� ��Ʈ����ũ
			else Result = First.toString();		// �ƴ϶�� ���� ������.
			
			if( First != 10 && First + Second == 10 ) Result += "/";		// ����� ó�� ������
			else if( First == 10 && Second == 10 ) Result += "X";			// 10Frame������ �߻� ������ ���. First�� ��Ʈ����ũ, Second�� ��Ʈ����ũ
			else Result += Second.toString();								// ����� ó�� ���н� 
			
			if( Framenum == 10 && (First == 10 || First+Second == 10) ){		// 10�� Frame���� ��Ʈ����ũ��, ����� �߻��� �ѹ� �� roll �� �� ����.
				
				Integer LastPin = gameScoreBoard.get(lastIndex + 1); 		// ������ �����͸� �������µ� �õ��Ѵ�. ������ NullPointExcpetion���� �˾Ƽ� ��������.
				if( LastPin == 10 )	Result += "X";									// �������� 10�� ġ�� ��Ʈ����ũ			
				else if(First == 10 && Second != 10 && Second + LastPin == 10 ) Result += "/"; // 10�� Frame���� ù��° ��Ʈ����ũ ġ�� 2,3��° �ļ� ����� ó����.
				else Result += LastPin.toString();							// ��Ʈ����ũ��, ���� �ƴϸ� ���� ���� ������.
				
				Score = First + Second + LastPin;						// LastPin�� ������ ��� ���� ��� �ٷ� �ؼ� Return. �Ʒ� �ڵ忡�� ������� ����.
				return new ScoreFrame(Framenum, Result, Score);
			}
			
		}catch(Exception e){
		}
		

		try {
			if (First == 10) {
				if (gameScoreBoard.size() - 1 >= lastIndex + 2) {		// ��Ʈ����ũ ������ ���� �� ���ڰ� �ִ� ��� ���Ѵ�. ���� ������ ����� �� ����.
					Score = 10 + gameScoreBoard.get(lastIndex + 1)
							+ gameScoreBoard.get(lastIndex + 2);
				}
			} else if (First + Second == 10) {							// ����� ó�� ������ ���� ���� �ϳ��� �ִ� ��� �����ش�.
				if (gameScoreBoard.size() - 1 >= lastIndex + 1) {
					Score = 10 + gameScoreBoard.get(lastIndex + 1);
				}
			} else {
				Score = First + Second;								// ����� ���н� �� ���ڸ� ���Ѵ�.
			}
		} catch (Exception e) {
			Score = 0;
		}
		return new ScoreFrame(Framenum, Result, Score);			// Score�� �Ի� �ȵǴ� ��� �׻� 0�̰�, Result ���� �߰��� ����� 
	}

	private HashMap<Integer, Integer> explore(Integer framenum) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int count = 1, tried = 1;
		for( int i = 0 ; i < gameScoreBoard.size() ; i ++ ){
			if( count == framenum ){
				map.put(tried, gameScoreBoard.get(i));
				map.put(0, i); 			// Key 0 : EndCount
			}
			
			if( gameScoreBoard.get(i) == 10 && count != 10 ){
				count++; tried = 1;
				continue;
			}
			count += tried / 2;
			tried = tried % 2 + 1;
		}
		return map;
	}
}

