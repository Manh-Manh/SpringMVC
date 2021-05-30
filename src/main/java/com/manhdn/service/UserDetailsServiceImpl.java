//package com.manhdn.service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.transaction.Transactional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.manhdn.dao.userDAO;
//import com.manhdn.entity.userEntity;
//@Service
//@Transactional
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//	@Autowired
//	private userDAO userDAO;
//	@Override
//	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//		userEntity appUser = this.userDAO.findAppUserByUserName(userName);
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
//
//}