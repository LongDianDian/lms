package cn.com.lms.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.mongodb.core.geo.Circle;
import org.springframework.stereotype.Repository;

import cn.com.lms.entity.Book;
import cn.com.lms.entity.BookTypeMapping;
import cn.com.lms.query.BookMappingQuery;
import cn.com.lms.query.BookQueryForApi;
import cn.videoworks.commons.dao.hibernate.AdvancedHibernateDao;

@Repository
public class BookTypeMappingDao extends AdvancedHibernateDao<BookTypeMapping> {

	public Integer countByQury(BookMappingQuery query) {
		if(query!=null){
			Criteria criteria = this.getCurrentSession().createCriteria(BookTypeMapping.class,"mapping").createCriteria("mapping.book", "book");
			if(StringUtils.isNotBlank(query.getBookname())){
				criteria.add(Restrictions.like("book.bookname", "%"+query.getBookname()+"%"));
			}
			if(StringUtils.isNotBlank(query.getAuthor())){
				criteria.add(Restrictions.like("book.author", "%"+query.getAuthor()+"%"));
			}
			if(StringUtils.isNotBlank(query.getPress())){
				criteria.add(Restrictions.like("book.press", "%"+query.getPress()+"%"));
			}
			if(null!= query.getPublicationDate()){
				criteria.add(Restrictions.eq("book.publicationDate", query.getPublicationDate()));
			}
			criteria.add(Restrictions.eq("mapping.bookTypeId", query.getBookTypeId()));
			criteria.addOrder(Order.desc("mapping.createtime"));
			criteria.setProjection(Projections.rowCount());
			Integer num = Integer.parseInt(criteria.uniqueResult().toString());
			return num;
		}
		return null;
	}

	public List<BookTypeMapping> listByQuery(BookMappingQuery query) {
		if(query!=null){
			Criteria criteria = this.getCurrentSession().createCriteria(BookTypeMapping.class,"mapping").createCriteria("mapping.book", "book");
			if(StringUtils.isNotBlank(query.getBookname())){
				criteria.add(Restrictions.like("book.bookname", "%"+query.getBookname()+"%"));
			}
			if(StringUtils.isNotBlank(query.getAuthor())){
				criteria.add(Restrictions.like("book.author", "%"+query.getAuthor()+"%"));
			}
			if(StringUtils.isNotBlank(query.getPress())){
				criteria.add(Restrictions.like("book.press", "%"+query.getPress()+"%"));
			}
			if(null!= query.getPublicationDate()){
				criteria.add(Restrictions.eq("book.publicationDate", query.getPublicationDate()));
			}
			criteria.add(Restrictions.eq("mapping.bookTypeId", query.getBookTypeId()));
			criteria.setFirstResult(query.getStart());
			criteria.setMaxResults(query.getLength());
			criteria.addOrder(Order.asc("mapping.bookOrder"));
			criteria.addOrder(Order.desc("mapping.createtime"));
//			criteria.addOrder(Order.desc("mapping.createtime"));
			return criteria.list();
		}
		return null;
	}

	public List<Integer> getBookIdsByBookTypeId(Integer bookTypeId) {
		Criteria criteria = this.getCurrentSession().createCriteria(BookTypeMapping.class,"mapping").createCriteria("mapping.book", "book");
		criteria.add(Restrictions.eq("mapping.bookTypeId", bookTypeId));
		criteria.setProjection(Projections.property("book.id"));
		return criteria.list();
	}

	public List<BookTypeMapping> findByBookQueryApi(
			BookQueryForApi bookQueryForApi) {
		if(bookQueryForApi!=null){
			Criteria criteria = this.getCurrentSession().createCriteria(BookTypeMapping.class,"mapping").createCriteria("mapping.book", "book");
			if(StringUtils.isNotBlank(bookQueryForApi.getBookname())){
				criteria.add(Restrictions.like("book.bookname", "%"+bookQueryForApi.getBookname()+"%"));
			}
			if(null!= bookQueryForApi.getBookTypeId()){
				criteria.add(Restrictions.eq("mapping.bookTypeId", bookQueryForApi.getBookTypeId()));
			}
			if(null != bookQueryForApi.getShelveStatus()){
				criteria.add(Restrictions.eq("mapping.shelveStatus", bookQueryForApi.getShelveStatus()));
			}
			criteria.setFirstResult(bookQueryForApi.getPageStartNum());
			criteria.setMaxResults(bookQueryForApi.getPageSize());
			criteria.addOrder(Order.asc("mapping.bookOrder"));
			criteria.addOrder(Order.desc("mapping.createtime"));
			return criteria.list();
		}
		return null;
	}

}
