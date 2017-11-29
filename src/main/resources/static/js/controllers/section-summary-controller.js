app.controller('SectionSummaryController', ['$scope', '$routeParams', '$http', '$document', '$window',
    function($scope,$routeParams,$http,$document,$window) {

    $scope.section = null;

    $scope.getSectionData = function() {
        if($routeParams.id) {
            $http.get("http://localhost:9090/sections/" + $routeParams.id)
                .then(function (response) {
                    $scope.section = response.data;
                });
            $http.get("http://localhost:9090/sections/" + $routeParams.id + "/summary")
                .then(function (response) {
                    $scope.section.summary = response.data;
                });
        }
    };
    $scope.getSectionData();

    /** API CALLS */
    $scope.runCode = function() {
        $http({
            method: 'POST',
            url: 'http://localhost:9090/python/',
            data: {
                code : $scope.editor.getDoc().getValue(),
                input: $scope.input
            }
        })
            .then(
                function(response) {
                    $scope.outf(response.data.response);
                })
            .catch(
                function(error) {
                    $scope.outf(error);
                }
            );
    };

    $scope.postSubmission = function() {
        $http({
            method: 'POST',
            url: 'http://localhost:9090/submissions/',
            data: {
                assignment: $scope.assignment,
                code : $scope.editor.getDoc().getValue()
            }
        })
            .then(
                function(response) {
                    submissionService.problemSubmit(response.data);
                    $window.history.back();
                })
            .catch(
                function(error) {
                    $scope.outf(error);
                }
            );
    };

}]);