<html>
<head>
<title>Customer Manager</title>
<link href="dist/css/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Loading Flat UI -->
<link href="dist/css/flat-ui.css" rel="stylesheet">

<!-- <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.19/angular.min.js"></script> -->
<script type="text/javascript"
	src="bower_components/angular/angular.min.js"></script>
<script type="text/javascript" src="javascripts/angularApp.config.js
"> </script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/angular-ui-router/0.2.10/angular-ui-router.js"></script>
<link href="bower_components/ax/css/xeditable.css" rel="stylesheet">
<script src="bower_components/ax/js/xeditable.js"></script>
<script src="javascripts/angularApp.js"></script>
<style>
.glyphicon-thumbs-up {
	cursor: pointer
}
</style>
</head>
<body ng-app="customerManager">

	<div class="navbar navbar-default navbar-fixed-top"
		ng-controller="NavCtrl" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span>
				</button>
				<a class="navbar-brand" href="#">ng-manager</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="/customer_manager/#/home">Home</a>
					</li>
					<li><a href="/customer_manager/#/create">Create </a></li>
					<li><a href="#contact">Contact</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>


	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<ui-view></ui-view>
		</div>
	</div>
</body>
</html>