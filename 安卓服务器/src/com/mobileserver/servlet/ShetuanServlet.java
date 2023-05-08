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

	/*��������ҵ������*/
	private ShetuanDAO shetuanDAO = new ShetuanDAO();

	/*Ĭ�Ϲ��캯��*/
	public ShetuanServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*��ȡaction����������action��ִֵ�в�ͬ��ҵ����*/
		String action = request.getParameter("action");
		if (action.equals("query")) {
			/*��ȡ��ѯ���ŵĲ�����Ϣ*/
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

			/*����ҵ���߼���ִ�����Ų�ѯ*/
			List<Shetuan> shetuanList = shetuanDAO.QueryShetuan(stUserName,shetuanName,bornDate,fuzeren);

			/*2�����ݴ����ʽ��һ����xml�ļ���ʽ������ѯ�Ľ����ͨ��xml��ʽ������ͻ���
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
			//��2�ֲ���json��ʽ(����������)�� �ͻ��˲�ѯ��ͼ����󣬷���json���ݸ�ʽ
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
			response.setContentType("text/json; charset=UTF-8");  //JSON������Ϊtext/json
			response.getOutputStream().write(stringer.toString().getBytes("UTF-8"));
		} else if (action.equals("add")) {
			/* ������ţ���ȡ���Ų������������浽�½������Ŷ��� */ 
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

			/* ����ҵ���ִ����Ӳ��� */
			String result = shetuanDAO.AddShetuan(shetuan);
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(result);
		} else if (action.equals("delete")) {
			/*ɾ�����ţ���ȡ���ŵĸ������˺�*/
			String stUserName = new String(request.getParameter("stUserName").getBytes("iso-8859-1"), "UTF-8");
			/*����ҵ���߼���ִ��ɾ������*/
			String result = shetuanDAO.DeleteShetuan(stUserName);
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			/*��ɾ���Ƿ�ɹ���Ϣ���ظ��ͻ���*/
			out.print(result);
		} else if (action.equals("updateQuery")) {
			/*��������֮ǰ�ȸ���stUserName��ѯĳ������*/
			String stUserName = new String(request.getParameter("stUserName").getBytes("iso-8859-1"), "UTF-8");
			Shetuan shetuan = shetuanDAO.GetShetuan(stUserName);

			// �ͻ��˲�ѯ�����Ŷ��󣬷���json���ݸ�ʽ, ��List<Book>��֯��JSON�ַ���
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
			response.setContentType("text/json; charset=UTF-8");  //JSON������Ϊtext/json
			response.getOutputStream().write(stringer.toString().getBytes("UTF-8"));
		} else if(action.equals("update")) {
			/* �������ţ���ȡ���Ų������������浽�½������Ŷ��� */ 
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

			/* ����ҵ���ִ�и��²��� */
			String result = shetuanDAO.UpdateShetuan(shetuan);
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(result);
		}
	}
}
