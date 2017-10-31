app.controller('MainController', ['$scope', '$location', function($scope, $location) {
	$scope.title = "Inicio";

	$scope.isActive = function(route) {
        return route === $location.path();
    }
    $scope.logout = function() {
        logout($scope.loginData);
    }
    function logout(params) {
        var req = {
            method: 'DELETE',
            url: "google/login"
        }
        $http(req).then(
            function(data){
                $cookies.remove("access_token");
                window.location.href="login";
            },function(){
                console.log("error");
            }
        );
    }

    function logout2(){
	    var auth2 = gapi.auth2.getAuthInstance();
	    auth2,signOut().then(function () {
	        console.log('user signer out.')
        })
    }
}]);