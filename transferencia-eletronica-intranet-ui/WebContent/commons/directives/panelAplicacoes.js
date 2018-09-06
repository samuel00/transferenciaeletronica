(function () {
    var app = angular.module('sefa-ui');

    app.directive('painelAplicacoes', function () {
        return {
            restrict: 'E',
            templateUrl:'/sefa-ui/v1/commons/directives/painel-aplicacoes-extranet.html',
            controller: function ($scope, $rootScope, $mdSidenav, $element, $templateRequest, $compile, $window, $http){

	        $scope.aplicacoes = [];
	        var lockLeft;
              
              $scope.openAplicacao = function(link) {
                localStorage.setItem("objModulo", angular.toJson($scope.aplicacoes));
                $window.open(link,'_self');
                $scope.telaNivelAplicacoes = false;
                lockLeft();
              }

              $scope.closeAplicacao = function(){
                  $scope.telaNivelAplicacoes = false;
                  lockLeft();
              }

              $rootScope.$on(config.events().conteudo.openTelaAplicacoes,function(event,data){
                if (data.lockLeft) {
                  lockLeft = data.lockLeft;
                  data = data.modulo;
                }
                
                console.warn('### openTelaAplicacoes');
                
                $scope.telaNivelAplicacoes = true;
                $scope.aplicacoes = data;
        		$('#content').scrollTop(0);
        		$mdSidenav('left').close();
              });	
                  
              $scope.$on(config.events().conteudo.closeTelaAplicacoes,function(){
            	$scope.telaNivelAplicacoes = false;
              });
            },
            link: function(scope) {
                scope.telaNivelAplicacoes = false;
                scope.aplicacoes;
            }
        }
    });
})();
