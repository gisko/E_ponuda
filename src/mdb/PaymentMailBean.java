package mdb;

import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import entity.Coupons;


@MessageDriven(
		activationConfig = {
			@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
			@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
			@ActivationConfigProperty(propertyName = "destination", propertyValue = "PaymentQueue") 
		}
	)
public class PaymentMailBean  implements MessageListener{

	@Resource(name="Mail")
	Session session;

	public PaymentMailBean() {

	}

	public void onMessage(Message message) {
		
	    try {
	        if (message instanceof ObjectMessage) {
	            ObjectMessage obj = (ObjectMessage) message;
	            Coupons info = (Coupons)obj.getObject();
	            
	            // Validate the credit card using web services...
	            
	            sendMessage(info);
	        } else {
	            System.out.println("MESSAGE BEAN: Message of wrong type: " + message.getClass().getName());
	        }
	    } catch (JMSException e) {
	        e.printStackTrace();
	    } catch (Throwable te) {
	        te.printStackTrace();
	    }
	}

	private void sendMessage(Coupons coup) throws AddressException, MessagingException {

		String adressTo= coup.getBuyer().getEmail();
		// Constructs the message 
		javax.mail.Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("eponudaisa@gmail.com"));
		msg.setRecipients(RecipientType.TO, InternetAddress.parse(adressTo));
		String subject = "E_ponuda support";
		msg.setSubject(subject);
		
		String body = "You successful buy your offer("+coup.getOffer().getName()+") for "
	    		  + coup.getOffer().getSalePrice() +" RSD. "
	      		+ "Here is coupon number: "+ coup.getCouponIdentifies()+" .";
		msg.setText(body);
		
		msg.setSentDate(new Date());
		
		// Sends the message
		Transport.send(msg);
	}
	
}
