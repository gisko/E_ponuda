package session;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Admin;
import entity.Branch;
import entity.Buyer;
import entity.Category;
import entity.Comment;
import entity.Coupons;
import entity.Image;
import entity.Offer;
import entity.Payment;
import entity.Seller;

@Stateless
@Remote(Init.class)
public class InitBean implements Init {

	@PersistenceContext(unitName = "E_ponuda")
	EntityManager em;
	
	public void init() {
		
		Buyer buyer = new Buyer("Nikola", "Simic", "sima", "sima",
				"064/222-333", "Kralja Petra 1", "gisko.ace@gmail.com");
		em.persist(buyer);
		
		Buyer buyer1 = new Buyer("Gisko", "Ilic", "gisko", "gisko",
				"065/77-11-99","Cirpanova br.18", "gisko.ace@gmail.com");
		em.persist(buyer1);
		
		Admin admin = new Admin("Dragoljub", "Ilic", "admin", "admin");
		em.persist(admin);
		
				
		Seller seller = new Seller("Nenad", "Golubovic", "neca", "neca",
				"065/22-333-412", "srecni@ljudi.com");
		em.persist(seller);		
		
		Seller seller1 = new Seller("Pera", "Peric", "pera", "pera",
				"061/2389-233", "petar@petar.com");
		em.persist(seller1);
		
				
		Branch branch = new Branch("Big", "021/200-300", "Bulevar 13", seller);
		em.persist(branch);
		branch = new Branch("Kings", "011/320-990", "Kralja petra 13", seller1);
		em.persist(branch);
		

		Category cat1 = new Category("Patike", "Patike za trcanje, sportske.");
		em.persist(cat1);
		Category cat2 = new Category("Bioskop", "Karte za filmske premijere.");
		em.persist(cat2);
		Category cat3 = new Category("Putovanja", "Evropske prestonice.");
		em.persist(cat3);
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dateC1=null;
		Date dateE1=null;
		Date dateC2=null;
		Date dateE2=null;
		try {
			dateC1 = sdf.parse("23/02/2015");
			dateE1 = sdf.parse("13/03/2015");
			
			dateC2 = sdf.parse("23/02/2015");
			dateE2 = sdf.parse("01/03/2015");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Offer off1 = new Offer("Nike", dateC1, dateE1, dateC1,
				dateE1, 7400.0, 6100.0, 6, "Nike running, sa kramponima",
				2, true, seller, cat1,"nike.png");
		em.persist(off1);
		
		Offer off2 = new Offer("Adidas", dateC2, dateE2, dateC2,
				dateE2, 6100.0, 5999.0, 20, "Fensi patike, duboke.",
				0, true, seller1, cat1,null);
		em.persist(off2);
		
		Offer off3 = new Offer("Avatar 2", dateC2, dateE2, dateC2,
				dateE2, 620.0, 379.0, 9, "Nastavak kultnog filma.",
				1, true, seller, cat2,"avatar.jpg");
		em.persist(off3);
		
		Offer off4 = new Offer("Pariz", dateC2, dateE2, dateC2,
				dateE2, 46000.0, 39899.0, 4, "Put u pariz,3 dana, 2 osobe.",
				0, true, seller, cat3,"pariz1.jpg");
		em.persist(off4);
		
		
		
		Date validTo=null;
		Date validTo1=null;
		try {
			validTo = sdf.parse("22/04/2015");
			validTo1 = sdf.parse("22/02/2015");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Coupons coupon = new Coupons( "p1!f-?553vgkz4n-2k!y5n", true, validTo, false, off1, buyer);
		em.persist(coupon);
		
		coupon = new Coupons( "gu2v-otacfpumjk-ljznt6", false, validTo, true, off1, buyer);
		em.persist(coupon);
		
		coupon = new Coupons( "ka6q-0pgrh87kdz-tipxu6", false, validTo1, false, off3, buyer);
		em.persist(coupon);
		
		Comment comment= new Comment("Odlican film. Cista 10-ka!!", off3, buyer1);
		em.persist(comment);
		
		comment= new Comment("Nije lose, moze proci..", off3, buyer);
		em.persist(comment);
		
		comment= new Comment("Odlicne, jel ima crne?", off1, buyer);
		em.persist(comment);
		
		comment= new Comment("Kinezara cista, pukle posle 2 dana!", off1, buyer1);
		em.persist(comment);
		
		comment= new Comment("Super. Za te pare vredi ici. Bio sam jednom, fantazija...", off4, buyer);
		em.persist(comment);
		
		
		Payment payment = new Payment(7400.0, true, false, buyer, off1);
		em.persist(payment);
		
		payment = new Payment(7400.0, true, false, buyer, off1);
		em.persist(payment);
		
		payment = new Payment(379.0, true, false, buyer, off3);
		em.persist(payment);
		
		payment = new Payment(6100.0, false, false, buyer, off2);
		em.persist(payment);
		
				
		Image img1= new Image("nike.png", off1);
		em.persist(img1);
		
		img1 = new Image("n1.png", off1);
		em.persist(img1);
		
		img1 = new Image("n2.png", off1);
		em.persist(img1);
		
		Image img3 = new Image("avatar.jpg", off3);
		em.persist(img3);
		
		img3 = new Image("Avatar-Bean.jpg", off3);
		em.persist(img3);
		
		img3 = new Image("avatar-sam09-8-251.jpg", off3);
		em.persist(img3);
		
		img3 = new Image("Avatarjakeneytiri.jpg", off3);
		em.persist(img3);
		
		Image img4 = new Image("pariz.jpg", off4);
		em.persist(img4);
		
		img4 = new Image("pariz1.jpg", off4);
		em.persist(img4);

	}
}
