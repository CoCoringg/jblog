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

	public Map<String, Object> findByMain(String id, long categoryNo, long postNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		BlogVo blogVo = blogRepository.findById(id);
		List<CategoryVo> categoryList = blogRepository.findByCategory(id);
		List<PostVo> postList = blogRepository.findByPostList(categoryList.get((int) categoryNo));
		PostVo postVo = postList.get(postList.size()-((int) postNo+1));
		map.put("blogVo", blogVo);
		map.put("categoryList", categoryList);
		map.put("postList", postList);
		map.put("post", postVo);
		
		return map;
	}
	
	public BlogVo findByBlog(String id) {
		return blogRepository.findById(id);
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

	public boolean categoryDelete(long categoryNo) {
		CategoryVo categoryVo = blogRepository.findByCategoryOne(categoryNo);
		List<CategoryVo> categoryList = blogRepository.findByCategory(categoryVo.getBlogId());
		
		if(categoryList.size() == 1) {
			return false;
		} else if(categoryVo.getPostCount() > 0) {
			return false;
		}
		
		return blogRepository.categoryDelete(categoryNo);
		
	}

	

	

}
