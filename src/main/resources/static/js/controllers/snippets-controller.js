app.controller('SnippetsController', ['$scope', '$http', '$window', 'snippetService',function($scope, $http, $window, snippetService) {
  $scope.snippets = [];

  $scope.pagination = {
      currentPage: 1,
      itemsPerPage: 10,
      maxSize: 10
  }

  $scope.getSnippets = function() {
      $http.get("http://localhost:9090/snippets/")
          .then(function successCallback(response) {
              $scope.snippets = response.data;
          }, function errorCallback(response) {
              console.log(response);
          });
  };
  $scope.getSnippets();

  $scope.loadSnippetToEditor = function(id){
    var result  = $scope.snippets.filter(function(o){return o.id == id;} );
    currentSnippet = result? result[0] : null; 
    if (currentSnippet == null) return;
    snippetService.setSnippet(currentSnippet);

    $window.location.href = '#!/editor';
  }

}]);