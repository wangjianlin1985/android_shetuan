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

	/*������������ҵ������*/
	private ShenqingDAO shenqingDAO = new ShenqingDAO();

	/*Ĭ�Ϲ��캯��*/
	public ShenqingServlet() {
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
			/*��ȡ��ѯ��������Ĳ�����Ϣ*/
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

			/*����ҵ���߼���ִ�����������ѯ*/
			List<Shenqing> shenqingList = shenqingDAO.QueryShenqing(shentuanObj,name,xuehao,userObj,sqTime,shenHeState);

			/*2�����ݴ����ʽ��һ����xml�ļ���ʽ������ѯ�Ľ����ͨ��xml��ʽ������ͻ���
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
			//��2�ֲ���json��ʽ(����������)�� �ͻ��˲�ѯ��ͼ����󣬷���json���ݸ�ʽ
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
			response.setContentType("text/json; charset=UTF-8");  //JSON������Ϊtext/json
			response.getOutputStream().write(stringer.toString().getBytes("UTF-8"));
		} else if (action.equals("add")) {
			/* ����������룺��ȡ��������������������浽�½�������������� */ 
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

			/* ����ҵ���ִ����Ӳ��� */
			String result = shenqingDAO.AddShenqing(shenqing);
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(result);
		} else if (action.equals("delete")) {
			/*ɾ���������룺��ȡ�������������id*/
			int shenqingId = Integer.parseInt(request.getParameter("shenqingId"));
			/*����ҵ���߼���ִ��ɾ������*/
			String result = shenqingDAO.DeleteShenqing(shenqingId);
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			/*��ɾ���Ƿ�ɹ���Ϣ���ظ��ͻ���*/
			out.print(result);
		} else if (action.equals("updateQuery")) {
			/*������������֮ǰ�ȸ���shenqingId��ѯĳ����������*/
			int shenqingId = Integer.parseInt(request.getParameter("shenqingId"));
			Shenqing shenqing = shenqingDAO.GetShenqing(shenqingId);

			// �ͻ��˲�ѯ������������󣬷���json���ݸ�ʽ, ��List<Book>��֯��JSON�ַ���
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
			response.setContentType("text/json; charset=UTF-8");  //JSON������Ϊtext/json
			response.getOutputStream().write(stringer.toString().getBytes("UTF-8"));
		} else if(action.equals("update")) {
			/* �����������룺��ȡ��������������������浽�½�������������� */ 
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

			/* ����ҵ���ִ�и��²��� */
			String result = shenqingDAO.UpdateShenqing(shenqing);
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(result);
		}
	}
}
