<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String username=(String)session.getAttribute("username");
    if(username==null){
        response.getWriter().println("<script>top.location.href='" + basePath + "login/login_view.action';</script>");
    }
%>
<HTML><HEAD><TITLE>添加社团</TITLE> 
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
/*验证表单*/
function checkForm() {
    var stUserName = document.getElementById("shetuan.stUserName").value;
    if(stUserName=="") {
        alert('请输入负责人账号!');
        return false;
    }
    var password = document.getElementById("shetuan.password").value;
    if(password=="") {
        alert('请输入登录密码!');
        return false;
    }
    var shetuanName = document.getElementById("shetuan.shetuanName").value;
    if(shetuanName=="") {
        alert('请输入社团名称!');
        return false;
    }
    var shetuanDesc = document.getElementById("shetuan.shetuanDesc").value;
    if(shetuanDesc=="") {
        alert('请输入社团简介!');
        return false;
    }
    var fuzeren = document.getElementById("shetuan.fuzeren").value;
    if(fuzeren=="") {
        alert('请输入负责人!');
        return false;
    }
    var telephone = document.getElementById("shetuan.telephone").value;
    if(telephone=="") {
        alert('请输入联系电话!');
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
    <TD align="left" vAlign=top >
    <s:form action="Shetuan/Shetuan_AddShetuan.action" method="post" id="shetuanAddForm" onsubmit="return checkForm();"  enctype="multipart/form-data" name="form1">
<table width='100%' cellspacing='1' cellpadding='3' class="tablewidth">

  <tr>
    <td width=30%>负责人账号:</td>
    <td width=70%><input id="shetuan.stUserName" name="shetuan.stUserName" type="text" /></td>
  </tr>

  <tr>
    <td width=30%>登录密码:</td>
    <td width=70%><input id="shetuan.password" name="shetuan.password" type="text" size="20" /></td>
  </tr>

  <tr>
    <td width=30%>社团名称:</td>
    <td width=70%><input id="shetuan.shetuanName" name="shetuan.shetuanName" type="text" size="20" /></td>
  </tr>

  <tr>
    <td width=30%>社团logo:</td>
    <td width=70%><input id="shetuanPhotoFile" name="shetuanPhotoFile" type="file" size="50" /></td>
  </tr>

  <tr>
    <td width=30%>社团简介:</td>
    <td width=70%><textarea id="shetuan.shetuanDesc" name="shetuan.shetuanDesc" rows="5" cols="50"></textarea></td>
  </tr>

  <tr>
    <td width=30%>成立日期:</td>
    <td width=70%><input type="text" readonly id="shetuan.bornDate"  name="shetuan.bornDate" onclick="setDay(this);"/></td>
  </tr>

  <tr>
    <td width=30%>负责人:</td>
    <td width=70%><input id="shetuan.fuzeren" name="shetuan.fuzeren" type="text" size="20" /></td>
  </tr>

  <tr>
    <td width=30%>联系电话:</td>
    <td width=70%><input id="shetuan.telephone" name="shetuan.telephone" type="text" size="20" /></td>
  </tr>

  <tr>
    <td width=30%>社团备注:</td>
    <td width=70%><textarea id="shetuan.shetuanMemo" name="shetuan.shetuanMemo" rows="5" cols="50"></textarea></td>
  </tr>

  <tr bgcolor='#FFFFFF'>
      <td colspan="4" align="center">
        <input type='submit' name='button' value='保存' >
        &nbsp;&nbsp;
        <input type="reset" value='重写' />
      </td>
    </tr>

</table>
</s:form>
   </TD></TR>
  </TBODY>
</TABLE>
</BODY>
</HTML>
