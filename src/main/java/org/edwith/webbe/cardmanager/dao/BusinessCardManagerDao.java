package org.edwith.webbe.cardmanager.dao;

import org.edwith.webbe.cardmanager.Utils.DataBaseUtil;
import org.edwith.webbe.cardmanager.dto.BusinessCard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BusinessCardManagerDao {
	private String addr = "";
	private String dbName = "";
	private String userId = "";
	private String userPw = "";
	
	private Integer LIMIT_NAME_LANGTH = 10;
	private Integer LIMIT_PHONE_LANGTH = 30;
	private Integer LIMIT_COMPANY_LANGTH = 20;
	private Integer LIMIT_CREATEDATE_LANGTH = 50;
	
	public BusinessCardManagerDao () {
		addr = "127.0.0.1:3306";
		dbName = "odk";
		userId = "root";
		userPw = "1234";
	}
	
	private boolean checkCardInputFormat(BusinessCard bccard) {
		if (bccard.getName().length() >= LIMIT_NAME_LANGTH) return false;
		if (bccard.getPhone().length() >= LIMIT_PHONE_LANGTH) return false;
		if (bccard.getCompanyName().length() >= LIMIT_COMPANY_LANGTH) return false;
		if (bccard.getCreateDate().length() >= LIMIT_CREATEDATE_LANGTH) return false;
		
		return true;
	}
	
    public List<BusinessCard> searchBusinessCard(String keyword){
	// 구현하세요.
		DataBaseUtil mysqlUtil = new DataBaseUtil();
    	if (!mysqlUtil.Connect(addr, dbName, userId, userPw)) return null;
    	
    	List<BusinessCard> cardList = new ArrayList<BusinessCard>();
    	BusinessCard bc = null;
    	
    	String sql = "SELECT * FROM businesscard WHERE name LIKE ?";
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	
    	try {
        	ps = mysqlUtil.getStatement(ps, sql);
    		ps.setString(1, "%"+keyword+"%");
    		rs = ps.executeQuery();
    		while (rs.next()) {
    			bc = new BusinessCard();
    			bc.setName(rs.getString("name"));
    			bc.setPhone(rs.getString("phone"));
    			bc.setCompanyName(rs.getString("companyName"));
    			bc.setCreateDate(rs.getString("createDate"));
    			cardList.add(bc);
    		}
    		 
    		mysqlUtil.close();
    		return cardList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }

    public BusinessCard addBusinessCard(BusinessCard businessCard){
	// 구현하세요.
    	if (!checkCardInputFormat(businessCard)) return null;
    	
		DataBaseUtil mysqlUtil = new DataBaseUtil();
    	if (!mysqlUtil.Connect(addr, dbName, userId, userPw)) return null;

    	String sql = "INSERT INTO businesscard VALUES (?, ?, ?, ?)";
    	PreparedStatement ps = null;
    	
    	try {
        	ps = mysqlUtil.getStatement(ps, sql);
    		ps.setString(1, businessCard.getName());
    		ps.setString(2, businessCard.getPhone());
    		ps.setString(3, businessCard.getCompanyName());
    		ps.setString(4, businessCard.getCreateDate());
    		ps.executeUpdate();
    		mysqlUtil.close();
    		return businessCard;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
}
