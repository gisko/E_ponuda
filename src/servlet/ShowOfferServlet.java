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

import session.BranchDaoLocal;
import session.CommentDaoLocal;
import session.ImageDaoLocal;
import session.OfferDaoLocal;
import session.SellerDaoLocal;
import entity.Offer;
import entity.User;

public class ShowOfferServlet extends HttpServlet{
	

	//private static Logger log = Logger.getLogger(ShowOfferServlet.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -8552683134862056264L;	
	@EJB
	private OfferDaoLocal offerDao;
	@EJB
	private CommentDaoLocal commentDao;
	@EJB
	private ImageDaoLocal imageDao;
	@EJB
	private BranchDaoLocal branchDao;
	@EJB
	private SellerDaoLocal sellerDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String offerID = (String)request.getParameter("offerID");
	
		try {
			HttpSession session = request.getSession(true);
			Offer o=null;
			Integer offID=null;
			try {
				offID = new Integer(offerID);
			  } catch (NumberFormatException e) {
				  RequestDispatcher disp = request.getRequestDispatcher("./Home?next=OFFER");
				  disp.forward(request, response);	
				  return;
			  }
			
			try{
				User user = (User)request.getSession().getAttribute("user");
				
				if(user!=null && (user.getDTYPE().equals("Seller")|| user.getDTYPE().equals("Admin"))){
				for(int i =0;i<offerDao.findAll().size();i++){
					if(offerDao.findAll().get(i).getId() == offID){
						o = offerDao.findById(offID);
					}
				}
				}else{
					for(int i =0;i<offerDao.findActiveOffers().size();i++){
						if(offerDao.findActiveOffers().get(i).getId() == offID){
							o = offerDao.findById(offID);
						}
				}
				}
				if(o==null){
					RequestDispatcher disp = request.getRequestDispatcher("./Home?next=OFFER");
					disp.forward(request, response);				
					return;
				}
			}catch(EJBException e) {				
				RequestDispatcher disp = request.getRequestDispatcher("./Home?next=OFFER");
				disp.forward(request, response);				
				return;
			}
			
			session.setAttribute("showOffer", o);
			session.setAttribute("branches", branchDao.findBranchForSeller(o.getManager()));
			session.setAttribute("images",  imageDao.findImageForOffer(offerDao.findById(new Integer(offerID))));
			
			o = offerDao.findById(new Integer(offerID));		
			session.setAttribute("comments",  commentDao.findCommentsForOffer(o));
			
			
			RequestDispatcher disp = request.getRequestDispatcher("showOffer.jsp");
			disp.forward(request, response);
			return;
			

		} catch (EJBException e) {			
			RequestDispatcher disp =null;
			if (e.getCause().getClass().equals(NoResultException.class)) {
				disp = request.getRequestDispatcher("./Home?next=OFFER");
				disp.forward(request, response);
				return;
			} else {
				disp = request.getRequestDispatcher("./Home?next=OFFER");
				disp.forward(request, response);
				return;
			
			}
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}

}
