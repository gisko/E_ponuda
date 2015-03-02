package servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import session.CouponsDaoLocal;
import session.OfferDaoLocal;
import session.UserDaoLocal;
import entity.Coupons;
import entity.Offer;

public class UpdateOffersServlet extends HttpServlet {
	static int _FIRST=0;
	/**
	 * 
	 */
	private static final long serialVersionUID = -6006685922650199318L;

	private static Logger log = Logger.getLogger(UpdateOffersServlet.class);
	@EJB
	private UserDaoLocal userDao;

	@EJB
	private OfferDaoLocal offerDao;
	@EJB
	private CouponsDaoLocal couponDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final HttpSession session = request.getSession(true);
		ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
		  @Override
		  public void run() {
		  System.out.println("Active check!");
		  log.info("UPDATE CHECK: Check for change date in active offers at "+ new Date());
		  Date trenutnoVreme = new Date();
		  List<Offer> lista = offerDao.findActiveOffers();
		  for(int i=0;i<lista.size();i++){
			  if(trenutnoVreme.after(lista.get(i).getExpirationDate())){
				  Offer o = offerDao.findById(lista.get(i).getId());				 
				  o.setActive(false);
				  o.setId(lista.get(i).getId());
				  offerDao.merge(o);
				  session.setAttribute("offers", offerDao.findActiveOffers());
			  
			  }
		  }
		  List<Coupons> coupons = couponDao.findAll();
		  for(int i=0;i<coupons.size();i++){
			  if(trenutnoVreme.after(lista.get(i).getExpirationDate())){
				  Coupons coup = couponDao.findById(coupons.get(i).getId());				 
				  coup.setActive(false);
				  coup.setId(coupons.get(i).getId());
				  couponDao.merge(coup);
				  session.setAttribute("coupons", couponDao.findAll());			  
			  }
		  }
		  
		  
		  }
		 
		}, 0, 1, TimeUnit.HOURS);
	
		
//		Timer timer = new Timer();
//	    Calendar date = Calendar.getInstance();
//	  //  date.set(
//	   //   Calendar.DAY_OF_WEEK,
//	   //   Calendar.SUNDAY
//	   // );
//	    date.set(Calendar.HOUR, 0);
//	    date.set(Calendar.MINUTE, 5);
//	    date.set(Calendar.SECOND, 0);
//	    date.set(Calendar.MILLISECOND, 0);
//	    // Schedule to run every Sunday in midnight
//	    timer.schedule(new TimerTask() {
//			
//			  public void run() {
//	    		    System.out.println("Active check!");
//	    		    //TODO generate report
//	    		  }
//	      
//	    },date.getTime(),1000 * 60 * 60 * 24
//	    );

		RequestDispatcher disp = request.getRequestDispatcher("./Home");
		disp.forward(request, response);
		return;
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}

}
