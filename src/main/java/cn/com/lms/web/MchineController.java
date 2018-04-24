package cn.com.lms.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.lms.dto.RestResponse;
import cn.com.lms.entity.Machine;
import cn.com.lms.entity.School;
import cn.com.lms.service.MachineService;
import cn.com.lms.service.SchoolService;
import cn.videoworks.commons.webdev.constant.ResponseStatusCode;

@Controller
@RequestMapping(value="lms/machine")
public class MchineController {
	
	@Resource
	private MachineService machineService;
	@Resource
	private SchoolService schoolService;
	
	@RequestMapping("list")
	public String list(Model model ){
		List<Machine> list = machineService.list();
		model.addAttribute("list", list);
		model.addAttribute("schoolList", schoolService.list());
		return "site.lms.machine.list";
	}
	
	@ResponseBody
	@RequestMapping("updateStatus")
	public RestResponse updateStatus(Integer id ){
		RestResponse response = new RestResponse();
		if(null!= id){
			Machine machine = machineService.fingById(id);
			if(null!=machine){
				machine.setDisabled(machine.getDisabled()==true?false:true);
				machineService.update(machine);
				response.setMessage("success");
				response.setStatusCode(ResponseStatusCode.OK);
				return response;
			}
		}
		response.setMessage("error");
		response.setStatusCode(ResponseStatusCode.BAD_REQUEST);
		return response;
	}
	
	@ResponseBody
	@RequestMapping("update")
	public RestResponse updateStatus(@RequestBody Map<String,Object> map){
		RestResponse response = new RestResponse();
		Integer id = Integer.parseInt((String)map.get("macId"));
		if(null!= id){
			Machine machine = machineService.fingById(id);
			if(null!=machine){
				String macNo = (String) map.get("macNo");
				Integer schoolId = Integer.parseInt((String)map.get("schoolId"));
				School school = schoolService.findById(schoolId);
				machine.setMacNo(macNo);
				machine.setSchool(school);
				machineService.update(machine);
				response.setMessage("success");
				response.setStatusCode(ResponseStatusCode.OK);
				return response;
			}
		}
		response.setMessage("error");
		response.setStatusCode(ResponseStatusCode.BAD_REQUEST);
		return response;
	}
}
