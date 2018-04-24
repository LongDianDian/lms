package cn.com.lms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.lms.dao.MediaDao;
import cn.com.lms.entity.Media;
import cn.com.lms.query.MediaQuery;
import cn.com.lms.service.MediaService;

@Service
public class MediaServiceImpl implements MediaService{
	
	@Resource
	private MediaDao dao;

	@Override
	public List<Media> listByQuery(MediaQuery mediaQuery) {
		return dao.listByQuery(mediaQuery);
	}

	@Override
	public void save(Media media) {
		dao.save(media);
	}

	@Override
	public List<Media> list() {
		return dao.list();
	}

	@Override
	public void deleteById(Integer mediaId) {
		dao.delete(mediaId);
	}

	@Override
	public List<Map<String, Object>> listByQueryForApi(MediaQuery mediaQuery) {
		List<Media> list =dao.listByQuery(mediaQuery);
		List<Map<String,Object> > mapList = new ArrayList<Map<String,Object>>();
		if(null!= list && list.size()>0){
			for(Media media : list){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("url",media.getUrl());
				map.put("id", media.getId());
				if(media.getType()==1){
					map.put("type", "img");
				}else if(media.getType()==2){
					map.put("type", "video");
				}
				mapList.add(map);
			}
		}
		return mapList;
	}

}
