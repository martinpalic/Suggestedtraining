<html>
<body>
<h2>Hello World!</h2>

<%
    String attrinbute = (String) request.getAttribute("attribute");
    out.println("<h1>");
    out.println(attrinbute + "</h1>");
%>
</body>
</html>
