var app = angular.module('Smartlab', ['ngRoute', 'ui.codemirror', 'ui.bootstrap', 'ui.bootstrap.datetimepicker']);

app.config(function($routeProvider){
    $routeProvider
        .when('/', {
            templateUrl: 'js/views/index.html'
        })
        .when('/editor', {
            templateUrl: 'js/views/editor.html'
        })
        .when('/nosotros', {
            templateUrl: 'js/views/about.html'
        })
        .when('/contacto', {
            templateUrl: 'js/views/contact.html'
        })
        .when('/problemas', {
            templateUrl: 'js/views/problems/list.html'
        })
        .when('/problemas/nuevo', {
            templateUrl: 'js/views/problems/create.html'
        })
        .otherwise({
            redirectTo: '/'
        });
});