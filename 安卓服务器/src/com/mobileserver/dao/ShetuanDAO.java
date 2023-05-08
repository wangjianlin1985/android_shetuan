package com.mobileserver.dao;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mobileserver.domain.Shetuan;
import com.mobileserver.util.DB;

public class ShetuanDAO {

	public List<Shetuan> QueryShetuan(String stUserName,String shetuanName,Timestamp bornDate,String fuzeren) {
		List<Shetuan> shetuanList = new ArrayList<Shetuan>();
		DB db = new DB();
		String sql = "select * from Shetuan where 1=1";
		if (!stUserName.equals(""))
			sql += " and stUserName like '%" + stUserName + "%'";
		if (!shetuanName.equals(""))
			sql += " and shetuanName like '%" + shetuanName + "%'";
		if(bornDate!=null)
			sql += " and bornDate='" + bornDate + "'";
		if (!fuzeren.equals(""))
			sql += " and fuzeren like '%" + fuzeren + "%'";
		try {
			ResultSet rs = db.executeQuery(sql);
			while (rs.next()) {
				Shetuan shetuan = new Shetuan();
				shetuan.setStUserName(rs.getString("stUserName"));
				shetuan.setPassword(rs.getString("password"));
				shetuan.setShetuanName(rs.getString("shetuanName"));
				shetuan.setShetuanPhoto(rs.getString("shetuanPhoto"));
				shetuan.setShetuanDesc(rs.getString("shetuanDesc"));
				shetuan.setBornDate(rs.getTimestamp("bornDate"));
				shetuan.setFuzeren(rs.getString("fuzeren"));
				shetuan.setTelephone(rs.getString("telephone"));
				shetuan.setShetuanMemo(rs.getString("shetuanMemo"));
				shetuanList.add(shetuan);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		return shetuanList;
	}
	/* 传入社团对象，进行社团的添加业务 */
	public String AddShetuan(Shetuan shetuan) {
		DB db = new DB();
		String result = "";
		try {
			/* 构建sql执行插入新社团 */
			String sqlString = "insert into Shetuan(stUserName,password,shetuanName,shetuanPhoto,shetuanDesc,bornDate,fuzeren,telephone,shetuanMemo) values (";
			sqlString += "'" + shetuan.getStUserName() + "',";
			sqlString += "'" + shetuan.getPassword() + "',";
			sqlString += "'" + shetuan.getShetuanName() + "',";
			sqlString += "'" + shetuan.getShetuanPhoto() + "',";
			sqlString += "'" + shetuan.getShetuanDesc() + "',";
			sqlString += "'" + shetuan.getBornDate() + "',";
			sqlString += "'" + shetuan.getFuzeren() + "',";
			sqlString += "'" + shetuan.getTelephone() + "',";
			sqlString += "'" + shetuan.getShetuanMemo() + "'";
			sqlString += ")";
			db.executeUpdate(sqlString);
			result = "社团添加成功!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "社团添加失败";
		} finally {
			db.all_close();
		}
		return result;
	}
	/* 删除社团 */
	public String DeleteShetuan(String stUserName) {
		DB db = new DB();
		String result = "";
		try {
			String sqlString = "delete from Shetuan where stUserName='" + stUserName + "'";
			db.executeUpdate(sqlString);
			result = "社团删除成功!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "社团删除失败";
		} finally {
			db.all_close();
		}
		return result;
	}

	/* 根据负责人账号获取到社团 */
	public Shetuan GetShetuan(String stUserName) {
		Shetuan shetuan = null;
		DB db = new DB();
		String sql = "select * from Shetuan where stUserName='" + stUserName + "'";
		try {
			ResultSet rs = db.executeQuery(sql);
			if (rs.next()) {
				shetuan = new Shetuan();
				shetuan.setStUserName(rs.getString("stUserName"));
				shetuan.setPassword(rs.getString("password"));
				shetuan.setShetuanName(rs.getString("shetuanName"));
				shetuan.setShetuanPhoto(rs.getString("shetuanPhoto"));
				shetuan.setShetuanDesc(rs.getString("shetuanDesc"));
				shetuan.setBornDate(rs.getTimestamp("bornDate"));
				shetuan.setFuzeren(rs.getString("fuzeren"));
				shetuan.setTelephone(rs.getString("telephone"));
				shetuan.setShetuanMemo(rs.getString("shetuanMemo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		return shetuan;
	}
	/* 更新社团 */
	public String UpdateShetuan(Shetuan shetuan) {
		DB db = new DB();
		String result = "";
		try {
			String sql = "update Shetuan set ";
			sql += "password='" + shetuan.getPassword() + "',";
			sql += "shetuanName='" + shetuan.getShetuanName() + "',";
			sql += "shetuanPhoto='" + shetuan.getShetuanPhoto() + "',";
			sql += "shetuanDesc='" + shetuan.getShetuanDesc() + "',";
			sql += "bornDate='" + shetuan.getBornDate() + "',";
			sql += "fuzeren='" + shetuan.getFuzeren() + "',";
			sql += "telephone='" + shetuan.getTelephone() + "',";
			sql += "shetuanMemo='" + shetuan.getShetuanMemo() + "'";
			sql += " where stUserName='" + shetuan.getStUserName() + "'";
			db.executeUpdate(sql);
			result = "社团更新成功!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "社团更新失败";
		} finally {
			db.all_close();
		}
		return result;
	}
}
