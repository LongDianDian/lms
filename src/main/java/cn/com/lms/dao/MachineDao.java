package cn.com.lms.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.com.lms.entity.Machine;
import cn.videoworks.commons.dao.hibernate.AdvancedHibernateDao;

@Repository
public class MachineDao  extends AdvancedHibernateDao<Machine>{

	public Machine getMac(String macId) {
		Criteria criteria = this.getCurrentSession().createCriteria(Machine.class);
		criteria.add(Restrictions.eq("mac", macId));
		return (Machine) criteria.uniqueResult();
	}

}
