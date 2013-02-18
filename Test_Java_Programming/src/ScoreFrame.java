
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
	}
	 
}
