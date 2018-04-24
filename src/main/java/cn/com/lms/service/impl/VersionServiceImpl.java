package cn.com.lms.service.impl;

import java.util.List;

import javax.annotation.Resource;




import org.springframework.stereotype.Service;

import cn.com.lms.dao.VersionDao;
import cn.com.lms.entity.Version;
import cn.com.lms.service.VersionService;

@Service
public class VersionServiceImpl implements VersionService{
	
	@Resource
	private VersionDao dao;
	@Override
	public Version getVersion() {
		List<Version> list = dao.getVersion();
		if(null!=list && list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
