package vue.authentification;

import java.sql.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	
	final String from = "aca0f716262f9b";
	final String password = "1f84ed37735ce0";

	public void mailSended(String email, int key) {
		String textHtml = " '<p><b>Bonjour</b>, <br/> Nous avons reçu une demande de \r\n"
				+ "changement de mot de passe pour cette adresse e-mail</p>"
				+ "<button style=\"background-color: black; border: none;"
				+ "padding: 6px; color: white; border-radius: 3px;"
				+ "font-size: 1em;>" + key + "</button>";

		Properties props = new Properties();
		props.put("mail.smtps.host", "sandbox.smtp.mailtrap.io");
		 props.put("mail.smtp.port", "2525");
		 props.put("mail.smtp.auth", "true");
		 props.put("mail.smtp.starttls.enable", "true");
		 //props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		// props.put("mail.debug", "true");
	

		 Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		}); 
		
		//Session session  = Session.getInstance(props, null);

		try {

			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("Changement de mot de passe");

			
			message.setText(textHtml);

			message.setHeader("Gestion de stockage", "projet");
			message.setSentDate(new Date(0));

			// Envoie de mail
			Transport.send(message);

		} catch (MessagingException e) {
			System.out.println("I'm here");
			//throw new RuntimeException(e);
			e.printStackTrace();
		}
	}

}
