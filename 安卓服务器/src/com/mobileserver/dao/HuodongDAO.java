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
	/* �������Ż���󣬽������Ż�����ҵ�� */
	public String AddHuodong(Huodong huodong) {
		DB db = new DB();
		String result = "";
		try {
			/* ����sqlִ�в��������Ż */
			String sqlString = "insert into Huodong(huodongName,huodongDesc,huodongTime,shetuanObj,huodongMemo) values (";
			sqlString += "'" + huodong.getHuodongName() + "',";
			sqlString += "'" + huodong.getHuodongDesc() + "',";
			sqlString += "'" + huodong.getHuodongTime() + "',";
			sqlString += "'" + huodong.getShetuanObj() + "',";
			sqlString += "'" + huodong.getHuodongMemo() + "'";
			sqlString += ")";
			db.executeUpdate(sqlString);
			result = "���Ż��ӳɹ�!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "���Ż���ʧ��";
		} finally {
			db.all_close();
		}
		return result;
	}
	/* ɾ�����Ż */
	public String DeleteHuodong(int huodongId) {
		DB db = new DB();
		String result = "";
		try {
			String sqlString = "delete from Huodong where huodongId=" + huodongId;
			db.executeUpdate(sqlString);
			result = "���Żɾ���ɹ�!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "���Żɾ��ʧ��";
		} finally {
			db.all_close();
		}
		return result;
	}

	/* ���ݻid��ȡ�����Ż */
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
	/* �������Ż */
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
			result = "���Ż���³ɹ�!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "���Ż����ʧ��";
		} finally {
			db.all_close();
		}
		return result;
	}
}
