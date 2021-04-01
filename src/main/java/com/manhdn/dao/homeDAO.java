package com.manhdn.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.manhdn.AppConstants;
import com.manhdn.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.manhdn.database.CommonDatabase;
import com.manhdn.entity.faceEntity;
import com.manhdn.entity.machineEntity;
import com.manhdn.entity.strapEntity;
import com.manhdn.entity.supplierEntity;

@Repository
public class homeDAO {

	@Autowired
	supplierDAO supD;
	faceDAO faceD;
	machineDAO machineD;
	strapDAO strapD;

	public homeDAO() {
		supD = new supplierDAO();
		faceD = new faceDAO();
		machineD = new machineDAO();
		strapD = new strapDAO();
	}

	public Map<String, String> loadMenu() {
		// TODO Auto-generated method stub
		Map<String, String> result = new HashMap<String, String>();
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String jsonStr = "";
		// Get list suppliers
		List<supplierEntity> lstSup = supD.findDataList(null, null);
		// to Json
		jsonStr = gson.toJson(lstSup);
		result.put(AppConstants.SUP, jsonStr);
		// convert to List<Object>
		List<supplierEntity> lSup = new ArrayList<supplierEntity>();
//		lSup = (List<supplierEntity>) gson.fromJson(jsonStr, supplierEntity.class);
		jsonStr = "";
		// Get list machine
		List<machineEntity> lstMachine = machineD.findDataList(null, null);
		// to Json
		jsonStr = gson.toJson(lstMachine);
		result.put(AppConstants.MACHINE, jsonStr);
		jsonStr = "";

		// Get list face
		List<faceEntity> lstFace = faceD.getListFaceName();
		// to Json
		jsonStr = gson.toJson(lstFace);
		result.put(AppConstants.FACE, jsonStr);
		jsonStr = "";

		// Get List Strap
		List<strapEntity> lstStrap = strapD.findDataList(null, null);
		// to Json
		jsonStr = gson.toJson(lstStrap);
		result.put(AppConstants.STRAP, jsonStr);
		jsonStr = "";
		// convert to List<Object>
//		List<Object> lSup = new ArrayList<Object>();
//		lSup = (List<Object>) gson.fromJson(jsonStr, Object.class);
//		if (lstSup != null) {
//			result.put(SUP, lSup);
//		}
//		List<Object> params = new ArrayList<Object>();
//		StringBuilder sql = new StringBuilder();
//
//		sql.append("SELECT * FROM suppliers s " + " WHERE 1 ");

//		result = (List<machineEntity>) cmd.getListObjByParams(sql, params, machineEntity.class);
		return result;
	}

}
