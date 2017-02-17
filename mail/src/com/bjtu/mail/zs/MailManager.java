package com.bjtu.mail.zs;

import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailManager {
	// 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
	public static String myEmailAccount = "631710518@qq.com";
	//rpyiktvhpshbbbih    1
	
	public static String myEmailPassword = "rpyiktvhpshbbbih";

	// 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般格式为: smtp.xxx.com
	// 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
	public static String myEmailSMTPHost = "smtp.qq.com";
	// public static String myEmailSMTPHost = "smtp.exmail.qq.com";

	// 收件人邮箱（替换为自己知道的有效邮箱）
	//changdi369271317
	public static String receiveMailAccount = "631710518@qq.com";

	public static void loop() throws Exception {
		// 1. 创建参数配置, 用于连接邮件服务器的参数配置
		Properties props = new Properties(); // 参数配置
		props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
		props.setProperty("mail.host", myEmailSMTPHost); // 发件人的邮箱的 SMTP 服务器地址
		props.setProperty("mail.smtp.auth", "true"); // 请求认证，参数名称与具体实现有关
		props.setProperty("mail.debug", "true"); // 开启debug调试
		props.setProperty("mail.smtp.port", "587");// QQ发送服务器的端口
		// STARTTLS是对纯文本通信协议的扩展。它提供一种方式将纯文本连接升级为加密连接（TLS或SSL）
		props.setProperty("mail.smtp.starttls.enable", "true");
		// QQ邮箱的rpyiktvhpshbbbih授权码
		props.setProperty("mail.smtp.password", "rpyiktvhpshbbbih");

		// 2. 根据配置创建会话对象, 用于和邮件服务器交互
		Session session = Session.getDefaultInstance(props);
		session.setDebug(true); // 设置为debug模式, 可以查看详细的发送 log

		// 3. 创建一封邮件
		MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount);

		// 4. 根据 Session 获取邮件传输对象
		Transport transport = session.getTransport();

		// 5. 使用 邮箱账号 和 密码 连接邮件服务器
		// 这里认证的邮箱必须与 message 中的发件人邮箱一致，否则报错，使用SSL方式进行连接
		transport.connect(myEmailAccount, "rpyiktvhpshbbbih");

		// 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人,
		// 抄送人, 密送人
		transport.sendMessage(message, message.getAllRecipients());

		// 7. 关闭连接
		transport.close();
	}

	/**
	 * 创建一封只包含文本的简单邮件
	 *
	 * @param session
	 *            和服务器交互的会话
	 * @param sendMail
	 *            发件人邮箱
	 * @param receiveMail
	 *            收件人邮箱
	 * @return
	 * @throws Exception
	 */
	public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {
		// 1. 创建一封邮件
		MimeMessage message = new MimeMessage(session);

		// 2. From: 发件人
		message.setFrom(new InternetAddress(sendMail, "淘宝网", "UTF-8"));

		// 3. To: 收件人（可以增加多个收件人、抄送、密送）
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "杨昌迪", "UTF-8"));

		// 4. Subject: 邮件主题
		message.setSubject("快递领取", "UTF-8");

		// 5. Content: 邮件正文（可以使用html标签）
		message.setContent("杨昌迪你好, 您的顺丰快递已经送至菜鸟驿站，请于中午1点前过来领取，谢谢！", "text/html;charset=UTF-8");

		// 6. 设置发件时间
		message.setSentDate(new Date());

		// 7. 保存设置
		message.saveChanges();

		return message;
	}

	public static void main(String[] args) throws Exception {
		for (int i = 1; i <= 1; i++){
			MailManager.loop();
		}
	}
}
