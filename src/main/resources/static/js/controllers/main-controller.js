app.controller('MainController', ['$scope', '$location', '$http',function($scope, $location, $http) {
	$scope.title = "Inicio";
	$scope.user = "invitado";

	$scope.isActive = function(route) {
        return route === $location.path();
    }
    $scope.logout = function() {
        logout($scope.loginData);
    }

    $http.get('http://localhost:9090/loggedUsers/').then(function(response){
        console.log(response.data);
    });

    $scope.getFullName = function() {
        $http.get('http://localhost:9090/loggedUsers/fullName/').then(function(response){
            console.log(response.data);
            $scope.user = response.data.name;
        });
    };




    /*function logout(params) {
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
    }*/
}]);