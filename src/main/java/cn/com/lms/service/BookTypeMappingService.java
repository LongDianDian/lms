package cn.com.lms.service;

import java.util.List;

import cn.com.lms.entity.Book;
import cn.com.lms.entity.BookTypeMapping;
import cn.com.lms.query.BookMappingQuery;
import cn.com.lms.query.BookQueryForApi;

public interface BookTypeMappingService {

	Integer countByQury(BookMappingQuery query);

	List<BookTypeMapping> listByQuery(BookMappingQuery query);

	List<Integer> getBookIdsByBookTypeId(Integer bookTypeId);

	void save(BookTypeMapping mapping);

	BookTypeMapping findById(int id);

	void update(BookTypeMapping bookTypeMapping);

	void update(List<BookTypeMapping> toUpdateList);

	void deleteById(int mappingId);

	List<BookTypeMapping> findByBookQueryApi(BookQueryForApi bookQueryForApi);

}
