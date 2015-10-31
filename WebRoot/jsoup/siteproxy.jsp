<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="org.jsoup.Jsoup" %>
<%@ page import="org.jsoup.nodes.*" %>
<%@ page import="org.jsoup.select.Elements" %>
<head>
<title>jsoup Demo</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>
<header> <h1> GBin1 最新文章 </h1> </header>

<ul id="result">
<%
		String url = request.getParameter("url");
		String elem = request.getParameter("elem");
		try{
			Document doc = Jsoup.connect(url).timeout(0).get();
			Elements items = doc.select(elem);
			for (Element item : items) {
				  Elements links = item.select("a");
				  for(Element link: links){
					  link.attr("href",link.attr("abs:href"));
				  }
				  
				  Elements imgs = item.select("img");
				  for(Element img: imgs){
					  img.attr("src",img.attr("abs:src"));
				  }
				  String html = item.html();
				  out.println("<li class=\"item\">" + html + "</li>");
			}

		}catch(Exception e){
			e.printStackTrace();
		}
%>
</ul>
</body>
</html>