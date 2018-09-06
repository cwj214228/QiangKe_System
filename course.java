package datebase;

public class course {
	private String href;
	private String coursename;
	private String xkkh;
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getXkkh() {
		return xkkh;
	}
	public void setXkkh(String xkkh) {
		this.xkkh = xkkh;
	}
	public course(String href, String coursename, String xkkh) {
		super();
		this.href = href;
		this.coursename = coursename;
		this.xkkh = xkkh;
	}
	
	
}
