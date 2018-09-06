package datebase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieStore;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javafx.stage.Window;
import server.Client;
import sun.applet.Main;
import view.LoginView;
import view.ScoreView;
import view.ShowCourse;


public class getMessage {
	private static HttpClient httpclient=HttpClients.createDefault();
	private HttpResponse response; 
	private HttpPost post;
	private HttpGet get;
	private HttpEntity entity;
	private String fileName="img\\yanzheng.jpg";
	private String loginUrl="http://10.10.240.34/default2.aspx";
	private String ImageUrl="http://10.10.240.34/CheckCode.aspx";
	static String VIEWSTATE;
	static String student;
	static String check;
	static String studentname;
	static String zymc;
	
	public static void main(String[] args) {
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "界面初始化失败！");
			e.printStackTrace();
		}
		new LoginView(httpclient).setVisible(true);
	}
	
	/**
	 * 获取"__VIEWSTATE"
	 * @throws ParseException
	 * @throws IOException
	 */
	public String getPageMessage() throws ParseException, IOException{
		
		get=new HttpGet(loginUrl);
		get.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		get.addHeader("Accept-Encoding","gzip, deflate");
		get.addHeader("Accept-Language","zh-CN,zh;q=0.9");
		get.addHeader("Cache-Control","max-age=0");
		get.addHeader("Connection","keep-alive");
		get.addHeader("Upgrade-Insecure-Requests","1");
		get.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
		response=httpclient.execute(get);
		entity=response.getEntity();
		String pagetext=EntityUtils.toString(entity,"utf-8");
		org.jsoup.nodes.Document document = Jsoup.parse(pagetext);
		VIEWSTATE = document.select("input[name=__VIEWSTATE]").attr("value");
		student=document.select("input[id=RadioButtonList1_2]").attr("value");
		get.abort();
		return VIEWSTATE;
	}
	
	/**
	 * 
	 * 获取验证码
	 */
	public void getImage() throws ClientProtocolException, IOException{
		get=new HttpGet(ImageUrl);
		get.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		get.addHeader("Accept-Encoding","gzip, deflate");
		get.addHeader("Accept-Language","zh-CN,zh;q=0.9");
		get.addHeader("Cache-Control","max-age=0");
		get.addHeader("Connection","keep-alive");
		get.addHeader("Upgrade-Insecure-Requests","1");
		get.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
		response=httpclient.execute(get);
		entity=response.getEntity();
		InputStream in = entity.getContent();  
        File file = new File(fileName); 
        try {  
            FileOutputStream fout = new FileOutputStream(file);  
            int l = -1;  
            byte[] tmp = new byte[1024];  
            while ((l = in.read(tmp)) != -1) {  
                fout.write(tmp, 0, l);  
            }  
            fout.flush();  
            fout.close();  
        } finally {    
            in.close();  
        }
        get.abort();
	}
	
	/**
	 * 登陆教务系统
	 * */
	public void login(String __VIEWSTATE,String name,String password,String check) throws ClientProtocolException, IOException{
		post=new HttpPost(loginUrl);
		post.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		post.addHeader("Accept-Encoding","gzip, deflate");
		post.addHeader("Accept-Language","zh-CN,zh;q=0.9");
		post.addHeader("Cache-Control","max-age=0");
		post.addHeader("Connection","keep-alive");
		post.addHeader("Content-Type","application/x-www-form-urlencoded");
		post.addHeader("Host","10.10.240.34");
		post.addHeader("Origin","http://10.10.240.34");
		post.addHeader("Referer","http://10.10.240.34/default2.aspx");
		post.addHeader("Upgrade-Insecure-Requests","1");
		post.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
		
		//设置登陆发送给服务器的参数
		ArrayList<NameValuePair> params=new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("__VIEWSTATE",__VIEWSTATE));
		params.add(new BasicNameValuePair("Button1"," "));
		params.add(new BasicNameValuePair("hidPdrs"," "));
		params.add(new BasicNameValuePair("hidsc"," "));
		params.add(new BasicNameValuePair("lbLanguage"," "));
		params.add(new BasicNameValuePair("RadioButtonList1","学生"));
		params.add(new BasicNameValuePair("Textbox1"," "));
		params.add(new BasicNameValuePair("TextBox2",password));
		params.add(new BasicNameValuePair("txtSecretCode",check));
		params.add(new BasicNameValuePair("txtUserName",name));
		
		post.setEntity(new UrlEncodedFormEntity(params));
		response=httpclient.execute(post);
		
		post.abort();
	} 
	
	/**
	 *登陆成功，获取学生的姓名 
	 * */
	
	public String getStudentName(String name) throws ParseException, IOException{
		get=new HttpGet("http://10.10.240.34/xs_main.aspx?xh="+name);
		get.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		get.addHeader("Accept-Encoding","gzip, deflate");
		get.addHeader("Accept-Language","zh-CN,zh;q=0.9");
		get.addHeader("Cache-Control","max-age=0");
		get.addHeader("Connection","keep-alive");
		get.addHeader("Host","jwxt.nfsysu.cn");
		get.addHeader("Referer","http://10.10.240.34/default2.aspx");
		get.addHeader("Upgrade-Insecure-Requests","1");
		get.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
		response=httpclient.execute(get);
		entity=response.getEntity();
		String pagetext=EntityUtils.toString(entity,"utf-8");
		org.jsoup.nodes.Document document = Jsoup.parse(pagetext);
		studentname = document.select("span[id=xhxm]").html();
		int begin = studentname.indexOf("");
		int end = studentname.indexOf('s', begin);
		studentname =studentname.substring(begin, end);
		get.abort();
		return studentname;
	}
	
	
	
	/**
	 * 向服务器发送请求，获取新的"__VIEWSTATE"的值。
	 * */
	public void getLastMessage(String name,String studentname) throws ClientProtocolException, IOException, URISyntaxException{
		URIBuilder builder=new URIBuilder("http://10.10.240.34/xscj_gc.aspx/");
		ArrayList<NameValuePair> params=new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("xh", name));
		params.add(new BasicNameValuePair("xm", studentname));
		params.add(new BasicNameValuePair("gnmkdm", "N121605"));
		builder.setParameters(params);
		
		post=new HttpPost(builder.build());
		
		post.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		post.addHeader("Accept-Encoding","gzip, deflate");
		post.addHeader("Accept-Language","zh-CN,zh;q=0.9");
		post.addHeader("Cache-Control","max-age=0");
		post.addHeader("Connection","keep-alive");
		post.addHeader("Host","10.10.240.34");
		post.addHeader("Referer","http://10.10.240.34/xs_main.aspx?xh="+name);
		post.addHeader("Upgrade-Insecure-Requests","1");
		post.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
		response=httpclient.execute(post);
		entity=response.getEntity();
		String pagetext=EntityUtils.toString(entity,"utf-8");
		org.jsoup.nodes.Document document = Jsoup.parse(pagetext);
		VIEWSTATE = document.select("input[name=__VIEWSTATE]").attr("value");
		
		post.abort();
	}
	/**
	 * 
	 * 获取教务系统的成绩
	 * */
	public void getScore(String name,String studentname) throws ClientProtocolException, IOException, URISyntaxException{
		
		post=new HttpPost("http://10.10.240.34/xscj_gc.aspx?xh="+name+"&xm="+studentname+"&gnmkdm=N121605");
		ArrayList<NameValuePair> params=new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("xh", name));
		params.add(new BasicNameValuePair("xm", studentname));
		params.add(new BasicNameValuePair("gnmkdm", "N121605"));
		params.add(new BasicNameValuePair("__VIEWSTATE",VIEWSTATE));
		params.add(new BasicNameValuePair("Button2","在校学习成绩查询"));
		params.add(new BasicNameValuePair("ddlXN",""));
		params.add(new BasicNameValuePair("ddlXQ",""));
		
		
		post.setEntity(new UrlEncodedFormEntity(params));
		
		post.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		post.addHeader("Accept-Encoding","gzip, deflate");
		post.addHeader("Accept-Language","zh-CN,zh;q=0.9");
		post.addHeader("Cache-Control","max-age=0");
		post.addHeader("Connection","keep-alive");
		post.addHeader("Content-Type","application/x-www-form-urlencoded");
		post.addHeader("Host","10.10.240.34");
		post.addHeader("Origin","http://10.10.240.34");
		post.addHeader("Referer","http://10.10.240.34/xscj_gc.aspx?xh="+name+"&xm="+studentname+"&gnmkdm=N121605");
		post.addHeader("Upgrade-Insecure-Requests","1");
		post.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
		response=httpclient.execute(post);
		entity=response.getEntity();
		String pagetext=EntityUtils.toString(entity,"utf-8");
		org.jsoup.nodes.Document document = Jsoup.parse(pagetext);
		VIEWSTATE = document.select("input[name=__VIEWSTATE]").attr("value");
		Element table=document.select("table.datelist").first();
		Elements one=table.select("tr");
		ArrayList<getScore> list=new ArrayList<getScore>();
		
		for(int i=1;i<one.size();i++){
			Element two=one.get(i);
			Elements three=two.select("td");
			list.add(new getScore(three.get(0).html(), three.get(1).html(), three.get(3).html(), three.get(4).html(), three.get(6).html(), three.get(7).html(), three.get(8).html()));
		}
		new ScoreView(list).setVisible(true);
		post.abort();
	}
	
	/**
	 * 点击学生抢课，获取新的“VIEWSTATE”和“zymc”
	 */
	public void getView(String name,String studentname) throws ClientProtocolException, IOException, URISyntaxException{
		post=new HttpPost("http://10.10.240.34/xsxk.aspx?xh="+name+"&xm="+studentname+"&gnmkdm=N121101");
		ArrayList<NameValuePair> params=new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("xh", name));
		params.add(new BasicNameValuePair("xm", studentname));
		params.add(new BasicNameValuePair("gnmkdm", "N121101"));
		
		
		post.setEntity(new UrlEncodedFormEntity(params));
		
		post.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		post.addHeader("Accept-Encoding","gzip, deflate");
		post.addHeader("Accept-Language","zh-CN,zh;q=0.9");
		post.addHeader("Cache-Control","max-age=0");
		post.addHeader("Connection","keep-alive");
		post.addHeader("Host","10.10.240.34");
		post.addHeader("Referer","http://10.10.240.34/xs_main.aspx?xh="+name);
		post.addHeader("Upgrade-Insecure-Requests","1");
		post.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
		response=httpclient.execute(post);
		entity=response.getEntity();
		String pagetext=EntityUtils.toString(entity,"utf-8");
		org.jsoup.nodes.Document document = Jsoup.parse(pagetext);
		VIEWSTATE = document.select("input[name=__VIEWSTATE]").attr("value");
		zymc=document.select("input[name=zymc]").attr("value");
		
		post.abort();
	}
	
	/**
	 * 点击选修课程
	 */
	public void getView2(String name,String studentname) throws ClientProtocolException, IOException, URISyntaxException{
		post=new HttpPost("http://10.10.240.34/xsxk.aspx?xh=162011050&xm=%E9%99%88%E6%96%87%E6%9D%B0&gnmkdm=N121101");
		ArrayList<NameValuePair> params=new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("xh", name));
		params.add(new BasicNameValuePair("xm", studentname));
		params.add(new BasicNameValuePair("gnmkdm", "N121101"));
		params.add(new BasicNameValuePair("__EVENTTARGET", ""));
		params.add(new BasicNameValuePair("__EVENTARGUMENT", ""));
		
		params.add(new BasicNameValuePair("__VIEWSTATE",VIEWSTATE));
		
		params.add(new BasicNameValuePair("xx", ""));
		params.add(new BasicNameValuePair("zymc", zymc));
		params.add(new BasicNameValuePair("Button2", "选修课程"));
		
		
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
		org.jsoup.nodes.Document document = Jsoup.parse(pagetext);
		VIEWSTATE = document.select("input[name=__VIEWSTATE]").attr("value");
		
		post.abort();
	}
	
	
	
	/**
	 * 点击快速选课
	 */
	public ArrayList<course> getView3(String name,String studentname) throws ClientProtocolException, IOException, URISyntaxException{
		post=new HttpPost("http://10.10.240.34/xsxk.aspx?xh="+name+"&xm="+studentname+"&gnmkdm=N121101");
		ArrayList<NameValuePair> params=new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("xh", name));
		params.add(new BasicNameValuePair("xm", studentname));
		params.add(new BasicNameValuePair("gnmkdm", "N121101"));
		params.add(new BasicNameValuePair("__EVENTARGUMENT", " "));
		params.add(new BasicNameValuePair("__EVENTTARGET", ""));
		params.add(new BasicNameValuePair("__VIEWSTATE",VIEWSTATE));
		params.add(new BasicNameValuePair("Button7", "快速选课"));
		params.add(new BasicNameValuePair("xx", ""));
		params.add(new BasicNameValuePair("zymc", ""));
		
		
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
		//System.out.println(pagetext);
		org.jsoup.nodes.Document document = Jsoup.parse(pagetext);
		Element table=document.select("table.datelist").first();
		Elements one=table.select("tr");
		ArrayList<course> list=new ArrayList<course>();
		
		for(int i=1;i<one.size()-1;i++){
			Element two=one.get(i);
			Elements three=two.select("td");
			Elements four=three.get(1).select("a");
			String href=three.toString();
			
			int begin = href.indexOf("h=")+2;
			int end = href.indexOf('&', begin);
			href =href.substring(begin, end);
			
			
			
			list.add(new course("http://10.10.240.34/xsxjs.aspx?xkkh="+href+"&xh="+name.toString(), four.get(0).html(),href));
		}
		
		
		
		VIEWSTATE = document.select("input[name=__VIEWSTATE]").attr("value");
		
		post.abort();
		return list;
	}
	
	
	/**
	 * 下一页
	 */
	public ArrayList<course> Nextpage(String name,String studentname,int k) throws ClientProtocolException, IOException, URISyntaxException{
		post=new HttpPost("http://10.10.240.34/xsxk.aspx?xh="+name+"&xm="+studentname+"&gnmkdm=N121101");
		ArrayList<NameValuePair> params=new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("xh", name));
		params.add(new BasicNameValuePair("xm", studentname));
		params.add(new BasicNameValuePair("gnmkdm", "N121101"));
		params.add(new BasicNameValuePair("__EVENTTARGET", "kcmcgrid:_ctl14:_ctl"+k));
		params.add(new BasicNameValuePair("__EVENTARGUMENT", " "));
		
		params.add(new BasicNameValuePair("__VIEWSTATE",VIEWSTATE));
		
		params.add(new BasicNameValuePair("xx", ""));
		params.add(new BasicNameValuePair("zymc", ""));
		
		
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
		org.jsoup.nodes.Document document = Jsoup.parse(pagetext);
		
		VIEWSTATE = document.select("input[name=__VIEWSTATE]").attr("value");
		
		Element table=document.select("table.datelist").first();
		Elements one=table.select("tr");
		ArrayList<course> list=new ArrayList<course>();
		
		for(int i=1;i<one.size()-1;i++){
			Element two=one.get(i);
			Elements three=two.select("td");
			Elements four=three.get(1).select("a");
			String href=three.toString();
			
			int begin = href.indexOf("h=")+2;
			int end = href.indexOf('&', begin);
			href =href.substring(begin, end);
			
			
			
			list.add(new course("http://10.10.240.34/xsxjs.aspx?xkkh="+href+"&xh="+name, four.get(0).html(),href));
		}
		
		post.abort();
		return list;
	}
	
	/**
	 * 选择老师
	 */
	public ArrayList<action_qiangke> selectteacher(String name,String xkkh,String surl,String coursename) throws ClientProtocolException, IOException, URISyntaxException{
		post=new HttpPost(surl);
		
		ArrayList<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("xh", "162011050"));
		params.add(new BasicNameValuePair("xkkh", xkkh));
		
		post.setEntity(new UrlEncodedFormEntity(params));
		post.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		post.addHeader("Accept-Encoding","gzip, deflate");
		post.addHeader("Accept-Language","zh-CN,zh;q=0.9");
		post.addHeader("Cache-Control","max-age=0");
		post.addHeader("Connection","keep-alive");
		post.addHeader("Host","10.10.240.34");
		post.addHeader("Upgrade-Insecure-Requests","1");
		post.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
		
		response=httpclient.execute(post);
		entity=response.getEntity();
		String pagetext=EntityUtils.toString(entity,"utf-8");
		
		
		
		org.jsoup.nodes.Document document = Jsoup.parse(pagetext);
		String viewstate=document.select("input[name=__VIEWSTATE]").attr("value");
		
		Element table=document.select("table.formlist").first();
		
		Elements one=table.select("tr");
		ArrayList<action_qiangke> list=new ArrayList<action_qiangke>();
		
		for(int i=1;i<one.size();i++){
			Element two=one.get(i);
			Elements three=two.select("td");
			Elements four=three.get(1).select("a");
			String time=three.get(5).html();
			String teachername=four.html();
			String xkkh2=three.toString();
			
			int begin = xkkh2.indexOf("xkkh=")+5;
			int end = xkkh2.indexOf('&', begin);
			xkkh2 =xkkh2.substring(begin, end);
			System.out.println(xkkh2);
			
			
			list.add(new action_qiangke(name,surl,viewstate,xkkh2,coursename,time,teachername,httpclient));
		}
		post.abort();
		return list;
		
	}
	
	
	public void doPost(String xh, String urlxkkh, String __VIEWSTATE2, String xkkh) throws ClientProtocolException, IOException{
		post=new HttpPost(urlxkkh);
		
		ArrayList<NameValuePair> params=new ArrayList<NameValuePair>();
		
		//params.add(new BasicNameValuePair("xh", xh));
		
		
		params.add(new BasicNameValuePair("__EVENTARGUMENT", " "));
		params.add(new BasicNameValuePair("RadioButtonList1", "0"));
		params.add(new BasicNameValuePair("__VIEWSTATE",__VIEWSTATE2));
		
		params.add(new BasicNameValuePair("__EVENTTARGET", "Button1"));
		params.add(new BasicNameValuePair("xkkh", xkkh));
		
		//params.add(new BasicNameValuePair("name", "网站"));
		
		
		post.setEntity(new UrlEncodedFormEntity(params));
		post.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		post.addHeader("Accept-Encoding","gzip, deflate");
		post.addHeader("Accept-Language","zh-CN,zh;q=0.9");
		post.addHeader("Cache-Control","max-age=0");
		post.addHeader("Connection","keep-alive");
		post.addHeader("Host","10.10.240.34");
		post.addHeader("Upgrade-Insecure-Requests","1");
		post.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36");
		
		int worker=1;
		while(true){
			response=httpclient.execute(post);
			entity=response.getEntity();
			String pagetext=EntityUtils.toString(entity,"utf-8");
			org.jsoup.nodes.Document document = Jsoup.parse(pagetext);
			String alert=document.select("script[language=javascript]").html();
			String result=pagetext.substring(pagetext.indexOf("alert(") + 7, pagetext.indexOf("</script>") - 3);
			
			System.out.println("第"+worker+"次抢课结果： "+result);
			if(result.indexOf("成功") > 0){
				break;
			}
			worker++;
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
	/**
	 * 抢课成功，向服务器发送信息
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public void succeed(administrators admin,String name,String password,String course) throws UnknownHostException, IOException{
			Client client=new Client();
			client.succeed(admin, name, password, course);
	}
	
	
}
