package com.manhdn.dao;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.manhdn.AppConstants;
import com.manhdn.FunctionCommon;
import com.manhdn.database.CommonDatabase;
import com.manhdn.entity.*;

@Repository
@Transactional
public class productDAO {
	private Logger logger = Logger.getLogger(productDAO.class);
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
		sql.append("SELECT * FROM `products` p WHERE (p.del_flag IS NULL OR p.del_flag != 1) ORDER BY p.id ");
		List<Object> params = new ArrayList<>();

//		sql.append(" WHERE ").append(cmd.sql_SelectLike("productName", "ÔGi"));
		List<productEntity> lst = (List<productEntity>) cmd.getListObjByParams(sql, params, productEntity.class);
//		productEntity pr = lst.get(0);
//		productEntity e = getDetail("a2");
		logger.info("Params: " + params + "Result: " + lst);
		return lst;
	}

	public List<productEntity> findDataList(Long userId, productEntity dataSearch) {

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT DISTINCT  p.id, p.productId, p.productName, p.quantity, p.supplierId, p.unitPrice, "
				+ " p.gender, p.status, p.strapId, "
				+ " p.faceId, p.machineId, p.material, p.otherFunc, p.image, p.description, p.del_flag, "
				+ " p.created_date, p.updated_date, p.deleted_date, " + " p.created_by, p.updated_by, p.deleted_by "
				+ " FROM products p join suppliers sup on p.supplierId = sup.supplierId "
				+ " join strap s on p.strapId = s.strapId "
				+ " join face f on f.faceId = p.faceId join machine m on m.machineId = p.machineId "
				+ " WHERE (p.del_flag != 1 or p.del_flag is null) ");
		List<Object> params = new ArrayList<>();
		if(!FunctionCommon.isEmpty(dataSearch.getSupplierId())) {
			sql.append(" and sup.supplierId = ? ");
			params.add(dataSearch.getSupplierId());
		}
		if(!FunctionCommon.isEmpty(dataSearch.getGender())) {
			sql.append(" and p.gender = ? ");
			params.add(dataSearch.getGender());
		}
		if(null!=dataSearch.getStatus()) {
			sql.append(" and p.status = ? ");
			params.add(dataSearch.getStatus());
		}
		List<productEntity> result = new ArrayList<productEntity>();
		result = (List<productEntity>) cmd.getListObjByParams(sql, params, productEntity.class);
		fillDataInDataList(result);
//		if (result.size() > 0) {
//			for (productEntity p : result) {
//				if (null != p) {
//					// Them supplier
//					supplierEntity sup = new supplierEntity();
//					supplierDAO supDAO = new supplierDAO();
//					sup = supDAO.findSupplierById(p.getSupplierId());
//					p.setSupplier(sup);
//					// Them face
//					faceEntity face = new faceEntity();
//					faceDAO faceDAO = new faceDAO();
//					face = faceDAO.findFaceById(p.getFaceId());
//					p.setFace(face);
//					// them machine
//					machineEntity machine = new machineEntity();
//					machineDAO machineDAO = new machineDAO();
//					machine = machineDAO.findMachineById(p.getMachineId());
//					p.setMachine(machine);
//				}
//			}
//		}
		logger.info("Params: " + params + "Result: " + result);
		return result;
	}

	public List<productEntity> findDataList(Long userId, Map<String, List<String>> mapSearch, Integer page,
			Integer pageSize) {

		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append(" SELECT DISTINCT  p.id, p.productId, p.productName, p.quantity, p.supplierId, p.unitPrice, "
				+ " p.gender, p.status, p.strapId, "
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
				// TIm theo tu khoa
				if (key.equals(AppConstants.MAP_SEARCH_STRING)) {
					if (!FunctionCommon.isEmpty(mapSearch.get(key))) {
						String sqlLike = FunctionCommon.generateSqlLike("p.productName", mapSearch.get(key).get(0));
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
		fillDataInDataList(result);
//		if (result.size() > 0) {
//			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//			String date = dtf.format(LocalDateTime.now());
//			for (productEntity p : result) {
//				if (null != p) {
//					if (p.getDiscount() != null && p.getDiscount() > 0 && p.getEndDate_discount() != null) {
//
//						try {
//							Date date_now = new SimpleDateFormat("yyyy-MM-dd").parse(date);
//							Date end_discount = new SimpleDateFormat("yyyy-MM-dd").parse(p.getEndDate_discount());
//							Integer i = date_now.compareTo(end_discount);
//							if (i > 0) {
//								p.setDiscount(0D);
//							}
//						} catch (Exception e) {
//							// TODO: handle exception
//							e.printStackTrace();
//						}
//					}
//					// Them supplier
//					supplierEntity sup = new supplierEntity();
//					supplierDAO supDAO = new supplierDAO();
//					sup = supDAO.findSupplierById(p.getSupplierId());
//					p.setSupplier(sup);
//					// Them face
//					faceEntity face = new faceEntity();
//					faceDAO faceDAO = new faceDAO();
//					face = faceDAO.findFaceById(p.getFaceId());
//					p.setFace(face);
//					// them machine
//					machineEntity machine = new machineEntity();
//					machineDAO machineDAO = new machineDAO();
//					machine = machineDAO.findMachineById(p.getMachineId());
//					p.setMachine(machine);
//				}
//			}
//		}
		logger.info("Params: " + params + "Result: " + result);
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
//		sql.append(" and (p.status != 0 or p.status is null) ");
		params.add(productId);
		List<productEntity> lst = (List<productEntity>) cmd.getListObjByParams(sql, params, productEntity.class);
		if (null == lst || lst.size() == 0) {
			logger.info("Params: " + params + "Result: " + result);
			return null;
		}
		fillDataInDataList(lst);
		result = lst.get(0);
//		if (null != result) {
//			// Them supplier
//			supplierEntity sup = new supplierEntity();
//			supplierDAO supDAO = new supplierDAO();
//			sup = supDAO.findSupplierById(result.getSupplierId());
//			result.setSupplier(sup);
//			// Them face
//			faceEntity face = new faceEntity();
//			faceDAO faceDAO = new faceDAO();
//			face = faceDAO.findFaceById(result.getFaceId());
//			result.setFace(face);
//			// them machine
//			machineEntity machine = new machineEntity();
//			machineDAO machineDAO = new machineDAO();
//			machine = machineDAO.findMachineById(result.getMachineId());
//			result.setMachine(machine);
//		}
		logger.info("Params: " + params + "Result: " + result);
		return result;
	}

	/**
	 * them san pham
	 * 
	 * @param userId
	 * @param product
	 * @return
	 */
	public boolean insertOrUpdate(Long userId, productEntity product) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		List<productEntity> listData = new ArrayList<productEntity>();
//		if (userId != null) {
//			product.setCreated_by(userId);
//		}
		if (userId == null || product == null) {
			logger.error("Id null: " + userId);
			return false;
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateNow = dtf.format(LocalDateTime.now());
		// Insert or update Order
		if (product.getProductId() == null) {
			String productId = AppConstants.ID_PRODUCT + (cmd.getMaxId("products", "id") + 1);
			product.setProductId(productId);
		}
		sql.append("INSERT INTO `products` ( productId, productName, " + " quantity, supplierId, unitPrice,  "
				+ " gender,  status, strapId, faceId, machineId, material, "
				+ " otherFunc, image, description, del_flag, created_date,"
				+ " updated_date, created_by, updated_by ) VALUES ");
		sql.append(" ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ");
		sql.append(" ON DUPLICATE KEY UPDATE " 
				+ " productName = VALUES(productName), "
				+ " quantity = VALUES(quantity), " 
				+ " supplierId = VALUES(supplierId), "
				+ " unitPrice = VALUES(unitPrice), "
				+ " gender = VALUES(gender), "
				+ " status = VALUES(status), " 
				+ " strapId = VALUES(strapId), "
				+ " faceId = VALUES(faceId), "
				+ " machineId = VALUES(machineId), " 
				+ " material = VALUES(material), "
				+ " otherFunc = VALUES(otherFunc), " 
				+ " image = VALUES(image), " 
				+ " description = VALUES(description), "
				+ " del_flag = VALUES(del_flag), "

				+ " updated_date = VALUES(updated_date), " + " updated_by = VALUES(updated_by) " + "");
		params.add(product.getProductId());
		params.add(product.getProductName());
		params.add(product.getQuantity());
		params.add(product.getSupplierId());
		params.add(product.getUnitPrice());
		params.add(product.getGender());
		params.add(product.getStatus());
		params.add(product.getStrapId());
		params.add(product.getFaceId());
		params.add(product.getMachineId());
		params.add(product.getMaterial());

		params.add(product.getOtherFunc() != null ? product.getOtherFunc() : "");
		params.add(product.getImage());
		params.add(product.getDescription() != null ? product.getDescription() : "");
		params.add(product.getDel_flag() != null ? product.getDel_flag() : "0");
		params.add(dateNow);
		params.add(dateNow);
		params.add(userId);
		params.add(userId);
		cmd = new CommonDatabase();
		boolean r = cmd.insertOrUpdateDataBase(sql, params);
		logger.info("Params: " + params + "Result: " + r);
		
		// Update discount
		discountDAO disD = new discountDAO();
		List<discountEntity> lstDiscount = disD.findDataList(0L, null);
		if(!FunctionCommon.isEmpty(product.getStrLstDiscount())) {
			
			
			product.setLstDiscount(lstDiscount);
			
			disD.insertOrUpdateProductDiscount(userId, product);
		}
		return r;
	}

	public List<productEntity> quickSearch(Long userId, String strSearch) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		sql.append("SELECT * FROM products p "
				+" WHERE ");
//		+ " p.productName like ? ");
		sql.append(FunctionCommon.generateSqlLike("p.productName", strSearch));
		sql.append(" AND (p.status != 0 or p.status is null) AND (p.del_flag is null OR p.del_flag !=1) ");
		
		params.add("%" + strSearch + "%");
		List<productEntity> lst = (List<productEntity>) cmd.getListObjByParams(sql, params, productEntity.class);
		if (null == lst || lst.size() == 0) {
			logger.info("Params: " + params + "Result: " + lst);
			return null;
		}
		fillDataInDataList(lst);
		logger.info("Params: " + params + "Result: " + lst);
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
		List<Object> params = new ArrayList<Object>();
		sql.append("SELECT * FROM products p " 
				+ " WHERE p.productId IN ( "
				+ " SELECT pd.productId FROM product_discount pd WHERE pd.discountId IN ( "
				+ " SELECT d.discountId FROM discount d WHERE d.endDate > SYSDATE() "
				+ ") "
				+ ") ");
		sql.append(" AND (p.status != 0 or p.status is null) ");
		sql.append(" AND (p.del_flag != 1 or p.del_flag is null) ");
		sql.append(" ORDER BY p.unitPrice ");
		sql.append(" LIMIT 0, 24 ");
		List<productEntity> lst = (List<productEntity>) cmd.getListObjByParams(sql, params, productEntity.class);
		if (null == lst || lst.size() == 0) {
			logger.info("Params: " + params + "Result: " + lst);
			return null;
		}
		fillDataInDataList(lst);
		logger.info("Params: " + params + "Result: " + lst);
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
			logger.info("Params: " + params + "Result: " + lst);
			return null;
		}
		fillDataInDataList(lst);
		logger.info("Params: " + params + "Result: " + lst);
		return lst;
	}

	/**
	 * Tìm kiếm sản phẩm liên quan - theo thương hiệu hoặc theo khoảng giá +-5 tr
	 * 
	 * @param dataSelected
	 * @return
	 */
	public List<productEntity> findListSuggest(productEntity dataSelected) {
		if (dataSelected == null) {
			logger.error("data null: " + dataSelected);
			return null;
		}
		StringBuilder sql = new StringBuilder();
		productEntity result = new productEntity();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM ( "
				+ " SELECT  p.id, p.productId, p.productName, p.quantity, p.supplierId, p.unitPrice, "
				+ " p.gender, p.status, p.strapId, "
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
			logger.info("Params: " + params + "Result: " + lst);
			return null;
		}
		fillDataInDataList(lst);
		logger.info("Params: " + params + "Result: " + lst);
		return lst;
	}

	/**
	 * them du lieu cho danh sach san pham
	 * 
	 * @param lst
	 */
	public void fillDataInDataList(List<productEntity> lst) {
		if (lst.size() > 0) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String date = dtf.format(LocalDateTime.now());
			for (productEntity p : lst) {
				if (null != p) {
					try {
						Date date_now = new SimpleDateFormat("yyyy-MM-dd").parse(date);
//						Integer i = date_now.compareTo(end_discount);
					} catch (Exception e) {
						// TODO: handle exception
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
				// them strap
				strapEntity strap = new strapEntity();
				strapDAO strapD = new strapDAO();
				strap = strapD.findstrapById(p.getStrapId());
				p.setStrap(strap);
				// Tim danh sach giam gia
				List<discountEntity> lstDiscount = new ArrayList<discountEntity>();
				discountDAO disD = new discountDAO();
				lstDiscount = disD.findListDiscountByProductId(p.getProductId());
				p.setLstDiscount(lstDiscount);
				// Them string giam gia
				StringBuilder str = new StringBuilder();
				if(!FunctionCommon.isEmpty(lstDiscount)) {
					for (discountEntity d : lstDiscount) {
						str.append(d.getDiscountId()).append(", ");
					}
					p.setStrLstDiscount(str.toString());
				}
			}
		}
	}

	public boolean delete(Long userId, String productId) {
		if(null == userId) {
			logger.error("Id null ");
			return false;
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateNow = dtf.format(LocalDateTime.now());
		boolean  result = false;
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE `products` p SET p.del_flag = 1, "
				+ " p.updated_by = ? , p.updated_date = ? "
				+ " WHERE p.productId = ? " );
		
		params.add(userId);
		params.add(dateNow);
		params.add(productId);
		result = cmd.insertOrUpdateDataBase(sql, params);
		logger.info("Params: " + params + "Result: " + result);
		return result;
	}
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
//}
//}
