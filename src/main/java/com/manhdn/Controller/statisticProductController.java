package com.manhdn.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.manhdn.AppConstants;
import com.manhdn.dao.discountDAO;
import com.manhdn.entity.discountEntity;
import com.manhdn.entity.statisticProductEntity;
import com.manhdn.entity.statisticProductEntity;
import com.manhdn.entity.userEntity;
import com.manhdn.entity.statisticProductEntity;
import com.manhdn.service.statisticProductService;
import com.manhdn.service.statisticProductService;

@Controller
public class statisticProductController extends CommonController<statisticProductEntity> {
	@Autowired
	statisticProductService service;
	private Logger logger = Logger.getLogger(statisticProductController.class);

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/admin/statisticProduct" }, method = RequestMethod.GET)
	public ModelAndView statisticProduct(HttpSession session) {
		service = new statisticProductService();
		mav = new ModelAndView("/admin/statistic/statisticProduct");
		if(!isAdmin(session)) {
				mav = new ModelAndView("redirect:/app-view");
				logger.error("Khong co quyen");
				session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
				return mav;
		}
		dataSelected = new statisticProductEntity();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateNow = dtf.format(LocalDateTime.now());
		dataSelected.setToDate(dateNow);
		Calendar c = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		try {
			Date date1 = format.parse(dateNow);
			c.setTime(date1);
//			c.add(Calendar.MONTH, -1);
			c.set(Calendar.DAY_OF_MONTH, 1);
			String dateLast = format.format(c.getTime());
			dataSelected.setFromDate(dateLast);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataList = service.findDaList(user.getUserId(), dataSelected);
//		mav.addObject("dataList",dataList);
//		service.insertOrUpdate(0L, dataSearch);
		addData();
		logger.info(mav);
		return mav;
	}
	
	
	@RequestMapping(value = { "/admin/statisticProductSearch" }, method = RequestMethod.POST)
	public ModelAndView doSearchStatisticProduct(@ModelAttribute("itemSearch") statisticProductEntity statisticProductSearch, HttpSession session,
			HttpServletRequest request) {
		userEntity user = (userEntity) session.getAttribute(AppConstants.SESSION_USER);
		if(!isAdmin(session)) {
			mav = new ModelAndView("redirect:/app-view");
			logger.error("Khong co quyen");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		service = new statisticProductService();
		mav = new ModelAndView("/admin/statistic/statisticProduct");
		if(statisticProductSearch == null) {
			mav = new ModelAndView("redirect:/admin");
			logger.error("data null");
			session.setAttribute(AppConstants.SESSION_MESSAGE, AppConstants.MESSAGE_ERROR);
			return mav;
		}
		dataSelected.setFromDate(statisticProductSearch.getFromDate());
		dataSelected.setToDate(statisticProductSearch.getToDate());
		dataList = service.findDaList(user.getUserId(), statisticProductSearch);
		addData();
		logger.info(mav);
//		return this.editProductView(session, dataInsert.getProductId());
		return mav;
	}
	
	@ModelAttribute("itemSearch")
	public statisticProductEntity dtSearch() {
		return new statisticProductEntity();
	}
}
