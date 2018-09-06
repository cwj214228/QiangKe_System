package datebase;

public class getScore {
	private String year;
	private String Semester;
	private String name;
	private String type;
	private String xuefen;
	private String jidian;
	private String score;
	
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getSemester() {
		return Semester;
	}
	public void setSemester(String semester) {
		Semester = semester;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getXuefen() {
		return xuefen;
	}
	public void setXuefen(String xuefen) {
		this.xuefen = xuefen;
	}
	public String getJidian() {
		return jidian;
	}
	public void setJidian(String jidian) {
		this.jidian = jidian;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public getScore(String year, String semester, String name, String type, String xuefen, String jidian,
			String score) {
		super();
		this.year = year;
		this.Semester = semester;
		this.name = name;
		this.type = type;
		this.xuefen = xuefen;
		this.jidian = jidian;
		this.score = score;
	}
	
}
