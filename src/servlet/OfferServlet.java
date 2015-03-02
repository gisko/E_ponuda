package servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import session.CategoryDaoLocal;
import session.OfferDaoLocal;
import entity.Offer;
import entity.Seller;
import entity.User;

public class OfferServlet extends HttpServlet{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -9146550601773022100L;
	public static String  messageOFF=null;
	private static Logger log = Logger.getLogger(OfferServlet.class);
	
	@EJB
	private OfferDaoLocal offerDao;
	
	@EJB
	private CategoryDaoLocal categoryDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actCH = (String)request.getParameter("actCH");
		String cont = (String)request.getParameter("cont");
	
		String name=request.getParameter("name");
		String dateCreated=request.getParameter("dateCreated");
		String expirationDate= request.getParameter("expirationDate");
		String validFrom=request.getParameter("validFrom");
		String validTo= request.getParameter("validTo");
		String regularPrice=request.getParameter("regularPrice");
		String salePrice = request.getParameter("salePrice");
		String maxOffers= request.getParameter("maxOffers");
		String description = request.getParameter("description");
		String categoryID =request.getParameter("categoryID");
		if(cont!=null && cont.equals("ADD")){
			RequestDispatcher disp = request.getRequestDispatcher("addOffer.jsp");
			disp.forward(request, response);
			return;
		}else if(cont!=null && cont.equals("CH")){
			HttpSession session = request.getSession(true);
			String 	id = (String)request.getParameter("id");
			Offer o = null;
			
			try{
			Integer x = Integer.parseInt(id);
			o = offerDao.findById(x);
			} catch (EJBException e) {
				//POGRESNA LOZINKA
				if (e.getCause().getClass().equals(NoResultException.class)) {
					RequestDispatcher disp = request.getRequestDispatcher("./Home?next=OFFER");
					disp.forward(request, response);
					return;
				} else {
					throw e;
				}
			}
				session.setAttribute("zaIzmenuOffer", o);
			
			
			RequestDispatcher disp = request.getRequestDispatcher("editOffer.jsp");
			disp.forward(request, response);
			return;
			
		}
		Integer idINT = null;
		String idAct = (String)request.getParameter("activna");
		String id=request.getParameter("id");
		try {
			
			if(actCH!=null && actCH.equals("true")){
			Offer offer =null;
			try{
				Integer x = Integer.parseInt(idAct);
				idINT = x;
				offer = offerDao.findById(x);
				} catch (EJBException e) {
					//POGRESNA LOZINKA
					if (e.getCause().getClass().equals(NoResultException.class)) {
						RequestDispatcher disp = request.getRequestDispatcher("./Home?next=OFFER");
						disp.forward(request, response);
						return;
					} else {
						throw e;
					}
				}
			 Date trenutnoVreme = new Date();
			 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			if(trenutnoVreme.after(offer.getExpirationDate())){
				offer.setActive(false);
				messageOFF = "Can't set active this offer becouse expiration date was at "+dateFormat.format(offer.getExpirationDate())+".";
				request.setAttribute("messageOFF", messageOFF);
				if(offer.getMaxOffers()==offer.getPurchasedOffers()){
					offer.setActive(false);
					messageOFF = "Can't set active this offer becouse we don't have anymore in stock.";
					request.setAttribute("messageOFF", messageOFF);
				}
			}
			else if(offer.getMaxOffers()==offer.getPurchasedOffers()){
				offer.setActive(false);
				messageOFF = "Can't set active this offer becouse we don't have anymore in stock.";
				request.setAttribute("messageOFF", messageOFF);
				
			}else{
				if(offer.isActive()) offer.setActive(false);
					else offer.setActive(true);
			}
			offer.setId(idINT);
			
			offerDao.merge(offer);
			log.info("OFFER: "+ offer.getName()+"is edited!");
			//session.setAttribute("offers", offerDao.findAll());
			RequestDispatcher disp = request.getRequestDispatcher("./Home?next=OFFER");
			disp.forward(request, response);
			return;
			
			}else if(cont!=null && cont.equals("head")){
				 String idOffer =request.getParameter("idOFF");
				 String headImage =request.getParameter("headImage");
				 Offer offer= null;
				 offer=offerDao.findById(new Integer(idOffer));							 
				 if(headImage==null)headImage="noImage.png"; //INICIJALNO NA SLIKU NO IMAGE
				 
				 offer.setHeadImageID(headImage);
				 
				 offer.setId(new Integer(idOffer));
				 offerDao.merge(offer);
				 log.info("OFFER: "+ offer.getName()+"is edited!");
				 	RequestDispatcher disp = request.getRequestDispatcher("./Home?next=OFFER");
					disp.forward(request, response);
					return;
			}else{
				
				
				Offer offer= null;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if(id!=null && !id.equals("")){
					
					offer=offerDao.findById(new Integer(id));
				}else {
					offer= new Offer();
					
				}
				if(name!=null && !name.equals("")){
					offer.setName(name);
				}
				try {			
				if(dateCreated!=null && !dateCreated.equals("")){
					offer.setDateCreated(sdf.parse(dateCreated));
				}else {
					Date date = new Date();
					offer.setDateCreated(date);
				}
				if(expirationDate!=null && !expirationDate.equals("")){
					offer.setExpirationDate(sdf.parse(expirationDate));
				}
				if(validFrom!=null && !validFrom.equals("")){
					offer.setValidFrom(sdf.parse(validFrom));
				}
				if(validTo!=null && !validTo.equals("")){
					offer.setValidTo(sdf.parse(validTo));
				}
				} catch (ParseException e) {
					messageOFF = "Wrong date format. Try again with yyyy-MM-dd";
					request.setAttribute("messageOFF", messageOFF);
					RequestDispatcher disp = request.getRequestDispatcher("addOffer.jsp");
					disp.forward(request, response);
					return;
				}
				if(regularPrice!=null && !regularPrice.equals("")){
					offer.setRegularPrice(new Double(regularPrice));
				}
				if(salePrice!=null && !salePrice.equals("")){
					offer.setSalePrice(new Double(salePrice));
				}
				if(maxOffers!=null && !maxOffers.equals("")){
					offer.setMaxOffers(new Integer(maxOffers));
				}
				if(description!=null && !description.equals("")){
					offer.setDescription(description);
				}
					
				if(categoryID!=null && !categoryID.equals("")){
					offer.setCategory(categoryDao.findById(new Integer(categoryID)));
				}
				
				User user = (User)request.getSession().getAttribute("user");
				if(user==null){
					RequestDispatcher disp = request.getRequestDispatcher("./Home");
					disp.forward(request, response);
					return;
				}
				if(user.getDTYPE().equals("Seller"))
					offer.setManager((Seller)user);
				
	
				 if(cont!=null && cont.equals("unesi")){
					 	offer.setActive(false);
						offer.setPurchasedOffers(0);
					try{ 
						offerDao.persist(offer);
						log.info("OFFER: "+ offer.getName()+"is added!");
					}catch (EJBException e) {
						messageOFF = "Offer name isn't unique. Try again!";
						request.setAttribute("messageOFF", messageOFF);
						RequestDispatcher disp = request.getRequestDispatcher("addOffer.jsp");
						disp.forward(request, response);
						return;
					}
				 }else if(cont!=null && cont.equals("edit")){
					
					 String idOffer =request.getParameter("idOffer");
					 Offer oldOFF = offerDao.findById(new Integer(idOffer));
					 
					 offer.setActive(oldOFF.isActive());
					 offer.setPurchasedOffers(oldOFF.getPurchasedOffers());
					 offer.setId(oldOFF.getId());
					 offerDao.merge(offer);
					 log.info("OFFER: "+ offer.getName()+"is edited!");
				 }
				RequestDispatcher disp = request.getRequestDispatcher("./Home?next=OFFER");
				disp.forward(request, response);
				return;
			}
		}  catch (IOException e) {
			log.error(e);
			try {
				RequestDispatcher disp = request.getRequestDispatcher("./Home?next=OFFER");
				disp.forward(request, response);
				return;
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}

}
