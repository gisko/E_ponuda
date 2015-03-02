package session;

import java.util.List;

import entity.Comment;
import entity.Offer;

public interface CommentDaoLocal extends GenericDaoLocal<Comment, Integer> {
	public List<Comment> findCommentsForOffer(Offer  offerID);
}
