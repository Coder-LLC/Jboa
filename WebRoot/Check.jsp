<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>部门经理审核报销单</title>
    
	
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
        
      </div>
      
      <div id="list">
        <center>
          <form name="check" method="post" action="getCheckResult.action?voucher_id=${requestScope.voucher_id }&position=${employee.position.name}&employee_id=${employee.id}"> 
                                   审核结果：
             <p>
             <select name="result">
               <option value="已通过">同意</option>
               <option value="未通过">不同意</option>    
             </select>
             </p>  
             <textarea name="comment" rows="7" cols="80"></textarea>
             <p> <input type="submit" value="提交"> <input type="reset" value="清除"> </p>
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
