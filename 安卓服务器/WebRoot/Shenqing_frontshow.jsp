<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%> 
<%@ page import="com.chengxusheji.domain.Shenqing" %>
<%@ page import="com.chengxusheji.domain.Shetuan" %>
<%@ page import="com.chengxusheji.domain.UserInfo" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    //��ȡ���е�Shetuan��Ϣ
    List<Shetuan> shetuanList = (List<Shetuan>)request.getAttribute("shetuanList");
    //��ȡ���е�UserInfo��Ϣ
    List<UserInfo> userInfoList = (List<UserInfo>)request.getAttribute("userInfoList");
    Shenqing shenqing = (Shenqing)request.getAttribute("shenqing");

%>
<HTML><HEAD><TITLE>�鿴��������</TITLE>
<STYLE type=text/css>
body{margin:0px; font-size:12px; background-image:url(<%=basePath%>images/bg.jpg); background-position:bottom; background-repeat:repeat-x; background-color:#A2D5F0;}
.STYLE1 {color: #ECE9D8}
.label {font-style.:italic; }
.errorLabel {font-style.:italic;  color:red; }
.errorMessage {font-weight:bold; color:red; }
</STYLE>
 <script src="<%=basePath %>calendar.js"></script>
</HEAD>
<BODY><br/><br/>
<s:fielderror cssStyle="color:red" />
<TABLE align="center" height="100%" cellSpacing=0 cellPadding=0 width="80%" border=0>
  <TBODY>
  <TR>
    <TD align="left" vAlign=top ><s:form action="" method="post" onsubmit="return checkForm();" enctype="multipart/form-data" name="form1">
<table width='100%' cellspacing='1' cellpadding='3'  class="tablewidth">
  <tr>
    <td width=30%>����id:</td>
    <td width=70%><%=shenqing.getShenqingId() %></td>
  </tr>

  <tr>
    <td width=30%>���������:</td>
    <td width=70%>
      <%=shenqing.getShentuanObj().getShetuanName() %>
    </td>
  </tr>

  <tr>
    <td width=30%>����:</td>
    <td width=70%><%=shenqing.getName() %></td>
  </tr>

  <tr>
    <td width=30%>ѧ��:</td>
    <td width=70%><%=shenqing.getXuehao() %></td>
  </tr>

  <tr>
    <td width=30%>��Ҫ�¼�:</td>
    <td width=70%><%=shenqing.getZysj() %></td>
  </tr>

  <tr>
    <td width=30%>���ԭ��:</td>
    <td width=70%><%=shenqing.getRhyy() %></td>
  </tr>

  <tr>
    <td width=30%>������:</td>
    <td width=70%>
      <%=shenqing.getUserObj().getName() %>
    </td>
  </tr>

  <tr>
    <td width=30%>����ʱ��:</td>
    <td width=70%><%=shenqing.getSqTime() %></td>
  </tr>

  <tr>
    <td width=30%>���״̬:</td>
    <td width=70%><%=shenqing.getShenHeState() %></td>
  </tr>

  <tr>
    <td width=30%>��˽��:</td>
    <td width=70%><%=shenqing.getShenHeResult() %></td>
  </tr>

  <tr>
      <td colspan="4" align="center">
        <input type="button" value="����" onclick="history.back();"/>
      </td>
    </tr>

</table>
</s:form>
   </TD></TR>
  </TBODY>
</TABLE>
</BODY>
</HTML>
