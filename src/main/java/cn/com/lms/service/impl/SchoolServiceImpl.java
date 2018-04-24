package cn.com.lms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.lms.dao.SchoolDao;
import cn.com.lms.entity.School;
import cn.com.lms.service.SchoolService;

@Service
public class SchoolServiceImpl implements SchoolService{
	
	@Resource
	private SchoolDao schoolDao;

	@Override
	public List<School> list() {
		return schoolDao.list();
	}

	@Override
	public void save(School school) {
		schoolDao.save(school);
	}

	@Override
	public void deleteById(int schoolId) {
		School school = schoolDao.get(schoolId);
		if(null!= school){
			school.setIsDelete(true);
		}
		schoolDao.update(school);
	}

	@Override
	public List<School> listWithOutDel() {
		return schoolDao.listWithOutDel();
	}

	@Override
	public School findById(int schoolId) {
		return schoolDao.get(schoolId);
	}

	@Override
	public void update(School school) {
		schoolDao.update(school);
	}

}
