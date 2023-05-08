package com.mobileserver.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Timestamp;
import java.util.List;

import com.mobileserver.dao.ShenqingDAO;
import com.mobileserver.domain.Shenqing;

import org.json.JSONStringer;

public class ShenqingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*构造社团申请业务层对象*/
	private ShenqingDAO shenqingDAO = new ShenqingDAO();

	/*默认构造函数*/
	public ShenqingServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*获取action参数，根据action的值执行不同的业务处理*/
		String action = request.getParameter("action");
		if (action.equals("query")) {
			/*获取查询社团申请的参数信息*/
			String shentuanObj = "";
			if (request.getParameter("shentuanObj") != null)
				shentuanObj = request.getParameter("shentuanObj");
			String name = request.getParameter("name");
			name = name == null ? "" : new String(request.getParameter(
					"name").getBytes("iso-8859-1"), "UTF-8");
			String xuehao = request.getParameter("xuehao");
			xuehao = xuehao == null ? "" : new String(request.getParameter(
					"xuehao").getBytes("iso-8859-1"), "UTF-8");
			String userObj = "";
			if (request.getParameter("userObj") != null)
				userObj = request.getParameter("userObj");
			String sqTime = request.getParameter("sqTime");
			sqTime = sqTime == null ? "" : new String(request.getParameter(
					"sqTime").getBytes("iso-8859-1"), "UTF-8");
			String shenHeState = request.getParameter("shenHeState");
			shenHeState = shenHeState == null ? "" : new String(request.getParameter(
					"shenHeState").getBytes("iso-8859-1"), "UTF-8");

			/*调用业务逻辑层执行社团申请查询*/
			List<Shenqing> shenqingList = shenqingDAO.QueryShenqing(shentuanObj,name,xuehao,userObj,sqTime,shenHeState);

			/*2种数据传输格式，一种是xml文件格式：将查询的结果集通过xml格式传输给客户端
			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>").append("\r\n")
			.append("<Shenqings>").append("\r\n");
			for (int i = 0; i < shenqingList.size(); i++) {
				sb.append("	<Shenqing>").append("\r\n")
				.append("		<shenqingId>")
				.append(shenqingList.get(i).getShenqingId())
				.append("</shenqingId>").append("\r\n")
				.append("		<shentuanObj>")
				.append(shenqingList.get(i).getShentuanObj())
				.append("</shentuanObj>").append("\r\n")
				.append("		<name>")
				.append(shenqingList.get(i).getName())
				.append("</name>").append("\r\n")
				.append("		<xuehao>")
				.append(shenqingList.get(i).getXuehao())
				.append("</xuehao>").append("\r\n")
				.append("		<zysj>")
				.append(shenqingList.get(i).getZysj())
				.append("</zysj>").append("\r\n")
				.append("		<rhyy>")
				.append(shenqingList.get(i).getRhyy())
				.append("</rhyy>").append("\r\n")
				.append("		<userObj>")
				.append(shenqingList.get(i).getUserObj())
				.append("</userObj>").append("\r\n")
				.append("		<sqTime>")
				.append(shenqingList.get(i).getSqTime())
				.append("</sqTime>").append("\r\n")
				.append("		<shenHeState>")
				.append(shenqingList.get(i).getShenHeState())
				.append("</shenHeState>").append("\r\n")
				.append("		<shenHeResult>")
				.append(shenqingList.get(i).getShenHeResult())
				.append("</shenHeResult>").append("\r\n")
				.append("	</Shenqing>").append("\r\n");
			}
			sb.append("</Shenqings>").append("\r\n");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(sb.toString());*/
			//第2种采用json格式(我们用这种)： 客户端查询的图书对象，返回json数据格式
			JSONStringer stringer = new JSONStringer();
			try {
			  stringer.array();
			  for(Shenqing shenqing: shenqingList) {
				  stringer.object();
			  stringer.key("shenqingId").value(shenqing.getShenqingId());
			  stringer.key("shentuanObj").value(shenqing.getShentuanObj());
			  stringer.key("name").value(shenqing.getName());
			  stringer.key("xuehao").value(shenqing.getXuehao());
			  stringer.key("zysj").value(shenqing.getZysj());
			  stringer.key("rhyy").value(shenqing.getRhyy());
			  stringer.key("userObj").value(shenqing.getUserObj());
			  stringer.key("sqTime").value(shenqing.getSqTime());
			  stringer.key("shenHeState").value(shenqing.getShenHeState());
			  stringer.key("shenHeResult").value(shenqing.getShenHeResult());
				  stringer.endObject();
			  }
			  stringer.endArray();
			} catch(Exception e){}
			response.setContentType("text/json; charset=UTF-8");  //JSON的类型为text/json
			response.getOutputStream().write(stringer.toString().getBytes("UTF-8"));
		} else if (action.equals("add")) {
			/* 添加社团申请：获取社团申请参数，参数保存到新建的社团申请对象 */ 
			Shenqing shenqing = new Shenqing();
			int shenqingId = Integer.parseInt(request.getParameter("shenqingId"));
			shenqing.setShenqingId(shenqingId);
			String shentuanObj = new String(request.getParameter("shentuanObj").getBytes("iso-8859-1"), "UTF-8");
			shenqing.setShentuanObj(shentuanObj);
			String name = new String(request.getParameter("name").getBytes("iso-8859-1"), "UTF-8");
			shenqing.setName(name);
			String xuehao = new String(request.getParameter("xuehao").getBytes("iso-8859-1"), "UTF-8");
			shenqing.setXuehao(xuehao);
			String zysj = new String(request.getParameter("zysj").getBytes("iso-8859-1"), "UTF-8");
			shenqing.setZysj(zysj);
			String rhyy = new String(request.getParameter("rhyy").getBytes("iso-8859-1"), "UTF-8");
			shenqing.setRhyy(rhyy);
			String userObj = new String(request.getParameter("userObj").getBytes("iso-8859-1"), "UTF-8");
			shenqing.setUserObj(userObj);
			String sqTime = new String(request.getParameter("sqTime").getBytes("iso-8859-1"), "UTF-8");
			shenqing.setSqTime(sqTime);
			String shenHeState = new String(request.getParameter("shenHeState").getBytes("iso-8859-1"), "UTF-8");
			shenqing.setShenHeState(shenHeState);
			String shenHeResult = new String(request.getParameter("shenHeResult").getBytes("iso-8859-1"), "UTF-8");
			shenqing.setShenHeResult(shenHeResult);

			/* 调用业务层执行添加操作 */
			String result = shenqingDAO.AddShenqing(shenqing);
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(result);
		} else if (action.equals("delete")) {
			/*删除社团申请：获取社团申请的申请id*/
			int shenqingId = Integer.parseInt(request.getParameter("shenqingId"));
			/*调用业务逻辑层执行删除操作*/
			String result = shenqingDAO.DeleteShenqing(shenqingId);
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			/*将删除是否成功信息返回给客户端*/
			out.print(result);
		} else if (action.equals("updateQuery")) {
			/*更新社团申请之前先根据shenqingId查询某个社团申请*/
			int shenqingId = Integer.parseInt(request.getParameter("shenqingId"));
			Shenqing shenqing = shenqingDAO.GetShenqing(shenqingId);

			// 客户端查询的社团申请对象，返回json数据格式, 将List<Book>组织成JSON字符串
			JSONStringer stringer = new JSONStringer(); 
			try{
			  stringer.array();
			  stringer.object();
			  stringer.key("shenqingId").value(shenqing.getShenqingId());
			  stringer.key("shentuanObj").value(shenqing.getShentuanObj());
			  stringer.key("name").value(shenqing.getName());
			  stringer.key("xuehao").value(shenqing.getXuehao());
			  stringer.key("zysj").value(shenqing.getZysj());
			  stringer.key("rhyy").value(shenqing.getRhyy());
			  stringer.key("userObj").value(shenqing.getUserObj());
			  stringer.key("sqTime").value(shenqing.getSqTime());
			  stringer.key("shenHeState").value(shenqing.getShenHeState());
			  stringer.key("shenHeResult").value(shenqing.getShenHeResult());
			  stringer.endObject();
			  stringer.endArray();
			}
			catch(Exception e){}
			response.setContentType("text/json; charset=UTF-8");  //JSON的类型为text/json
			response.getOutputStream().write(stringer.toString().getBytes("UTF-8"));
		} else if(action.equals("update")) {
			/* 更新社团申请：获取社团申请参数，参数保存到新建的社团申请对象 */ 
			Shenqing shenqing = new Shenqing();
			int shenqingId = Integer.parseInt(request.getParameter("shenqingId"));
			shenqing.setShenqingId(shenqingId);
			String shentuanObj = new String(request.getParameter("shentuanObj").getBytes("iso-8859-1"), "UTF-8");
			shenqing.setShentuanObj(shentuanObj);
			String name = new String(request.getParameter("name").getBytes("iso-8859-1"), "UTF-8");
			shenqing.setName(name);
			String xuehao = new String(request.getParameter("xuehao").getBytes("iso-8859-1"), "UTF-8");
			shenqing.setXuehao(xuehao);
			String zysj = new String(request.getParameter("zysj").getBytes("iso-8859-1"), "UTF-8");
			shenqing.setZysj(zysj);
			String rhyy = new String(request.getParameter("rhyy").getBytes("iso-8859-1"), "UTF-8");
			shenqing.setRhyy(rhyy);
			String userObj = new String(request.getParameter("userObj").getBytes("iso-8859-1"), "UTF-8");
			shenqing.setUserObj(userObj);
			String sqTime = new String(request.getParameter("sqTime").getBytes("iso-8859-1"), "UTF-8");
			shenqing.setSqTime(sqTime);
			String shenHeState = new String(request.getParameter("shenHeState").getBytes("iso-8859-1"), "UTF-8");
			shenqing.setShenHeState(shenHeState);
			String shenHeResult = new String(request.getParameter("shenHeResult").getBytes("iso-8859-1"), "UTF-8");
			shenqing.setShenHeResult(shenHeResult);

			/* 调用业务层执行更新操作 */
			String result = shenqingDAO.UpdateShenqing(shenqing);
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(result);
		}
	}
}
