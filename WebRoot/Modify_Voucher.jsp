<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    
    <title>修改报销单</title>
    
    <style>
      #main{width:100%;border:0px;height:700px;}
	  #top{width:100%;border:0px;height:100px;}
	  #middle{width:100%;border:0px;height:50px}
	  #list{width:100%;;border:0px;height:500px}
	  #foot{width:100%;border:0px;height:50px}
    </style>
    
  </head>
   <% 
     List login=(List) session.getAttribute("employee");
     if(login==null){
       request.setAttribute("message", "你无权访问此页面，请登陆后重试");
       request.getRequestDispatcher("Login.jsp").forward(request, response);
     }
   %> 
  <body>
    <div id="main">
      <div id="top">
        <table width="100%" height="100%">
          <tr>       
             <td> <center> <strong >欢迎使用Jboa办公自动化系统</strong> </center> </td>
          </tr>
        </table>
      </div>
      
      <div id="middle">
                        欢迎用户：
        <c:forEach var="employee" items="${sessionScope.employee }" varStatus="status">
          ${employee.name}
        </c:forEach>
                        登陆
      </div>
      
      <div id="list">
        <center>
        ${message}
        <form name="voucher" method="post" action="midifyVoucher.action?vid=${requestScope.vid}" onsubmit="return check()">
          <table width="85%">
            <tr>
              <td width="50%" height="30"><strong>基本信息</strong></td>
            </tr>
            
            <c:forEach var="voucherdetail" items="${requestScope.voucherdetail }" varStatus="status">
            <tr>
              <td height="35px">填写人：<input name="creater" type="text" value="${voucherdetail.employee2.name}"> </td>
           	  <td width="50%">总金额：<input name="totalacount" type="text" value="${voucherdetail.total_account }"> </td>
            </tr>
            
            <tr>
              <td height="35px">填报时间：<input name="create_time" type="text" value="${voucherdetail.create_time }"></td>
              <td> 状态：<input name="status" type="text" value="${voucherdetail.status }" readonly="readonly"></td>
            </tr>
            
            <tr>
              <td colspan="2">事由：</td>
            </tr>
            
            <tr>
               <td colspan="2" > <textarea name="event" rows="7" cols="127"> ${voucherdetail.event } </textarea></td>
            </tr> 
            </c:forEach>
          </table>
          
          <table width="85%">
            <tr>
              <td height="35px"><strong>项目</strong></td>
              <td><strong>金额</strong></td>
              <td><strong>费用说明</strong></td>
            </tr>
            
            <c:forEach var="voucherdetails" items="${requestScope.voucherdetails}" varStatus="status">
            <tr>
              <td height="35px"><input name="item" type="text" value="${voucherdetails.item}"></td>
              <td><input name="account" type="text" value="${voucherdetails.account }"></td>
              <td><input name="desc" type="text" value="${voucherdetails.desc }"></td>
            </tr>
            </c:forEach>
            
          </table>
          
          <p> <input type="submit" value="提交"> <input type="reset" value="清除"> </p>
        </form>
        </center>
      </div>
      
      <div id="foot">
        <center>
          <p><strong>Copyright  &nbsp;   &copy;  &nbsp; LLC</strong></p>
        </center>
      </div>
      
    </div>
    
  </body>
</html>
