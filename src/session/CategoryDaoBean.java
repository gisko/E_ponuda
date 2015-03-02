package session;

import javax.ejb.Local;
import javax.ejb.Stateless;

import entity.Category;
@Stateless
@Local(CategoryDaoLocal.class)
public class CategoryDaoBean extends GenericDaoBean<Category, Integer> implements
		CategoryDaoLocal {

	

}
