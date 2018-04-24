package cn.com.lms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.lms.dao.BookTypeDao;
import cn.com.lms.entity.BookType;
import cn.com.lms.service.BookTypeService;

@Service
public class BookTypeIServiceImpl implements BookTypeService{
	@Resource
	private BookTypeDao bookTypeDao;

	@Override
	public List<BookType> list() {
		return bookTypeDao.listByOrder();
	}

	@Override
	public void update(List<BookType> listToUpdate) {
		bookTypeDao.update(listToUpdate);
	}

	@Override
	public BookType findById(Integer typeId) {
		return bookTypeDao.get(typeId);
	}

	@Override
	public void delete(Integer typeId) {
		bookTypeDao.delete(typeId);
	}

	@Override
	public void update(BookType bookType) {
		bookTypeDao.update(bookType);
	}

	@Override
	public void save(BookType bookType) {
		bookTypeDao.save(bookType);
	}
	
	
}
