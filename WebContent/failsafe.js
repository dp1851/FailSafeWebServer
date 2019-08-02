var app = angular.module('failsafe', []);

app.controller('ShowUserController', function($scope, $http) {
    $http.get('http://172.20.10.12:8080/Failsafe/rest/failsafe/show').
        then(function(response) {
            $scope.users = response.data;
        });
});

app.controller('RemoveUserController', function($scope, $http) {
	$scope.removeUser = function(user) {
		$http.post('http://172.20.10.12:8080/Failsafe/rest/failsafe/remove/'+ user.id).
        then(function(response) {

        });
	}; 
    
});

app.controller('AddUserController', function($scope, $http) {
	$scope.addUser = function(user) {
		$http.post('http://172.20.10.12:8080/Failsafe/rest/failsafe/add/'+ user.id + "_" + user.firstName + "_" + user.lastName).
        then(function(response) {
        });
	}; 
    
});

app.controller('AuthenticateUserController', function($scope, $http) {
	$scope.authenticateUser = function(user) {
		$http.post('http://172.20.10.12:8080/Failsafe/rest/failsafe/authenticate/'+ user.id).
        then(function(response) {
        });
	}; 
    
});