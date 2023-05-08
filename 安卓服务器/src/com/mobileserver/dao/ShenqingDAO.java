package com.mobileserver.dao;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mobileserver.domain.Shenqing;
import com.mobileserver.util.DB;

public class ShenqingDAO {

	public List<Shenqing> QueryShenqing(String shentuanObj,String name,String xuehao,String userObj,String sqTime,String shenHeState) {
		List<Shenqing> shenqingList = new ArrayList<Shenqing>();
		DB db = new DB();
		String sql = "select * from Shenqing where 1=1";
		if (!shentuanObj.equals(""))
			sql += " and shentuanObj = '" + shentuanObj + "'";
		if (!name.equals(""))
			sql += " and name like '%" + name + "%'";
		if (!xuehao.equals(""))
			sql += " and xuehao like '%" + xuehao + "%'";
		if (!userObj.equals(""))
			sql += " and userObj = '" + userObj + "'";
		if (!sqTime.equals(""))
			sql += " and sqTime like '%" + sqTime + "%'";
		if (!shenHeState.equals(""))
			sql += " and shenHeState like '%" + shenHeState + "%'";
		try {
			ResultSet rs = db.executeQuery(sql);
			while (rs.next()) {
				Shenqing shenqing = new Shenqing();
				shenqing.setShenqingId(rs.getInt("shenqingId"));
				shenqing.setShentuanObj(rs.getString("shentuanObj"));
				shenqing.setName(rs.getString("name"));
				shenqing.setXuehao(rs.getString("xuehao"));
				shenqing.setZysj(rs.getString("zysj"));
				shenqing.setRhyy(rs.getString("rhyy"));
				shenqing.setUserObj(rs.getString("userObj"));
				shenqing.setSqTime(rs.getString("sqTime"));
				shenqing.setShenHeState(rs.getString("shenHeState"));
				shenqing.setShenHeResult(rs.getString("shenHeResult"));
				shenqingList.add(shenqing);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		return shenqingList;
	}
	/* ��������������󣬽���������������ҵ�� */
	public String AddShenqing(Shenqing shenqing) {
		DB db = new DB();
		String result = "";
		try {
			/* ����sqlִ�в������������� */
			String sqlString = "insert into Shenqing(shentuanObj,name,xuehao,zysj,rhyy,userObj,sqTime,shenHeState,shenHeResult) values (";
			sqlString += "'" + shenqing.getShentuanObj() + "',";
			sqlString += "'" + shenqing.getName() + "',";
			sqlString += "'" + shenqing.getXuehao() + "',";
			sqlString += "'" + shenqing.getZysj() + "',";
			sqlString += "'" + shenqing.getRhyy() + "',";
			sqlString += "'" + shenqing.getUserObj() + "',";
			sqlString += "'" + shenqing.getSqTime() + "',";
			sqlString += "'" + shenqing.getShenHeState() + "',";
			sqlString += "'" + shenqing.getShenHeResult() + "'";
			sqlString += ")";
			db.executeUpdate(sqlString);
			result = "����������ӳɹ�!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "�����������ʧ��";
		} finally {
			db.all_close();
		}
		return result;
	}
	/* ɾ���������� */
	public String DeleteShenqing(int shenqingId) {
		DB db = new DB();
		String result = "";
		try {
			String sqlString = "delete from Shenqing where shenqingId=" + shenqingId;
			db.executeUpdate(sqlString);
			result = "��������ɾ���ɹ�!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "��������ɾ��ʧ��";
		} finally {
			db.all_close();
		}
		return result;
	}

	/* ��������id��ȡ���������� */
	public Shenqing GetShenqing(int shenqingId) {
		Shenqing shenqing = null;
		DB db = new DB();
		String sql = "select * from Shenqing where shenqingId=" + shenqingId;
		try {
			ResultSet rs = db.executeQuery(sql);
			if (rs.next()) {
				shenqing = new Shenqing();
				shenqing.setShenqingId(rs.getInt("shenqingId"));
				shenqing.setShentuanObj(rs.getString("shentuanObj"));
				shenqing.setName(rs.getString("name"));
				shenqing.setXuehao(rs.getString("xuehao"));
				shenqing.setZysj(rs.getString("zysj"));
				shenqing.setRhyy(rs.getString("rhyy"));
				shenqing.setUserObj(rs.getString("userObj"));
				shenqing.setSqTime(rs.getString("sqTime"));
				shenqing.setShenHeState(rs.getString("shenHeState"));
				shenqing.setShenHeResult(rs.getString("shenHeResult"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		return shenqing;
	}
	/* ������������ */
	public String UpdateShenqing(Shenqing shenqing) {
		DB db = new DB();
		String result = "";
		try {
			String sql = "update Shenqing set ";
			sql += "shentuanObj='" + shenqing.getShentuanObj() + "',";
			sql += "name='" + shenqing.getName() + "',";
			sql += "xuehao='" + shenqing.getXuehao() + "',";
			sql += "zysj='" + shenqing.getZysj() + "',";
			sql += "rhyy='" + shenqing.getRhyy() + "',";
			sql += "userObj='" + shenqing.getUserObj() + "',";
			sql += "sqTime='" + shenqing.getSqTime() + "',";
			sql += "shenHeState='" + shenqing.getShenHeState() + "',";
			sql += "shenHeResult='" + shenqing.getShenHeResult() + "'";
			sql += " where shenqingId=" + shenqing.getShenqingId();
			db.executeUpdate(sql);
			result = "����������³ɹ�!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "�����������ʧ��";
		} finally {
			db.all_close();
		}
		return result;
	}
}
