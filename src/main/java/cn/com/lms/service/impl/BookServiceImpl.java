package cn.com.lms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.lms.dao.BookDao;
import cn.com.lms.entity.Book;
import cn.com.lms.query.BookMappingQuery;
import cn.com.lms.query.BookQuery;
import cn.com.lms.service.BookService;
import cn.videoworks.commons.webdev.vo.Page;

@Service
public class BookServiceImpl implements BookService{

	@Resource
	private BookDao bookDao;

	/**
	 * 获得page
	 */
	@Override
	public Integer countByQury(BookQuery query) {
		return bookDao.countByQury(query);
	}

	/**
	 * 获得列表
	 */
	@Override
	public List<Book> listByQuery(BookQuery query) {
		
		return bookDao.listByQuery(query);
	}

	@Override
	public Integer countForAddModel(List<Integer> bookIds, BookMappingQuery query) {
		return bookDao.countForAddModel(bookIds,query);
	}

	@Override
	public List<Book> listForAddModel(List<Integer> bookIds, BookMappingQuery query) {
		// TODO Auto-generated method stub
		return bookDao.listForAddModel(bookIds,query);
	}

	@Override
	public Book getById(Integer bookId) {
		return bookDao.get(bookId);
	}
}
