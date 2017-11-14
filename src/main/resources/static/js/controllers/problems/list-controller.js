app.factory('ProblemService', function() {
    var created = false;
    return {
        problemCreated: function() {
            console.log("Informando que fue creado");
            created = true;
            console.log("Nuevo estado: " + created);
        },
        isProblemCreated: function () {
            const wasCreated = created;
            created = false;
            console.log(wasCreated);
            return wasCreated;
        }
    }
});

app.controller('ListProblemsController', ['$scope', '$http', 'ProblemService',
    function($scope, $http, problemService) {
    $scope.problems = [];
    $scope.problemPage = [];

    $scope.pagination = {
        currentPage: 1,
        itemsPerPage: 5,
        maxSize: 5
    };

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

    $scope.showAlertSuccess = problemService.isProblemCreated();
}]);