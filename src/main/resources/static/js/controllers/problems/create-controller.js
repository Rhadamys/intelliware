app.controller('CreateProblemController', ['$scope', '$http', '$window', 'ProblemService',
    function ($scope, $http, $window, problemService) {
    $scope.problemService = problemService;
    $scope.calendar = {
        opened: false,
        defaultTime: '23:59:59',
        buttonBar: {
            show: true,
            now: {
                show: false
            },
            today: {
                show: true,
                text: 'Hoy',
                cls: 'btn-sm btn-default'
            },
            clear: {
                show: true,
                text: 'Borrar',
                cls: 'btn-sm btn-default'
            },
            date: {
                show: true,
                text: 'Fecha',
                cls: 'btn-sm btn-default'
            },
            time: {
                show: true,
                text: 'Hora',
                cls: 'btn-sm btn-default'
            },
            close: {
                show: true,
                text: 'Cerrar',
                cls: 'btn-sm btn-default'
            },
            cancel: {
                show: false,
                text: 'Cancelar',
                cls: 'btn-sm btn-default'
            }
        },
        datePicker: {
            minDate: new Date()
        },
        timePicker: {
            showMeridian: false
        },
        saveAs: 'json'
    };

    $scope.matchQuery = false;
    $scope.showAlertForm = false;
    $scope.showAlertService = false;
    $scope.students = [];

    $scope.problem = {
        deadline: {
            date: '',
            time: ''
        },
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
    $scope.addTestCase = function () {
        $scope.problem.testCases.push({
            entrada: '',
            salida: ''
        });
    };

    $scope.resetTestCase = function (id) {
        $scope.problem.testCases[id].entrada = '';
        $scope.problem.testCases[id].salida = '';
        $scope.problem.testCases[id].description = '';
    };

    $scope.removeTestCase = function (id) {
        $scope.problem.testCases.splice(id, 1);
        if ($scope.problem.testCases.length === 0)
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
    $scope.assignStudent = function (student, update) {
        $scope.problem.assignments.push(student);
        var index = $scope.students.indexOf(student);
        $scope.students.splice(index, 1);
        if (update) $scope.updateSections();
    }
    ;

    $scope.removeAssignment = function (id) {
        var student = $scope.problem.assignments[id];
        $scope.problem.assignments.splice(id, 1);
        $scope.students.push(student);
        $scope.updateSections();
    };

    $scope.filterFunction = function (student) {
        var match = $scope.query !== '' && ($scope.clean(student.firstName).includes($scope.query) ||
            $scope.clean(student.lastName).includes($scope.query));
        if (match) $scope.matchQuery = true;
        return match;
    };

    $scope.assignAll = function () {
        $scope.students.forEach(function (student) {
            $scope.problem.assignments.push(student);
        });
        $scope.students = [];
        $scope.sections = [];
    };

    $scope.assignSection = function () {
        var students = $scope.students.filter(function (student) {
            var attendingSection = false;
            student.sectionsAttending.forEach(function (section) {
                if (section.semester === $scope.selectedSection)
                    attendingSection = true;
            });

            return attendingSection;
        });

        students.forEach(function (student) {
            $scope.assignStudent(student);
        });
        $scope.updateSections();
    };

    $scope.resetAssignments = function () {
        $scope.problem.assignments.forEach(function (student) {
            $scope.students.push(student);
        });
        $scope.problem.assignments = [];
        $scope.updateSections();
    }

    // Other functions
    $scope.clean = function (text) {
        var replace = 'áéíóúüÁÉÍÓÚÜ';
        var repWith = 'aeiouuAEIOUU';
        for (var i = 0; i < replace.length; i++)
            text = text.replace(replace[i], repWith[i]);

        return text.toLowerCase();
    };

    $scope.$watch('query', function (value) {
        $scope.query = $scope.clean(value);
        $scope.matchQuery = false;
    });

    $scope.updateSections = function () {
        $scope.sections = [];
        $scope.students.forEach(function (student) {
            student.sectionsAttending.forEach(function (section) {
                if (!$scope.sections.includes(section.semester))
                    $scope.sections.push(section.semester);
            });
        });
        $scope.sections.sort();
    };

    $scope.getColor = function (section) {
        return $scope.colors[$scope.allSections.indexOf(section) % $scope.colors.length]
    };

    $scope.send = function () {
        if($scope.form.$error.required) {
            $scope.showAlertForm = true;
            $window.scrollTo(0, 0);
        } else {
            $http.post("http://localhost:9090/problems", $scope.problem)
                .then(function successCallback() {
                    $scope.problemService.problemCreated();
                    $window.history.back();
                }, function errorCallback() {
                    $scope.showAlertService = true;
                });
        }
    };

    // Initial data
    $scope.getStudents = function () {
        $http.get("http://localhost:9090/students/", $scope.problem)
            .then(function successCallback(response) {
                $scope.students = response.data;
                $scope.updateSections();
            }, function errorCallback(response) {
                console.log("Error " + response);
            });
    };

    $scope.getStudents();
    $scope.allSections = $scope.sections;
}]);