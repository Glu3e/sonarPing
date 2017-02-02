import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
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

public class SonarPingEmailModel implements Runnable {
	private String to;
	private String from;
	private String username;
	private String password;
	private String host;
	private Properties props;
	private Session session;

	public SonarPingEmailModel(String to, String from, String username, String password){
		this.to = to;
		this.from = from;
		this.username = username;
		this.password = password;
		
		this.props = new Properties();
		this.props.put("mail.smtp.auth", "true");
		this.props.put("mail.smtp.starttls.enable", "true");
		this.props.put("mail.smtp.host", "smtp.gmail.com");
		this.props.put("mail.smtp.port", "587");
	}
	
	public void sessionInitialize(){
		// Get the Session object.
		session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
	}

	public void run(){
		try {
			
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject("Testing Subject");
			
			
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("Attached is the video recording");
			
			
			
			Multipart multiPartMessage = new MimeMultipart();
			multiPartMessage.addBodyPart(messageBodyPart);
			
			messageBodyPart = new MimeBodyPart();
			String fileName = "./pictures/cam.png";
			
			DataSource fileDataSource = new FileDataSource(fileName);
			
			messageBodyPart.setDataHandler(new DataHandler(fileDataSource));
		    messageBodyPart.setFileName(fileName);
		    multiPartMessage.addBodyPart(messageBodyPart);
		    
		    message.setContent(multiPartMessage);
			
			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
