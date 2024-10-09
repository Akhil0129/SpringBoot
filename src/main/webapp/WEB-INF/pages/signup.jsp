<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Login Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>
<header style="height: 30px;background-color: yellow;">
</header>

<div class="container">
<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSEN001Q5AoJ2REqLeVFIjweNX-DXvXVA1FDA&s"/>
<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSEN001Q5AoJ2REqLeVFIjweNX-DXvXVA1FDA&s"/>
<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSEN001Q5AoJ2REqLeVFIjweNX-DXvXVA1FDA&s"/>
 
 <h2 style="background-color: fuchsia;">Signup Page!!!</h2>
 <hr/>
 <h3 style="color: red">${message}</h3>
   <form action="signup" method="post"enctype="multipart/form-data">
     <div style="width: 50%">
	   <h5>FirstName :</h5>
	   <input required="required" type="text" class="form-control" name="firstName"/> 
	      <h5>LastName :</h5>
	   <input required="required" type="text" class="form-control" name="lastName"/> 
	    <h5>Password :</h5>
	   <input required="required" type="password" class="form-control"  name="password"/> 
	    <h5>Email :</h5>
	   
	   <input required="required" type="email" class="form-control"  name="email"/> 
	   	<input  type="file" class="form-control"  name="file"/> 
	   
	    <hr/>
	   <button type="submit" class="btn btn-primary">Signup!</button>
	   </div>
   </form>
   <hr/>
 </div>
</body>
</html>

