package session;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.persistence.Query;

import entity.Buyer;
import entity.Coupons;
import entity.Payment;
@Stateless
@Local(PaymentDaoLocal.class)
public class PaymentDaoBean extends GenericDaoBean<Payment, Integer> implements
		PaymentDaoLocal {
	@Override
	public List<Payment> findPaymentsForBuyer(Buyer buyerID) {
		Query q = em.createNamedQuery("findPaymentsForBuyer");
		q.setParameter("buyerID", (buyerID));
		@SuppressWarnings("unchecked")
		List<Payment> payments = (List<Payment>) q.getResultList();
		return payments;
	}
	
	@Resource(name="JmsConnectionFactory")
	private ConnectionFactory qcf;

	@Resource(name="PaymentQueue")
	private Queue paymentQueue;
	
	
	public void sendEmail(Coupons coupon) {
		
		Connection connection = null;
		Session session = null;
		MessageProducer producer = null;
		
		try {
			// Creates a connection
			connection = qcf.createConnection();

            // Creates a session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Creates a message producer from the Session to the Topic or Queue
            producer = session.createProducer(paymentQueue);

            // Creates an object message
            ObjectMessage object = session.createObjectMessage();
            object.setObject(coupon);
		    
            // Tells the producer to send the object message
            producer.send(object);
            
            // Closes the producer
            producer.close();
            
            // Closes the session
            session.close();
            
            // Closes the connection
            connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
