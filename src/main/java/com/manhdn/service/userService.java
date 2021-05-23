package com.manhdn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.manhdn.dao.productDAO;
import com.manhdn.dao.userDAO;
import com.manhdn.entity.productEntity;
import com.manhdn.entity.userEntity;

@Service
public class userService {
	@Autowired
	userDAO dao;
	
	/**
	 * 
	 * @param userId
	 * @param dataSearch
	 * @return
	 */
	public List<userEntity> findDaList(Long userId, productEntity dataSearch) {
		dao =new userDAO();
		return dao.findDataList(userId, dataSearch);
	}

	public userEntity login(userEntity dataSearch) {
		// TODO Auto-generated method stub
		dao =new userDAO();
		return dao.login(dataSearch);
		
	}

	public boolean insertOrUpdate(Long userId, userEntity userCheckOut) {
		// TODO Auto-generated method stub
		dao = new userDAO();
		return dao.insertOrUpdate(userId,userCheckOut);
		
	}

	public boolean register(userEntity dataSearch) {
		dao = new userDAO();
		return dao.insertOrUpdate(null, dataSearch);
	}

	public userEntity findUserByUserEmail(String email) {
		dao = new userDAO();
		return dao.findUserByUserEmail(email);
	}

	public userEntity getDetail(Long userId) {
		dao = new userDAO();
		return dao.getDetail(userId);
	}

	public boolean delete(Long userId, Long userDeleteId) {
		dao = new userDAO();
		return dao.delete(userId, userDeleteId);
	}
	
//	@Override
//	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//		userEntity appUser = this.dao.findAppUserByUserName(userName);
//
//		if (appUser == null) {
//			System.out.println("User not found! " + userName);
//			throw new UsernameNotFoundException("User " + userName + " was not found in the database");
//		}
//
//		System.out.println("Found User: " + appUser);
//		List<String> roleNames = appUser.getListRoleName();
//		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
//		if (roleNames != null) {
//			for (String role : roleNames) {
//				GrantedAuthority authority = new SimpleGrantedAuthority(role);
//				grantList.add(authority);
//			}
//		}
//
//		UserDetails userDetails = new User(appUser.getUserName(),appUser.getNewPassword(), grantList);
//
//		return userDetails;
//	}
}
