package com.manhdn;

import com.manhdn.database.CommonDatabase;

public class AppConstants {
	public static final int PAGE_SIZE = 6;
	// OS - order status Long
	public static final Long OS_NO_ORDER = 1L;
	public static final Long OS_ORDERED = 2L;
	public static final Long OS_ORDER_COMPLETE = 3L;
	public static final Long OS_ORDER_CANCEL = 4L;
	// table name
	public static String SUP = "supplier";
	public static String FACE = "face";
	public static String MACHINE = "machine";
	public static String STRAP = "strap";
	public static String PRICE = "price";
	// status
	public static final Long STATUS_ACTIVE = 1L; // user, product, supplier,...
	public static final Long STATUS_BLOCK = 0L;
	// status string
	public static String ACTIVE = "Hoạt động";
	public static String BLOCK = "Không hoạt động";
	public static String ODER_NOT_PROCESS = "Chưa xử lý";
	public static String ODER_PROCESS = "Đã xử lý";
	public static String ODER_CANCEL = "Đã hủy";
	public static String strSPEC = "áàảãạăắằẳẵặâấầẩẫậđéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵ";
//			+ "ÁÀẢÃẠĂẮẰẲẴẶÂẤẦẨẪẬĐÉÈẺẼẸÊẾỀỂỄỆÍÌỈĨỊÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢÚÙỦŨỤƯỨỪỬỮỰÝỲỶỸỴ";
	public static String strREPL = "aaaaaaaaaaaaaaaaadeeeeeeeeeeeiiiiiooooooooooooooooouuuuuuuuuuuyyyyy";
//			+ "AAAAAAAAAAAAAAAAADEEEEEEEEEEEIIIIIOOOOOOOOOOOOOOOOOUUUUUUUUUUUYYYYY";
	public static String MAP_SEARCH_SUPPLIER_ID = "supplierId";
	public static String MAP_SEARCH_MACHINE_ID = "machineId";
	public static String MAP_SEARCH_STRAP_ID = "strapId";
	public static String MAP_SEARCH_AMOUNT = "amount";
	public static String MAP_SEARCH_PRODUCT_ID = "productId";
	public static String MAP_SEARCH_CART_QUANTITY = "cartQuantity";
	public static final String MAP_SEARCH_PAGE = "page";
	public static String MAP_SEARCH_STRING = "searchString"; // string quickSearch
	
	// Session
	public static String SESSION_CART = "cart";
	public static String SESSION_USER = "user";
	public static String SESSION_MESSAGE = "message";
	
	
	// Sinh ID
	public static String ID_PRODUCT = "PRO";
	public static String ID_MACHINE = "M";
	public static String ID_FACE = "F";
	public static String ID_SUPPLIER = "SUP";
	public static String ID_ODER = "ORDER";
	public static String ID_USER = "user";
	// ORDER STATUS
	
	
}
