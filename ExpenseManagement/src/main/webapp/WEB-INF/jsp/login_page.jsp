
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en-US">
<head>
<title>Login User</title>
		
	  <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
		<meta http-equiv="content-language" content="en-us" />
		
		<link rel="stylesheet" type="text/css"	href="/css/bootstrap.min.css"> 
    	<link rel="stylesheet" type="text/css"	href="/css/metro-bootstrap.css">
		<link rel="stylesheet" type="text/css"	href="/css/jquery-ui.css" />
		<link rel="stylesheet" type="text/css" href="/css/login.css">
		<link rel="stylesheet" type="text/css" href="/css/dialog.css" />
		<script type="text/javascript" src="/js/jquery 2.0.3.js"></script>
		<!--<script type="text/javascript"  />/jstz-1.0.4.min.js"></script>-->

	</head>
	<body>

		
				<input type="hidden" id="consent" value="">
	
		<div class="main_wrapper clearfix">
		<a href='C:/Users/OCR1P/Desktop/New folder/register_page.html'><button type="button" class="regbutton" >Register</button>
			<div class="wrapper_body">
			
				<form name="login_form" id="login_form" method="post">
					
					
					<table cellspacing="0" cellpadding="0" class="login_panel">
			
							<td><input id="userName" name="username" type="text" placeholder="Login Email Id" >
							<!-- For to set cursor at the textbox -->
							<script>document.getElementById('userName').focus()</script></td>
						</tr>
						<tr>
							<td><input id="password" name="password" type="Password" placeholder="Password" onkeypress="enterKeyPressed(event);"></td>
						</tr>
						<tr>
							<td width="500px">
								
								       		
								       		
								
							</td>
						</tr>	
						<tr>
							<td><div id="error_msg" style="display: block; clear: both; color: red;"></div></td>
						</tr>
						<tr>
							<td><button type="button" class="btn btn-primary" onclick="performLogin()" style="background: #02b281;padding: 10px;width: 100%;color: #fff;font-size: 20px !important;text-align: center;border: 1px solid #02b281;">Login</button></td>
						</tr>
						<tr>
							<td>

</td>
						</tr>
					</table> 
				</form>
			</div>  
			</div>
	</body>
</html>

