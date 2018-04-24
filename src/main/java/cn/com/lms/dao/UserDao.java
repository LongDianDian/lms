package cn.com.lms.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.com.lms.entity.User;
import cn.videoworks.commons.dao.hibernate.AdvancedHibernateDao;

@Repository
public class UserDao extends AdvancedHibernateDao<User>{

	public List<User> login(String username, String password) {
		Criteria criteria = this.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		criteria.add(Restrictions.eq("password", password));
		return criteria.list();
	}

	public List<User> getByUserName(String username) {
		Criteria criteria = this.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username",username));
		return criteria.list();
	}

}
