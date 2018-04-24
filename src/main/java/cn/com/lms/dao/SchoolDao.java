package cn.com.lms.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.com.lms.entity.School;
import cn.videoworks.commons.dao.hibernate.AdvancedHibernateDao;

@Repository
public class SchoolDao extends AdvancedHibernateDao<School>{

	public List<School> listWithOutDel() {
		Criteria criteria = this.getCurrentSession().createCriteria(School.class);
		criteria.add(Restrictions.eq("isDelete", false));
		return criteria.list();
	}

}
