package servlet;

import java.io.IOException;
import java.text.DateFormat;
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

import org.apache.log4j.Logger;

import session.CouponsDaoLocal;
import entity.Coupons;

public class CouponsServlet extends HttpServlet{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -9146550601773022100L;
	public static String  messageCOUP=null;
	private static Logger log = Logger.getLogger(CouponsServlet.class);
	
	@EJB
	private CouponsDaoLocal couponsDao;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actCH = (String)request.getParameter("actCH");
		String usdCH = (String)request.getParameter("usdCH");
		String id = (String)request.getParameter("activna");
		String id2 = (String)request.getParameter("used");
		Integer idINT = null;
		try {
			
			if(actCH!=null && actCH.equals("true")){
			Coupons coup =null;
			try{
				Integer x = Integer.parseInt(id);
				idINT = x;
				coup = couponsDao.findById(x);
				} catch (EJBException e) {
					//POGRESNA LOZINKA
					if (e.getCause().getClass().equals(NoResultException.class)) {
						RequestDispatcher disp = request.getRequestDispatcher("./Home?next=COUP");
						disp.forward(request, response);
						return;
					} else {
						throw e;
					}
				}
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date trenutnoVreme = new Date();			
			Date ponudaIstice = coup.getValidTo();	
			
			
			if(coup.isActive()){
				coup.setActive(false);
				
			}
			else {
				coup.setActive(true);
				
			}
			
			if(trenutnoVreme.after(ponudaIstice)){
				coup.setActive(false);
				coup.setUsed(false);
				messageCOUP = "This coupon isn't valid anymore. Deadline was at "+ dateFormat.format(ponudaIstice) +".";
				request.setAttribute("messageCOUP", messageCOUP);		
			}
					
			coup.setId(idINT);
			couponsDao.merge(coup);
			log.info("COUPON: "+coup.getCouponIdentifies() +" is ACTIVE: "+coup.isActive()+" | USED: "+coup.isUsed());	
			
			RequestDispatcher disp = request.getRequestDispatcher("./Home?next=COUP");
			disp.forward(request, response);
			return;		
			}else if(usdCH!=null && usdCH.equals("true")){
				Coupons coup =null;
				try{
					Integer x = Integer.parseInt(id2);
					idINT = x;
					coup = couponsDao.findById(x);
					} catch (EJBException e) {
						//POGRESNA LOZINKA
						if (e.getCause().getClass().equals(NoResultException.class)) {
							RequestDispatcher disp = request.getRequestDispatcher("./Home?next=COUP");
							disp.forward(request, response);
							return;
						} else {
							throw e;
						}
					}
				coup.setUsed(true);
				coup.setActive(false);
				
				coup.setId(idINT);
				
				couponsDao.merge(coup);
				log.info("COUPON: "+coup.getCouponIdentifies() +" is ACTIVE: "+coup.isActive()+" | USED: "+coup.isUsed());	
				
				RequestDispatcher disp = request.getRequestDispatcher("./Home?next=COUP");
				disp.forward(request, response);
				return;		
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
