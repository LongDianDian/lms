package cn.com.lms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.lms.dao.LibraryDao;
import cn.com.lms.entity.Library;
import cn.com.lms.query.LibraryQueryForApi;
import cn.com.lms.service.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService{
	
	@Resource
	private LibraryDao dao;

	@Override
	public List<Library> list() {
		return dao.list();
	}

	@Override
	public Library findById(Integer id) {
		return dao.get(id);
	}

	@Override
	public void update(Library lib) {
		dao.update(lib);
	}

	@Override
	public void save(Library lib) {
		dao.save(lib);
	}

	@Override
	public void deleteById(Integer libraryId) {
		dao.delete(libraryId);
	}

	@Override
	public List<Map<String, Object>> findByLibraryQueryApi(
			LibraryQueryForApi libraryQueryForApi) {
		List<Library> list = dao.findByLibraryQueyrApi(libraryQueryForApi);
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		if(null!=list && list.size()>0){
			for(Library library: list){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("title", library.getTitle());
				map.put("id", library.getId());
				map.put("createTime",library.getCreateTime());
				map.put("type", library.getType());
				map.put("kind", libraryQueryForApi.getKind());
				mapList.add(map);
			}
		}
		return mapList;
	}

	@Override
	public List<Library> listByKind(int kind) {
		// TODO Auto-generated method stub
		return dao.listBykind(kind);
	}

}
