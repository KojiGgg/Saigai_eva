<!DOCTYPE HTML>
<html>
<head>
<title>サーバ通信</title>
<script type="text/javascript" charset="utf-8" src="cordova-2.0.0.js">
function postXml(){
	var req = new XMLHttpRequest();
	if (req) {
		req.onreadystatechange = function(){
			if (req.readyState == 4 && req.status == 200) {
				alert(req.responseText);
			}};
			var url = "dump.php";
			//req.setRequestHeader("content-type","text/xml");
			req.open('POST', url);
			req.send('<?xml version="1.0" encoding="UTF-8" ?>\n'+
			'<data-source type="info.fractus.coli.da.HsqlPoolDataAccess">\n'+
			'<property name="userid" value="00000"'
			'<property name="password" value="12345"/>\n'+
			'<property name="url" value="jdbc:hsqldb:"/>\n'+
			'<property name="driverClassName" value="org.hsqldb.jdbcDriver"/>\n'+
			'<property name="path" value="/WEB-INF/db"/>\n'+
			'<property name="username" value="sa"/>\n'+
			'</data-source>');
	}
}
</script>
</head>
<body><h1>XML送信中</h1></body>
</html>
