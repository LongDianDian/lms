package cn.com.lms.service;

import java.util.List;

import cn.com.lms.entity.School;

public interface SchoolService {

	List<School> list();

	void save(School school);

	void deleteById(int schoolId);

	List<School> listWithOutDel();

	School findById(int schoolId);

	void update(School school);

}
