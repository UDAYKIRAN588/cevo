<%@page import="org.knoesis.Cevo.Result.Result"%>
<%@page import="org.knoesis.Cevo.VerbTagger.VerbTagger"%>
<%@page import="java.util.List"%>
<html>
<head>
<link >
<title>JSP Test - SubmitTest.jsp</title>
</head>
<%!VerbTagger vb = null;
List<Result> result = null;
String myText =null;
%>
<body>
<form action="Index.jsp" >

<textarea rows="4" cols="50" name="a" id="a" onfocus="alert(uday)">
uday
</textarea> <br/>
<input type="Submit" value="Click to Submit">
hello
</form>
<br>
<%
String myText = request.getParameter("a");
if (myText != null) {
vb = new VerbTagger();
result = vb.pos(myText);
%>


<table border="5">
<tr><td>Word</td><td>PartOfSpeechAnnotation</td><td>OffsetBegin</td><td>OffsetEnd</td><td>lemma</td><td>verbClass</td><td>verbUri</td></tr>
<% for (Result r: result) { %>
<tr><td><%=r.getWord() %></td>
<td><%=r.getPartOfSpeechAnnotation() %></td>
<td><%=r.getOffsetBegin() %></td>
<td><%=r.getOffsetEnd() %></td>
<td><%=r.getLemma() %></td>
<td><%=r.getVerbClass() %></td>
<td><%=r.getVebUri() %></td></tr>

<%} %>

<% }%> 
<>
</body>
</html> 