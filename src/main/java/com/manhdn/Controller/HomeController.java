package com.manhdn.Controller;

import java.sql.Connection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.manhdn.service.productService;
import com.manhdn.dao.productDAO;
import com.manhdn.database.MySQLConnector;
import com.manhdn.entity.productEntity;

@Controller
@Component
public class HomeController extends CommonController<productEntity> {
	/**
	 * index
	 * 
	 * @return
	 */
	@Autowired(required = false)
	productService prS;
	@Autowired(required = false)
	productDAO dao;
//	@RequestMapping(value = { "/app-view" }, method = RequestMethod.GET)
//	public ModelAndView homePage() throws Exception {
//		prS =new productService();
//		dao =new productDAO();
//		List<productEntity> lst = prS.findAll();
////		Connection c =new MySQLConnector().getConnection();
//		ModelAndView mav = new ModelAndView("/user/home");
//		mav.addObject("dataList", lst);
//		return mav;
//	}

	/**
	 * @author manhdn view detail
	 * @return
	 */
//	@RequestMapping(value = { "/app-view/viewDetail" }, method = RequestMethod.GET)
//	public ModelAndView viewDetail() {
//		ModelAndView mav = new ModelAndView("/user/viewDetail");
//		return mav;
//	}

	/**
	 * Login
	 */
	@RequestMapping(value = { "/app-view/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("/user/login");
		return mav;
	}
}
