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

	/*�������Żҵ������*/
	private HuodongDAO huodongDAO = new HuodongDAO();

	/*Ĭ�Ϲ��캯��*/
	public HuodongServlet() {
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
			/*��ȡ��ѯ���Ż�Ĳ�����Ϣ*/
			String huodongName = request.getParameter("huodongName");
			huodongName = huodongName == null ? "" : new String(request.getParameter(
					"huodongName").getBytes("iso-8859-1"), "UTF-8");
			Timestamp huodongTime = null;
			if (request.getParameter("huodongTime") != null)
				huodongTime = Timestamp.valueOf(request.getParameter("huodongTime"));
			String shetuanObj = "";
			if (request.getParameter("shetuanObj") != null)
				shetuanObj = request.getParameter("shetuanObj");

			/*����ҵ���߼���ִ�����Ż��ѯ*/
			List<Huodong> huodongList = huodongDAO.QueryHuodong(huodongName,huodongTime,shetuanObj);

			/*2�����ݴ����ʽ��һ����xml�ļ���ʽ������ѯ�Ľ����ͨ��xml��ʽ������ͻ���
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
			//��2�ֲ���json��ʽ(����������)�� �ͻ��˲�ѯ��ͼ����󣬷���json���ݸ�ʽ
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
			response.setContentType("text/json; charset=UTF-8");  //JSON������Ϊtext/json
			response.getOutputStream().write(stringer.toString().getBytes("UTF-8"));
		} else if (action.equals("add")) {
			/* ������Ż����ȡ���Ż�������������浽�½������Ż���� */ 
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

			/* ����ҵ���ִ����Ӳ��� */
			String result = huodongDAO.AddHuodong(huodong);
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(result);
		} else if (action.equals("delete")) {
			/*ɾ�����Ż����ȡ���Ż�Ļid*/
			int huodongId = Integer.parseInt(request.getParameter("huodongId"));
			/*����ҵ���߼���ִ��ɾ������*/
			String result = huodongDAO.DeleteHuodong(huodongId);
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			/*��ɾ���Ƿ�ɹ���Ϣ���ظ��ͻ���*/
			out.print(result);
		} else if (action.equals("updateQuery")) {
			/*�������Ż֮ǰ�ȸ���huodongId��ѯĳ�����Ż*/
			int huodongId = Integer.parseInt(request.getParameter("huodongId"));
			Huodong huodong = huodongDAO.GetHuodong(huodongId);

			// �ͻ��˲�ѯ�����Ż���󣬷���json���ݸ�ʽ, ��List<Book>��֯��JSON�ַ���
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
			response.setContentType("text/json; charset=UTF-8");  //JSON������Ϊtext/json
			response.getOutputStream().write(stringer.toString().getBytes("UTF-8"));
		} else if(action.equals("update")) {
			/* �������Ż����ȡ���Ż�������������浽�½������Ż���� */ 
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

			/* ����ҵ���ִ�и��²��� */
			String result = huodongDAO.UpdateHuodong(huodong);
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(result);
		}
	}
}
