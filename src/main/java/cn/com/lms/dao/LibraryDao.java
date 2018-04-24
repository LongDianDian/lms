package cn.com.lms.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.com.lms.entity.Event;
import cn.com.lms.entity.Library;
import cn.com.lms.query.LibraryQueryForApi;
import cn.videoworks.commons.dao.hibernate.AdvancedHibernateDao;

@Repository
public class LibraryDao extends AdvancedHibernateDao<Library>{
	
	

	public List<Library> findByLibraryQueyrApi(
			LibraryQueryForApi libraryQueryForApi) {
		Criteria criteria = this.getCurrentSession().createCriteria(Library.class);
		if(StringUtils.isNotBlank(libraryQueryForApi.getTitle())){
			criteria.add(Restrictions.like("title", "%"+libraryQueryForApi.getTitle()+"%"));
		}
		if(null!= libraryQueryForApi.getType()){
			criteria.add(Restrictions.eq("type", libraryQueryForApi.getType()));
		}
		if(null!= libraryQueryForApi.getKind()){
			criteria.add(Restrictions.eq("kind", libraryQueryForApi.getKind()));
		}
		if(null!= libraryQueryForApi.getSchoolId()){
			criteria.add(Restrictions.eq("id", libraryQueryForApi.getSchoolId()));
		}
		criteria.addOrder(Order.desc("createTime"));
		criteria.setFirstResult(libraryQueryForApi.getPageStartNum());
		criteria.setMaxResults(libraryQueryForApi.getPageSize()+libraryQueryForApi.getPageStartNum());
		return criteria.list();
	}

	public List<Library> listBykind(Integer kind) {
		Criteria criteria = this.getCurrentSession().createCriteria(Library.class);
		if(null!= kind){
			criteria.add(Restrictions.eq("kind", kind));
		}
		criteria.addOrder(Order.desc("createTime"));
		return criteria.list();
	}

}
