package com.mobileserver.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Timestamp;
import java.util.List;

import com.mobileserver.dao.ShetuanDAO;
import com.mobileserver.domain.Shetuan;

import org.json.JSONStringer;

public class ShetuanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*构造社团业务层对象*/
	private ShetuanDAO shetuanDAO = new ShetuanDAO();

	/*默认构造函数*/
	public ShetuanServlet() {
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
			/*获取查询社团的参数信息*/
			String stUserName = request.getParameter("stUserName");
			stUserName = stUserName == null ? "" : new String(request.getParameter(
					"stUserName").getBytes("iso-8859-1"), "UTF-8");
			String shetuanName = request.getParameter("shetuanName");
			shetuanName = shetuanName == null ? "" : new String(request.getParameter(
					"shetuanName").getBytes("iso-8859-1"), "UTF-8");
			Timestamp bornDate = null;
			if (request.getParameter("bornDate") != null)
				bornDate = Timestamp.valueOf(request.getParameter("bornDate"));
			String fuzeren = request.getParameter("fuzeren");
			fuzeren = fuzeren == null ? "" : new String(request.getParameter(
					"fuzeren").getBytes("iso-8859-1"), "UTF-8");

			/*调用业务逻辑层执行社团查询*/
			List<Shetuan> shetuanList = shetuanDAO.QueryShetuan(stUserName,shetuanName,bornDate,fuzeren);

			/*2种数据传输格式，一种是xml文件格式：将查询的结果集通过xml格式传输给客户端
			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>").append("\r\n")
			.append("<Shetuans>").append("\r\n");
			for (int i = 0; i < shetuanList.size(); i++) {
				sb.append("	<Shetuan>").append("\r\n")
				.append("		<stUserName>")
				.append(shetuanList.get(i).getStUserName())
				.append("</stUserName>").append("\r\n")
				.append("		<password>")
				.append(shetuanList.get(i).getPassword())
				.append("</password>").append("\r\n")
				.append("		<shetuanName>")
				.append(shetuanList.get(i).getShetuanName())
				.append("</shetuanName>").append("\r\n")
				.append("		<shetuanPhoto>")
				.append(shetuanList.get(i).getShetuanPhoto())
				.append("</shetuanPhoto>").append("\r\n")
				.append("		<shetuanDesc>")
				.append(shetuanList.get(i).getShetuanDesc())
				.append("</shetuanDesc>").append("\r\n")
				.append("		<bornDate>")
				.append(shetuanList.get(i).getBornDate())
				.append("</bornDate>").append("\r\n")
				.append("		<fuzeren>")
				.append(shetuanList.get(i).getFuzeren())
				.append("</fuzeren>").append("\r\n")
				.append("		<telephone>")
				.append(shetuanList.get(i).getTelephone())
				.append("</telephone>").append("\r\n")
				.append("		<shetuanMemo>")
				.append(shetuanList.get(i).getShetuanMemo())
				.append("</shetuanMemo>").append("\r\n")
				.append("	</Shetuan>").append("\r\n");
			}
			sb.append("</Shetuans>").append("\r\n");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(sb.toString());*/
			//第2种采用json格式(我们用这种)： 客户端查询的图书对象，返回json数据格式
			JSONStringer stringer = new JSONStringer();
			try {
			  stringer.array();
			  for(Shetuan shetuan: shetuanList) {
				  stringer.object();
			  stringer.key("stUserName").value(shetuan.getStUserName());
			  stringer.key("password").value(shetuan.getPassword());
			  stringer.key("shetuanName").value(shetuan.getShetuanName());
			  stringer.key("shetuanPhoto").value(shetuan.getShetuanPhoto());
			  stringer.key("shetuanDesc").value(shetuan.getShetuanDesc());
			  stringer.key("bornDate").value(shetuan.getBornDate());
			  stringer.key("fuzeren").value(shetuan.getFuzeren());
			  stringer.key("telephone").value(shetuan.getTelephone());
			  stringer.key("shetuanMemo").value(shetuan.getShetuanMemo());
				  stringer.endObject();
			  }
			  stringer.endArray();
			} catch(Exception e){}
			response.setContentType("text/json; charset=UTF-8");  //JSON的类型为text/json
			response.getOutputStream().write(stringer.toString().getBytes("UTF-8"));
		} else if (action.equals("add")) {
			/* 添加社团：获取社团参数，参数保存到新建的社团对象 */ 
			Shetuan shetuan = new Shetuan();
			String stUserName = new String(request.getParameter("stUserName").getBytes("iso-8859-1"), "UTF-8");
			shetuan.setStUserName(stUserName);
			String password = new String(request.getParameter("password").getBytes("iso-8859-1"), "UTF-8");
			shetuan.setPassword(password);
			String shetuanName = new String(request.getParameter("shetuanName").getBytes("iso-8859-1"), "UTF-8");
			shetuan.setShetuanName(shetuanName);
			String shetuanPhoto = new String(request.getParameter("shetuanPhoto").getBytes("iso-8859-1"), "UTF-8");
			shetuan.setShetuanPhoto(shetuanPhoto);
			String shetuanDesc = new String(request.getParameter("shetuanDesc").getBytes("iso-8859-1"), "UTF-8");
			shetuan.setShetuanDesc(shetuanDesc);
			Timestamp bornDate = Timestamp.valueOf(request.getParameter("bornDate"));
			shetuan.setBornDate(bornDate);
			String fuzeren = new String(request.getParameter("fuzeren").getBytes("iso-8859-1"), "UTF-8");
			shetuan.setFuzeren(fuzeren);
			String telephone = new String(request.getParameter("telephone").getBytes("iso-8859-1"), "UTF-8");
			shetuan.setTelephone(telephone);
			String shetuanMemo = new String(request.getParameter("shetuanMemo").getBytes("iso-8859-1"), "UTF-8");
			shetuan.setShetuanMemo(shetuanMemo);

			/* 调用业务层执行添加操作 */
			String result = shetuanDAO.AddShetuan(shetuan);
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(result);
		} else if (action.equals("delete")) {
			/*删除社团：获取社团的负责人账号*/
			String stUserName = new String(request.getParameter("stUserName").getBytes("iso-8859-1"), "UTF-8");
			/*调用业务逻辑层执行删除操作*/
			String result = shetuanDAO.DeleteShetuan(stUserName);
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			/*将删除是否成功信息返回给客户端*/
			out.print(result);
		} else if (action.equals("updateQuery")) {
			/*更新社团之前先根据stUserName查询某个社团*/
			String stUserName = new String(request.getParameter("stUserName").getBytes("iso-8859-1"), "UTF-8");
			Shetuan shetuan = shetuanDAO.GetShetuan(stUserName);

			// 客户端查询的社团对象，返回json数据格式, 将List<Book>组织成JSON字符串
			JSONStringer stringer = new JSONStringer(); 
			try{
			  stringer.array();
			  stringer.object();
			  stringer.key("stUserName").value(shetuan.getStUserName());
			  stringer.key("password").value(shetuan.getPassword());
			  stringer.key("shetuanName").value(shetuan.getShetuanName());
			  stringer.key("shetuanPhoto").value(shetuan.getShetuanPhoto());
			  stringer.key("shetuanDesc").value(shetuan.getShetuanDesc());
			  stringer.key("bornDate").value(shetuan.getBornDate());
			  stringer.key("fuzeren").value(shetuan.getFuzeren());
			  stringer.key("telephone").value(shetuan.getTelephone());
			  stringer.key("shetuanMemo").value(shetuan.getShetuanMemo());
			  stringer.endObject();
			  stringer.endArray();
			}
			catch(Exception e){}
			response.setContentType("text/json; charset=UTF-8");  //JSON的类型为text/json
			response.getOutputStream().write(stringer.toString().getBytes("UTF-8"));
		} else if(action.equals("update")) {
			/* 更新社团：获取社团参数，参数保存到新建的社团对象 */ 
			Shetuan shetuan = new Shetuan();
			String stUserName = new String(request.getParameter("stUserName").getBytes("iso-8859-1"), "UTF-8");
			shetuan.setStUserName(stUserName);
			String password = new String(request.getParameter("password").getBytes("iso-8859-1"), "UTF-8");
			shetuan.setPassword(password);
			String shetuanName = new String(request.getParameter("shetuanName").getBytes("iso-8859-1"), "UTF-8");
			shetuan.setShetuanName(shetuanName);
			String shetuanPhoto = new String(request.getParameter("shetuanPhoto").getBytes("iso-8859-1"), "UTF-8");
			shetuan.setShetuanPhoto(shetuanPhoto);
			String shetuanDesc = new String(request.getParameter("shetuanDesc").getBytes("iso-8859-1"), "UTF-8");
			shetuan.setShetuanDesc(shetuanDesc);
			Timestamp bornDate = Timestamp.valueOf(request.getParameter("bornDate"));
			shetuan.setBornDate(bornDate);
			String fuzeren = new String(request.getParameter("fuzeren").getBytes("iso-8859-1"), "UTF-8");
			shetuan.setFuzeren(fuzeren);
			String telephone = new String(request.getParameter("telephone").getBytes("iso-8859-1"), "UTF-8");
			shetuan.setTelephone(telephone);
			String shetuanMemo = new String(request.getParameter("shetuanMemo").getBytes("iso-8859-1"), "UTF-8");
			shetuan.setShetuanMemo(shetuanMemo);

			/* 调用业务层执行更新操作 */
			String result = shetuanDAO.UpdateShetuan(shetuan);
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(result);
		}
	}
}
