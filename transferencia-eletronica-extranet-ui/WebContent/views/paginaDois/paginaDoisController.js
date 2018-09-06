(function () {

    var app = angular.module('paginaDois');

    app.controller('paginaDoisController', ['$location', '$timeout', '$scope', '$http', '$routeParams', 'usuarioService', 'toastService', '$mdDialog' , function ($location, $timeout, $scope, $http, $routeParams, usuarioService, toastService, $mdDialog) {
        var ctrl = this;
        ctrl.id = $routeParams.id;
        ctrl.selected = [];
        ctrl.usuarioSelecionado = [];

        ctrl.usuarioSelecionado = JSON.parse(sessionStorage.getItem('usuarioSelecionado'));
        console.log("ctrl.usuarioSelecionado: ", ctrl.usuarioSelecionado);

        ctrl.voltar = function () {
            $location.path('/usuarios');
        }
        $scope.hide = function () {
            $mdDialog.hide();
        }
        ctrl.showDetail = function () {
            $mdDialog.show({
                controller: DialogController,
                templateUrl: 'views/paginaDois/dialog.html',
                clickOutsideToClose: false,
            });
            function DialogController($scope, $mdDialog) {

                $scope.hide = function () {
                    $location.path('/paginaDois');
                    $mdDialog.hide();
                };

                $scope.concluir = function () {
                    $location.path('/');
                    $mdDialog.hide();
                    toastService.show('Concluido com sucesso!', 'success');
                    sessionStorage.clear();
                };
            }
        };

    }]);

})();
