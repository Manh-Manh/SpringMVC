package com.manhdn.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.manhdn.AppConstants;
import com.manhdn.entity.ajaxEntity;
import com.manhdn.entity.faceEntity;
import com.manhdn.entity.machineEntity;
import com.manhdn.entity.strapEntity;
import com.manhdn.entity.supplierEntity;
import com.manhdn.service.homeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class menuInterceptor implements HandlerInterceptor {

	@Autowired
	private homeService service;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, String> maps = service.loadMenu();
		
		Gson gson = new GsonBuilder().create();
		JsonArray jsonArray =new JsonArray();
		JsonElement elements;
		// List Supplier
		List<supplierEntity> lstSup = new ArrayList<supplierEntity>();
		String jsonStr = maps.get(AppConstants.SUP);
		JsonParser parser = new JsonParser();
		elements = parser.parse(jsonStr);
		jsonArray = elements.getAsJsonArray();
		for(int i = 0; i<jsonArray.size();i++ ) {
			supplierEntity e = gson.fromJson(jsonArray.get(i), supplierEntity.class);
			lstSup.add(e);
		}
		request.setAttribute(AppConstants.SUP, lstSup);
		
		// List Face
		jsonStr = maps.get(AppConstants.FACE);
		elements = parser.parse(jsonStr);
		jsonArray = new JsonArray();
		jsonArray = elements.getAsJsonArray();
		List<faceEntity> lstFace = new ArrayList<faceEntity>();
		for(int i = 0; i<jsonArray.size();i++ ) {
			faceEntity e = gson.fromJson(jsonArray.get(i), faceEntity.class);
			lstFace.add(e);
		}
		request.setAttribute(AppConstants.FACE, lstFace);
		
		// List Machine
		jsonStr = maps.get(AppConstants.MACHINE);
		elements = parser.parse(jsonStr);
		jsonArray = new JsonArray();
		jsonArray = elements.getAsJsonArray();
		List<machineEntity> lstMachine = new ArrayList<machineEntity>();
		for(int i = 0; i<jsonArray.size();i++ ) {
			machineEntity e = gson.fromJson(jsonArray.get(i), machineEntity.class);
			lstMachine.add(e);
		}
		request.setAttribute(AppConstants.MACHINE, lstMachine);
		
		// List Strap
		jsonStr = maps.get(AppConstants.STRAP);
		elements = parser.parse(jsonStr);
		jsonArray = new JsonArray();
		jsonArray = elements.getAsJsonArray();
		List<strapEntity> lstStrap = new ArrayList<strapEntity>();
		for(int i = 0; i<jsonArray.size();i++ ) {
			strapEntity e = gson.fromJson(jsonArray.get(i), strapEntity.class);
			lstStrap.add(e);
		}
		request.setAttribute(AppConstants.STRAP, lstStrap);
		// list gender
		List<String> lstGender = new ArrayList<String>();
		lstGender.add("Nam");
		lstGender.add("Nữ");
		request.setAttribute(AppConstants.GENDER, lstGender);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
