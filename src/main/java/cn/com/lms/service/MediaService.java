package cn.com.lms.service;

import java.util.List;
import java.util.Map;

import cn.com.lms.entity.Media;
import cn.com.lms.query.MediaQuery;

public interface MediaService {

	List<Media> listByQuery(MediaQuery mediaQuery);

	void save(Media media);

	List<Media> list();

	void deleteById(Integer mediaId);

	List<Map<String, Object>> listByQueryForApi(MediaQuery mediaQuery);

}
