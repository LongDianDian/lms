package cn.com.lms.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.com.lms.entity.Book;
import cn.com.lms.entity.Event;
import cn.com.lms.query.EventQuery;
import cn.com.lms.query.EventQueryForApi;
import cn.videoworks.commons.dao.hibernate.AdvancedHibernateDao;

@Repository
public class EventDao extends AdvancedHibernateDao<Event> {

	public Integer countByQury(EventQuery query) {
		Criteria criteria = this.getCurrentSession().createCriteria(Event.class);
		if(StringUtils.isNotBlank(query.getTitle())){
			criteria.add(Restrictions.like("title", "%"+query.getTitle()+"%"));
		}
		if(null!= query.getType()){
			criteria.add(Restrictions.eq("type", query.getType()));
		}
		criteria.addOrder(Order.desc("createTime"));
		criteria.setProjection(Projections.rowCount());
		Integer num = Integer.parseInt(criteria.uniqueResult().toString());
		return num;
	}

	public List<Event> listByQuery(EventQuery query) {
		Criteria criteria = this.getCurrentSession().createCriteria(Event.class);
		if(StringUtils.isNotBlank(query.getTitle())){
			criteria.add(Restrictions.like("title", "%"+query.getTitle()+"%"));
		}
		if(null!= query.getType()){
			criteria.add(Restrictions.eq("type", query.getType()));
		}
		criteria.addOrder(Order.desc("createTime"));
		criteria.setFirstResult(query.getStart());
		criteria.setMaxResults(query.getLength());
		return criteria.list();
	}

	public List<Event> findByEventQueryForApi(EventQueryForApi eventQueryForApi) {
		Criteria criteria = this.getCurrentSession().createCriteria(Event.class,"event").createAlias("event.school","school");
		if(StringUtils.isNotBlank(eventQueryForApi.getTitle())){
			criteria.add(Restrictions.like("event.title", "%"+eventQueryForApi.getTitle()+"%"));
		}
		if(null!= eventQueryForApi.getType()){
			criteria.add(Restrictions.eq("event.type", eventQueryForApi.getType()));
		}
		if(null!= eventQueryForApi.getSchoolId()){
			criteria.add(Restrictions.eq("school.id", eventQueryForApi.getSchoolId()));
		}
		criteria.addOrder(Order.desc("event.createTime"));
		criteria.setFirstResult(eventQueryForApi.getPageStartNum());
		criteria.setMaxResults(eventQueryForApi.getPageSize()+eventQueryForApi.getPageStartNum());
		return criteria.list();
	}

}
