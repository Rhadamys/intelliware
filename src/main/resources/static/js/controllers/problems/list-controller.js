app.controller('ListProblemsController', ['$scope', '$http', function($scope, $http) {
    $scope.problems = [];
    $scope.problemPage = [];

    $scope.pagination = {
        currentPage: 1,
        itemsPerPage: 5,
        maxSize: 5
    }

    // Initial data
    $scope.getProblems = function() {
        $http.get("http://localhost:9090/problems/", $scope.problem)
            .then(function successCallback(response) {
                $scope.problems = response.data;
            }, function errorCallback(response) {
                console.log("Error " + response);
            });
    };
    $scope.getProblems();
}]);