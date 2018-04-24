package cn.com.lms.service;

import java.util.List;
import java.util.Map;

import cn.com.lms.entity.Library;
import cn.com.lms.query.LibraryQueryForApi;

public interface LibraryService {

	public List<Library> list();

	public Library findById(Integer id);

	public void update(Library lib);

	public void save(Library lib);

	public void deleteById(Integer libraryId);

	public List<Map<String, Object>> findByLibraryQueryApi(
			LibraryQueryForApi libraryQueryForApi);

	public List<Library> listByKind(int kind);

}
