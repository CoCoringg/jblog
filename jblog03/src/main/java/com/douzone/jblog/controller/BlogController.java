package com.douzone.jblog.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	
	@RequestMapping(value={"", "/{pathNo1}", "/{pathNo1}/{pathNo2}"})
	public String blog(@PathVariable("id") String id, 
			@PathVariable("pathNo1") Optional<Long> pathNo1, 
			@PathVariable("pathNo2") Optional<Long> pathNo2, 
			Model model ) {
		
//		long categoryNo = 0L;
//		long postNo = 0L;
//		
//		if(pathNo2.isPresent()) {
//			postNo = pathNo2;
//			pathNo1 = pat
//		} 
		
		Map<String, Object> map = blogService.findByBlog(id);
		model.addAllAttributes(map);
		return "blog/blog-main";
	}
	
	@RequestMapping(value="/admin/basic", method=RequestMethod.GET) 
	public String basic() {
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value="/admin/update", method=RequestMethod.POST)
	public String update(BlogVo blogVo,
			@PathVariable("id") String id,
			@RequestParam(value="file") MultipartFile multipartFile) {
		String url = fileUploadService.restoreImage(multipartFile);
		
		if(url == null) {
			BlogVo vo = (BlogVo) servletContext.getAttribute("blog");
			url = vo.getLogo();
		}
		
		blogVo.setLogo(url);
		blogService.updateBasic(blogVo);
		
		return "redirect:/"+id+"/admin/basic";
	}
	
	@RequestMapping(value="/admin/write", method=RequestMethod.GET)
	public String write(@PathVariable("id") String id, Model model) {
		// 카테고리 찾아오기
		List<CategoryVo> categoryList = blogService.findByCategory(id);
		model.addAttribute("categoryList", categoryList);
		
		return "blog/blog-admin-write";
	}
	
	@RequestMapping(value="/admin/write", method=RequestMethod.POST)
	public String write(@PathVariable("id") String id,
			@RequestParam(value="category") String category, PostVo postVo) {
		// 포스트 하기
		postVo.setCategoryNo(Long.parseLong(category));
		blogService.write(postVo);
		
		return "redirect:/"+id+"/"+postVo.getCategoryNo();
	}
	
	@RequestMapping(value="/admin/category", method=RequestMethod.GET)
	public String category(@PathVariable("id") String id, Model model) {
		List<CategoryVo> categoryList = blogService.findByCategoryAndPost(id);
		model.addAttribute("categoryList", categoryList);
		return "blog/blog-admin-category";
	}
	
	@RequestMapping(value="admin/category/add", method = RequestMethod.POST)
	public String categoryAdd(@PathVariable("id") String id, CategoryVo categoryVo, Model model) {
		categoryVo.setBlogId(id);
		blogService.categoryAdd(categoryVo);
		return "redirect:/"+id+"/admin/category";
	}
	
}
