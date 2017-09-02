<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">



<head>
	<title>Main Menu</title>
	<meta http-equiv="content-type" 
		content="text/html;charset=utf-8" />
	<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<link rel="stylesheet" href="/jadrn050/login_example/tabs.css">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="/jquery/jquery.js"></script>
	<script src="/jadrn050/login_example/logout.js"></script>
	<script src="/jadrn050/login_example/tabs.js"></script>
	<script src="/jadrn050/login_example/add_json.js"></script>
	<script src="/jadrn050/login_example/edit_json.js"></script>
	<script src="/jadrn050/login_example/validation.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 
	    
      
</head>

<body>

<h2 class="mainhead">Sai Laptop WareHouse</h2>
	<div align="center" id="logout"><a href="/jadrn050/servlet/Logout">Logout Now</a></div>
	<div id="raw"></div>
	<ul class="tab">
	  <li><a href="javascript:void(0)" class="tablinks active" onclick="openCity(event, 'AddItem')">Merchandise In</a></li>
	  <li><a href="javascript:void(0)" class="tablinks" onclick="openCity(event, 'EditItem')">Merchandise Out</a></li>
	</ul>

	<div id="AddItem" class="tabcontent" align="center" style="display : block">
	  <h2 class="mainhead">Merchandise In</h2>
	  <h3 id="addstatus"><%= session.getAttribute("message")%></h3>
		  <form id="formverify" method="post" action="/jadrn050/servlet/Menu">
			<table>
			<tr>
				<td>SKU</td>
				<td><input type="text" name="skuinput" size="20" id="skuinput" required pattern="[A-Z]{3}-[0-9]{3}" oninvalid="setCustomValidity('This field is required and please enter 3 letter followed by - and 3 letters eg: ABC-123')"></td>
				<td id="skumessage"><%= session.getAttribute("skumessage")%></td>
			</tr>
			<tr>
				<td>Date</td>
				<td><input type="text" id="adddate" name="adddate" size="20" required></td>
				
			</tr>
			<tr>
				<td>Quantity</td>
				<td><input type="text" id="addquantity" name="addquantity" size="20" required></td>
				<td id="addquantitymessage"></td>
			</tr>
			<tr>
                <td><input class="button" type="reset" id="addresetbutt"/></td>
                <td><input class="button" type="submit" id="addSubmit" value="Submit" /></td>
            </tr>
		</table>
			 
			<table id="adddiv" frame="box">
			<tr><th>Product Information</th></tr>
			<tr>
				<td>SKU</td>
				<td>
				<p name="addskuinputp" id="addskuinputp"></p>
				</td>
			</tr>
			
			<tr>
				<td>Category</td>
				<td>
				<p name="addcategoryinput" id="addcategoryinputp"></p>
				</td>
			</tr>
			
			<tr>
				<td>Vendor</td>
				<td>
				<p name="addvendorinput" id="addvendorinputp"></p>
				</td>
			</tr>
			<tr>
				<td>Vendor Model</td>
				<td><p id="addvendormodel" name="addvendormodel" size="20"></p></td>
				
			</tr>
			<tr>
				<td>Description</td>
				<td><p name="adddescription" id="adddescription"></p></td>
			</tr>
			<tr>
				<td>Features</td>
				<td><p id="addfeatures" name="addfeatures"></p></td>
			</tr>
			<tr>
				<td>Cost</td>
				<td><p id="addcost" name="addcost" ></p></td>
			</tr>
			<tr>
				<td>Retail</td>
				<td><p id="addretail" name="addretail"></p></td>
			</tr>
			<tr>
				<td>
						<div id="addchangeimage"></div>
				</td>
			</tr>
			
		</table>
		

			
		  </form>

		
		
	</div>

	<div id="EditItem" align="center" class="tabcontent">
	<h2 class="mainhead">Merchandise Out</h2>
	  <h3 id="editstatus"></h3>
	  <div id="pic"></div>
	  <div id="resetbutt"></div>
		  <form id="formverifyout" method="post" action="/jadrn050/servlet/MerchandiseOut">
			<table>
			<tr>
				<td>SKU</td>
				<td><input type="text" name="outskuinput" size="20" id="outskuinput" required pattern="[A-Z]{3}-[0-9]{3}" oninvalid="setCustomValidity('This field is required and please enter 3 letter followed by - and 3 letters eg: ABC-123')"></td>
				<td id="outskumessage"><%= session.getAttribute("skumessage") %></td>
			</tr>
			<tr>
				<td>Date</td>
				<td><input type="text" id="outdate" name="outdate" size="20" required></td>
			</tr>
			<tr>
				<td>Quantity</td>
				<td><input type="text" id="outquantity" name="outquantity" size="20" required></td>
				<td id="outquantitymessage"></td>
			</tr>
			<tr>
                <td><input class="button" type="reset" /></td>
                <td><input class="button" type="submit" type="deleteSubmit" value="Add" /></td>
            </tr>
			<tr><td id="message"><%= session.getAttribute("message") %></td></tr>
		</table>
			 
			<table id="outdiv" frame="box">
			<tr><th>Product Information</th></tr>
			<tr>
				<td>SKU</td>
				<td>
				<p name="outskuinputp" id="outskuinputp"></p>
				</td>
			</tr>
			
			<tr>
				<td>Category</td>
				<td>
				<p name="outcategoryinput" id="outcategoryinputp"></p>
				</td>
			</tr>
			
			<tr>
				<td>Vendor</td>
				<td>
				<p name="outvendorinput" id="outvendorinputp"></p>
				</td>
			</tr>
			<tr>
				<td>Vendor Model</td>
				<td><p id="outvendormodel" name="outvendormodel" size="20"></p></td>
				
			</tr>
			<tr>
				<td>Description</td>
				<td><p name="outdescription" id="outdescription"></p></td>
			</tr>
			<tr>
				<td>Features</td>
				<td><p id="outfeatures" name="outfeatures"></p></td>
			</tr>
			<tr>
				<td>Cost</td>
				<td><p id="outcost" name="outcost" ></p></td>
			</tr>
			<tr>
				<td>Retail</td>
				<td><p id="outretail" name="outretail"></p></td>
			</tr>
			<tr>
				<td>
						<div id="outchangeimage"></div>
				</td>
			</tr>
			
		</table>
		

			
		  </form>

	  
	</div>

   
<% if(session.getAttribute("username") == null)
	response.sendRedirect("/jadrn050/jsp/login_err.jsp");
%>
    <div>
        <h2>You have Logged In !!!</h2>
	<h3>Welcome <%= session.getAttribute("username") %></h3>
	<h3>The message is: <%= session.getAttribute("message") %>
	<h3>Your session id is <%= session.getId() %>



        <div id="status">
        </div>
	
    </div>
    
</body>
</html>

