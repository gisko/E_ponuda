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
import session.CouponsDaoLocal;
import session.OfferDaoLocal;
import session.UserDaoLocal;
import entity.Coupons;
import entity.Seller;
import entity.User;

public class ShowServlet extends HttpServlet{
	

	//private static Logger log = Logger.getLogger(ShowServlet.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -8552683134862056264L;
	
	@EJB
	private UserDaoLocal userDao;	
	@EJB
	private OfferDaoLocal offerDao;	
	@EJB
	private CouponsDaoLocal couponsDao;
	@EJB
	private BranchDaoLocal branchDao;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		String cont = (String)request.getParameter("cont");
		String couponID = (String)request.getParameter("couponID");
		String sellersID = (String)request.getParameter("sellersID");
		try{
		if(cont!=null && cont.equals("branches")){
			Coupons coup = couponsDao.findById(new Integer(couponID));
			session.setAttribute("couponBranch", branchDao.findBranchForSeller(
					((Seller)(offerDao.findById(
							coup.getOffer().getId())
							).getManager())));
			
			RequestDispatcher disp = request.getRequestDispatcher("showBranches.jsp");
			disp.forward(request, response);
			return;
		}else if(cont!=null && cont.equals("seller")){
			if(!sellersID.equals("All")){
			User user = userDao.findById(new Integer(sellersID));
			if(user==null){
				RequestDispatcher disp = request.getRequestDispatcher("./Home");
				disp.forward(request, response);
				return;
			}
			session.setAttribute("offers", offerDao.findOffersForManager((Seller)user));
			RequestDispatcher disp = request.getRequestDispatcher("offers.jsp");
			disp.forward(request, response);
			return;
			}else{
				session.setAttribute("offers", offerDao.findAll());
				RequestDispatcher disp = request.getRequestDispatcher("offers.jsp");
				disp.forward(request, response);
				return;
			}
		}
		}catch(NumberFormatException e){
			RequestDispatcher disp = request.getRequestDispatcher("offers.jsp");
			disp.forward(request, response);
			return;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}

}
