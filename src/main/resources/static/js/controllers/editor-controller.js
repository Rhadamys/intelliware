app.controller('EditorController', ['$scope', '$routeParams', '$http', '$document', '$window', 'snippetService', 'SubmissionService',
    function($scope,$routeParams,$http,$document,$window, snippetService, submissionService) {
    $scope.editor = null;
    $scope.console = document.getElementById("output");
    $scope.snippetDescription = ' ';

    $scope.editorOptions = {
        mode: 'python',
        lineNumbers: true,
        indentWithTabs: true,
        theme: 'default',
    };

    $scope.loadSnippet = function(){
        snippet = snippetService.popSnippet();
        if(snippet != null){
            $scope.editor.getDoc().setValue(snippet.code);
            $scope.snippetTitle = snippet.title;
            $scope.snippetDescription = snippet.description;
            $scope.currentSnippet = null;
        }
    };

    $scope.codemirrorLoaded = function(_editor){
        // Editor part
        var _doc = _editor.getDoc();
        _editor.focus();

        // Options
        _doc.markClean();

        $scope.editor = _editor;

        $scope.loadSnippet();
    };

    /**
     * Cambia el tema del editor de acuerdo al botón que se haya presionado.
     * @param theme Tema que se aplicará.
     */
    $scope.changeTheme = function(theme){
        $scope.editor.setOption('theme',theme);
    };

    /** API CALLS */
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
    $scope.postSnippet = function() {

        $http({
            method: 'POST',
            url: 'http://localhost:9090/snippets/',
            data: {
                code : $scope.editor.getDoc().getValue(),
                title: $scope.snippetTitle,
                description: $scope.snippetDescription,
                user_id: $scope.user.id
            }
        })
        .then(
            function(response) {
                $scope.outf(response.data.response);
                $window.location.href = '#!/snippets';
        })
        .catch(
            function(error) {
                $scope.outf(error);
        }
        );
    };


    $scope.outf = function(text) {
        $scope.console.innerHTML = text;
    };

    /** FUNCIONES DE SKULPT */
    $scope.builtinRead = function(x) {
        if (Sk.builtinFiles === undefined || Sk.builtinFiles["files"][x] === undefined)
            throw "File not found: '" + x + "'";
        return Sk.builtinFiles["files"][x];
    };

    $scope.runIt = function() {
        var prog = $scope.editor.getDoc().getValue();
        $scope.console.innerHTML = '';
        Sk.pre = "output";
        Sk.execLimit = 10000; // Timeout: 10 sec.
        Sk.configure({output: $scope.outf, read: $scope.builtinRead});
        (Sk.TurtleGraphics || (Sk.TurtleGraphics = {})).target = 'mycanvas';
        var myPromise = Sk.misceval.asyncToPromise(function() {
            return Sk.importMainWithBody("<stdin>", false, prog, true);
        });
        myPromise.then(function(mod) {
                console.log('success');
            },
            function(err) {
                $scope.console.innerHTML = err.toString();
            });
    };

    $scope.isThemeActive = function(theme) {
        return $scope.editor.getOption('theme') === theme;
    };

    $scope.assignment = null;
    $scope.getAssignment = function() {
        if($routeParams.id) {
            $http.get("http://localhost:9090/assignments/" + $routeParams.id)
                .then(function (response) {
                    $scope.assignment = response.data;
                });
        }
    };
    $scope.getAssignment();
}]);