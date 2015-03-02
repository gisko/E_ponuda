package servlet;

import java.io.IOException;

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

import session.BuyerDaoLocal;
import session.SellerDaoLocal;
import session.UserDaoLocal;
import entity.Buyer;
import entity.Seller;
import entity.User;

public class RegisterServlet extends HttpServlet{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -9146550601773022100L;
	
	private static Logger log = Logger.getLogger(RegisterServlet.class);
	public static String  messageREG=null;
	@EJB
	private BuyerDaoLocal buyerDao;

	@EJB
	private SellerDaoLocal sellerDao;

	@EJB
	private UserDaoLocal userDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = (String)request.getParameter("type");
		if(type!=null && type.equals("REG")){
			RequestDispatcher disp = request.getRequestDispatcher("register.jsp");
			disp.forward(request, response);
			return;
		}else if(type!=null && type.equals("CH")){
			HttpSession session = request.getSession(true);
			String 	id = (String)request.getParameter("id");
			User user = null;
			
			try{
			Integer x = Integer.parseInt(id);
			user = userDao.findById(x);
			} catch (EJBException e) {
				//POGRESNA LOZINKA
				if (e.getCause().getClass().equals(NoResultException.class)) {
					RequestDispatcher disp = request.getRequestDispatcher("updateData.jsp");
					disp.forward(request, response);
					return;
				} else {
					throw e;
				}
			}
				session.setAttribute("zaIzmenuUser", user);
				RequestDispatcher disp = request.getRequestDispatcher("updateData.jsp");
				disp.forward(request, response);
				return;
			
		}
		
		try {
			String control = request.getParameter("control");	
			String 	username = request.getParameter("korisnickoIme");
			String 	password = request.getParameter("lozinka");
			String firstName = request.getParameter("ime");
			String lastName = request.getParameter("prezime");
			String telephone = request.getParameter("telefon");
			String 	email = request.getParameter("email");
			String address = request.getParameter("adresa");
			
			if(control!=null && control.equals("BUYER")){
				
				HttpSession session = request.getSession(true);			
				try{
				Buyer buyer = new Buyer(firstName, lastName, username, password, telephone, address, email);
				buyerDao.persist(buyer);
				} catch (EJBException e) {
					messageREG = "Username already exist. Try again!";
					request.setAttribute("messageREG", messageREG);
					RequestDispatcher disp = request.getRequestDispatcher("register.jsp");
					disp.forward(request, response);
					return;
				}
				User user = null;
				user = userDao.findUser(username, password);
				if(user==null){
					RequestDispatcher disp = request.getRequestDispatcher("./Home");
					disp.forward(request, response);
					return;
				}
				Buyer buyer1 = (Buyer) user;
				session.setAttribute("user", buyer1);
				log.info("REGISTER: Buyer "+ username+" created account!");
				RequestDispatcher disp = request.getRequestDispatcher("./Home?next=OFFER");
				disp.forward(request, response);
				return;
			
			}else if(control!=null && control.equals("SELLER")){
				try{
				Seller seller = new Seller(firstName, lastName, username, password, telephone, email);
				sellerDao.persist(seller);
				} catch (EJBException e) {
					messageREG = "Username already exist. Try again!";
					request.setAttribute("messageREG", messageREG);
					RequestDispatcher disp = request.getRequestDispatcher("register.jsp");
					disp.forward(request, response);
					return;
				}
				log.info("REGISTER: SELLER "+ username+" was created by admin!");
				
				RequestDispatcher disp = request.getRequestDispatcher("./Home?next=SEL");
				
				disp.forward(request, response);
				return;
			}else //IZMENE
				if(control!=null && control.equals("CHANGE")){
					String 	newPassword = request.getParameter("novaLozinka");
					String 	username2 = request.getParameter("username2");
					
					User user = null;
					try{
					user = userDao.findUser(username2, password);
					} catch (EJBException e) {
						//POGRESNA LOZINKA
						if (e.getCause().getClass().equals(NoResultException.class)) {
							RequestDispatcher disp = request.getRequestDispatcher("updateData.jsp");
							disp.forward(request, response);
							return;
						} else {
							throw e;
						}
					}
					
					if(user.getDTYPE().equals("Buyer")){
					HttpSession session = request.getSession(true);
					
					if(newPassword==null || newPassword.trim().equals("")) newPassword=password;
				
					Buyer buyer = new Buyer(firstName, lastName, username2, newPassword, telephone, address, email);					
					buyer.setDTYPE("Buyer");
					buyer.setId(user.getId());
					
					buyerDao.merge(buyer);
					log.info("EDIT BUYER: Buyer "+ username+" edited account!");
					session.setAttribute("user", buyer);
					
					RequestDispatcher disp = request.getRequestDispatcher("./Home?next=OFFER");
					disp.forward(request, response);
					return;
					}else{
						if(newPassword==null || newPassword.trim().equals("")) newPassword=password;
						Seller seller = new Seller(firstName, lastName, username2, newPassword, telephone, email);					
						seller.setDTYPE("Seller");
						seller.setId(user.getId());
						
						sellerDao.merge(seller);
						log.info("EDIT SELLER: Seller "+ username+" edited account!");
						//session.setAttribute("user", seller);
						
						RequestDispatcher disp = request.getRequestDispatcher("./Home?next=SEL");
						disp.forward(request, response);
						return;
					}
					}
			
			
		}  catch (IOException e) {
			log.error(e);
			throw e;
		}
		
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}

}
