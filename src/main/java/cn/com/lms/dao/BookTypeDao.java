package cn.com.lms.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import cn.com.lms.entity.BookType;
import cn.videoworks.commons.dao.hibernate.AdvancedHibernateDao;

@Repository
public class BookTypeDao extends AdvancedHibernateDao<BookType>{

	public List<BookType> listByOrder() {
		Criteria criteria = this.getCurrentSession().createCriteria(BookType.class);
		criteria.addOrder(Order.asc("order"));
		return criteria.list();
	}

}
