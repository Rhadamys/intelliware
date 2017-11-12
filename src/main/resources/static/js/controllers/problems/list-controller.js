app.controller('ListProblemsController', ['$scope', '$http', function($scope, $http) {
    $scope.problems = [];

    $scope.currentPage = 1;
    $scope.itemsPerPage = 5;
    $scope.maxSize = 5;

    // Initial data
    $scope.getProblems = function() {
        $http.get("http://localhost:9090/problems/all", $scope.problem)
            .then(function successCallback(response) {
                $scope.problems = response.data;
            }, function errorCallback(response) {
                console.log("Error " + response);
            });
    };
    $scope.getProblems();
}]);