
public class ScoreFrame {
	public Integer Framenum;
	public String Result;
	public Integer Score = -1;
	
	public ScoreFrame(Integer Framenum, String Result, Integer Score) {
		// TODO Auto-generated constructor stub
		this.Framenum = Framenum;
		this.Result = Result;
		this.Score = Score;
	}

	public static ScoreFrame getInstanceof(Integer Framenum, String Result, Integer Score) {
		// TODO Auto-generated method stub
		return new ScoreFrame(Framenum, Result, Score);
		// �̷��� �ϸ� �޸� ��� �Դ� ���� ���� �ʴ°�?
		// Effective Java ���� ����ߴ� �����߿� �ϳ������� ������, ������ �������� �ٽ� �ѹ� ã�ƺ���!
		// instance ���� �ʿ�.
	}
	 
	public boolean equals(ScoreFrame obj) {
		// TODO Auto-generated method stub
		if( Framenum.equals(obj.Framenum) && Result.equals(obj.Result) && Score.equals(obj.Score) ){
			return true;
		}
		return false;
	}
}
