<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%>
<%@ page import="com.chengxusheji.domain.Shetuan" %>
<%@ page import="com.chengxusheji.domain.UserInfo" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    //获取所有的Shetuan信息
    List<Shetuan> shetuanList = (List<Shetuan>)request.getAttribute("shetuanList");
    //获取所有的UserInfo信息
    List<UserInfo> userInfoList = (List<UserInfo>)request.getAttribute("userInfoList");
    String username=(String)session.getAttribute("username");
    if(username==null){
        response.getWriter().println("<script>top.location.href='" + basePath + "login/login_view.action';</script>");
    }
%>
<HTML><HEAD><TITLE>添加社团申请</TITLE> 
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
    var name = document.getElementById("shenqing.name").value;
    if(name=="") {
        alert('请输入姓名!');
        return false;
    }
    var xuehao = document.getElementById("shenqing.xuehao").value;
    if(xuehao=="") {
        alert('请输入学号!');
        return false;
    }
    var zysj = document.getElementById("shenqing.zysj").value;
    if(zysj=="") {
        alert('请输入主要事迹!');
        return false;
    }
    var rhyy = document.getElementById("shenqing.rhyy").value;
    if(rhyy=="") {
        alert('请输入入会原因!');
        return false;
    }
    var sqTime = document.getElementById("shenqing.sqTime").value;
    if(sqTime=="") {
        alert('请输入申请时间!');
        return false;
    }
    var shenHeState = document.getElementById("shenqing.shenHeState").value;
    if(shenHeState=="") {
        alert('请输入审核状态!');
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
    <s:form action="Shenqing/Shenqing_AddShenqing.action" method="post" id="shenqingAddForm" onsubmit="return checkForm();"  enctype="multipart/form-data" name="form1">
<table width='100%' cellspacing='1' cellpadding='3' class="tablewidth">

  <tr>
    <td width=30%>申请的社团:</td>
    <td width=70%>
      <select name="shenqing.shentuanObj.stUserName">
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
    <td width=30%>姓名:</td>
    <td width=70%><input id="shenqing.name" name="shenqing.name" type="text" size="20" /></td>
  </tr>

  <tr>
    <td width=30%>学号:</td>
    <td width=70%><input id="shenqing.xuehao" name="shenqing.xuehao" type="text" size="20" /></td>
  </tr>

  <tr>
    <td width=30%>主要事迹:</td>
    <td width=70%><textarea id="shenqing.zysj" name="shenqing.zysj" rows="5" cols="50"></textarea></td>
  </tr>

  <tr>
    <td width=30%>入会原因:</td>
    <td width=70%><textarea id="shenqing.rhyy" name="shenqing.rhyy" rows="5" cols="50"></textarea></td>
  </tr>

  <tr>
    <td width=30%>申请人:</td>
    <td width=70%>
      <select name="shenqing.userObj.user_name">
      <%
        for(UserInfo userInfo:userInfoList) {
      %>
          <option value='<%=userInfo.getUser_name() %>'><%=userInfo.getName() %></option>
      <%
        }
      %>
    </td>
  </tr>

  <tr>
    <td width=30%>申请时间:</td>
    <td width=70%><input id="shenqing.sqTime" name="shenqing.sqTime" type="text" size="20" /></td>
  </tr>

  <tr>
    <td width=30%>审核状态:</td>
    <td width=70%><input id="shenqing.shenHeState" name="shenqing.shenHeState" type="text" size="20" /></td>
  </tr>

  <tr>
    <td width=30%>审核结果:</td>
    <td width=70%><textarea id="shenqing.shenHeResult" name="shenqing.shenHeResult" rows="5" cols="50"></textarea></td>
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
