package cn.com.lms.service;

import java.util.List;

import cn.com.lms.entity.BookType;

public interface BookTypeService {

	public List<BookType> list();

	public void update(List<BookType> listToUpdate);

	public BookType findById(Integer typeId);

	public void delete(Integer typeId);

	public void update(BookType bookType);

	public void save(BookType bookType);

}
