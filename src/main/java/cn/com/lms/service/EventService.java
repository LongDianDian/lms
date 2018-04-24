package cn.com.lms.service;

import java.util.List;
import java.util.Map;

import cn.com.lms.entity.Book;
import cn.com.lms.entity.Event;
import cn.com.lms.query.EventQuery;
import cn.com.lms.query.EventQueryForApi;
import cn.com.lms.vo.EventVo;

public interface EventService {
	
	public Event saveEventByEventVo(EventVo eventVo);

	public Integer countByQury(EventQuery query);

	public List<Event> listByQuery(EventQuery query);

	public Event findById(Integer eventId);

	public void deleteById(int id);

	public List<Map<String,Object>>findByEventQueryApi(EventQueryForApi eventQueryForApi);

}
