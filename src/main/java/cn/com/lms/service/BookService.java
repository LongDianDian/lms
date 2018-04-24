package cn.com.lms.service;

import java.util.List;

import cn.com.lms.entity.Book;
import cn.com.lms.query.BookMappingQuery;
import cn.com.lms.query.BookQuery;
import cn.videoworks.commons.webdev.vo.Page;

public interface BookService {

	Integer  countByQury(BookQuery query);

	List<Book> listByQuery(BookQuery query);

	Integer countForAddModel(List<Integer> bookIds, BookMappingQuery query);

	List<Book> listForAddModel(List<Integer> bookIds,BookMappingQuery query);

	Book getById(Integer bookId);

}
