package servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import session.BranchDaoLocal;
import session.CategoryDaoLocal;
import session.ImageDaoLocal;
import session.OfferDaoLocal;
import session.SellerDaoLocal;
import session.UserDaoLocal;
import entity.Branch;
import entity.Category;
import entity.Image;
import entity.Offer;
import entity.Seller;
import entity.User;

public class RemoveServlet extends HttpServlet{
	

	private static Logger log = Logger.getLogger(RemoveServlet.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -8552683134862056264L;
	
	@EJB
	private UserDaoLocal userDao;
	
	@EJB
	private OfferDaoLocal offerDao;
	
	@EJB
	private CategoryDaoLocal categoryDao;
	
	@EJB
	private BranchDaoLocal branchDao;
	
	@EJB
	private SellerDaoLocal sellerDao;
	
	@EJB
	private ImageDaoLocal imageDao;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);		
		String cont = (String)request.getParameter("cont");
		String categoryID = (String)request.getParameter("categoryID");
		String sellersID = (String)request.getParameter("sellersID");
		String branchesID = (String)request.getParameter("branchesID");
		String offerID = (String)request.getParameter("offerID");
		
		if(cont!=null && cont.equals("CAT")){
			Category cat = categoryDao.findById(new Integer(categoryID));
			categoryDao.remove(cat);
			log.info("REMOVE CATEGORY: CATEGORY "+ cat.getName()+" removed!");
			session.setAttribute("category", categoryDao.findAll());
			RequestDispatcher disp = request.getRequestDispatcher("category.jsp");
			disp.forward(request, response);
			return;
		}else if(cont!=null && cont.equals("SEL")){
			User user = userDao.findById(new Integer(sellersID));	
			if(user==null){
				RequestDispatcher disp = request.getRequestDispatcher("./Home");
				disp.forward(request, response);
				return;
			}
			
			for(Branch b : branchDao.findBranchForSeller(((Seller)user))){
				branchDao.remove(b);
			}	
			
			userDao.remove((Seller)user);
			log.info("REMOVE SELLER/BRANCHES: SELLER "+ user.getUsername()+" removed!");
			session.setAttribute("branches", branchDao.findAll());
			session.setAttribute("sellers",userDao.findByType("Seller"));
			RequestDispatcher disp = request.getRequestDispatcher("sellers.jsp");
			disp.forward(request, response);
			return;
		}else if(cont!=null && cont.equals("BR")){
			Branch b = branchDao.findById(new Integer(branchesID));			
			User user = (User)request.getSession().getAttribute("user");
			if(user==null){
				RequestDispatcher disp = request.getRequestDispatcher("./Home");
				disp.forward(request, response);
				return;
			}
			branchDao.remove(b);
			log.info("REMOVE BRANCHES: Branches "+ b.getName()+" removed!");
			session.setAttribute("branches", branchDao.findBranchForSeller(sellerDao.findById(user.getId())));
			RequestDispatcher disp = request.getRequestDispatcher("branches.jsp");
			disp.forward(request, response);
			return;
		}else if(cont!=null && cont.equals("OFF")){
			Offer o = offerDao.findById(new Integer(offerID));	
			User user = (User)request.getSession().getAttribute("user");
			if(user==null){
				RequestDispatcher disp = request.getRequestDispatcher("./Home");
				disp.forward(request, response);
				return;
			}
			
			for(Image img : imageDao.findImageForOffer(o)){
				imageDao.remove(img);
			}
			
			offerDao.remove(o);			
			log.info("REMOVE OFFER/IMAGES: Offer "+ o.getName()+" removed!");
			session.setAttribute("offers", offerDao.findOffersForManager(((Seller)user)));
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
