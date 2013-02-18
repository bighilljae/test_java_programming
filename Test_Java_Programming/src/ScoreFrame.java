
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
		// 이렇게 하면 메모리 잡아 먹는 양이 늘지 않는가?
		// Effective Java 에서 언급했던 내용중에 하나였던것 같은데, 시험이 끝나더라도 다시 한번 찾아볼것!
	}
	 
}
