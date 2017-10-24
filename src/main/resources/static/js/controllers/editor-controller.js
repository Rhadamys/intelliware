app.controller('EditorController', ['$scope', '$document', function($scope) {
    $scope.editor = null;
    $scope.console = document.getElementById("output");

    $scope.editorOptions = {
        mode: 'python',
        lineNumbers: true,
        indentWithTabs: true,
        theme: 'default',
    };

    $scope.codemirrorLoaded = function(_editor){
        // Editor part
        var _doc = _editor.getDoc();
        _editor.focus();

        // Options
        _doc.markClean();

        $scope.editor = _editor;
    };

    /**
     * Cambia el tema del editor de acuerdo al botón que se haya presionado.
     * @param theme Tema que se aplicará.
     */
    $scope.changeTheme = function(theme){
        $scope.editor.setOption('theme',theme);
    }

    /** FUNCIONES DE SKULPT */
    $scope.outf = function(text) {
        $scope.console.innerHTML = $scope.console.innerHTML + text;
    }

    $scope.builtinRead = function(x) {
        if (Sk.builtinFiles === undefined || Sk.builtinFiles["files"][x] === undefined)
            throw "File not found: '" + x + "'";
        return Sk.builtinFiles["files"][x];
    }

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
    }

    $scope.isThemeActive = function(theme) {
        return $scope.editor.getOption('theme') === theme;
    }
}]);