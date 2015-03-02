package servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import session.CommentDaoLocal;
import session.OfferDaoLocal;
import entity.Buyer;
import entity.Comment;
import entity.Offer;
import entity.User;

public class CommentServlet extends HttpServlet{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -9146550601773022100L;
	
	private static Logger log = Logger.getLogger(CommentServlet.class);
	
	@EJB
	private OfferDaoLocal offerDao;
	@EJB
	private CommentDaoLocal commentDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		String ponudaID = (String)request.getParameter("ponuda");
		String message = (String)request.getParameter("message");
		String cont = (String)request.getParameter("cont");
		String komentar = (String)request.getParameter("komentar");
		HttpSession session = request.getSession();
		
		Offer o = null;
		if(cont!=null && cont.equals("add")){
		o = offerDao.findById(new Integer(ponudaID));
		User user = (User)request.getSession().getAttribute("user");
		if(user!=null){		
		Comment com = new Comment(message,o,(Buyer)user);
		commentDao.persist(com);
			}
		}else if(cont!=null && cont.equals("rem")){			
			Comment com= commentDao.findById(new Integer(komentar));
			commentDao.remove(com);		
		}
		
		o = offerDao.findById(new Integer(ponudaID));		
		session.setAttribute("comments",  commentDao.findCommentsForOffer(o));
	
		log.info("COMMENT: is added!");	
		RequestDispatcher disp = request.getRequestDispatcher("showOffer.jsp?offerID="+ponudaID);
		disp.forward(request, response);
		return;
		
		
	}catch (EJBException | NumberFormatException ex) {
		RequestDispatcher disp = request.getRequestDispatcher("./Home");
		disp.forward(request, response);
		return;
	}
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}

}
