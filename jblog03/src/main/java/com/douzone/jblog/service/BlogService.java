package com.douzone.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;

	public BlogVo findById(String id) {
		return blogRepository.findById(id);
	}

	public Map<String, Object> findByBlog(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		BlogVo blogVo = blogRepository.findById(id);
		List<CategoryVo> categoryList = blogRepository.findByCategory(id);
		
		map.put("blogVo", blogVo);
		map.put("categoryList", categoryList);
		
		return map;
	}

	public boolean updateBasic(BlogVo blogVo) {
		return blogRepository.updateBasic(blogVo);
	}

	public List<CategoryVo> findByCategory(String id) {
		return blogRepository.findByCategory(id);
	}

	public boolean write(PostVo postVo) {
		return blogRepository.write(postVo);
	}

	public List<CategoryVo> findByCategoryAndPost(String id) {
		return blogRepository.findByCategoryandPost(id);
	}

	public boolean categoryAdd(CategoryVo categoryVo) {
		return blogRepository.categoryAdd(categoryVo);
	}

	

}
