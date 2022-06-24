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
		PostVo postVo = new PostVo();
		BlogVo blogVo = blogRepository.findById(id);
		List<CategoryVo> categoryList = blogRepository.findByCategory(id);
		List<PostVo> postList = blogRepository.findByPostList(categoryList.get((int) categoryNo));
		if(postList.size() != 0) {
			postVo = postList.get(postList.size()-((int) postNo+1));
		}
		
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

	public String findByPost(String category) {
		List<PostVo> postList = blogRepository.findByPostList(Long.parseLong(category));
		if(postList.size() == 0) {
			return null;
		}
		long newPostNo = postList.size()-1;
		
		return String.valueOf(newPostNo);
	}

	public Map<String, Object> findByNewPost(String id, String category) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 카테고리 리스트 중 인덱스 찾아내기
		List<String> categoryList = blogRepository.findByCategoryNo(id);
		map.put("categoryNo", categoryList.indexOf(category));
		
		// 포스트 리스트 중 인덱스 찾아내기
		// 새 포스트 찾아오기
		String postId = findByPost(category);
		if (postId == null) {
			map.put("postNo", "0");
			return map;
		}
		
		map.put("postNo", postId);
		return map;
	}

}
