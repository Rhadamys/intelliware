var app = angular.module('Smartlab', ['ngRoute', 'ui.codemirror', 'ui.bootstrap']);

app.config(function($routeProvider){
    $routeProvider
        .when('/', {
            templateUrl: 'js/views/index.html',
            controller: 'MainController'
        })
        .when('/editor', {
            templateUrl: 'js/views/editor.html',
            controller: 'EditorController'
        })
        .when('/nosotros', {
            templateUrl: 'js/views/about.html',
            controller: 'MainController'
        })
        .when('/contacto', {
            templateUrl: 'js/views/contact.html',
            controller: 'MainController'
        })
        .when('/problemas', {
            templateUrl: 'js/views/problems/list.html',
            controller: 'ListProblemsController'
        })
        .when('/problemas/nuevo', {
            templateUrl: 'js/views/problems/create.html',
            controller: 'CreateProblemController'
        })
        .otherwise({
            redirectTo: '/'
        });
});