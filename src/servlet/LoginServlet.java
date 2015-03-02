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

import session.BranchDaoLocal;
import session.UserDaoLocal;
import entity.Admin;
import entity.Buyer;
import entity.Seller;
import entity.User;

public class LoginServlet extends HttpServlet{
	

	private static Logger log = Logger.getLogger(LoginServlet.class);
	public static String  messageAdmin=null;
	public static String  messageBuyer=null;
	public static String  messageSeller=null;
	/**
	 * 
	 */
	private static final long serialVersionUID = -8552683134862056264L;
	
	@EJB
	private UserDaoLocal userDao;
	@EJB
	private BranchDaoLocal branchDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = (String)request.getParameter("type");
		String cont = (String)request.getParameter("cont");
		if(logInWrong(request,response,type)) return;
		try {

			String username = request.getParameter("korisnickoIme");
			String password = request.getParameter("lozinka");
			// tip logovanja

			
			if ((username == null) || (username.trim().equals(""))
					|| (password == null) || (password.trim().equals(""))) {
				if(logInWrong(request,response,cont)) return;
			}

			User user = null;
			user = userDao.findUser(username, password);
			HttpSession session = request.getSession(true);
		
			if(cont!=null && cont.equals("seller")){
				if(user.getDTYPE().equals("Seller")){
					Seller seller = (Seller) user;
					session.setAttribute("branches", branchDao.findBranchForSeller(seller));
					session.setAttribute("user", seller);
					log.info("LOGIN: Seller "+seller.getUsername() +" is logged in");
				}else{
					log.info("LOGIN: Invalid username/password.");
					if(logInWrong(request,response,cont))return;
				}
			}else if(cont!=null && cont.equals("buyer")){
				if(user.getDTYPE().equals("Buyer")){
					Buyer buyer = (Buyer) user;
					session.setAttribute("user", buyer);
					log.info("LOGIN: Buyer "+buyer.getUsername() +" is loged in");
				}else{
					log.info("LOGIN: Invalid username/password.");
					if(logInWrong(request,response,cont))return;
				}
			}else if(cont!=null && cont.equals("admin")){
				if(user.getDTYPE().equals("Admin")){
					Admin admin = (Admin) user;
					session.setAttribute("user", admin);
					log.info("LOGIN: Admin is logged in");
				}else{
					log.info("LOGIN: Invalid username/password.");
					if(logInWrong(request,response,cont))return;
				}
			}
			
			RequestDispatcher disp = request.getRequestDispatcher("/Home?next=OFFER");
			disp.forward(request, response);

		} catch (EJBException e) {
			if (e.getCause().getClass().equals(NoResultException.class)) {
				if(logInWrong(request,response,cont))return;
			} else {
				throw e;
			}
		} catch (ServletException e) {
			log.error(e);
			throw e;
		} catch (IOException e) {
			log.error(e);
			throw e;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}
	
	private boolean logInWrong(HttpServletRequest request, HttpServletResponse response, String type) throws ServletException, IOException{
		if(type!=null && type.equalsIgnoreCase("ADMIN")){
			messageAdmin = "Invalid username/password combination.";
			request.setAttribute("messageAdmin", messageAdmin);
			RequestDispatcher disp = request.getRequestDispatcher("loginAdmina.jsp");
			disp.forward(request, response);
			return true;
		}
		else if(type!=null && type.equalsIgnoreCase("BUYER")){
			messageBuyer = "Invalid username/password combination.";
			request.setAttribute("messageBuyer", messageBuyer);
			RequestDispatcher disp = request.getRequestDispatcher("loginBuyer.jsp");
			disp.forward(request, response);
			return true;
		}else if(type!=null && type.equalsIgnoreCase("SELLER")){
			messageSeller = "Invalid username/password combination.";
			request.setAttribute("messageSeller", messageSeller);
			RequestDispatcher disp = request.getRequestDispatcher("loginSeller.jsp");
			disp.forward(request, response);
			return true;
		}
		return false;
	}

}
