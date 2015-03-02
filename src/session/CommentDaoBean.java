package session;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import entity.Comment;
import entity.Offer;
@Stateless
@Local(CommentDaoLocal.class)
public class CommentDaoBean extends GenericDaoBean<Comment, Integer> implements
		CommentDaoLocal {
	@Override
	public List<Comment> findCommentsForOffer(Offer offerID) {
		Query q = em.createNamedQuery("findCommentsForOffer");
		q.setParameter("offerID", (offerID));
		@SuppressWarnings("unchecked")
		List<Comment> comment = (List<Comment>) q.getResultList();
		return comment;
	}

}
