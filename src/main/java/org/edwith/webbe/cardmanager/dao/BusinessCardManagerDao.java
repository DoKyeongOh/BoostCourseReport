package org.edwith.webbe.cardmanager.dao;

import org.edwith.webbe.cardmanager.Utils.DataBaseUtil;
import org.edwith.webbe.cardmanager.dto.BusinessCard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BusinessCardManagerDao {
	private String addr = "";
	private String dbName = "";
	private String userId = "";
	private String userPw = "";
	
	public BusinessCardManagerDao () {
		addr = "127.0.0.1:3306";
		dbName = "odk";
		userId = "root";
		userPw = "1234";
	}
	
    public List<BusinessCard> searchBusinessCard(String keyword){
	// 구현하세요.
		DataBaseUtil mysqlUtil = new DataBaseUtil();
    	if (!mysqlUtil.Connect(addr, dbName, userId, userPw)) return null;
    	
    	List<BusinessCard> cardList = new ArrayList<BusinessCard>();
    	String name = "";
    	String phone = "";
    	String companyName = "";
    	String date = "";
    	
    	String sql = "SELECT * FROM businesscard WHERE name LIKE ?";
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try {
        	ps = mysqlUtil.getStatement(ps, sql);
    		ps.setString(1, "%"+keyword+"%");
    		rs = ps.executeQuery();
    		while (rs.next()) {
    			name = rs.getString("name");
    			phone = rs.getString("phone");
    			companyName = rs.getString("companyName");
    		}
    		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    	
    	return null;
    }

    public BusinessCard addBusinessCard(BusinessCard businessCard){
	// 구현하세요.
    	return null;
    }
}
