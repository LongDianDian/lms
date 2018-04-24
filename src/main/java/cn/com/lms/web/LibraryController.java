package cn.com.lms.web;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctc.wstx.util.StringUtil;

import cn.com.lms.entity.Library;
import cn.com.lms.entity.Media;
import cn.com.lms.query.LibraryQueryForApi;
import cn.com.lms.query.MediaQuery;
import cn.com.lms.service.LibraryService;
import cn.com.lms.service.MediaService;
import cn.videoworks.commons.webdev.constant.ResponseStatusCode;
import cn.videoworks.commons.webdev.http.RestResponse;

@Controller
@RequestMapping("lms/library")
public class LibraryController {
	
	@Resource
	private LibraryService libraryService;
	
	@Resource
	private MediaService mediaService;
	
	@RequestMapping("list/{kind}")
	public String libraryList(Model model,@PathVariable int kind ){
		List<Library> list = libraryService.list();
//		model.addAttribute("list", libraryService.list());
		LibraryQueryForApi libraryQueryForApi = new LibraryQueryForApi();
		libraryQueryForApi.setKind(kind);
		model.addAttribute("list",libraryService.listByKind(kind));
		model.addAttribute("kind", kind);
		return "site.lms.library.list";
	}
	
	
	@RequestMapping("addOrUpdate")
	@ResponseBody
	public RestResponse addOrUpdate(String title, Integer id,Integer kind ){
		RestResponse response = new RestResponse();
		if(StringUtils.isEmpty(title)){
			response.setMessage("操作异常！");
			response.setStatusCode(ResponseStatusCode.BAD_REQUEST);
			return response;
		}
		if(null!=id){
			Library lib = libraryService.findById(id);
			lib.setTitle(title);
			libraryService.update(lib);
		}else{
			Library lib = new Library();
			lib.setCreateTime(new Date());
			lib.setTitle(title);
			lib.setType(1);
			lib.setKind(kind);
			libraryService.save(lib);
		}
		response.setStatusCode(ResponseStatusCode.OK);
		return response;
	}
	
	@RequestMapping("delete/{libraryId}")
	@ResponseBody
	public RestResponse delete(@PathVariable Integer libraryId){
		RestResponse response = new RestResponse() ;
		libraryService.deleteById(libraryId);
		response.setStatusCode(ResponseStatusCode.OK);
		return response; 
	}
	@RequestMapping("detail/{libraryId}")
	public String detail(Model model, @PathVariable Integer libraryId,Integer start){
		MediaQuery mediaQuery = new MediaQuery();
		mediaQuery.setLibraryId(libraryId);
		mediaQuery.setPageSize(10000);
		if(null == start) start =0 ;
		mediaQuery.setPageStartNum(start);
		List<Media> medias = mediaService.listByQuery(mediaQuery);
		model.addAttribute("medias", medias);
		model.addAttribute("libraryId", libraryId);
		return "site.lms.library.detail";
	}
	
	
	
}
