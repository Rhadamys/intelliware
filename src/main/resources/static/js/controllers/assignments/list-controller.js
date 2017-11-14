app.controller('ListAssignmentsController', ['$scope', '$http', function ($scope, $http) {
    $scope.assignments = [];

    $scope.pagination = {
        currentPage: 1,
        itemsPerPage: 5,
        maxSize: 5
    };

    // Initial data
    $scope.getAssignments = function () {
        $http.get("http://localhost:9090/students/" + $scope.$parent.user.instance.id + "/assignments")
            .then(function successCallback(response) {
                $scope.assignments = response.data;
            }, function errorCallback(response) {
                console.log("Error " + response);
            });
    };
    $scope.getAssignments();
}]);