<!doctype html>
<html lang="en" >
  <head>
    <meta charset="utf-8">
    <title>My HTML File</title>
        <!-- Loading Bootstrap -->
    <link href="dist/css/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Loading Flat UI -->
    <link href="dist/css/flat-ui.css" rel="stylesheet">

    <link rel="shortcut icon" href="../../dist/img/favicon.ico">
    <script src="bower_components/angular/angular.js"></script>
    <script src="js/app.js"></script>
  </head>
  <body ng-app="customerApp" ng-controller="RegistrationController">
    <style>
      body {
        min-height: 2000px;
        padding-top: 70px;
      }
    </style>
        <div class="navbar navbar-default navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
          </button>
          <a class="navbar-brand" href="#">SAMPLE</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="/customer_manager">Home</a></li>
            <li><a href="/customer_manager/signup">Sign Up</a></li>
            <li><a href="#contact">Contact</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="#">Action</a></li>
                <li><a href="#">Another action</a></li>
                <li><a href="">Something else here</a></li>
                <li class="divider"></li>
                <li class="dropdown-header">Nav header</li>
                <li><a href="#">Separated link</a></li>
                <li><a href="#">One more separated link</a></li>
              </ul>
            </li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><a href="../navbar/">Default</a></li>
            <li class="active"><a href="./">Static top</a></li>
            <li><a href="../navbar-fixed-top/">Fixed top</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
   <ul>
   <form name="myform"
   method="POST"
   action="/customer_manager/customers"
    ng-submit="register()"
    novalidate>

    <div class="textintro">
      <h1>Form Validation</h1>
      <p>Please fill out the following form</p>
      <p ng-show="message">{{ message }}</p>
    </div>

    <fieldset>
    <div>
      <input type="text" name="firstname" 
        placeholder="First Name"
        ng-model="user.firstname"
        ng-required="true">
        <p class="error validationerror" ng-show="myform.firstname.$invalid && myform.firstname.$touched">
        You must fill out your first name.</p>
     </div>
     <div>
      <input type="text" name="lastname"
        placeholder="Last Name" 
        ng-model="user.lastname"
        ng-required="true">
        <p class="error validationerror" ng-show="myform.lastname.$invalid && myform.lastname.$touched">
        You must fill out your last name.</p>
     </div>
      <div>
      <input type="email" name="email"
        placeholder="Email" 
        ng-model="user.email">
        <p class="error validationerror" ng-show="myform.email.$invalid && myform.email.$touched">
      </div>
      <div>
      <input type="text" name="address"
        placeholder="Address" 
        ng-model="user.address"
        ng-required="true">
        <p class="error validationerror" ng-show="myform.address.$invalid && myform.address.$touched">
        You must fill out your address.</p>
     </div>
      

     <div>
      <input type="text" name="schoolname"
        placeholder="School Name" 
        ng-model="user.schoolname"
        ng-required="false">
     </div>
    </fieldset>
    

    <button type="submit" class="btn">Submit</button>
  </form>
    
    
    
   
		   
   </ul>
    <script src="js/script.js"></script>
    <script src="dist/js/vendor/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="dist/js/flat-ui.min.js"></script>
  </body>
</html>