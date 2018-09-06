package datebase;

import org.apache.http.client.HttpClient;

public class action_qiangke {
	private String xh;
	private String urlxkkh;
	private String __VIEWSTATE;
	private String xkkh;
	private String coursename;
	private String time;
	private String teachername;
	private HttpClient httpclient;
	
	
	
	public HttpClient getHttpclient() {
		return httpclient;
	}
	public void setHttpclient(HttpClient httpclient) {
		this.httpclient = httpclient;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTeachername() {
		return teachername;
	}
	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getUrlxkkh() {
		return urlxkkh;
	}
	public void setUrlxkkh(String urlxkkh) {
		this.urlxkkh = urlxkkh;
	}
	public String get__VIEWSTATE() {
		return __VIEWSTATE;
	}
	public void set__VIEWSTATE(String __VIEWSTATE) {
		this.__VIEWSTATE = __VIEWSTATE;
	}
	public String getXkkh() {
		return xkkh;
	}
	public void setXkkh(String xkkh) {
		this.xkkh = xkkh;
	}
	public action_qiangke(String xh, String urlxkkh, String __VIEWSTATE, String xkkh) {
		super();
		this.xh = xh;
		this.urlxkkh = urlxkkh;
		this.__VIEWSTATE = __VIEWSTATE;
		this.xkkh = xkkh;
	}
	public action_qiangke(String xh, String urlxkkh, String __VIEWSTATE, String xkkh, String coursename,String time,
			String teachername,HttpClient httpclient) {
		super();
		this.xh = xh;
		this.urlxkkh = urlxkkh;
		this.__VIEWSTATE = __VIEWSTATE;
		this.xkkh = xkkh;
		
		this.coursename=coursename;
		this.time = time;
		this.teachername = teachername;
		this.httpclient=httpclient;
	}
	
	
}
