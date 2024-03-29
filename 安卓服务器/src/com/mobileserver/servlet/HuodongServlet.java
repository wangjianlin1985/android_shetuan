package com.mobileserver.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Timestamp;
import java.util.List;

import com.mobileserver.dao.HuodongDAO;
import com.mobileserver.domain.Huodong;

import org.json.JSONStringer;

public class HuodongServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*构造社团活动业务层对象*/
	private HuodongDAO huodongDAO = new HuodongDAO();

	/*默认构造函数*/
	public HuodongServlet() {
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
			/*获取查询社团活动的参数信息*/
			String huodongName = request.getParameter("huodongName");
			huodongName = huodongName == null ? "" : new String(request.getParameter(
					"huodongName").getBytes("iso-8859-1"), "UTF-8");
			Timestamp huodongTime = null;
			if (request.getParameter("huodongTime") != null)
				huodongTime = Timestamp.valueOf(request.getParameter("huodongTime"));
			String shetuanObj = "";
			if (request.getParameter("shetuanObj") != null)
				shetuanObj = request.getParameter("shetuanObj");

			/*调用业务逻辑层执行社团活动查询*/
			List<Huodong> huodongList = huodongDAO.QueryHuodong(huodongName,huodongTime,shetuanObj);

			/*2种数据传输格式，一种是xml文件格式：将查询的结果集通过xml格式传输给客户端
			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>").append("\r\n")
			.append("<Huodongs>").append("\r\n");
			for (int i = 0; i < huodongList.size(); i++) {
				sb.append("	<Huodong>").append("\r\n")
				.append("		<huodongId>")
				.append(huodongList.get(i).getHuodongId())
				.append("</huodongId>").append("\r\n")
				.append("		<huodongName>")
				.append(huodongList.get(i).getHuodongName())
				.append("</huodongName>").append("\r\n")
				.append("		<huodongDesc>")
				.append(huodongList.get(i).getHuodongDesc())
				.append("</huodongDesc>").append("\r\n")
				.append("		<huodongTime>")
				.append(huodongList.get(i).getHuodongTime())
				.append("</huodongTime>").append("\r\n")
				.append("		<shetuanObj>")
				.append(huodongList.get(i).getShetuanObj())
				.append("</shetuanObj>").append("\r\n")
				.append("		<huodongMemo>")
				.append(huodongList.get(i).getHuodongMemo())
				.append("</huodongMemo>").append("\r\n")
				.append("	</Huodong>").append("\r\n");
			}
			sb.append("</Huodongs>").append("\r\n");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(sb.toString());*/
			//第2种采用json格式(我们用这种)： 客户端查询的图书对象，返回json数据格式
			JSONStringer stringer = new JSONStringer();
			try {
			  stringer.array();
			  for(Huodong huodong: huodongList) {
				  stringer.object();
			  stringer.key("huodongId").value(huodong.getHuodongId());
			  stringer.key("huodongName").value(huodong.getHuodongName());
			  stringer.key("huodongDesc").value(huodong.getHuodongDesc());
			  stringer.key("huodongTime").value(huodong.getHuodongTime());
			  stringer.key("shetuanObj").value(huodong.getShetuanObj());
			  stringer.key("huodongMemo").value(huodong.getHuodongMemo());
				  stringer.endObject();
			  }
			  stringer.endArray();
			} catch(Exception e){}
			response.setContentType("text/json; charset=UTF-8");  //JSON的类型为text/json
			response.getOutputStream().write(stringer.toString().getBytes("UTF-8"));
		} else if (action.equals("add")) {
			/* 添加社团活动：获取社团活动参数，参数保存到新建的社团活动对象 */ 
			Huodong huodong = new Huodong();
			int huodongId = Integer.parseInt(request.getParameter("huodongId"));
			huodong.setHuodongId(huodongId);
			String huodongName = new String(request.getParameter("huodongName").getBytes("iso-8859-1"), "UTF-8");
			huodong.setHuodongName(huodongName);
			String huodongDesc = new String(request.getParameter("huodongDesc").getBytes("iso-8859-1"), "UTF-8");
			huodong.setHuodongDesc(huodongDesc);
			Timestamp huodongTime = Timestamp.valueOf(request.getParameter("huodongTime"));
			huodong.setHuodongTime(huodongTime);
			String shetuanObj = new String(request.getParameter("shetuanObj").getBytes("iso-8859-1"), "UTF-8");
			huodong.setShetuanObj(shetuanObj);
			String huodongMemo = new String(request.getParameter("huodongMemo").getBytes("iso-8859-1"), "UTF-8");
			huodong.setHuodongMemo(huodongMemo);

			/* 调用业务层执行添加操作 */
			String result = huodongDAO.AddHuodong(huodong);
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(result);
		} else if (action.equals("delete")) {
			/*删除社团活动：获取社团活动的活动id*/
			int huodongId = Integer.parseInt(request.getParameter("huodongId"));
			/*调用业务逻辑层执行删除操作*/
			String result = huodongDAO.DeleteHuodong(huodongId);
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			/*将删除是否成功信息返回给客户端*/
			out.print(result);
		} else if (action.equals("updateQuery")) {
			/*更新社团活动之前先根据huodongId查询某个社团活动*/
			int huodongId = Integer.parseInt(request.getParameter("huodongId"));
			Huodong huodong = huodongDAO.GetHuodong(huodongId);

			// 客户端查询的社团活动对象，返回json数据格式, 将List<Book>组织成JSON字符串
			JSONStringer stringer = new JSONStringer(); 
			try{
			  stringer.array();
			  stringer.object();
			  stringer.key("huodongId").value(huodong.getHuodongId());
			  stringer.key("huodongName").value(huodong.getHuodongName());
			  stringer.key("huodongDesc").value(huodong.getHuodongDesc());
			  stringer.key("huodongTime").value(huodong.getHuodongTime());
			  stringer.key("shetuanObj").value(huodong.getShetuanObj());
			  stringer.key("huodongMemo").value(huodong.getHuodongMemo());
			  stringer.endObject();
			  stringer.endArray();
			}
			catch(Exception e){}
			response.setContentType("text/json; charset=UTF-8");  //JSON的类型为text/json
			response.getOutputStream().write(stringer.toString().getBytes("UTF-8"));
		} else if(action.equals("update")) {
			/* 更新社团活动：获取社团活动参数，参数保存到新建的社团活动对象 */ 
			Huodong huodong = new Huodong();
			int huodongId = Integer.parseInt(request.getParameter("huodongId"));
			huodong.setHuodongId(huodongId);
			String huodongName = new String(request.getParameter("huodongName").getBytes("iso-8859-1"), "UTF-8");
			huodong.setHuodongName(huodongName);
			String huodongDesc = new String(request.getParameter("huodongDesc").getBytes("iso-8859-1"), "UTF-8");
			huodong.setHuodongDesc(huodongDesc);
			Timestamp huodongTime = Timestamp.valueOf(request.getParameter("huodongTime"));
			huodong.setHuodongTime(huodongTime);
			String shetuanObj = new String(request.getParameter("shetuanObj").getBytes("iso-8859-1"), "UTF-8");
			huodong.setShetuanObj(shetuanObj);
			String huodongMemo = new String(request.getParameter("huodongMemo").getBytes("iso-8859-1"), "UTF-8");
			huodong.setHuodongMemo(huodongMemo);

			/* 调用业务层执行更新操作 */
			String result = huodongDAO.UpdateHuodong(huodong);
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(result);
		}
	}
}
