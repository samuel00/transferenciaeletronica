var app = angular.module('transferencia-eletronica', ['ngRoute','TrasnferenciaServico','ngMaterial','ngTable']);

app.config(function($routeProvider, $locationProvider,$mdDateLocaleProvider) {

        $routeProvider.when('/transferencia', {
            templateUrl: 'views/transferencia/principal.html',
            controller: 'TransferenciaController'
        });

       // foto no singular!

        $routeProvider.when('/transferencia/new', {
            templateUrl: 'views/transferencia/transferencia.html',
            controller: 'TransferenciaController'
        });

        $routeProvider.otherwise({redirectTo: '/transferencia'});
        
        $mdDateLocaleProvider.formatDate = function(date) {
            return date ? moment(date).format('DD/MM/YYYY') : null;
          };
          $mdDateLocaleProvider.parseDate = function(dateString) {
            var m = moment(dateString, 'DD/MM/YYYY', true);
            return m.isValid() ? m.toDate() : new Date(NaN);
      };

    });

app.controller("HelloController", function($scope) {
  $scope.message = "Hello, AngularJS";	
});