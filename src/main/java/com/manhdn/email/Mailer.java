package com.manhdn.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.manhdn.FunctionCommon;
import com.manhdn.entity.orderEntity;
import com.manhdn.entity.productEntity;
@Service
public class Mailer {
	
	private JavaMailSender mailSender;
	
	private String recipientAddress;
	private String subject = "Xác nhận đơn hàng";

	public void sendMail(orderEntity order) throws MessagingException {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config-mail.xml");
		mailSender = (JavaMailSender) context.getBean("mailSender");		
		
		
		
		StringBuilder message = new StringBuilder();
		message.append("");
		message.append("Dear ")
				.append(!FunctionCommon.isEmpty(order.getUser().getFullName()) ? order.getUser().getFullName()
						: order.getUser().getUserName()).append(",<br>");
		message.append("Đơn hàng của bạn đã được đặt thành công.<br>");
		for(productEntity p : order.getListProduct()) {
			message.append("  ").append(p.getProductName()).append("<b>x").append(p.getCartQuantity()).append("</b><br>");
		}
		message.append("Đơn hàng của bạn sẽ được giao sớm nhất có thể. <br> Xin cảm ơn!");
		
		recipientAddress = order.getUser().getEmail();
		
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
	    mimeMessage.setContent(message.toString(), "text/html;charset=utf-8"); 
	    
	    helper.setTo(order.getUser().getEmail());
	    helper.setSubject(subject);
		
		mailSender.send(mimeMessage);
//		 // creates a simple e-mail object
//        SimpleMailMessage email = new SimpleMailMessage();
//        email.setTo(recipientAddress);
//        email.setSubject(subject);
//        email.setText(message.toString());
//        mailSender.send(email);
	}
	public void sendMail2(String mailTo) throws MessagingException {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config-mail.xml");
		mailSender = (JavaMailSender) context.getBean("mailSender");		
				
		StringBuilder message = new StringBuilder();
		message.append("Dear \n");
		message.append("\t TESTTTTT á đù ứ ư ô");
				
		MimeMessage mimeMessage = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
	    mimeMessage.setContent(message.toString(), "text/html;charset=utf-8"); 
	    
	    helper.setTo(mailTo);
	    helper.setSubject(subject);
		 // creates a simple e-mail object
//        SimpleMailMessage email = new SimpleMailMessage();
//        email.setTo(mailTo);
//        email.setSubject(subject);
//        email.setText(message.toString());
        
        mailSender.send(mimeMessage);
	}
}
