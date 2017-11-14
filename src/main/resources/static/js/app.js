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
        .when('/tareas', {
            templateUrl: 'js/views/assignments/list.html'
        })
        .when('/snippets', {
            templateUrl: 'js/views/snippets/list.html',
            controller: 'SnippetsController'
        })
        .when('/snippets/detail', {
            templateUrl: 'js/views/snippets/detail.html',
            controller: 'SnippetsController'
        })
        .otherwise({
            redirectTo: '/'
        });
});

app.service('snippetService', function() {
    var snippet = null;
  
    var setSnippet = function(newObj) {
        snippet = newObj;
    };
  
    var popSnippet = function(){
        poppedSnippet = snippet;
        snippet = null;
        return poppedSnippet;
    };
  
    return {
      setSnippet: setSnippet,
      popSnippet: popSnippet
    };
  
  });