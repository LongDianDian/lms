package cn.com.lms.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.com.lms.entity.Event;
import cn.com.lms.entity.Media;
import cn.com.lms.query.MediaQuery;
import cn.videoworks.commons.dao.hibernate.AdvancedHibernateDao;

@Repository
public class MediaDao extends AdvancedHibernateDao<Media>{

	public List<Media> listByQuery(MediaQuery mediaQuery) {
		Criteria criteria = this.getCurrentSession().createCriteria(Media.class);
		if(null!= mediaQuery.getType()){
			criteria.add(Restrictions.eq("type", mediaQuery.getType()));
		}
		if(null!= mediaQuery.getLibraryId()){
			criteria.add(Restrictions.eq("libraryId", mediaQuery.getLibraryId()));
		}
		criteria.addOrder(Order.desc("createTime"));
		criteria.setFirstResult(mediaQuery.getPageStartNum());
		criteria.setMaxResults(mediaQuery.getPageSize()+mediaQuery.getPageStartNum());
		return criteria.list();
	}

}
