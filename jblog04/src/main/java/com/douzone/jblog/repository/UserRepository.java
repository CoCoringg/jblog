package com.douzone.jblog.repository;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.UserVo;

@Repository
public class UserRepository {
	@Autowired
	private SqlSession sqlSession;

	public UserVo insert(@Valid UserVo userVo) {
		if(sqlSession.insert("user.insert", userVo)==1) {
			return userVo;
		}
		return null;
	}

	public UserVo findByIdAndPassword(UserVo userVo) {
		return sqlSession.selectOne("user.findByIdAndPassword", userVo);
	}

	public List<String> findAllUser() {
		return sqlSession.selectList("user.findAllUser");
	}
	
	
}
