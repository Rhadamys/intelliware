app.factory('SubmissionService', function() {
    var submission = null;
    return {
        problemSubmit: function(sub) {
            submit = true;
            submission = sub;
        },
        getSubmission: function () {
            const sub = submission;
            submission = null;
            return sub;
        }
    }
});

app.controller('ListAssignmentsController', ['$scope', '$http', 'SubmissionService',
    function ($scope, $http, submissionService) {
    $scope.assignments = [];

    $scope.pagination = {
        currentPage: 1,
        itemsPerPage: 5,
        maxSize: 5
    };

    $scope.submission = submissionService.getSubmission();

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