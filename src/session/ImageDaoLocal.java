package session;

import java.util.List;

import entity.Image;
import entity.Offer;

public interface ImageDaoLocal extends GenericDaoLocal<Image, Integer> {
	public List<Image> findImageForOffer(Offer  offID);
}
