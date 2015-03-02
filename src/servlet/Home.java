package servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import session.BranchDaoLocal;
import session.BuyerDaoLocal;
import session.CategoryDaoLocal;
import session.CommentDaoLocal;
import session.CouponsDaoLocal;
import session.OfferDaoLocal;
import session.PaymentDaoLocal;
import session.SellerDaoLocal;
import session.UserDaoLocal;
import entity.Category;
import entity.Seller;
import entity.User;

public class Home extends HttpServlet {
	static int _FIRST=0;
	/**
	 * 
	 */
	private static final long serialVersionUID = -6006685922650199318L;

	//private static Logger log = Logger.getLogger(Home.class);
	@EJB
	private UserDaoLocal userDao;	
	@EJB
	private CategoryDaoLocal categoryDao;	
	@EJB
	private OfferDaoLocal offerDao;	
	@EJB
	private CouponsDaoLocal couponsDao;	
	@EJB
	private PaymentDaoLocal paymentsDao;	
	@EJB
	private BranchDaoLocal branchDao;	
	@EJB
	private SellerDaoLocal sellerDao;	
	@EJB
	private BuyerDaoLocal buyerDao;
	@EJB
	private CommentDaoLocal commentDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final HttpSession session = request.getSession();
		
		
		String next = (String)request.getParameter("next");
		
//		if(_FIRST<=0){							
//			session.setAttribute("offers",  offerDao.findActiveOffers());
//			_FIRST++;
//		}
		session.setAttribute("offers",  offerDao.findActiveOffers());
		getServletContext().setAttribute("offersCATEGORY", offerDao.findActiveOffers());
		//session.setAttribute("offersCATEGORY",  offerDao.findActiveOffers());	
		//session.setAttribute("category", categoryDao.findAll());
		getServletContext().setAttribute("category",categoryDao.findAll());
		session.setAttribute("sellers", userDao.findByType("Seller"));		
		
		session.setAttribute("branches", branchDao.findAll());
		session.setAttribute("comments", commentDao.findAll());
		session.setAttribute("payments", paymentsDao.findAll());
	//	log.info("HOME REDIRECT: show/redirect servlet to " +next +".");
		
		if(next!=null && next.equals("SEL")){
			request.setAttribute("sellers", userDao.findByType("Seller"));
			
			User user = (User)request.getSession().getAttribute("user");
			if(user!=null && user.getDTYPE().equals("Admin")){	
			RequestDispatcher disp = request.getRequestDispatcher("sellers.jsp");
			disp.forward(request, response);
			return;
			}else{
				RequestDispatcher disp = request.getRequestDispatcher("index.jsp");
				disp.forward(request, response);
				return;
			}
			
		}else if(next!=null && next.equals("CAT")){
			request.setAttribute("category", categoryDao.findAll());
			
			RequestDispatcher disp = request.getRequestDispatcher("category.jsp");
			disp.forward(request, response);
			return;
		}else if(next!=null && next.equals("OFFER")){
			User user = (User)request.getSession().getAttribute("user");
			if(user!=null && user.getDTYPE().equals("Seller")){	
				
				session.setAttribute("offers", offerDao.findOffersForManager(sellerDao.findById(user.getId())));
				
				session.setAttribute("branches", branchDao.findBranchForSeller(sellerDao.findById(user.getId())));
				RequestDispatcher disp = request.getRequestDispatcher("offers.jsp");
				disp.forward(request, response);
				return;
			}else if(user!=null && user.getDTYPE().equals("Admin")){	
				session.setAttribute("offers", offerDao.findAll());
				
				RequestDispatcher disp = request.getRequestDispatcher("offers.jsp");
				disp.forward(request, response);
				return;
			}else{
				//CATEGORY SORT
				try{
				String catID = (String)request.getParameter("catID");
				String sellID = (String)request.getParameter("sellID");
				if(catID!=null && !catID.equals("ALL")){
					Category cat = categoryDao.findById(new Integer(catID));
					if(cat!=null){
					session.setAttribute("offers", offerDao.findOffersPerCategory(cat));
					String managerName="Active offers per "+cat.getName()+":";
					request.setAttribute("managerName",managerName);
					}
				}else if(sellID!=null){
					Seller sell = sellerDao.findById(new Integer(sellID));
					if(sell!=null){
					session.setAttribute("offers", offerDao.findActiveOffersForManager(sell));
					String managerName=sell.getUsername()+"'s active offers:";
					request.setAttribute("managerName",managerName);
					}
				}else{					
					session.setAttribute("offers", offerDao.findActiveOffers());
				}
				}catch(NumberFormatException e){
					RequestDispatcher disp = request.getRequestDispatcher("index.jsp");
					disp.forward(request, response);
					return;
				}
				
				RequestDispatcher disp = request.getRequestDispatcher("index.jsp");
				disp.forward(request, response);
				return;
			}
			
		}else if(next!=null && next.equals("COUP")){
			User user = (User)request.getSession().getAttribute("user");
			if(user!=null && user.getDTYPE().equals("Admin")){	
			session.setAttribute("coupons", couponsDao.findAll());
			
			RequestDispatcher disp = request.getRequestDispatcher("coupons.jsp");
			disp.forward(request, response);
			return;
			}else if(user!=null && user.getDTYPE().equals("Buyer")){
				
				session.setAttribute("coupons",couponsDao.findCoupounsForBuyer(buyerDao.findById(user.getId())) );
				
				RequestDispatcher disp = request.getRequestDispatcher("coupons.jsp");
				disp.forward(request, response);
				return;
				
			}
		}else if(next!=null && next.equals("PAY")){
			User user = (User)request.getSession().getAttribute("user");
			if(user!=null && user.getDTYPE().equals("Admin")){	
				session.setAttribute("payments", paymentsDao.findAll());
				
				RequestDispatcher disp = request.getRequestDispatcher("payments.jsp");
				disp.forward(request, response);
			return;
			}else if(user!=null && user.getDTYPE().equals("Buyer")){	
				session.setAttribute("payments",paymentsDao.findPaymentsForBuyer(buyerDao.findById(user.getId())));
				
				RequestDispatcher disp = request.getRequestDispatcher("payments.jsp");
				disp.forward(request, response);
			return;
			}
		}else if(next!=null && next.equals("BR")){
			User user = (User)request.getSession().getAttribute("user");
			if(user!=null && user.getDTYPE().equals("Seller")){					
			session.setAttribute("branches", branchDao.findBranchForSeller(sellerDao.findById(user.getId())));
			
			RequestDispatcher disp = request.getRequestDispatcher("branches.jsp");
			disp.forward(request, response);
			return;
			}
		}
		
		User user = (User)request.getSession().getAttribute("user");
		if(user!=null && user.getDTYPE().equals("Seller")){				
		RequestDispatcher disp = request.getRequestDispatcher("offers.jsp");
		disp.forward(request, response);
		return;
		}else if(user!=null && user.getDTYPE().equals("Admin")){				
			RequestDispatcher disp = request.getRequestDispatcher("offers.jsp");
			disp.forward(request, response);
			return;
		}else{
			RequestDispatcher disp = request.getRequestDispatcher("index.jsp");
			disp.forward(request, response);
			return;
		}
		
		
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}

}
