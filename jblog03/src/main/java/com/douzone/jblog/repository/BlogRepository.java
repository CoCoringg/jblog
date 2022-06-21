package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Repository
public class BlogRepository {
	@Autowired
	private SqlSession sqlSession;

	public String insert(UserVo vo) {
		String blogId = null;
		if(sqlSession.insert("blog.insert", vo)==1) {
			blogId = vo.getId();
		}
		return blogId;
	}

	public boolean categoryInsert(String blogId) {
		return sqlSession.insert("blog.categoryInsert", blogId)==1;
	}

	public BlogVo findById(String id) {
		return sqlSession.selectOne("blog.findById", id);
	}

	public List<CategoryVo> findByCategory(String id) {
		return sqlSession.selectList("blog.findByCategory", id);
	}

	public boolean updateBasic(BlogVo blogVo) {
		return sqlSession.update("blog.updateBasic", blogVo)==1;
	}

	public boolean write(PostVo postvo) {
		return sqlSession.insert("blog.write", postvo)==1;
	}

	public List<PostVo> findByPost(PostVo postVo) {
		return sqlSession.selectList("blog.findByPost", postVo);
	}

	public List<CategoryVo> findByCategoryandPost(String id) {
		return sqlSession.selectList("blog.findByCategoryAndPost", id);
	}

	public boolean categoryAdd(CategoryVo categoryVo) {
		return sqlSession.insert("blog.categoryAdd", categoryVo)==1;
	}


}
