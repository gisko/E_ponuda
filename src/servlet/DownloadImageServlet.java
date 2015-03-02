package servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.log4j.Logger;

import session.ImageDaoLocal;
import session.OfferDaoLocal;
import entity.Image;
import entity.Offer;

public class DownloadImageServlet extends HttpServlet{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5429899701021061373L;
	private static Logger log = Logger.getLogger(DownloadImageServlet.class);

	@EJB
	private ImageDaoLocal imageDao;
	
	@EJB
	private OfferDaoLocal offerDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding(response.getCharacterEncoding());
		HttpSession session = request.getSession(true);
		String cont = (String)request.getParameter("cont");
		String id = (String)request.getParameter("id");
		if(cont!=null && cont.equals("ADD")){			
			Offer o =null;
			try{
				Integer x = Integer.parseInt(id);
				o = offerDao.findById(x);
				if(o!=null){		
					o.setId(x);
					
					session.setAttribute("images", imageDao.findImageForOffer(o));
					session.setAttribute("offerImage", o);
				}
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
			
			RequestDispatcher disp = request.getRequestDispatcher("addImages.jsp");
			disp.forward(request, response);
			return;
		}
		
		ServletRequestContext ctx = new ServletRequestContext(request);
		if (ServletFileUpload.isMultipartContent(ctx)) {

			try {
				// Create a factory for disk-based file items
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(2000000);
				// Create a new file upload handler
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(3000000);
				// Parse the request
				List<FileItem> items = upload.parseRequest(request);
				// Process the uploaded items
				String fileName=null;
				String offerID=null;
				for (FileItem item : items) {
					if (item.isFormField()) {
						offerID = item.getString(request.getCharacterEncoding());
					} else {
						fileName = item.getName();
						long sizeInBytes = item.getSize();
						int idx = fileName.lastIndexOf("\\"); // owser
						if (idx != -1)
							fileName = fileName.substring(idx + 1);
						if (sizeInBytes != 0) {
							
							File uploadedFile = new File(getServletContext().getRealPath("") + File.separator+"img"+File.separator + fileName);
							File uploadedFileSave = new File("C:\\Users\\Gisko\\Desktop\\ISA_workspace\\E_ponuda\\web\\img"+File.separator + fileName);						
							
							item.write(uploadedFile);
							item.write(uploadedFileSave);
						}
					}
				}
				
				if(fileName!=null && !fileName.equals("")){
					String idIMG = (String)request.getParameter("idOFF");
					Image image = new Image(fileName,offerDao.findById(new Integer(offerID)));
					imageDao.persist(image);
					log.info("IMAGE: "+fileName+" is added!");
					Integer x = Integer.parseInt(idIMG);
					session.setAttribute("images",  imageDao.findImageForOffer(offerDao.findById(new Integer(x))));
					
				}
				RequestDispatcher disp = request.getRequestDispatcher("addImages.jsp");
				disp.forward(request, response);
			
				return;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}

}
