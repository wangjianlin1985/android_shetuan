package com.mobileserver.dao;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mobileserver.domain.Huodong;
import com.mobileserver.util.DB;

public class HuodongDAO {

	public List<Huodong> QueryHuodong(String huodongName,Timestamp huodongTime,String shetuanObj) {
		List<Huodong> huodongList = new ArrayList<Huodong>();
		DB db = new DB();
		String sql = "select * from Huodong where 1=1";
		if (!huodongName.equals(""))
			sql += " and huodongName like '%" + huodongName + "%'";
		if(huodongTime!=null)
			sql += " and huodongTime='" + huodongTime + "'";
		if (!shetuanObj.equals(""))
			sql += " and shetuanObj = '" + shetuanObj + "'";
		try {
			ResultSet rs = db.executeQuery(sql);
			while (rs.next()) {
				Huodong huodong = new Huodong();
				huodong.setHuodongId(rs.getInt("huodongId"));
				huodong.setHuodongName(rs.getString("huodongName"));
				huodong.setHuodongDesc(rs.getString("huodongDesc"));
				huodong.setHuodongTime(rs.getTimestamp("huodongTime"));
				huodong.setShetuanObj(rs.getString("shetuanObj"));
				huodong.setHuodongMemo(rs.getString("huodongMemo"));
				huodongList.add(huodong);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		return huodongList;
	}
	/* 传入社团活动对象，进行社团活动的添加业务 */
	public String AddHuodong(Huodong huodong) {
		DB db = new DB();
		String result = "";
		try {
			/* 构建sql执行插入新社团活动 */
			String sqlString = "insert into Huodong(huodongName,huodongDesc,huodongTime,shetuanObj,huodongMemo) values (";
			sqlString += "'" + huodong.getHuodongName() + "',";
			sqlString += "'" + huodong.getHuodongDesc() + "',";
			sqlString += "'" + huodong.getHuodongTime() + "',";
			sqlString += "'" + huodong.getShetuanObj() + "',";
			sqlString += "'" + huodong.getHuodongMemo() + "'";
			sqlString += ")";
			db.executeUpdate(sqlString);
			result = "社团活动添加成功!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "社团活动添加失败";
		} finally {
			db.all_close();
		}
		return result;
	}
	/* 删除社团活动 */
	public String DeleteHuodong(int huodongId) {
		DB db = new DB();
		String result = "";
		try {
			String sqlString = "delete from Huodong where huodongId=" + huodongId;
			db.executeUpdate(sqlString);
			result = "社团活动删除成功!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "社团活动删除失败";
		} finally {
			db.all_close();
		}
		return result;
	}

	/* 根据活动id获取到社团活动 */
	public Huodong GetHuodong(int huodongId) {
		Huodong huodong = null;
		DB db = new DB();
		String sql = "select * from Huodong where huodongId=" + huodongId;
		try {
			ResultSet rs = db.executeQuery(sql);
			if (rs.next()) {
				huodong = new Huodong();
				huodong.setHuodongId(rs.getInt("huodongId"));
				huodong.setHuodongName(rs.getString("huodongName"));
				huodong.setHuodongDesc(rs.getString("huodongDesc"));
				huodong.setHuodongTime(rs.getTimestamp("huodongTime"));
				huodong.setShetuanObj(rs.getString("shetuanObj"));
				huodong.setHuodongMemo(rs.getString("huodongMemo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		return huodong;
	}
	/* 更新社团活动 */
	public String UpdateHuodong(Huodong huodong) {
		DB db = new DB();
		String result = "";
		try {
			String sql = "update Huodong set ";
			sql += "huodongName='" + huodong.getHuodongName() + "',";
			sql += "huodongDesc='" + huodong.getHuodongDesc() + "',";
			sql += "huodongTime='" + huodong.getHuodongTime() + "',";
			sql += "shetuanObj='" + huodong.getShetuanObj() + "',";
			sql += "huodongMemo='" + huodong.getHuodongMemo() + "'";
			sql += " where huodongId=" + huodong.getHuodongId();
			db.executeUpdate(sql);
			result = "社团活动更新成功!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "社团活动更新失败";
		} finally {
			db.all_close();
		}
		return result;
	}
}
