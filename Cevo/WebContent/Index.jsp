<%@page import="org.knoesis.Cevo.Result.Result"%>
<%@page import="org.knoesis.Cevo.VerbTagger.VerbTagger"%>
<%@page import="java.util.List"%>
<html>
<head>
<title>JSP Test - SubmitTest.jsp</title>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<div class="container-fluid">
<h1>Lighter, Secondary Text</h1>
  <p>The small element is used to create a lighter, secondary text in any heading:</p>
<%!VerbTagger vb = null;
List<Result> result = null;
String myText =null;
%>
<body>
<form action="Index.jsp" >
<div class="form-group">
<textarea rows="10" cols="30" name="a"  class="form-control">

</textarea> <br/> 
<input type="Submit" onclick="document.getElementById('demo').innerHTML = 'Hello JavaScript!'" class="btn btn-success" value="Annotate">
</div>
</form>
<br>
<%
String myText = request.getParameter("a");
if (myText != null) {
vb = new VerbTagger();
result = vb.pos(myText);
%>


<table class="table table-striped">
<tr><th>Word</th><th>PartOfSpeechAnnotation</th><th>OffsetBegin</th><th>OffsetEnd</th><th>lemma</th><th>verbClass</th><th>verbUri</th></tr>
<% for (Result r: result) { %>

<% if (r.getPartOfSpeechAnnotation().startsWith("N"))  {%>
<tr><td><%=r.getWord() %></td>
<td><%=r.getPartOfSpeechAnnotation() %></td>
<td><%=r.getOffsetBegin() %></td>
<td><%=r.getOffsetEnd() %></td>
<td><%=r.getLemma() %></td>
<td> <a href="#" data-toggle="popover" data-trigger="focus" data-content=<%=r.getVebUri() %>><%=r.getVerbClass() %></a></td>
<td><%=r.getVebUri() %></td></tr>

<%} %>
<%} %>

<% }%> 
</table>
</div>
<script>
$(document).ready(function(){
	$('[data-toggle="popover"]')
    .popover()
    .click(function(e) { 
        e.preventDefault(); 
    });
   /*$('[data-toggle="popover"]').popover();  */ 
    
});
</script>
</body>
</html> 