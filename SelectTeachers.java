package datebase;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class SelectTeachers {
	HttpClient httpclient;
	HttpPost post;
	String VIEWSTATE;
	String EVENTTARGET="";
	String __EVENTARGUMENT="";
	String name;
	String studentname;
	String url;
	HttpEntity entity;
	HttpResponse response;
	
	
	
	public SelectTeachers(HttpClient httpclient, String vIEWSTATE, String name, String studentname,String url) {
		super();
		this.httpclient = httpclient;
		this.VIEWSTATE = vIEWSTATE;
		this.name = name;
		this.studentname = studentname;
		this.url=url;
	}





	public HttpClient getHttpclient() {
		return httpclient;
	}





	public void setHttpclient(HttpClient httpclient) {
		this.httpclient = httpclient;
	}





	public String getVIEWSTATE() {
		return VIEWSTATE;
	}





	public void setVIEWSTATE(String vIEWSTATE) {
		VIEWSTATE = vIEWSTATE;
	}





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}





	public String getStudentname() {
		return studentname;
	}





	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}





	public void selectteacher() throws ClientProtocolException, IOException{
		post=new HttpPost(url);
		ArrayList<NameValuePair> params=new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("xh", name));
		//params.add(new BasicNameValuePair("xm", studentname));
		
		params.add(new BasicNameValuePair("__EVENTTARGET",""));
		params.add(new BasicNameValuePair("__EVENTARGUMENT", ""));
		
		params.add(new BasicNameValuePair("__VIEWSTATE",VIEWSTATE));
		
		//params.add(new BasicNameValuePair("xx", ""));
		//params.add(new BasicNameValuePair("zymc", ""));
		
		post.setEntity(new UrlEncodedFormEntity(params));
		post.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		post.addHeader("Accept-Encoding","gzip, deflate");
		post.addHeader("Accept-Language","zh-CN,zh;q=0.9");
		post.addHeader("Cache-Control","max-age=0");
		post.addHeader("Connection","keep-alive");
		post.addHeader("Host","10.10.240.34");
		post.addHeader("Referer","http://10.10.240.34/xsxk.aspx?xh="+name+"&xm="+studentname+"&gnmkdm=N121101");
		post.addHeader("Upgrade-Insecure-Requests","1");
		post.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
		response=httpclient.execute(post);
		entity=response.getEntity();
		String pagetext=EntityUtils.toString(entity,"utf-8");
		System.out.println(pagetext);
		
		
		post.abort();
		
	}
	
}
