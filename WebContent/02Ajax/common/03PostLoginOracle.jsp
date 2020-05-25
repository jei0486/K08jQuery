<%@page import="org.json.simple.JSONObject"%>
<%@page import="controller.OracleDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--PostOracle.jsp --%>  

<%@page trimDirectiveWhitespaces="true" %>
<%
String id = request.getParameter("user_id");
String pw = request.getParameter("user_pw");

OracleDAO dao = new OracleDAO();
boolean isMember = dao.isMember(id, pw);
JSONObject json = new JSONObject();


if(isMember==true){
	json.put("result",1);
	json.put("message","로그인 성공입니다.");
	
	String html = "";
	html += "<table class = 'table table-bordered' style='width: 300px;'>";
	html += "<tr>";
	html += "<td>회원님 반갑습니다</td>";
	html += "</tr>";
	html += "</table>";
	
	json.put("html", html);
}else{
	json.put("result", 0);
	json.put("message", "로그인 실패입니다.");
}
//콜백 데이터는 스트링형태만 가능하다 객체는 못넘겨줌 그래서 toString을 사용함
String jsonStr = json.toJSONString();
out.println(jsonStr);
%>

  
    
