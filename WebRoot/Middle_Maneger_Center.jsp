<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="Entity.Employee"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>部门经理中心</title>
    
    <script type="text/javascript">
      function check(){
        alert('即将注销，确定后退出该页面。');
        window.location.href='Employee_Center.jsp';
      }
    </script>
    
  </head>
  
  <% 
    List login=(List) session.getAttribute("employee");
    if(login==null){
       request.setAttribute("message", "你无权访问此页面，请登陆后重试");
       request.getRequestDispatcher("Login.jsp").forward(request, response);
    }
    else if(login!=null){
    	Employee employee=new Employee();
        Iterator <Employee> iterator=login.iterator();
        employee=iterator.next();
        if(employee.getPosition().getName().equals("部门经理")!=true){
           request.setAttribute("message", "你无权访问此页面，请登陆后重试");
           request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }
  %>
  
  <body>
   <c:forEach var="employee" items="${sessionScope.employee }" varStatus="status">
     <p>
                     欢迎用户： 
         ${employee.name} 
                     登陆
     </p>
     <p>${message }</p>
     
     <p> <a href="middleManegerQueryVoucher.action?department_id=${employee.department.id}">查询报销单 </a> </p>
   
     <p><a href="employeeLoginOut.action" onclick="check()">注销</a></p>
   </c:forEach>
   
 </body>
 
</html>
