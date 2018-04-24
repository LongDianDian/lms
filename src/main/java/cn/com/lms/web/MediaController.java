package cn.com.lms.web;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.geronimo.mail.util.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.lms.entity.Media;
import cn.com.lms.entity.School;
import cn.com.lms.query.MediaQuery;
import cn.com.lms.service.MediaService;
import cn.videoworks.commons.webdev.constant.ResponseStatusCode;
import cn.videoworks.commons.webdev.http.RestResponse;

@Controller
@RequestMapping("lms/media")
public class MediaController {
	
	@Resource
	private MediaService mediaService;
	
	@Resource
	private Properties lmsConfig;

	@RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody
	public RestResponse doAdd(@RequestBody Media media){
		RestResponse response = new RestResponse();
		if(media!=null){
			if(StringUtils.isNotEmpty(media.getUrl())){
				String headPostUrl = uploadPoster(media.getUrl());
				if(StringUtils.isEmpty(headPostUrl)){
					response.setMessage("上传文件异常！");
					response.setStatusCode(ResponseStatusCode.BAD_REQUEST);
					return response;
				}
				media.setUrl(headPostUrl);
			}
			media.setCreateTime(new Date());
			mediaService.save(media);
			response.setMessage("success");
			response.setStatusCode(ResponseStatusCode.OK);
			return response;
		}
		response.setMessage("上传文件异常！");
		response.setStatusCode(ResponseStatusCode.BAD_REQUEST);
		return response;
	}
	public String uploadPoster(String base64Str){
		if(StringUtils.isNotEmpty(base64Str)){
			String[] obj =base64Str.split(",");
			String fileName = UUID.randomUUID().toString().replace("-", "")+".";
			fileName += obj[0].split(";")[0].split("/")[1];
			String basicPath = lmsConfig.getProperty("basis_url");
			try {
				FileOutputStream out = new FileOutputStream(basicPath+"/"+fileName);
				byte[] data = new Base64().decode(obj[1]);
				out.write(data);
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			String httpUrl = lmsConfig.getProperty("http_url");
			return httpUrl+"/"+ fileName;
		}
		return null;
	}
	
	@RequestMapping("del/{mediaId}")
	@ResponseBody
	public RestResponse detail(Model model, @PathVariable Integer mediaId){
		RestResponse response = new RestResponse();
		mediaService.deleteById(mediaId);
		response.setStatusCode(ResponseStatusCode.OK);
		return response;
	}
}
