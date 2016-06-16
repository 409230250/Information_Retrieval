<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.*"%>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="javascript/tooltip.js"></script>




<%@ include file="NavBar.jsp"%>


<div id="main_content">
   <div class="container">
	<div class="col-md-12">
		<div class="row" >
			<fieldset class="field">
			  <legend align="center">Search "${SearchString}": top ${size} results</legend>
			<div class="thumbnail" style="margin: auto;
    									width: 73%;
    									border: 3px solid #FFF;
    									padding: 10px;">
				<div>
					<%
                		int label = 1;
                    %>
					<c:forEach var="DOC" items="${docInfo}">
					
					  <b><a href=${DOC.docURL} target="_blank"><p style="font-size: 135%"><%=label%>. ${DOC.docName}</p></a></b>
					  <p style="color:green">URL: ${DOC.docURL}</p>
					  <p style="font-size: 100%">${DOC.docBody}</p><br/>
					  <br/>
					  <%label++;%>
					</c:forEach>
				</div>
			</div>
			</fieldset>
		</div>
	</div>
</div></div>


