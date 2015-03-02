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
import entity.Branch;
import entity.Seller;
import entity.User;

public class BranchServlet extends HttpServlet{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -9146550601773022100L;
	
	private static Logger log = Logger.getLogger(BranchServlet.class);
	
	@EJB
	private BranchDaoLocal branchDao;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cont = (String)request.getParameter("cont");	
		if(cont!=null && cont.equals("ADD")){
			RequestDispatcher disp = request.getRequestDispatcher("addBranch.jsp");
			disp.forward(request, response);
			return;
		}else if(cont!=null && cont.equals("CH")){
			HttpSession session = request.getSession(true);
			String 	id = (String)request.getParameter("id");
			Branch b = null;
			
			try{
			Integer x = Integer.parseInt(id);
			b = branchDao.findById(x);
			} catch (EJBException e) {
				//POGRESNA LOZINKA
				if (e.getCause().getClass().equals(NoResultException.class)) {
					RequestDispatcher disp = request.getRequestDispatcher("./Home?next=BR");
					disp.forward(request, response);
					return;
				} else {
					throw e;
				}
			}
				session.setAttribute("zaIzmenuBr", b);
				RequestDispatcher disp = request.getRequestDispatcher("editBranch.jsp");
				disp.forward(request, response);
				return;
			
		}
	
		try {
			if(cont!=null && cont.equals("unesi")){
				
				String nazivB = (String)request.getParameter("nazivB");
				String phoneB = (String)request.getParameter("phoneB");
				String addressB = (String)request.getParameter("addressB");
				User user = (User)request.getSession().getAttribute("user");
				if(user==null){
					RequestDispatcher disp = request.getRequestDispatcher("./Home");
					disp.forward(request, response);
					return;
				}
				
				
				Branch b = new Branch(nazivB,phoneB,addressB,(Seller)user);
				branchDao.persist(b);
				
				log.info("BRANCH: "+ nazivB+" is added by "+user.getUsername());		
				RequestDispatcher disp = request.getRequestDispatcher("./Home?next=BR");
				disp.forward(request, response);
				return;
			}else if(cont!=null && cont.equals("edit")){
				String 	id = (String)request.getParameter("id");
				String nazivB = (String)request.getParameter("nazivB");
				String phoneB = (String)request.getParameter("phoneB");
				String addressB = (String)request.getParameter("addressB");
				User user = (User)request.getSession().getAttribute("user");
				if(user==null){
					RequestDispatcher disp = request.getRequestDispatcher("./Home");
					disp.forward(request, response);
					return;
				}
				Branch oldBR = null;
				try{
					Integer x = Integer.parseInt(id);
					oldBR = branchDao.findById(x);
					} catch (EJBException e) {
						//POGRESNA LOZINKA
						if (e.getCause().getClass().equals(NoResultException.class)) {
							RequestDispatcher disp = request.getRequestDispatcher("editBranch.jsp");
							disp.forward(request, response);
							return;
						} else {
							throw e;
						}
					}			
				Branch b = new Branch(nazivB,phoneB,addressB,(Seller)user);
				b.setId(oldBR.getId());
				
				branchDao.merge(b);
				log.info("BRANCH: "+ nazivB+" is edited by "+user.getUsername());		
				
				RequestDispatcher disp = request.getRequestDispatcher("./Home?next=BR");
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
