<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
  
    <title>查看报销单详细 </title>
    
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
       <p>
                        欢迎用户：         
            ${employee.name}                  
                         登陆
      </p>
      </div>
      
      <div id="list">
       <c:forEach var="voucherdetail" items="${requestScope.voucherdetail }" varStatus="status"> 
        <center>
          <table width="85%" >
            <tr>
              <td height="35px">创建人</td>
              <td>创建时间</td>   
              <td>事由</td>
              <td>总金额</td>
              <td>状态</td>       
            </tr>
           
            <tr>
              <td height="35px">${voucherdetail.employee2.name }</td>
              <td>${voucherdetail.create_time }</td>   
              <td>${voucherdetail.event }</td>
              <td>${voucherdetail.total_account }</td>
              <td>${voucherdetail.status }</td>             
            </tr>  
            
            <tr>
              <td colspan="5">事由：</td>
            </tr>
            
            <tr>
               <td colspan="5" ><textarea name="event" rows="7" cols="127" readonly="readonly" >${voucherdetail.status }</textarea></td>
            </tr>   
             
           
          </table>
        
        <table width="85%">
          <tr>
            <td width="25%" height="35px">申请人</td>  <td width="25%">项目</td>  <td width="25%">金额</td> <td width="25%">说明</td>
          </tr>
          <c:forEach var="voucherdetails" items="${requestScope.voucherdetails}" varStatus="status">
           <tr>
             <td width="25%" height="35px">${voucherdetails.voucher.employee2.name  }</td>
             <td width="25%">${voucherdetails.item }</td>
             <td width="25%">${voucherdetails.account }</td>
             <td width="25%">${voucherdetails.desc }</td>
           </tr>
          </c:forEach>
        </table>

        <c:if test="${voucherdetail.status=='未审核' }">
          <c:if test="${employee.position.name=='部门经理' }">
            <p><a href="getVoucherForCheck.action?voucher_id=${voucherdetail.id}">审核</a></p>
          </c:if>
        </c:if>
        
        <c:if test="${voucherdetail.status=='未审核' }">
           <c:if test="${employee.position.name=='总经理' }">
            <p><a href="getVoucherForCheck.action?voucher_id=${voucherdetail.id}">审核</a></p>
          </c:if>
        </c:if>
        
      </center>
      
       </c:forEach>
      </div> 
      </c:forEach>
      
      <div id="foot">
         <center>
          <p><strong>Copyright  &nbsp;   &copy;  &nbsp; LLC</strong></p>
        </center>
      </div> 
      
    </div>
    
  </body>
</html>
