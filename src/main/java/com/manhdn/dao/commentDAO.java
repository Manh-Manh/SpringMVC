package com.manhdn.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.manhdn.AppConstants;
import com.manhdn.FunctionCommon;
import com.manhdn.database.CommonDatabase;
import com.manhdn.entity.commentEntity;
import com.manhdn.entity.productEntity;
import com.manhdn.entity.userEntity;

@Repository
public class commentDAO {
	private CommonDatabase cmd;
	private Logger logger = Logger.getLogger(commentDAO.class);
	public commentDAO() {
		cmd = new CommonDatabase();
	}

	public commentEntity findById(String cmtId) {
		if (FunctionCommon.isEmpty(cmtId)) {
			return null;
		}
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM comment WHERE (status is null or status !=0) and commentId = ? ");
		sql.append(" ORDER BY created_date asc ");
		List<Object> params = new ArrayList<>();
		params.add(cmtId);
		List<commentEntity> lst = (List<commentEntity>) cmd.getListObjByParams(sql, params, commentEntity.class);
		if (lst != null) {

			logger.info("Params: " + params + "Result: " + lst);
			return lst.get(0);
		}
		logger.info("Params: " + params + "Result: " + lst);
		return null;
	}
	
	public List<commentEntity> findListCmtByParentId(String cmtId) {
		if (FunctionCommon.isEmpty(cmtId)) {
			logger.error("Id null: " + cmtId);
			return null;
		}
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM comment WHERE (status is null or status !=0) and parentId = ? ");
		sql.append(" ORDER BY created_date asc ");
		List<Object> params = new ArrayList<>();
		params.add(cmtId);
		List<commentEntity> lst = (List<commentEntity>) cmd.getListObjByParams(sql, params, commentEntity.class);
		if (lst != null) {
			for(commentEntity c: lst) {
				userDAO uDAO =new userDAO();
				userEntity u = uDAO.findUserById(c.getUserId());
				c.setUser(u);
			}
			logger.info("Params: " + params + "Result: " + lst);
			return lst;
		}
		logger.info("Params: " + params + "Result: " + lst);
		return null;
	}
	public void getListCommentReply(commentEntity cmt ) {
		cmt.setListCmtReply(this.findListCmtByParentId(cmt.getCommentId()));
		if(FunctionCommon.isEmpty(cmt.getListCmtReply())) {
			logger.error(cmt);
			return;
		}
		for(commentEntity c : cmt.getListCmtReply()) {
			List<commentEntity> listChill = this.findListCmtByParentId(c.getCommentId());
			c.setListCmtReply(listChill);
			this.getListCommentReply(c);
		}
	}
	public List<commentEntity> getAllCommetByProductId(String productId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM comment WHERE (status is null or status !=0) "
				+ " AND (parentId is null OR parentId = '' ) "
				+ " AND productId = ? ");
		sql.append(" ORDER BY created_date asc ");
		List<Object> params = new ArrayList<>();
		params.add(productId);
		List<commentEntity> lst = (List<commentEntity>) cmd.getListObjByParams(sql, params, commentEntity.class);
		if(lst!=null && lst.size()>0) {
			for(commentEntity c :lst) {
				// get user info
				userDAO uDAO =new userDAO();
				userEntity u = uDAO.findUserById(c.getUserId());
				c.setUser(u);
				// get list comment reply
				this.getListCommentReply(c);
			}
		}
		logger.info("Params: " + params + "Result: " + lst);
		
		return lst;
	}
	public boolean insertOrUpdate(Long userId, commentEntity cmt) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		if (userId == null || cmt == null) {
			logger.error("Id null: " + userId + cmt);
			return false;
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String dateNow = dtf.format(LocalDateTime.now());
		// Insert or update Order
		if(cmt.getCommentId() == null) {
			String cmtID = AppConstants.ID_COMMENT + (cmd.getMaxId("comment", "id") + 1);
			cmt.setCommentId(cmtID);
		}
		sql.append("INSERT INTO `comment` ( commentId, userId, productId, parentId, "
				+ "status, content, created_date, updated_date, "
				+ " created_by, updated_by ) VALUES ");
		
		sql.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");		
		sql.append("ON DUPLICATE KEY UPDATE "
				+ " status = VALUES(status), "
				+ " content = VALUES(content), "
				+ " updated_date = VALUES(updated_date), "
				+ " updated_by = VALUES(updated_by) "
				+ "");
		params.add(cmt.getCommentId());
		params.add(userId);
		params.add(cmt.getProductId());
		params.add(cmt.getParentId() != null ? cmt.getParentId() : "");
		params.add(cmt.getStatus() != null ? cmt.getStatus() : "1");
		params.add(cmt.getContent());
		params.add(dateNow);
		params.add(dateNow);
		params.add(userId);
		params.add(userId);
		boolean result = cmd.insertOrUpdateDataBase(sql, params);
		logger.info("Params: " + params + "Result: " + result);
		return result;
	}
}
