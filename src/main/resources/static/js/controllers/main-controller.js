app.controller('MainController', ['$scope', '$location', '$http',function($scope, $location, $http) {
	$scope.title = "Inicio";
	$scope.user = null;
	$scope.flag = 0;

	$scope.templateUrl = function () {
	    if($scope.user) {
	        var template = 'js/template/student-links.html';
	        $scope.user.instance.roles.forEach(function(role) {
	            if(role.name === 'Teacher') template = 'js/template/teacher-links.html';
            });
	        return template;
        }
        return'js/template/guest-links.html';
    };

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
        $http.get('http://localhost:9090/users/logged').then(function(response){
            $scope.user = response.data;
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

    $scope.getUserInfo = function(mail) {
        $http({
            method: 'POST',
            url: 'http://localhost:9090/users/byMail/',
            data: {
                email: mail
            }
        })
        .then(function(res) {
            console.log("respuesta");
            console.log(res.data);
            if ($scope.user == null) {$scope.user={};}
            $scope.user = $scope.joinObjects($scope.user,res.data);
        })
        .catch(function(error) {
            console.log("Logout error : ", error);
            return null;
        });
    };

    $scope.logout = function() {
        $http({
            method: 'POST',
            url: 'http://localhost:9090/logout',
        })
        .then(function(res) {
            console.log($scope.user);
            $scope.user = null;
        })
        .catch(function(error) {
			console.log("Logout error : ", error);
		});
    };
    
    $scope.joinObjects = function(obj1,obj2){
        for (var attrname in obj2) { obj1[attrname] = obj2[attrname]; }
        return obj1;
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