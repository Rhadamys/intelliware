var app = angular.module('VoteClick', ['ngRoute', 'ui.codemirror']);

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
        .otherwise({
            redirectTo: '/'
        });
});