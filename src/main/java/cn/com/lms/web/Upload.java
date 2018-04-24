package cn.com.lms.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.videoworks.commons.util.json.JsonConverter;

@Controller
@RequestMapping("lms/upload")
public class Upload {
	
	@Resource
	private Properties lmsConfig;

	private String[] allowFiles = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };

	
	@RequestMapping(value="img",method=RequestMethod.POST)
	@ResponseBody
	public String uploadImg( MultipartFile upfile, HttpServletRequest request){
		Map<String,Object> resulte = new HashMap<String,Object>();
		if(null!=upfile){
			if(!checkFileType(upfile.getOriginalFilename())){
				resulte.put("state", "不允许的文件格式");
				return JsonConverter.format(resulte);
			}
			if(upfile.getSize()>2*1024*1024){
				resulte.put("state", "上传文件不能超过2M");
				return JsonConverter.format(resulte);
				
			}
			String [] arry = upfile.getOriginalFilename().split("\\.");
			String fileName =UUID.randomUUID().toString().replace("-","")+"."+arry[1];
			String path = lmsConfig.getProperty("basis_url");
	        File targetFile = new File(path, fileName);  
	        if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        } 
	        //保存  
	        try {  
	        	upfile.transferTo(targetFile);  
	        } catch (Exception e) {  
	        	resulte.put("state", "io异常");
	        	return JsonConverter.format(resulte);
	        }
//	        {"name":"27771488018180752.jpg", "originalName": "RVjB-fyavvsk2791328.jpg", "size": 29901, "state": "SUCCESS", "type": ".jpg", "url": "/20170225/27771488018180752.jpg"}    
	        resulte.put("name", fileName);
	        resulte.put("originalName", upfile.getOriginalFilename());
	        resulte.put("size", upfile.getSize());
	        resulte.put("state", "SUCCESS");
	        resulte.put("type", arry[1]);
	        resulte.put("url", lmsConfig.getProperty("http_url")+"/"+fileName);
	        return JsonConverter.format(resulte);
		}
		
		resulte.put("state", "未知异常");
		return JsonConverter.format(resulte);
	}
	
	private boolean checkFileType(String fileName) {
		Iterator type = Arrays.asList(allowFiles).iterator();
		while (type.hasNext()) {
			String ext = (String) type.next();
			if (fileName.toLowerCase().endsWith(ext)) {
				return true;
			}
		}
		return false;
	}
}
