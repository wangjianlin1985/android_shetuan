<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%>
<%@ page import="com.chengxusheji.domain.Shetuan" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    //获取所有的Shetuan信息
    List<Shetuan> shetuanList = (List<Shetuan>)request.getAttribute("shetuanList");
    String username=(String)session.getAttribute("username");
    if(username==null){
        response.getWriter().println("<script>top.location.href='" + basePath + "login/login_view.action';</script>");
    }
%>
<HTML><HEAD><TITLE>添加社团活动</TITLE> 
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
    var huodongName = document.getElementById("huodong.huodongName").value;
    if(huodongName=="") {
        alert('请输入活动名称!');
        return false;
    }
    var huodongDesc = document.getElementById("huodong.huodongDesc").value;
    if(huodongDesc=="") {
        alert('请输入活动内容!');
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
    <s:form action="Huodong/Huodong_AddHuodong.action" method="post" id="huodongAddForm" onsubmit="return checkForm();"  enctype="multipart/form-data" name="form1">
<table width='100%' cellspacing='1' cellpadding='3' class="tablewidth">

  <tr>
    <td width=30%>活动名称:</td>
    <td width=70%><input id="huodong.huodongName" name="huodong.huodongName" type="text" size="80" /></td>
  </tr>

  <tr>
    <td width=30%>活动内容:</td>
    <td width=70%><textarea id="huodong.huodongDesc" name="huodong.huodongDesc" rows="5" cols="50"></textarea></td>
  </tr>

  <tr>
    <td width=30%>活动时间:</td>
    <td width=70%><input type="text" readonly id="huodong.huodongTime"  name="huodong.huodongTime" onclick="setDay(this);"/></td>
  </tr>

  <tr>
    <td width=30%>活动社团:</td>
    <td width=70%>
      <select name="huodong.shetuanObj.stUserName">
      <%
        for(Shetuan shetuan:shetuanList) {
      %>
          <option value='<%=shetuan.getStUserName() %>'><%=shetuan.getShetuanName() %></option>
      <%
        }
      %>
    </td>
  </tr>

  <tr>
    <td width=30%>活动备注:</td>
    <td width=70%><textarea id="huodong.huodongMemo" name="huodong.huodongMemo" rows="5" cols="50"></textarea></td>
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
