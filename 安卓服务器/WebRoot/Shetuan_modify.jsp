<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%> 
<%@ page import="com.chengxusheji.domain.Shetuan" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    Shetuan shetuan = (Shetuan)request.getAttribute("shetuan");

    String username=(String)session.getAttribute("username");
    if(username==null){
        response.getWriter().println("<script>top.location.href='" + basePath + "login/login_view.action';</script>");
    }
%>
<HTML><HEAD><TITLE>�޸�����</TITLE>
<STYLE type=text/css>
BODY {
	MARGIN-LEFT: 0px; BACKGROUND-COLOR: #ffffff
}
.STYLE1 {color: #ECE9D8}
.label {font-style.:italic; }
.errorLabel {font-style.:italic;  color:red; }
.errorMessage {font-weight:bold; color:red; }
</STYLE>
 <script src="<%=basePath %>calendar.js"></script>
<script language="javascript">
/*��֤��*/
function checkForm() {
    var stUserName = document.getElementById("shetuan.stUserName").value;
    if(stUserName=="") {
        alert('�����븺�����˺�!');
        return false;
    }
    var password = document.getElementById("shetuan.password").value;
    if(password=="") {
        alert('�������¼����!');
        return false;
    }
    var shetuanName = document.getElementById("shetuan.shetuanName").value;
    if(shetuanName=="") {
        alert('��������������!');
        return false;
    }
    var shetuanDesc = document.getElementById("shetuan.shetuanDesc").value;
    if(shetuanDesc=="") {
        alert('���������ż��!');
        return false;
    }
    var fuzeren = document.getElementById("shetuan.fuzeren").value;
    if(fuzeren=="") {
        alert('�����븺����!');
        return false;
    }
    var telephone = document.getElementById("shetuan.telephone").value;
    if(telephone=="") {
        alert('��������ϵ�绰!');
        return false;
    }
    return true; 
}
 </script>
</HEAD>
<BODY background="<%=basePath %>images/adminBg.jpg">
<s:fielderror cssStyle="color:red" />
<TABLE align="center" height="100%" cellSpacing=0 cellPadding=0 width="80%" border=0>
  <TBODY>
  <TR>
    <TD align="left" vAlign=top ><s:form action="Shetuan/Shetuan_ModifyShetuan.action" method="post" onsubmit="return checkForm();" enctype="multipart/form-data" name="form1">
<table width='100%' cellspacing='1' cellpadding='3' class="tablewidth">
  <tr>
    <td width=30%>�������˺�:</td>
    <td width=70%><input id="shetuan.stUserName" name="shetuan.stUserName" type="text" value="<%=shetuan.getStUserName() %>" readOnly /></td>
  </tr>

  <tr>
    <td width=30%>��¼����:</td>
    <td width=70%><input id="shetuan.password" name="shetuan.password" type="text" size="20" value='<%=shetuan.getPassword() %>'/></td>
  </tr>

  <tr>
    <td width=30%>��������:</td>
    <td width=70%><input id="shetuan.shetuanName" name="shetuan.shetuanName" type="text" size="20" value='<%=shetuan.getShetuanName() %>'/></td>
  </tr>

  <tr>
    <td width=30%>����logo:</td>
    <td width=70%><img src="<%=basePath %><%=shetuan.getShetuanPhoto() %>" width="200px" border="0px"/><br/>
    <input type=hidden name="shetuan.shetuanPhoto" value="<%=shetuan.getShetuanPhoto() %>" />
    <input id="shetuanPhotoFile" name="shetuanPhotoFile" type="file" size="50" /></td>
  </tr>
  <tr>
    <td width=30%>���ż��:</td>
    <td width=70%><textarea id="shetuan.shetuanDesc" name="shetuan.shetuanDesc" rows=5 cols=50><%=shetuan.getShetuanDesc() %></textarea></td>
  </tr>

  <tr>
    <td width=30%>��������:</td>
    <% DateFormat bornDateSDF = new SimpleDateFormat("yyyy-MM-dd");  %>
    <td width=70%><input type="text" readonly  id="shetuan.bornDate"  name="shetuan.bornDate" onclick="setDay(this);" value='<%=bornDateSDF.format(shetuan.getBornDate()) %>'/></td>
  </tr>

  <tr>
    <td width=30%>������:</td>
    <td width=70%><input id="shetuan.fuzeren" name="shetuan.fuzeren" type="text" size="20" value='<%=shetuan.getFuzeren() %>'/></td>
  </tr>

  <tr>
    <td width=30%>��ϵ�绰:</td>
    <td width=70%><input id="shetuan.telephone" name="shetuan.telephone" type="text" size="20" value='<%=shetuan.getTelephone() %>'/></td>
  </tr>

  <tr>
    <td width=30%>���ű�ע:</td>
    <td width=70%><textarea id="shetuan.shetuanMemo" name="shetuan.shetuanMemo" rows=5 cols=50><%=shetuan.getShetuanMemo() %></textarea></td>
  </tr>

  <tr bgcolor='#FFFFFF'>
      <td colspan="4" align="center">
        <input type='submit' name='button' value='����' >
        &nbsp;&nbsp;
        <input type="reset" value='��д' />
      </td>
    </tr>

</table>
</s:form>
   </TD></TR>
  </TBODY>
</TABLE>
</BODY>
</HTML>
