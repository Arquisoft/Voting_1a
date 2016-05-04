var app = angular.module("voters",[]);

	app.config(function($httpProvider) {
		$httpProvider.defaults.useXDomain = true;
		delete $httpProvider.defaults.headers.common['X-Requested-With'];
	});
  
	app.controller("controller", function($scope, $http) {
		$scope.exito = false;
		$scope.fracaso = false;
		$scope.form = {};
		
		$scope.login = function(){
			console.log($scope.form);
			$http.post("http://localhost:8080/user", $scope.form)
				.success(function(data) {
					$scope.exito = true;
					$scope.fracaso = false;
					$scope.response = data;
				})
				.error(function(data) {
					$scope.exito = false;
					$scope.fracaso = true;
					console.log('Error:');
					console.log(data);
			});
		};
		
	});
