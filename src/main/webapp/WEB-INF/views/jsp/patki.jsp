<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sht" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><sht:message code="patki.title"/></title>
</head>
<body>

	<a href="?language=en">English</a>
	<a href="?language=es">Espan~~ol</a>
	
	<h1><sht:message code="patki.greeting"/> ${name}</h1>
	<p><sht:message code="patki.thisIsMy"/>:</p>
	<img src='./img/koza.jpg' width="300px" />

	<h3><sht:message code="patki.myPatki"/>:</h3>

	<ul>
		<c:forEach items="${patki}" var="patka" varStatus="loop">
			<li><a href="./patki/${loop.index}">${patka.name}</a> peche se na ${patka.gradusi} gradusa!</li>
		</c:forEach>
	</ul>

	<s:form commandName="newPatka">
		<s:input path='name' type="text" placeholder="Enter name"/> 
		<s:input path='gradusi' type="number" placeholder="Enter gradusi"/>
		
		<button><sht:message code="patki.addPatka"/></button>
	</s:form>

	
</body>
</html>