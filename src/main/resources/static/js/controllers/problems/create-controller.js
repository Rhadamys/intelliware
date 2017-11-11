app.controller('CreateProblemController', ['$scope', '$http', '$window', function($scope, $http, $window) {
    $scope.today = function() {
      var today = new Date();
      var year = today.getFullYear();
      var month = ('0' + (today.getMonth() + 1)).substr(-2);
      var day = ('0' + (today.getDate())).substr(-2);
      return year + '-' + month + '-' + day + 'T00:00';
    };

    $scope.colors = [
        '#F26118',
        '#4D3649',
        '#BA8326',
        '#38698C',
        '#488B49',
        '#4AAD52',
        '#5F47A9',
        '#B1366D',
        '#1E2D5C',
        '#E02C40'
    ];

    $scope.matchQuery = false;
    $scope.students = [];

    $scope.problem = {
        testCases: [
            {
                input: null,
                output: null,
                description: null
            }
        ],
        assignments: []
    };

    $scope.sections = [];

    // Test cases
    $scope.addTestCase = function() {
        $scope.problem.testCases.push({
            entrada: '',
            salida: ''
        });
    };

    $scope.resetTestCase = function(id) {
        $scope.problem.testCases[id].entrada = '';
        $scope.problem.testCases[id].salida = '';
        $scope.problem.testCases[id].description = '';
    };

    $scope.removeTestCase = function(id) {
        $scope.problem.testCases.splice(id, 1);
        if($scope.problem.testCases.length === 0)
            $scope.resetTestCases();
    };

    $scope.resetTestCases = function () {
        $scope.problem.testCases = [
            {
                input: null,
                output: null,
                description: null
            }
        ];
    };

    // Assignments
    $scope.assignStudent = function(student, update) {
        $scope.problem.assignments.push(student);
        var index = $scope.students.indexOf(student);
        $scope.students.splice(index, 1);
        if(update) $scope.updateSections();
    }
    ;

    $scope.removeAssignment = function(id) {
        var student = $scope.problem.assignments[id];
        $scope.problem.assignments.splice(id, 1);
        $scope.students.push(student);
        $scope.updateSections();
    };

    $scope.filterFunction = function(element) {
        var match = $scope.query !== '' && ($scope.clean(element.user.firstName).includes($scope.query) ||
            $scope.clean(element.user.lastName).includes($scope.query));
        if(match) $scope.matchQuery = true;
        return match;
    };

    $scope.assignAll = function() {
        $scope.students.forEach(function(student) {
            $scope.problem.assignments.push(student);
        });
        $scope.students = [];
        $scope.sections = [];
    };

    $scope.assignSection = function() {
        var students = $scope.students.filter(function(student) {
            return student.section.semester === $scope.selectedSection
        });

        students.forEach(function(student) {
            if(student.section.semester === $scope.selectedSection)
                $scope.assignStudent(student, false);
        });
        $scope.updateSections();
    };

    $scope.resetAssignments = function() {
        $scope.problem.assignments.forEach(function(student) {
            $scope.students.push(student);
        });
        $scope.problem.assignments = [];
        $scope.updateSections();
    }

    // Other functions
    $scope.clean = function(text) {
        var replace = 'áéíóúüÁÉÍÓÚÜ';
        var repWith = 'aeiouuAEIOUU';
        for(var i = 0; i < replace.length; i++)
            text = text.replace(replace[i], repWith[i]);

        return text.toLowerCase();
    };

    $scope.$watch('query', function (value) {
        $scope.query = $scope.clean(value);
        $scope.matchQuery = false;
    });

    $scope.updateSections = function() {
        $scope.sections = [];
        $scope.students.forEach(function(student) {
            if(!$scope.sections.includes(student.section.semester))
                $scope.sections.push(student.section.semester);
        });
        $scope.sections.sort();
    };

    $scope.getColor = function(section) {
        return $scope.colors[$scope.allSections.indexOf(section) % $scope.colors.length]
    };

    $scope.send = function () {
      $http.post("http://localhost:9090/problems", $scope.problem)
          .then(function successCallback() {
              $window.history.back();
          }, function errorCallback() {
              alert('Su solicitud no pudo ser procesada. Intente nuevamente en unos instantes.');
          });
    };

    // Initial data
    $scope.getStudents = function() {
        $http.get("http://localhost:9090/students/all", $scope.problem)
            .then(function successCallback(response) {
                $scope.students = response.data;
            }, function errorCallback(response) {
                console.log("Error " + response);
            });
    };

    $scope.getStudents();
    $scope.updateSections();
    $scope.allSections = $scope.sections;
}]);