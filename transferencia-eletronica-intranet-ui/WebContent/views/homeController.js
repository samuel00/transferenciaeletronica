(function () {

    var app = angular.module('transferencia-eletronica-intranet-ui');

    app.controller('homeController', [ '$location','$scope' ,function ($location,$scope) {

        var bemVindo=this;
        $scope.$emit(config.events().conteudo.mudarTituloPagina,'Home');

        bemVindo.gotoUsuario = function (){
          $location.path("/usuarios");
        };

   }]);
})();
