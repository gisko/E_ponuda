package servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import session.CouponsDaoLocal;
import session.OfferDaoLocal;
import session.PaymentDaoLocal;
import entity.Buyer;
import entity.Coupons;
import entity.Offer;
import entity.Payment;
import entity.User;

public class PaymentServlet extends HttpServlet{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -9146550601773022100L;
	public static String  messagePAY=null;
	private static Logger log = Logger.getLogger(PaymentServlet.class);
	
	@EJB
	private PaymentDaoLocal paymentsDao;
	
	@EJB
	private OfferDaoLocal offerDao;
	
	@EJB
	private CouponsDaoLocal couponDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String payMade = (String)request.getParameter("actCH");
		String canceled = (String)request.getParameter("usdCH");
	
		String id2 = (String)request.getParameter("used");
		String cont = (String)request.getParameter("cont");
		String offerID = (String)request.getParameter("offerID");
		String next = (String)request.getParameter("next");
		String payID = (String)request.getParameter("payID");
	
		
		Integer idINT = null;
		try {
			HttpSession session = request.getSession();
			if(payMade!=null && payMade.equals("true")){
			Payment pay =null;
			Integer x = Integer.parseInt(payID);
			idINT = x;
			pay = paymentsDao.findById(x);
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date trenutnoVreme = new Date();			
			Date ponudaIstice = pay.getOffer().getExpirationDate();
			
			if(trenutnoVreme.after(ponudaIstice)) {
				messagePAY = "Can't buy this offer anymore. Deadline was at "+dateFormat.format(ponudaIstice);
				request.setAttribute("messagePAY", messagePAY);
				RequestDispatcher disp = request.getRequestDispatcher("./Home?next=PAY");
				disp.forward(request, response);
				return;
			}
			
			pay.setPaymentsMade(true);		
			pay.setId(idINT);	
			Offer o =offerDao.findById(pay.getOffer().getId());
			
			int brojKupljenih = o.getPurchasedOffers();					
			brojKupljenih++;
			if(brojKupljenih<=o.getMaxOffers()) {
				o.setPurchasedOffers(brojKupljenih);
				if(brojKupljenih==o.getMaxOffers()) o.setActive(false);
			}
			else o.setActive(false);
			o.setId(pay.getOffer().getId());
			paymentsDao.merge(pay);
			offerDao.merge(o);		
			log.info("PAYENT/OFFER: payment with "+ o.getName()+" is edited!");
			session.setAttribute("offers", offerDao.findAll());
			session.setAttribute("payments", paymentsDao.findAll());
			String couponIdentifier= getRandomCouponID();		
			
			Coupons coup = new Coupons(
					couponIdentifier,false,o.getValidTo(),true,pay.getOffer(),pay.getBuyer());
			couponDao.persist(coup);
			log.info("COUPON: "+ coup.getCouponIdentifies()+"is added!");
			//============MAIL==================
			  
		/*	String USER_NAME = "eponudaisa";  // GMail user name (just the part before "@gmail.com")
			  String PASSWORD = "ra442011"; // GMail password
			  String RECIPIENT = pay.getBuyer().getEmail();
			  String[] to = { RECIPIENT };
			  String subject = "E_ponuda support";
		      String body = "You successful buy your offer("+pay.getOffer().getName()+") for "
		    		  + pay.getOffer().getSalePrice() +" RSD. "
		      		+ "Here is coupon number: "+ couponIdentifier+" .";
		      Mail.sendFromGMail(USER_NAME, PASSWORD, to, subject, body);
		      log.info("MAIL: was sent to "+pay.getBuyer().getUsername());
*/
			//================================
			
			paymentsDao.sendEmail(coup);
			log.info("MAIL SENT:  Mail was sent successfully");
			session.setAttribute("coupons", couponDao.findAll());
			RequestDispatcher disp = request.getRequestDispatcher("./Home?next=PAY");
			disp.forward(request, response);
			return;		
			}else if(canceled!=null && canceled.equals("true")){				
				Payment pay =null;
				try{
					Integer x = Integer.parseInt(id2);
					idINT = x;
					pay = paymentsDao.findById(x);
					} catch (EJBException e) {
						//POGRESNA LOZINKA
						if (e.getCause().getClass().equals(NoResultException.class)) {
							RequestDispatcher disp = request.getRequestDispatcher("./Home?next=PAY");
							disp.forward(request, response);
							return;
						} else {
							throw e;
						}
					}
				
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date trenutnoVreme = new Date();			
				Date ponudaIstice = pay.getOffer().getExpirationDate();				
				if(trenutnoVreme.after(ponudaIstice) || pay.getOffer().getPurchasedOffers()==pay.getOffer().getMaxOffers()) pay.setCanceled(true);
				else{
					messagePAY = "Can't canceled this payment until "+ dateFormat.format(ponudaIstice) +".";
					request.setAttribute("messagePAY", messagePAY);
					RequestDispatcher disp = request.getRequestDispatcher("./Home?next=PAY");
					disp.forward(request, response);
					return;
				}
				
				pay.setId(idINT);
				
				paymentsDao.merge(pay);
				log.info("PAYENT: payment changed PAYED: "+pay.isPaymentsMade()+" | CANCELED: "+pay.isCanceled());
				session.setAttribute("payments", paymentsDao.findAll());
				RequestDispatcher disp = request.getRequestDispatcher("./Home?next=PAY");
				disp.forward(request, response);
				return;		
			
			}else if(cont!=null && cont.equals("ADD")){
				User user = (User)request.getSession().getAttribute("user");
				if(user==null){
					RequestDispatcher disp = request.getRequestDispatcher("./Home");
					disp.forward(request, response);
					return;
				}
				Offer o = offerDao.findById(new Integer(offerID));
				Payment pay = new Payment(o.getSalePrice(),false,false,(Buyer)user,o);
				paymentsDao.persist(pay);
				log.info("PAYMENT: "+ pay.getId() +" is added!");
				session.setAttribute("payments", paymentsDao.findAll());				
				RequestDispatcher disp=null;
				if(next!=null && next.equals("show"))
					disp = request.getRequestDispatcher("./ShowOfferServlet?offerID="+offerID);
				else
					disp = request.getRequestDispatcher("./Home?next=OFFER");
				disp.forward(request, response);
				return;	
			}
		}  catch (IOException e) {
			log.error(e);
			throw e;
		}
	}
	
	private String getRandomCouponID(){
		char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		    if(i==4) sb.append("-");
		    if(i==14) sb.append("-");
		}
		String couponID = sb.toString();
		List<Coupons> coupons = couponDao.findAll();
		for(int i =0 ; i < coupons.size();i++){
			if(coupons.get(i).getCouponIdentifies().equalsIgnoreCase(couponID))
				getRandomCouponID();
		}
		return couponID;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}

}
