<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<title>Admin</title>


</head>
<body>
<div class="container">
	<div class="row">
		
        
        <form class="form-horizontal" action="user_registration" name="user_registration" method="post">
<fieldset>

<!-- Form Name -->
<legend>Register Here</legend>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="textinput">User ID</label>  
  <div class="col-md-4">
  <input id="textinput" name="user" placeholder="Insert your User ID" class="form-control input-md" required="" type="text">
  <span class="help-block"> </span>  
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="textinput">Name</label>  
  <div class="col-md-4">
  <input id="textinput" name="name" placeholder="Insert your Name" class="form-control input-md" required="" type="text">
  <span class="help-block"> </span>  
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="textinput">Email</label>  
  <div class="col-md-4">
  <input id="textinput" name="email" placeholder="Insert your Email" class="form-control input-md" required="" type="email">
  <span class="help-block"> </span>  
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="textinput">Contact Number</label>  
  <div class="col-md-4">
  <input id="textinput" name="contactinfo" placeholder="Insert your Contact Number" class="form-control input-md" required="" type="text">
  <span class="help-block"> </span>  
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="textinput">Password</label>  
  <div class="col-md-4">
  <input id="textinput" name="password" placeholder="Your Password" class="form-control input-md" required="" type="password">
  <span class="help-block"> </span>  
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="singlebutton"> </label>
  <div class="col-md-4">
  <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
    <button id="singlebutton" name="singlebutton" class="btn btn-primary">Register</button>
  </div>
</div>

</fieldset>
</form>
  
	</div>
</div>
</body>
</html>