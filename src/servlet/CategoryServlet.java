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

import session.CategoryDaoLocal;
import entity.Category;

public class CategoryServlet extends HttpServlet{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -9146550601773022100L;
	public static String  messageCAT=null;
	private static Logger log = Logger.getLogger(CategoryServlet.class);

	@EJB
	private CategoryDaoLocal categoryDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cont = (String)request.getParameter("cont");
		if(cont!=null && cont.equals("ADD")){
			RequestDispatcher disp = request.getRequestDispatcher("addCategory.jsp");
			disp.forward(request, response);
			return;
		}else if(cont!=null && cont.equals("CH")){
			HttpSession session = request.getSession(true);
			String 	id = (String)request.getParameter("id");
			Category cat = null;
			
			try{
			Integer x = Integer.parseInt(id);
			cat = categoryDao.findById(x);
			} catch (EJBException e) {
				//POGRESNA LOZINKA
				if (e.getCause().getClass().equals(NoResultException.class)) {
					RequestDispatcher disp = request.getRequestDispatcher("./Home?next=CAT");
					disp.forward(request, response);
					return;
				} else {
					throw e;
				}
			}
				session.setAttribute("zaIzmenuCat", cat);
				RequestDispatcher disp = request.getRequestDispatcher("editCategory.jsp");
				disp.forward(request, response);
				return;
			
		}
		
		String naziv = (String)request.getParameter("naziv");
		String opis = (String)request.getParameter("opis");
		try {
			if(cont!=null && cont.equals("unesi")){
				try{
				Category cat = new Category(naziv,opis);			
				categoryDao.persist(cat);
				} catch (EJBException e) {
					messageCAT = "Category name already exist. Try again!";
					request.setAttribute("messageCAT", messageCAT);
					RequestDispatcher disp = request.getRequestDispatcher("addCategory.jsp");
					disp.forward(request, response);
					return;
			}
				log.info("CATEGORY: "+ naziv+" is added");	
				response.setContentType("text/html; charset=UTF-8"); 
				RequestDispatcher disp = request.getRequestDispatcher("./Home?next=CAT");
				disp.forward(request, response);
				return;
				
			}else if(cont!=null && cont.equals("edit")){
				String 	id = (String)request.getParameter("id");
				Category oldCat = null;
				try{
					Integer x = Integer.parseInt(id);
					oldCat = categoryDao.findById(x);
					} catch (EJBException e) {
						//POGRESNA LOZINKA
						if (e.getCause().getClass().equals(NoResultException.class)) {
							RequestDispatcher disp = request.getRequestDispatcher("editCategory.jsp");
							disp.forward(request, response);
							return;
						} else {
							throw e;
						}
					}			
				Category cat = new Category(naziv,opis);
				cat.setId(oldCat.getId());
				
				categoryDao.merge(cat);
				log.info("CATEGORY: "+ naziv+" is edited!");		
				
				RequestDispatcher disp = request.getRequestDispatcher("./Home?next=CAT");
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
