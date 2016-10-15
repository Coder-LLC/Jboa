<%@page import="Entity.Voucher"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>查询报销单结果</title>
    
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
      
      <c:forEach var="employee" items="${sessionScope.employee }" varStatus="status">
      <div id="middle">
                        欢迎用户：     
          ${employee.name}
                        登陆
         <p align="right">   
           <a href="employeeQueryVoucher.action?status=1&employee_id=${employee.id}">未审核</a>&nbsp;&nbsp;
           <a href="employeeQueryVoucher.action?status=2&employee_id=${employee.id}">已通过</a>&nbsp;&nbsp;
           <a href="employeeQueryVoucher.action?status=3&employee_id=${employee.id}">未通过</a>
         </p>                     
      </div> 
      
      <div id="list">
        <center> 
          <table width="90%" height="75%" >
          <tr> <td width="25%">${message}</td> </tr>
            <c:forEach var="voucher" items="${requestScope.voucher }" varStatus="status">
              <tr>            
                <td width="23%">填报日期：${voucher.create_time }</td>
                <td width="21%">填报人：${voucher.employee2.name }</td>
                <td width="21%">总金额:${voucher.total_account }</td>
                <td width="20%">状态:${voucher.status }</td>
                <td width="15%">
                  <a href="getVoucherDetail.action?vid=${voucher.id}">查看&nbsp;</a>
                  <c:if test="${voucher.status=='未审核' }">
                    <a href="getVoucher.action?vid=${voucher.id}"> 修改&nbsp;</a>
                  </c:if>  
                  <!--  <a href="deleteVoucher.action?vid=${voucher.id}"> 删除&nbsp;</a>  -->
                </td>
              </tr>
            </c:forEach>
          </table>
          <c:if test="${requestScope.status==0 }">
            <form name="page" method="post" action="employeeQueryVoucher.action?employee_id=${employee_id}">
          </c:if>   
          <c:if test="${requestScope.status==1 }"> 
             <form name="page" method="post" action="employeeQueryVoucher.action?status=1&employee_id=${employee_id}">   
          </c:if>  
            <c:if test="${requestScope.status==2 }"> 
             <form name="page" method="post" action="employeeQueryVoucher.action?status=2&employee_id=${employee_id}">   
          </c:if>  
           <c:if test="${requestScope.status==3 }"> 
             <form name="page" method="post" action="employeeQueryVoucher.action?status=3&employee_id=${employee_id}">   
          </c:if>         
                                     页码: 
            <select name="pageNumber">    
            <%  
              //分页获取总页面
              if(request.getAttribute("totalpage")!=null){
                  int i=1;   
                  int totalpage=Integer.parseInt(request.getAttribute("totalpage").toString());
                  for( i = 1;i <= totalpage;i++) {
            %>
              <option> 
                 <%=i%> 
              </option>  
                <%}%>
            <%}%>
           </select> 
           <input type="submit"name="button"value="查询" />      
          </form>       
        </center>
      </div>
      </c:forEach>
      
      <div id="foot"> 
        <center>
          <p><strong>Copyright &nbsp; &copy; &nbsp; LLC</strong></p>
        </center>
      </div>
      
    </div>  
    
  </body>
</html>
