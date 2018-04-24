package cn.com.lms.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.com.lms.entity.Book;
import cn.com.lms.query.BookMappingQuery;
import cn.com.lms.query.BookQuery;
import cn.videoworks.commons.dao.hibernate.AdvancedHibernateDao;
import cn.videoworks.commons.webdev.vo.Page;
@Repository
public class BookDao  extends AdvancedHibernateDao<Book>{

	public Integer countByQury(BookQuery query) {
		Criteria criteria = this.getCurrentSession().createCriteria(Book.class);
		if(StringUtils.isNotBlank(query.getBookname())){
			criteria.add(Restrictions.like("bookname", "%"+query.getBookname()+"%"));
		}
		if(StringUtils.isNotBlank(query.getAuthor())){
			criteria.add(Restrictions.like("author", "%"+query.getAuthor()+"%"));
		}
		if(StringUtils.isNotBlank(query.getPress())){
			criteria.add(Restrictions.like("press", "%"+query.getPress()+"%"));
		}
		if(null!= query.getPublicationDate()){
			criteria.add(Restrictions.eq("publicationDate", query.getPublicationDate()));
		}
		criteria.addOrder(Order.desc("createtime"));
		criteria.setProjection(Projections.rowCount());
		Integer num = Integer.parseInt(criteria.uniqueResult().toString());
		return num;
	}

	public List<Book> listByQuery(BookQuery query) {
		Criteria criteria = this.getCurrentSession().createCriteria(Book.class);
		if(StringUtils.isNotBlank(query.getBookname())){
			criteria.add(Restrictions.like("bookname", "%"+query.getBookname()+"%"));
		}
		if(StringUtils.isNotBlank(query.getAuthor())){
			criteria.add(Restrictions.like("author", "%"+query.getAuthor()+"%"));
		}
		if(StringUtils.isNotBlank(query.getPress())){
			criteria.add(Restrictions.like("press", "%"+query.getPress()+"%"));
		}
		if(null!= query.getPublicationDate()){
			criteria.add(Restrictions.eq("publicationDate", query.getPublicationDate()));
		}
		criteria.addOrder(Order.desc("createtime"));
		criteria.setFirstResult(query.getStart());
		criteria.setMaxResults(query.getLength());
		return criteria.list();
	}

	public Integer countForAddModel(List<Integer> bookIds,  BookMappingQuery query) {
		Criteria criteria = this.getCurrentSession().createCriteria(Book.class);
		if(null!= bookIds && bookIds.size()>0){
			criteria.add(Restrictions.not(Restrictions.in("id", bookIds)));
		}
		if(StringUtils.isNotBlank(query.getBookname())){
			criteria.add(Restrictions.like("bookname", "%"+query.getBookname()+"%"));
		}
		if(StringUtils.isNotBlank(query.getAuthor())){
			criteria.add(Restrictions.like("author", "%"+query.getAuthor()+"%"));
		}
		if(StringUtils.isNotBlank(query.getPress())){
			criteria.add(Restrictions.like("press", "%"+query.getPress()+"%"));
		}
		if(null!= query.getPublicationDate()){
			criteria.add(Restrictions.eq("publicationDate", query.getPublicationDate()));
		}
		criteria.addOrder(Order.desc("createtime"));
		criteria.setProjection(Projections.rowCount());
		Integer num = Integer.parseInt(criteria.uniqueResult().toString());
		return num;
	}

	public List<Book> listForAddModel(List<Integer> bookIds, BookMappingQuery query) {
		Criteria criteria = this.getCurrentSession().createCriteria(Book.class);
		if(null!= bookIds && bookIds.size()>0){
			criteria.add(Restrictions.not(Restrictions.in("id", bookIds)));
		}
		if(StringUtils.isNotBlank(query.getBookname())){
			criteria.add(Restrictions.like("bookname", "%"+query.getBookname()+"%"));
		}
		if(StringUtils.isNotBlank(query.getAuthor())){
			criteria.add(Restrictions.like("author", "%"+query.getAuthor()+"%"));
		}
		if(StringUtils.isNotBlank(query.getPress())){
			criteria.add(Restrictions.like("press", "%"+query.getPress()+"%"));
		}
		if(null!= query.getPublicationDate()){
			criteria.add(Restrictions.eq("publicationDate", query.getPublicationDate()));
		}
		criteria.addOrder(Order.desc("createtime"));
		criteria.setFirstResult(query.getStart());
		criteria.setMaxResults(query.getStart()+query.getLength());
		return criteria.list();
	}

}
