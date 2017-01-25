<!doctype html>
<html lang="en" >
  <head>
    <meta charset="utf-8">
     <base href="/customer_manager/">
    <title>My HTML File</title>
        <!-- Loading Bootstrap -->
    <link href="dist/css/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Loading Flat UI -->
    <link href="dist/css/flat-ui.css" rel="stylesheet">

    <link rel="shortcut icon" href="../../dist/img/favicon.ico">
    <script src="bower_components/angular/angular.js"></script>
     <script src="bower_components/angular-route/angular-route.min.js"></script>
    <script src="js/app.js"></script>
    <script src="js/controllers/MainCtrl.js"></script>
    <script src="js/controllers/CustomerCtrl.js"></script>
    <script src="js/services/CustomerService.js"></script>
    <script src="js/appRoutes.js"></script>
  </head>


<body ng-app="sampleApp" ng-controller="CustomerController">
<div class="container">

    <!-- HEADER -->
    <nav class="navbar navbar-inverse">
        <div class="navbar-header">
            <a class="navbar-brand" href="home">Stencil: Node and Angular</a>
        </div>

        <!-- LINK TO OUR PAGES. ANGULAR HANDLES THE ROUTING HERE -->
        <ul class="nav navbar-nav">
            <li><a href="customerlist">Customer</a></li>
        </ul>
    </nav>

    <!-- ANGULAR DYNAMIC CONTENT -->
    <div ng-view></div>

</div>
       <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="dist/js/vendor/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="dist/js/flat-ui.min.js"></script>
</body>



  </body>
</html>