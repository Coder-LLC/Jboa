<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>添加报销单</title>

     <style>
      #main{width:100%;border:0px;height:600px;}
	  #top{width:100%;border:0px;height:100px;}
	  #middle{width:100%;border:0px;height:50px}
	  #list{width:100%;;border:0px;height:400px}
	  #foot{width:100%;border:0px;height:50px}
    </style>
    
    <script type="text/javascript">
      function check(){
	    if(document.voucher.creater.value==""){
		  alert("填写人不能为空");
		  return false;
	    }
	    if(document.voucher.totalacount.value==""){
		  alert("总金额不能为空");
		  return false;
            }
	    if(document.voucher.create_time.value==""){
		   alert("填报时间不能为空");
		   return false;
	    }
	    if(document.voucher.status.value==""){
		   alert("状态不能为空");
		   return false;
	    }
	    if(document.voucher.event.value==""){
			alert("事由不能为空");
			return false;
	    }
        var item=document.getElementsByName("item");
		for(var i=0;i<item.length;i++){
			if(item[i].value==""){
				alert("项目不能为空");
				return false;
			}
	    }
	    var account=document.getElementsByName("account");
		for(var j=0;j<account.length;j++){
			if(account[j].value==""){
				alert("金额不能为空");
				return false;
			}
		}
	    var desc=document.getElementsByName("desc");
		for(var k=0;k<account.length;k++){
			if(desc[k].value==""){
				alert("费用说明不能为空");
				return false;
			}
		  }
      }
    
      function addOne(){
		var one=document.getElementById("one");
		var newone=one.cloneNode(true);
		document.getElementById("tab").appendChild(newone);
		var height1=document.getElementById("main").clientHeight;
		document.getElementById("main").style.height=height1+25;
		document.getElementById("list").style.height=height1+25;
	  }
    
	  function deleteone(){
		var v=document.getElementById("tab").rows;//返回表格中所有行的数组
		if(v.length>2){
		    document.getElementById("tab").deleteRow(v.length-1);
		}
	  }
    </script>
    
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
        <form name="voucher" method="post" action="addVoucher.action" onsubmit="return check()">
          <table width="85%">
            <tr>
              <td width="50%" height="30"><strong>基本信息</strong></td>
            </tr>
            
            <tr>
              <td height="35px">填写人：<input name="creater" type="text"> </td>
           	  <td width="50%">总金额：<input name="totalacount" type="text"> </td>
            </tr>
            
            <tr>
              <td height="35px">填报时间：<input name="create_time" type="text"></td>
              <td> 
                状态：
                <select name="status"> <option selected="selected">未审核</option> </select>
              </td>
            </tr>
            
            <tr>
              <td colspan="2">事由：</td>
            </tr>
            
            <tr>
               <td colspan="2" > <textarea name="event" rows="7" cols="127"></textarea> </td>
            </tr>   
            
          </table>
          
          <table width="85%" id="tab">
            <tr>
              <td height="35px"><strong>项目</strong></td>
              <td><strong>金额</strong></td>
              <td><strong>费用说明</strong></td>
            </tr>
            
            <tr id="one">
              <td height="35px"><input name="item" type="text"></td>
              <td><input name="account" type="text"></td>
              <td><input name="desc" type="text"></td>
            </tr>
          </table>   
          <p><input type="button" value="添加栏" onClick="addOne()"> <input type="button" value="删除栏" onClick="deleteone()"> </p>
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
