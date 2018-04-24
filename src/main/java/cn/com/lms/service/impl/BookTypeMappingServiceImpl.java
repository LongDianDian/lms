package cn.com.lms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.lms.dao.BookTypeMappingDao;
import cn.com.lms.entity.BookTypeMapping;
import cn.com.lms.query.BookMappingQuery;
import cn.com.lms.query.BookQueryForApi;
import cn.com.lms.service.BookTypeMappingService;

@Service
public class BookTypeMappingServiceImpl implements BookTypeMappingService{
	
	@Resource
	private BookTypeMappingDao dao;

	@Override
	public Integer countByQury(BookMappingQuery query) {
		return dao.countByQury(query);
	}

	@Override
	public List<BookTypeMapping> listByQuery(BookMappingQuery query) {
		return dao.listByQuery(query);
	}

	@Override
	public List<Integer> getBookIdsByBookTypeId(Integer bookTypeId) {
		return dao.getBookIdsByBookTypeId(bookTypeId);
	}

	@Override
	public void save(BookTypeMapping mapping) {
		dao.save(mapping);
	}

	@Override
	public BookTypeMapping findById(int id) {
		return dao.get(id);
	}

	@Override
	public void update(BookTypeMapping bookTypeMapping) {
		dao.update(bookTypeMapping);
	}

	@Override
	public void update(List<BookTypeMapping> toUpdateList) {
		dao.update(toUpdateList);
	}

	@Override
	public void deleteById(int mappingId) {
		dao.delete(mappingId);
	}

	@Override
	public List<BookTypeMapping> findByBookQueryApi(
			BookQueryForApi bookQueryForApi) {
		return dao.findByBookQueryApi(bookQueryForApi);
	}

}
