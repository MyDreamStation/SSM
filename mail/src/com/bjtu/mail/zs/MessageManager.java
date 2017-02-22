package com.bjtu.mail.zs;
import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element; 
public class MessageManager {

	private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
	
	public static void main(String [] args) {
		
		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod(Url);

		client.getParams().setContentCharset("GBK");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=GBK");

		int mobile_code = (int)((Math.random()*9+1)*100000);

	    String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");

		NameValuePair[] data = {//提交短信
			    new NameValuePair("account", "用户名"), 
			    new NameValuePair("password", "密码"), //查看密码请登录用户中心->验证码、通知短信->帐户及签名设置->APIKEY
			    //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
			    new NameValuePair("mobile", "手机号码"), 
			    new NameValuePair("content", content),
		};
		method.setRequestBody(data);

		try {
			client.excuteMethod(method);
			
			String SubmitResult =method.getResponseBodyAsString();

			//System.out.println(SubmitResult);

			Document doc = DocumentHelper.parseText(SubmitResult);
			Element root = doc.getRootElement();

			String code = root.elementText("code");
			String msg = root.elementText("msg");
			String smsid = root.elementText("smsid");

			System.out.println(code);
			System.out.println(msg);
			System.out.println(smsid);

			 if("2".equals(code)){
				System.out.println("短信提交成功");
			}

		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
}
