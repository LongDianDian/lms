package cn.com.lms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.lms.dao.EventDao;
import cn.com.lms.entity.Book;
import cn.com.lms.entity.Event;
import cn.com.lms.entity.Media;
import cn.com.lms.entity.School;
import cn.com.lms.query.EventQuery;
import cn.com.lms.query.EventQueryForApi;
import cn.com.lms.service.EventService;
import cn.com.lms.service.SchoolService;
import cn.com.lms.vo.EventVo;

@Service
public class EventServiceImpl implements EventService{
	
	@Resource
	private EventDao dao;
	
	@Resource
	private SchoolService schoolService;

	@Override
	public Event saveEventByEventVo(EventVo eventVo) {
		Event event = new Event();

		event.setTitle(eventVo.getTitle());
		event.setContent(eventVo.getContent());
		event.setOutline(eventVo.getOutline());
		event.setCreateTime(new Date());
		event.setType(eventVo.getType());
		event.setSchool(schoolService.findById(eventVo.getSchoolId()));
		List<Media> medias = new ArrayList<Media>();
		if(null!= eventVo.getImgs() && eventVo.getImgs().size()>0){
			Date time = new Date();
			for(String img : eventVo.getImgs()){
				Media media = new Media();
				media.setCreateTime(time);
				media.setKind(1);
				media.setType(1);
				medias.add(media);
				media.setUrl(img);
			}
		}
		event.setMedias(medias);
		if(null!=eventVo.getId()){
			event.setId(eventVo.getId());
			dao.update(event);
		}else{
			dao.save(event);
		}
		return event;
	}

	@Override
	public Integer countByQury(EventQuery query) {
		return dao.countByQury(query);
	}

	@Override
	public List<Event> listByQuery(EventQuery query) {
		return dao.listByQuery(query);
	}

	@Override
	public Event findById(Integer eventId) {
		return dao.get(eventId);
	}

	@Override
	public void deleteById(int id) {
		dao.delete(id);
	}

	@Override
	public List<Map<String,Object>> findByEventQueryApi(EventQueryForApi eventQueryForApi) {
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		List<Event> list = dao.findByEventQueryForApi(eventQueryForApi);
		if(null!= list  && list.size()>0){
			for(Event mapping: list){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", mapping.getId());
				map.put("title", mapping.getTitle());
				map.put("type", mapping.getType());
//				map.put("content", mapping.getContent());
				map.put("outline", mapping.getOutline());
				map.put("createtime", mapping.getCreateTime());
				List<Map<String,String>> mediasList = new ArrayList<Map<String,String>>();
				if(null!=mapping.getMedias() && mapping.getMedias().size()>0){
					for(Media media : mapping.getMedias()){
						Map<String,String> mediamap = new HashMap<String,String>();
						mediamap.put("url", media.getUrl());
						if(media.getType()==1){
							mediamap.put("type", "img");
						}else if (media.getType()==2){
							mediamap.put("type", "video");
						}
						mediasList.add(mediamap);
					}
				}
				map.put("medias", mediasList);
				mapList.add(map);
			}
		}
		return mapList;
	}


}
