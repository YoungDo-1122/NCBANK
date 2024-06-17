package ncbank.utility;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.springframework.stereotype.Repository;

@Repository
public class EmailManager {
	// 구글 이메일
	static final String user_email = "jcjhjg12@gmail.com";
	// 애플리케이션 비번
	static final String user_pw = "bpyt jizy nxmn pqro";
	// 호스트 주소 - 호스트를 통해 Gmail SMTP 
	static final String smtp_host = "smtp.gmail.com";
	// 포트번호
	static final int smtp_port = 465; // TLS : 587, SSL : 465
	
	// 받을 이메일, 제목, 내용
	public static void sendEmail(String email, String subject, String text, String filePath) {
		System.out.println("EmailManager sendEmail()");

		Properties props = System.getProperties();
		props.put("mail.smtp.host", smtp_host);
		props.put("mail.smtp.port", smtp_port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true"); // TLS 사용
		props.put("mail.smtp.ssl.trust", smtp_host);
		
		
		System.out.println("filePath : " + filePath);
		
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user_email, user_pw);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user_email));
			// 받는 이메일
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			// 제목
			message.setSubject(subject);
			// 내용
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(text);
			System.out.println("[text 세팅]");
			
			// 첨부 파일
			MimeBodyPart attachmentBodyPart = null;
			if (null != filePath && !filePath.isEmpty()) {
				attachmentBodyPart = new MimeBodyPart();
	            FileDataSource fileDataSource = new FileDataSource(new File(filePath));
	            attachmentBodyPart.setDataHandler(new DataHandler(fileDataSource));
	            attachmentBodyPart.setFileName(fileDataSource.getName());
	            System.out.println("[메일 첨부파일 세팅]");
			}
			
            // MimeMultipart : 이메일 본문과 첨부파일을 포함하는 복합 메시지 객체
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart); // 메시지 추가
            if (null != attachmentBodyPart) {
            	multipart.addBodyPart(attachmentBodyPart); // 첨부파일 추가
            }
            
            // 메시지에 Multipart 설정
            message.setContent(multipart);
            System.out.println("[메세지 객체 세팅]");
            
			// 발송
			Transport.send(message);
			System.out.println("[발송]");

		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("에외발생");
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	} // Send()
	
	
	 // 받을 이메일, 제목, 내용, 파일 경로, JSP 경로
    public static void sendJspEmail(String email, String subject, String jspPath, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("EmailManager sendJspEmail()");

        Properties props = System.getProperties();
        props.put("mail.smtp.host", smtp_host);
        props.put("mail.smtp.port", smtp_port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true"); // TLS 사용
        props.put("mail.smtp.ssl.trust", smtp_host);
        
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user_email, user_pw);
            }
        });

        try {
            // JSP에서 HTML 내용 가져오기
            String htmlContent = getHtmlContent(request, response, jspPath);
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user_email));
            // 받는 이메일
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            // 제목
            message.setSubject(subject);
            // 내용
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(htmlContent, "text/html; charset=utf-8");
            System.out.println("[jsp 세팅]");
            
            // MimeMultipart : 이메일 본문과 첨부파일을 포함하는 복합 메시지 객체
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart); // 메시지 추가
            
            // 메시지에 Multipart 설정
            message.setContent(multipart);
            System.out.println("[메세지 객체 세팅]");
            
            // 발송
            Transport.send(message);
            System.out.println("[발송]");
            
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("에외발생");
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // Send()
    
    private static String getHtmlContent(HttpServletRequest request, HttpServletResponse response, String jspPath) throws ServletException, IOException {
        StringWriter writer = new StringWriter();
        RequestDispatcher dispatcher = request.getRequestDispatcher(jspPath);

        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response) {
            @Override
            public PrintWriter getWriter() {
                return new PrintWriter(writer);
            }
        };

        dispatcher.include(request, responseWrapper);
        return writer.toString();
    }
    
}
	
