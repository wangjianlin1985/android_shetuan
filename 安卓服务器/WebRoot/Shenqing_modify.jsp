<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%> 
<%@ page import="com.chengxusheji.domain.Shenqing" %>
<%@ page import="com.chengxusheji.domain.Shetuan" %>
<%@ page import="com.chengxusheji.domain.UserInfo" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    //获取所有的Shetuan信息
    List<Shetuan> shetuanList = (List<Shetuan>)request.getAttribute("shetuanList");
    //获取所有的UserInfo信息
    List<UserInfo> userInfoList = (List<UserInfo>)request.getAttribute("userInfoList");
    Shenqing shenqing = (Shenqing)request.getAttribute("shenqing");

    String username=(String)session.getAttribute("username");
    if(username==null){
        response.getWriter().println("<script>top.location.href='" + basePath + "login/login_view.action';</script>");
    }
%>
<HTML><HEAD><TITLE>修改社团申请</TITLE>
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
    <TD align="left" vAlign=top ><s:form action="Shenqing/Shenqing_ModifyShenqing.action" method="post" onsubmit="return checkForm();" enctype="multipart/form-data" name="form1">
<table width='100%' cellspacing='1' cellpadding='3' class="tablewidth">
  <tr>
    <td width=30%>申请id:</td>
    <td width=70%><input id="shenqing.shenqingId" name="shenqing.shenqingId" type="text" value="<%=shenqing.getShenqingId() %>" readOnly /></td>
  </tr>

  <tr>
    <td width=30%>申请的社团:</td>
    <td width=70%>
      <select name="shenqing.shentuanObj.stUserName">
      <%
        for(Shetuan shetuan:shetuanList) {
          String selected = "";
          if(shetuan.getStUserName().equals(shenqing.getShentuanObj().getStUserName()))
            selected = "selected";
      %>
          <option value='<%=shetuan.getStUserName() %>' <%=selected %>><%=shetuan.getShetuanName() %></option>
      <%
        }
      %>
    </td>
  </tr>

  <tr>
    <td width=30%>姓名:</td>
    <td width=70%><input id="shenqing.name" name="shenqing.name" type="text" size="20" value='<%=shenqing.getName() %>'/></td>
  </tr>

  <tr>
    <td width=30%>学号:</td>
    <td width=70%><input id="shenqing.xuehao" name="shenqing.xuehao" type="text" size="20" value='<%=shenqing.getXuehao() %>'/></td>
  </tr>

  <tr>
    <td width=30%>主要事迹:</td>
    <td width=70%><textarea id="shenqing.zysj" name="shenqing.zysj" rows=5 cols=50><%=shenqing.getZysj() %></textarea></td>
  </tr>

  <tr>
    <td width=30%>入会原因:</td>
    <td width=70%><textarea id="shenqing.rhyy" name="shenqing.rhyy" rows=5 cols=50><%=shenqing.getRhyy() %></textarea></td>
  </tr>

  <tr>
    <td width=30%>申请人:</td>
    <td width=70%>
      <select name="shenqing.userObj.user_name">
      <%
        for(UserInfo userInfo:userInfoList) {
          String selected = "";
          if(userInfo.getUser_name().equals(shenqing.getUserObj().getUser_name()))
            selected = "selected";
      %>
          <option value='<%=userInfo.getUser_name() %>' <%=selected %>><%=userInfo.getName() %></option>
      <%
        }
      %>
    </td>
  </tr>

  <tr>
    <td width=30%>申请时间:</td>
    <td width=70%><input id="shenqing.sqTime" name="shenqing.sqTime" type="text" size="20" value='<%=shenqing.getSqTime() %>'/></td>
  </tr>

  <tr>
    <td width=30%>审核状态:</td>
    <td width=70%><input id="shenqing.shenHeState" name="shenqing.shenHeState" type="text" size="20" value='<%=shenqing.getShenHeState() %>'/></td>
  </tr>

  <tr>
    <td width=30%>审核结果:</td>
    <td width=70%><textarea id="shenqing.shenHeResult" name="shenqing.shenHeResult" rows=5 cols=50><%=shenqing.getShenHeResult() %></textarea></td>
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
