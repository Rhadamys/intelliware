app.controller('MainController', ['$scope', '$location', '$http',function($scope, $location, $http) {
	$scope.title = "Inicio";
	$scope.name = "invitado";
	$scope.flag = 0;

	$scope.isActive = function(route) {
        return route === $location.path();
    };

    /*$http.get('http://localhost:9090/loggedUsers/').then(function(response){
        $scope.user = response.data;
        console.log($scope.user);
    });
    //console.log($scope.user);*/

    var init = function(){
        //console.log("primer get");
        $http.get('http://localhost:9090/loggedUsers/').then(function(response){
            $scope.user = response.data;
            //console.log($scope.user);
        });
        //console.log("segundo get");
        $http.get('http://localhost:9090/loggedUsers/new/').then(function(response) {
                //console.log = 'Usuario añadido correctamente';
            }).catch(function(data) {
                //console.log = 'Se ha producido un error';
        });
        //console.log("pase los dos");
    };
    init();




    /*$http.post('http://localhost:9090/loggedUsers/new',$scope.user.mail
    ).then(function(data) {
        console.log = 'Usuario añadido correctamente';
    }).catch(function(data) {
        console.log = 'Se ha producido un error';
    });*/

    $scope.getFullName = function() {
        $http.get('http://localhost:9090/loggedUsers/fullName').then(function(response){
            //console.log(response.data);
            $scope.name = response.data.name;
        });
    };

    /*$scope.logout = function() {
        logout($scope.loginData);
    }*/

    // method for logout
    /*$scope.logout = function() {
        $http.post('/logout').success(function(res) {
            $scope.user = null;
            })
            .error(function(error) {
                console.log("Logout error : ", error);
            });
    };

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
    }*/
}]);