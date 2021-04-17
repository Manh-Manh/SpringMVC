package com.manhdn.dao;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
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
import com.manhdn.AppConstants;
import com.manhdn.FunctionCommon;
import com.manhdn.database.CommonDatabase;
import com.manhdn.entity.*;

@Repository
@Transactional
public class productDAO {

	private CommonDatabase cmd;
	private Long priceRange = 5000000L;
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

//		sql.append(" WHERE ").append(cmd.sql_SelectLike("productName", "ÔGi"));
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

	public List<productEntity> findDataList(Long userId, Map<String, List<String>> mapSearch, Integer page, Integer pageSize) {

		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append(" SELECT DISTINCT  p.id, p.productId, p.productName, p.quantity, p.supplierId, p.unitPrice, "
				+ " p.disCount, p.gender, p.startDate_discount, p.endDate_discount, p.status, p.strapId, "
				+ " p.faceId, p.machineId, p.material, p.otherFunc, p.image, p.description, p.del_flag, "
				+ " p.created_date, p.updated_date, p.deleted_date, " + " p.created_by, p.updated_by, p.deleted_by "
				+ " FROM products p join suppliers sup on p.supplierId = sup.supplierId "
				+ " join strap s on p.strapId = s.strapId "
				+ " join face f on f.faceId = p.faceId join machine m on m.machineId = p.machineId "
				+ " WHERE (p.del_flag != 1 or p.del_flag is null) AND p.status = 1 ");
		// Tìm kiếm nâng cao
		if (mapSearch != null || mapSearch.size() == 0) {
			Set<String> keys = mapSearch.keySet();
			for (String key : keys) {
//				if (key.equals(AppConstants.MAP_SEARCH_PAGE)) {
//					page = Integer.valueOf(mapSearch.get(key).get(0));
//				} else {
				if (!key.equals(AppConstants.MAP_SEARCH_AMOUNT) && !key.equals(AppConstants.MAP_SEARCH_STRING)) {
					if (mapSearch.get(key).size() > 0) {
						sql.append(" AND ( ");
						sql.append(FunctionCommon.generateSqlIn("p." + key, mapSearch.get(key)));
						sql.append(" ) ");
						params.addAll(mapSearch.get(key));
					}
				}
				if (key.equals(AppConstants.MAP_SEARCH_AMOUNT)) {
					// Timf kiem theo khoang gia
					sql.append(" AND ( p.unitPrice BETWEEN ? AND ? )");
					params.addAll(mapSearch.get(key));
				}
				if (key.equals(AppConstants.MAP_SEARCH_STRING)) {
					if (!FunctionCommon.isEmpty(mapSearch.get(key))) {
						String sqlLike = FunctionCommon.generateSqlLike("productName",
								mapSearch.get(key).get(0));
						if (!FunctionCommon.isEmpty(sqlLike)) {
							sql.append(" AND ( ");
							sql.append(sqlLike);
							sql.append(" ) ");
						}
					}
				}
			}
		}
		sql.append(" ORDER By p.unitPrice ");
		// Page size
		if (page == null || page < 1) {
			page = 1;
		}
		if (pageSize != null) {
			sql.append(" LIMIT ?, ? ");
			params.add((page - 1) * pageSize);
			params.add(pageSize);
		}
		List<productEntity> result = new ArrayList<productEntity>();
		result = (List<productEntity>) cmd.getListObjByParams(sql, params, productEntity.class);
		if (result.size() > 0) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String date = dtf.format(LocalDateTime.now());
			for (productEntity p : result) {
				if (null != p) {
					if (p.getDiscount() != null && p.getDiscount() > 0 && p.getEndDate_discount() != null) {

						try {
							Date date_now = new SimpleDateFormat("yyyy-MM-dd").parse(date);
							Date end_discount = new SimpleDateFormat("yyyy-MM-dd").parse(p.getEndDate_discount());
							Integer i = date_now.compareTo(end_discount);
							if (i > 0) {
								p.setDiscount(0D);
							}
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
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

	public List<productEntity> quickSearch(Long userId, String strSearch) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		productEntity result = new productEntity();
		List<Object> params = new ArrayList<Object>();

		sql.append("SELECT * FROM products p " + "WHERE p.productName like ? ");

		sql.append(" and (p.status != 0 or p.status is null) ");
		params.add("%" + strSearch + "%");
		List<productEntity> lst = (List<productEntity>) cmd.getListObjByParams(sql, params, productEntity.class);
		if (null == lst || lst.size() == 0) {
			return null;
		}
		return lst;
	}

	public Integer countDataList(Long userId, Map<String, List<String>> mapSearch) {
		// TODO Auto-generated method stub
		List<productEntity> lst = this.findDataList(userId, mapSearch, null, null);
		if (lst != null) {
			return lst.size();
		} else {
			return 0;
		}
	}

	public List<productEntity> findListSale() {
		StringBuilder sql = new StringBuilder();
		productEntity result = new productEntity();
		List<Object> params = new ArrayList<Object>();
		sql.append("SELECT * FROM products p " + " WHERE p.discount > 0 AND sysdate() < p.endDate_discount ");
		sql.append(" AND (p.status != 0 or p.status is null) ");
		sql.append(" ORDER BY p.unitPrice ");
		List<productEntity> lst = (List<productEntity>) cmd.getListObjByParams(sql, params, productEntity.class);
		if (null == lst || lst.size() == 0) {
			return null;
		}
		return lst;
	}

	public List<productEntity> findListNew() {
		StringBuilder sql = new StringBuilder();
		productEntity result = new productEntity();
		List<Object> params = new ArrayList<Object>();
		sql.append("SELECT * FROM products p " + " WHERE 1 ");
		sql.append(" AND (p.status != 0 or p.status is null) ");
		sql.append(" ORDER BY p.created_date desc LIMIT 0, 12 ");
		List<productEntity> lst = (List<productEntity>) cmd.getListObjByParams(sql, params, productEntity.class);
		if (null == lst || lst.size() == 0) {
			return null;
		}
		return lst;
	}

	/**
	 * Tìm kiếm sản phẩm liên quan 
	 * - theo thương hiệu hoặc theo khoảng giá +-5 tr
	 * @param dataSelected
	 * @return
	 */
	public List<productEntity> findListSuggest(productEntity dataSelected) {
		if(dataSelected == null) {
			return null;
		}
		StringBuilder sql = new StringBuilder();
		productEntity result = new productEntity();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM ( "
				+ " SELECT  p.id, p.productId, p.productName, p.quantity, p.supplierId, p.unitPrice, "
				+ " p.disCount, p.gender, p.startDate_discount, p.endDate_discount, p.status, p.strapId, "
				+ " p.faceId, p.machineId, p.material, p.otherFunc, p.image, p.description, p.del_flag, "
				+ " p.created_date, p.updated_date, p.deleted_date, " + " p.created_by, p.updated_by, p.deleted_by "
				+ " FROM products p "
//				+ " join suppliers sup on p.supplierId = sup.supplierId "
//				+ " join strap s on p.strapId = s.strapId "
//				+ " join face f on f.faceId = p.faceId join machine m on m.machineId = p.machineId "
				+ " WHERE (p.del_flag != 1 or p.del_flag is null) AND p.status = 1 ");
		// Theo thương hiệu
		if (!FunctionCommon.isEmpty(dataSelected.getSupplierId())) {
			sql.append(" AND ( ");
			sql.append(" p.supplierId = ? ");
			sql.append("  ");
			params.add(dataSelected.getSupplierId());
		}
		// Theo khoangr gia +-5tr
		if (dataSelected.getUnitPrice() != null) {
			sql.append(" OR ( p.unitPrice BETWEEN ? AND ? )");
			params.add(dataSelected.getUnitPrice() - priceRange);
			params.add(dataSelected.getUnitPrice() + priceRange);
		}
		sql.append(" ) ");
		sql.append(" ORDER BY RAND() LIMIT 0, 12 ) sb ");
		sql.append(" ORDER BY sb.unitPrice ");
		
		List<productEntity> lst = (List<productEntity>) cmd.getListObjByParams(sql, params, productEntity.class);
		if (null == lst || lst.size() == 0) {
			return null;
		}
		return lst;
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
