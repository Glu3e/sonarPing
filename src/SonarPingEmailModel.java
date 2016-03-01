import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SonarPingEmailModel {
	private String to;
	private String from;
	private String username;
	private String password;
	private String host;
	private Properties props;
	private Session session;

	public SonarPingEmailModel(String to, String from, String username, String password, String hoste){
		this.to = to;
		this.from = from;
		this.username = username;
		this.password = password;
		this.host = hoste;
		this.props = new Properties();
		this.props.put("mail.smtp.auth", "true");
		this.props.put("mail.smtp.starttls.enable", "true");
		this.props.put("mail.smtp.host", host);
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

	public void fire(){
		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject("Testing Subject");

			// Now set the actual message
			message.setText("Hello, this is sample for to check send " +
					"email using JavaMailAPI " +
					"PYOJOON IT WORKS !!!!!!" +
					"" +
					"");

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
