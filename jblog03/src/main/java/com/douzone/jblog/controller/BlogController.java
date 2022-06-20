package com.douzone.jblog.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping("")
	public String blog(@PathVariable("id") String id, Model model ) {
		Map<String, Object> map = blogService.findByBlog(id);
		model.addAllAttributes(map);
		return "blog/blog-main";
	}
	
	@RequestMapping("/admin/basic") 
	public String basic() {
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping("/admin/update")
	public String update(BlogVo blogVo, @RequestParam(value="file") MultipartFile multipartFile) {
		return "redirect:/"+blogVo.getId()+"/admin/basic";
	}
	
}
