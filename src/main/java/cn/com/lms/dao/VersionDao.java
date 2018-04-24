package cn.com.lms.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.com.lms.entity.Version;
import cn.videoworks.commons.dao.hibernate.AdvancedHibernateDao;

@Repository
public class VersionDao  extends AdvancedHibernateDao<Version>{

	public List<Version> getVersion() {
		Criteria criteria = (Criteria) this.getCurrentSession().createCriteria(Version.class);
		criteria.add(Restrictions.eq("disabled",false));
		return criteria.list();
	}
	
}
