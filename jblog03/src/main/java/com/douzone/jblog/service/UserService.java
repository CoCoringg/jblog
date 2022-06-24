package com.douzone.jblog.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.repository.UserRepository;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;

	public boolean insert(@Valid UserVo userVo) {
		UserVo vo = userRepository.insert(userVo);
		String blogId = blogRepository.insert(vo);
		boolean blog = blogRepository.categoryInsert(blogId);
		return blog;
	}

	private UserVo getUser(UserVo userVo) {
		return userRepository.findByIdAndPassword(userVo);
	}
	
	public UserVo getUser(String id, String password) {
		UserVo vo = new UserVo();
		vo.setId(id);
		vo.setPassword(password);
		
		return getUser(vo);
	}

	public List<String> findAllUser() {
		return userRepository.findAllUser();
	}

	
	
	
}
