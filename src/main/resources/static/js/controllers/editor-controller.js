app.controller('EditorController', ['$scope', function($scope) {
    $scope.editor = null;

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
        _editor.setOption('firstLineNumber', 10);
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
}]);