<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%> 
<%@ page import="com.chengxusheji.domain.Shenqing" %>
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
    Shenqing shenqing = (Shenqing)request.getAttribute("shenqing");

%>
<HTML><HEAD><TITLE>查看社团申请</TITLE>
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
    <td width=30%>申请id:</td>
    <td width=70%><%=shenqing.getShenqingId() %></td>
  </tr>

  <tr>
    <td width=30%>申请的社团:</td>
    <td width=70%>
      <%=shenqing.getShentuanObj().getShetuanName() %>
    </td>
  </tr>

  <tr>
    <td width=30%>姓名:</td>
    <td width=70%><%=shenqing.getName() %></td>
  </tr>

  <tr>
    <td width=30%>学号:</td>
    <td width=70%><%=shenqing.getXuehao() %></td>
  </tr>

  <tr>
    <td width=30%>主要事迹:</td>
    <td width=70%><%=shenqing.getZysj() %></td>
  </tr>

  <tr>
    <td width=30%>入会原因:</td>
    <td width=70%><%=shenqing.getRhyy() %></td>
  </tr>

  <tr>
    <td width=30%>申请人:</td>
    <td width=70%>
      <%=shenqing.getUserObj().getName() %>
    </td>
  </tr>

  <tr>
    <td width=30%>申请时间:</td>
    <td width=70%><%=shenqing.getSqTime() %></td>
  </tr>

  <tr>
    <td width=30%>审核状态:</td>
    <td width=70%><%=shenqing.getShenHeState() %></td>
  </tr>

  <tr>
    <td width=30%>审核结果:</td>
    <td width=70%><%=shenqing.getShenHeResult() %></td>
  </tr>

  <tr>
      <td colspan="4" align="center">
        <input type="button" value="返回" onclick="history.back();"/>
      </td>
    </tr>

</table>
</s:form>
   </TD></TR>
  </TBODY>
</TABLE>
</BODY>
</HTML>
