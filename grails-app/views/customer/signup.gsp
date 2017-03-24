<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>Add A Customer</title>
<script src="//code.angularjs.org/snapshot/angular.min.js"></script>
<script
	src="${request.contextPath}/bower_components/angular/angular.min.js"></script>
<script src="javascripts/application.js"> </script>

</head>
<body>
	<g:form name="signup_form" controller="customer">
		<div>
			<label for="lastName">Last Name</label>
			<g:textField name="customer_last_name" value='' required="true" />
			<br />
		</div>
		<div>
			<label for="firstName">First Name</label>
			<g:textField name="customer_first_name" value='' required="true" />
			<br />
		</div>
		<div>
			<label for="companyname">Company Name</label>
			<g:textField name="company_name" placeholder="Optional " value='' />
			Opt out:
			<g:checkBox name="checkbox" value="no" />
			<br />
		</div>
		<label for="address">Address</label>
		<g:textField name="address" value='' required="true" />
		<br />
		<label for="email_address">Email Address</label>
		<input type="email"></><g:textField name="email_address"
			placeholder='Enter your Email' type='email' value='' required="true" />
		<br />
		<g:actionSubmit action="create" name="create"
			value="Create An Account" />
	</g:form>
	<br></br>
	<div ng-controller="FormValidator">
		<form name="myForm" nonvalidate>
			<label> Customer Email: <input type="email"
				ng-model="email_address" />
			</label>
		</form>
		<pre> Customer Email="{{email_address}}"</pre>
	</div>
	<script>
       (function(angular){
    	   'use strict'
    	    var app = angular.module('FormValidator', []); 
    	   app.directive('email_address', function($q, $timeout){
    		   return {
    			    require: 'ngModel',
    			    link: function(scope, elm, attrs, ctrl) {
    			      var usernames = ['Jim', 'John', 'Jill', 'Jackie'];

    			      ctrl.$asyncValidators.username = function(modelValue, viewValue) {

    			        if (ctrl.$isEmpty(modelValue)) {
    			          // consider empty model valid
    			          return $q.resolve();
    			        }
    			        var def = $q.defer();

    			        $timeout(function() {
    			          // Mock a delayed response
    			          if (usernames.indexOf(modelValue) === -1) {
    			            // The username is available
    			            def.resolve();
    			          } else {
    			            def.reject();
    			          }

    			        }, 2000);

    			        return def.promise;
    			      };
    			    }
    			  };
    		   
    	   });
    	   
       })(window.angular)
       
       </script>
</body>
</html>