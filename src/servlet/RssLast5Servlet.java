package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import session.OfferDaoLocal;
import entity.Offer;

public class RssLast5Servlet extends HttpServlet{
	

	//private static Logger log = Logger.getLogger(ShowServlet.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -8552683134862056264L;
	@EJB
	private OfferDaoLocal offerDao;	

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hostName= request.getServerName();
		int hostPort= request.getServerPort();
		String applicationPath = request.getContextPath();
		
		String linkStart= "http://"+hostName+":"+hostPort+applicationPath+"/";
		
		PrintWriter out= response.getWriter();
		String rssXML="";
		rssXML+="<?xml version=\"1.0\"  encoding=\"UTF-8\" ?>\n";
		rssXML+="<rss version=\"2.0\">\n";
		rssXML+="\n";
		rssXML+="<channel>\n";
		rssXML+="<title>E_ponuda</title>\n";
		rssXML+="<link>http://localhost:8080/E_ponuda/</link>\n";
		rssXML+="<description>Top 5 new offers from E_ponuda</description>\n";
		rssXML+="\n";
		if(offerDao.findActiveOffers().size()>4){
			List<Offer> offers = offerDao.findActiveOffers();
			int k=0;
			for(int i = offers.size()-1;i>=0;i--){
				rssXML+="<item>\n";
				rssXML+="<title>"+offers.get(i).getName()+"</title>\n";
				rssXML+="<link>"+linkStart+"ShowOfferServlet?offerID="+offers.get(i).getId()+"</link>\n";
				rssXML+="<description>"+offers.get(i).getDescription()+"</description>\n";
				rssXML+="</item>\n";
				rssXML+="\n";
				k++;
			if(k==5)break;
			}
		
		}else{				
		for(Offer offer: offerDao.findActiveOffers())
		{
			rssXML+="<item>\n";
			rssXML+="<title>"+offer.getName()+"</title>\n";
			rssXML+="<link>"+linkStart+"ShowOfferServlet?offerID="+offer.getId()+"</link>\n";
			rssXML+="<description>"+offer.getDescription()+"</description>\n";
			rssXML+="</item>\n";
			rssXML+="\n";
		}
		}
		rssXML+="</channel>\n";
		rssXML+="</rss>\n"; 
		
		response.setContentType("application/rss+xml");
		out.write(rssXML);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}

}
