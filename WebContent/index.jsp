<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Failsafe</title>
</head>
<script language="javascript">

		function redirectToUsers(elem) {
			elem.setAttribute
		}

		function callREST() {
			//alert(window.XMLHttpRequest);
		    //var xhttp = new XMLHttpRequest();
		    var xhttp = new ActiveXObject('Msxml2.XMLHTTP');
		    xhttp.onreadystatechange = function() {
		        if (this.readyState == 4 && this.status == 200) {
		            document.getElementById("response").innerHTML = this.responseText;
		        }
		    };
		    xhttp.open("GET", "http://localhost:8080/Failsafe/rest/failsafe/authenticate/1", true);
		    xhttp.send();
		    //alert("Error ->" + xhttp.status);
		   	//document.getElementById("div").innerHTML = "<table><tr><td>Remove ID</td><td><input id=\"remove-id\" type=\"text\"/></td>";
		}

</script>

<body>
Welcome to Failsafe
<form name="main" method="post" action="" onsubmit="redirectToUsers();">
	<input type="submit" value="Add user" name="Submit"/>
</form>
</body>
</html>