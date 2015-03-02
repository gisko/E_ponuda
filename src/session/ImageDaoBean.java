package session;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import entity.Image;
import entity.Offer;
@Stateless
@Local(ImageDaoLocal.class)
public class ImageDaoBean extends GenericDaoBean<Image, Integer> implements
		ImageDaoLocal {

	
	@Override
	public List<Image> findImageForOffer(Offer offID) {
		Query q = em.createNamedQuery("findImageForOffer");
		q.setParameter("offID", (offID));
		@SuppressWarnings("unchecked")
		List<Image> images = (List<Image>) q.getResultList();
		return images;
	}
	

}
