package com.manhdn.dao;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.ls.LSInput;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.manhdn.database.CommonDatabase;
import com.manhdn.entity.*;

@Repository
@Transactional
public class productDAO {

	private CommonDatabase cmd;

	public productDAO() {
		cmd = new CommonDatabase();
	}

	/**
	 * Get all
	 * 
	 * @return
	 */
	public List<productEntity> findAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM products ");
		List<Object> params = new ArrayList<>();
		List<productEntity> lst = (List<productEntity>) cmd.getListObjByParams(sql, params, productEntity.class);
//		productEntity pr = lst.get(0);
//		productEntity e = getDetail("a2");
		return lst;
	}

	public List<productEntity> findDataList(Long userId, productEntity dataSearch) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT DISTINCT  p.id, p.productId, p.productName, p.quantity, p.supplierId, p.unitPrice, "
				+ " p.disCount, p.gender, p.startDate_discount, p.endDate_discount, p.status, p.strapId, "
				+ " p.faceId, p.machineId, p.material, p.otherFunc, p.image, p.description, p.del_flag, "
				+ " p.created_date, p.updated_date, p.deleted_date, " + " p.created_by, p.updated_by, p.deleted_by "
				+ " FROM products p join suppliers sup on p.supplierId = sup.supplierId "
				+ " join strap s on p.strapId = s.strapId "
				+ " join face f on f.faceId = p.faceId join machine m on m.machineId = p.machineId "
				+ " WHERE (p.del_flag != 1 or p.del_flag is null) and p.status = 1 ");
		List<Object> params = new ArrayList<>();
		List<productEntity> result = new ArrayList<productEntity>();
		result = (List<productEntity>) cmd.getListObjByParams(sql, params, productEntity.class);
		if (result.size() > 0) {
			for (productEntity p : result) {
				if (null != p) {
					// Them supplier
					supplierEntity sup = new supplierEntity();
					supplierDAO supDAO = new supplierDAO();
					sup = supDAO.findSupplierById(p.getSupplierId());
					p.setSupplier(sup);
					// Them face
					faceEntity face = new faceEntity();
					faceDAO faceDAO = new faceDAO();
					face = faceDAO.findFaceById(p.getFaceId());
					p.setFace(face);
					// them machine
					machineEntity machine = new machineEntity();
					machineDAO machineDAO = new machineDAO();
					machine = machineDAO.findMachineById(p.getMachineId());
					p.setMachine(machine);
				}
			}
		}
		return result;
	}

	/**
	 * Get detail
	 * 
	 * @param productId
	 * @return
	 */
	public productEntity getProductDetail(String productId) {
		StringBuilder sql = new StringBuilder();
		productEntity result = new productEntity();
		List<Object> params = new ArrayList<Object>();
//		sql.append("SELECT p.id, p.productId, p.productName, p.quantity, p.unitPrice, discount, gender, "
//				+ " status "
//				+ ""
//				+ ""
//				+ " FROM products p join suppilers sup on p.suppilerId = sup.suppilerId "
//				+ " join face f on p.faceId = f. faceId "
//				+ " join machine m on p.machineId = m.machineId "
//				+ " join strap s on p.strapId = s.strapId "
//				+ "  ");
//		sql.append("WHERE p.producId = ? and p.status = 1 ");
		sql.append("SELECT * FROM products p " + "WHERE p.productId = ");
		sql.append("?");
		sql.append(" and (p.status != 0 or p.status is null) ");
		params.add(productId);
		List<productEntity> lst = (List<productEntity>) cmd.getListObjByParams(sql, params, productEntity.class);
		if (null == lst || lst.size() == 0) {
			return null;
		}
		result = lst.get(0);
		if (null != result) {
			// Them supplier
			supplierEntity sup = new supplierEntity();
			supplierDAO supDAO = new supplierDAO();
			sup = supDAO.findSupplierById(result.getSupplierId());
			result.setSupplier(sup);
			// Them face
			faceEntity face = new faceEntity();
			faceDAO faceDAO = new faceDAO();
			face = faceDAO.findFaceById(result.getFaceId());
			result.setFace(face);
			// them machine
			machineEntity machine = new machineEntity();
			machineDAO machineDAO = new machineDAO();
			machine = machineDAO.findMachineById(result.getMachineId());
			result.setMachine(machine);
		}
		return result;
	}

	/**
	 * them san pham
	 * 
	 * @param userId
	 * @param product
	 * @return
	 */
	public boolean insert(Long userId, productEntity product) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		List<productEntity> listData = new ArrayList<productEntity>();
//		if (userId != null) {
//			product.setCreated_by(userId);
//		}
		cmd = new CommonDatabase();
		
		return cmd.insertOrUpdateDataBase(sql, params);
	}
	
//	public boolean insertF(Long userId, productEntity product) {
//		StringBuilder sql = new StringBuilder();
//		List<Object> params = new ArrayList<>();
//		List<faceEntity> listData = new ArrayList<faceEntity>();
////		if (userId != null) {
////			product.setCreated_by(userId);
////		}
//		try {
//			// Bước 1: Tạo đối tượng luồng và liên kết nguồn dữ liệu
//			File f = new File("C:\\Users\\Admin\\eclipse-workspace2\\crawl\\Seiko.txt");
//			FileInputStream fr = new FileInputStream(f);
//			String data = "";
//			InputStreamReader isr = new InputStreamReader(fr, StandardCharsets.UTF_8);
//
//			// Bước 2: Đọc dữ liệu
//			BufferedReader br = new BufferedReader(isr);
//			String line;
//			while ((line = br.readLine()) != null) {
//				data += line;
//			}
//			System.out.println(data);
//			GsonBuilder builder = new GsonBuilder();
//			Gson g = builder.create();
//			JsonParser parser = new JsonParser();
//			JsonElement tradeElement = parser.parse(data);
//			JsonArray arr = tradeElement.getAsJsonArray();
//			for (int i = 0; i < arr.size(); i++) {
//				faceEntity o = (faceEntity) g.fromJson(arr.get(i), faceEntity.class);
//
//				listData.add(o);
//			}
////		     listData = (List<productEntity>) g.fromJson(data, productEntity.class);
//			System.out.println("abc");
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		// date time
//
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		String date = dtf.format(LocalDateTime.now());
////		product.setCreated_date(date);
//
//		for (int i = 0; i < listData.size(); i++) {
//			faceEntity p = listData.get(i);
//			p.setCreated_by(1L);
//			p.setCreated_date(date);
//			String id = "F" + (i + 1);
//			p.setFaceId(id);
//			p.setStatus("1");
//			p.setFaceName(p.getGlass());
//			p.setThickness(rep(p.getThickness()));
//			p.setFaceSize(rep(p.getFaceSize()));
//			p.setWaterProof(rep(p.getWaterProof()));
//		}
//
////		sql.append("INSERT INTO products (  productId, productName,"
////				+ " quantity, supplierId, unitPrice, discount, gender, "
////				+ " status,  material, otherFunc, image, description," + " del_flag, created_date, created_by  ) ");
////		sql.append("VALUES  ");
////		
//		sql.append("INSERT INTO face (id, faceId, faceName, thickness, faceSize, glass, waterProof, "
//				+ " description, status, created_date, created_by  ) ") ;
//		sql.append("VALUES  ");
//		for (int i = 0; i<listData.size();i++) {
//			faceEntity p =listData.get(i);
//			sql.append(" (?,?,?,?,?,?,?,?,?,?,?) ");
//			if(i<listData.size()-1) {
//				sql.append(",");
//			}
//			params.add(i+1);
//			params.add(p.getFaceId());
//			params.add(p.getFaceName());
//			params.add(p.getThickness());
//			params.add(p.getFaceSize());
//			params.add(p.getGlass());
//			params.add(p.getWaterProof());
//			params.add(p.getDescription());
//			params.add(p.getStatus());
//			params.add(p.getCreated_date());
//			params.add(p.getCreated_by());
//		}
//
//		cmd = new CommonDatabase();
//		boolean b = cmd.insertOrUpdateDataBase(sql, params);
//		return false;
//	}
//	
//	
//	private String rep(String s) {
//		s = s.replace("m", "");
//		return s.replace("M", "");
//	}

}
