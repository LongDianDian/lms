package cn.com.lms.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lms")
public class IndexController {
	@RequestMapping("")
	public void lms (HttpServletResponse response){
		try {
			response.sendRedirect("/lms/login");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
