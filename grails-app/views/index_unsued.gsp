<!doctype html>
<html lang="en" ng-app="customerApp">
<head>
<meta charset="utf-8">
<title>My HTML File</title>
<!-- Loading Bootstrap -->
<link href="dist/css/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Loading Flat UI -->
<link href="dist/css/flat-ui.css" rel="stylesheet">

<link rel="shortcut icon" href="../../dist/img/favicon.ico">
<script src="bower_components/angular/angular.js"></script>
<script src="js/app.js"></script>
</head>
<body ng-controller="CustomerListController">
	<style>
body {
	min-height: 2000px;
	padding-top: 70px;
}
</style>
	<div class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span>
				</button>
				<a class="navbar-brand" href="#">SAMPLE</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="/customer_manager/signup">Sign Up</a></li>
					<li><a href="#contact">Contact</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Dropdown <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">Action</a></li>
							<li><a href="#">Another action</a></li>
							<li><a href="">Something else here</a></li>
							<li class="divider"></li>
							<li class="dropdown-header">Nav header</li>
							<li><a href="#">Separated link</a></li>
							<li><a href="#">One more separated link</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="../navbar/">Default</a></li>
					<li class="active"><a href="./">Static top</a></li>
					<li><a href="../navbar-fixed-top/">Fixed top</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
	<ul>

		<p>
			Quick Search: <input ng-model="$ctrl.query">
		</p>
		<div class="panel panel-default">
			<div class="panel-heading">Customer List</div>
			<table class="table">
				<tr>
					<th>Firstname</th>
					<th>Lastname</th>
					<th>Email</th>
				</tr>
				<tr ng-repeat="customer in customerList | filter:$ctrl.query">
					<th>{{customer.customer_first_name}}</th>
					<th>{{customer.customer_last_name}}</th>
					<th>{{customer.email_address}}</th>

				</tr>

			</table>
		</div>

	</ul>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="dist/js/vendor/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="dist/js/flat-ui.min.js"></script>
</body>
</html>