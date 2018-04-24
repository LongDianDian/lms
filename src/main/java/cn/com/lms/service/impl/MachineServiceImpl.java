package cn.com.lms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.lms.dao.MachineDao;
import cn.com.lms.entity.Machine;
import cn.com.lms.service.MachineService;

@Service
public class MachineServiceImpl  implements  MachineService{
	
	@Resource
	private MachineDao dao;

	@Override
	public void save(Machine machine) {
		dao.save(machine);
	}

	@Override
	public Machine getMac(String macId) {
		return dao.getMac(macId);
	}

	@Override
	public List<Machine> list() {
		return dao.list();
	}

	@Override
	public Machine fingById(Integer id) {
		return dao.get(id);
	}

	@Override
	public void update(Machine machine) {
		dao.update(machine);
	}

}
