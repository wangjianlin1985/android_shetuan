package com.mobileserver.dao;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mobileserver.domain.ClassInfo;
import com.mobileserver.util.DB;

public class ClassInfoDAO {

	public List<ClassInfo> QueryClassInfo(String classNo,String className,String collegeName,String specialName) {
		List<ClassInfo> classInfoList = new ArrayList<ClassInfo>();
		DB db = new DB();
		String sql = "select * from ClassInfo where 1=1";
		if (!classNo.equals(""))
			sql += " and classNo like '%" + classNo + "%'";
		if (!className.equals(""))
			sql += " and className like '%" + className + "%'";
		if (!collegeName.equals(""))
			sql += " and collegeName like '%" + collegeName + "%'";
		if (!specialName.equals(""))
			sql += " and specialName like '%" + specialName + "%'";
		try {
			ResultSet rs = db.executeQuery(sql);
			while (rs.next()) {
				ClassInfo classInfo = new ClassInfo();
				classInfo.setClassNo(rs.getString("classNo"));
				classInfo.setClassName(rs.getString("className"));
				classInfo.setCollegeName(rs.getString("collegeName"));
				classInfo.setSpecialName(rs.getString("specialName"));
				classInfo.setBornDate(rs.getTimestamp("bornDate"));
				classInfo.setBanzhuren(rs.getString("banzhuren"));
				classInfoList.add(classInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		return classInfoList;
	}
	/* 传入班级对象，进行班级的添加业务 */
	public String AddClassInfo(ClassInfo classInfo) {
		DB db = new DB();
		String result = "";
		try {
			/* 构建sql执行插入新班级 */
			String sqlString = "insert into ClassInfo(classNo,className,collegeName,specialName,bornDate,banzhuren) values (";
			sqlString += "'" + classInfo.getClassNo() + "',";
			sqlString += "'" + classInfo.getClassName() + "',";
			sqlString += "'" + classInfo.getCollegeName() + "',";
			sqlString += "'" + classInfo.getSpecialName() + "',";
			sqlString += "'" + classInfo.getBornDate() + "',";
			sqlString += "'" + classInfo.getBanzhuren() + "'";
			sqlString += ")";
			db.executeUpdate(sqlString);
			result = "班级添加成功!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "班级添加失败";
		} finally {
			db.all_close();
		}
		return result;
	}
	/* 删除班级 */
	public String DeleteClassInfo(String classNo) {
		DB db = new DB();
		String result = "";
		try {
			String sqlString = "delete from ClassInfo where classNo='" + classNo + "'";
			db.executeUpdate(sqlString);
			result = "班级删除成功!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "班级删除失败";
		} finally {
			db.all_close();
		}
		return result;
	}

	/* 根据班级编号获取到班级 */
	public ClassInfo GetClassInfo(String classNo) {
		ClassInfo classInfo = null;
		DB db = new DB();
		String sql = "select * from ClassInfo where classNo='" + classNo + "'";
		try {
			ResultSet rs = db.executeQuery(sql);
			if (rs.next()) {
				classInfo = new ClassInfo();
				classInfo.setClassNo(rs.getString("classNo"));
				classInfo.setClassName(rs.getString("className"));
				classInfo.setCollegeName(rs.getString("collegeName"));
				classInfo.setSpecialName(rs.getString("specialName"));
				classInfo.setBornDate(rs.getTimestamp("bornDate"));
				classInfo.setBanzhuren(rs.getString("banzhuren"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.all_close();
		}
		return classInfo;
	}
	/* 更新班级 */
	public String UpdateClassInfo(ClassInfo classInfo) {
		DB db = new DB();
		String result = "";
		try {
			String sql = "update ClassInfo set ";
			sql += "className='" + classInfo.getClassName() + "',";
			sql += "collegeName='" + classInfo.getCollegeName() + "',";
			sql += "specialName='" + classInfo.getSpecialName() + "',";
			sql += "bornDate='" + classInfo.getBornDate() + "',";
			sql += "banzhuren='" + classInfo.getBanzhuren() + "'";
			sql += " where classNo='" + classInfo.getClassNo() + "'";
			db.executeUpdate(sql);
			result = "班级更新成功!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "班级更新失败";
		} finally {
			db.all_close();
		}
		return result;
	}
}
