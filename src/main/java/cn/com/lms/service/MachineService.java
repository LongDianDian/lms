package cn.com.lms.service;

import java.util.List;

import cn.com.lms.entity.Machine;

public interface MachineService {

	void save(Machine machine);

	Machine getMac(String macId);

	List<Machine> list();

	Machine fingById(Integer id);

	void update(Machine machine);

}
