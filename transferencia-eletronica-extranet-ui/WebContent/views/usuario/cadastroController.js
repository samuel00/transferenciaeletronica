(function () {

    var app = angular.module('usuario');

    app.controller('cadastroController', ['$location', '$timeout', '$scope', '$http', '$routeParams', 'usuarioService', 'toastService', function ($location, $timeout, $scope, $http, $routeParams, usuarioService, toastService) {
        var usuario = this;
        usuario.id = $routeParams.id;
        usuario.listagem = [];
        usuario.selected = [];
        usuario.itemSelecionado = [];
        //tamanho mínimo do nome (exemplo para mostrar como criar uma validação configurada a partir do controller)
        usuario.nomeMinTam = 3;

        usuario.model = {
            id: null,
            nome: null,
            idade: null
        };

        usuario.listagem = {
            filtro: null,
            grade: [],
        }

        usuario.model = JSON.parse(sessionStorage.getItem('userSelected'));

        //gera um evento que informa o controller de conteudo para modificar o nome da página
        $scope.$emit(config.events().conteudo.mudarTituloPagina, 'Listagem de Usuários');

        usuario.redirecionarCadastrar = function (id) {
            if
            (id != undefined)
                id = '/' + id;
            else
                id = '';
            $location.path("/usuario" + id);
        };

        usuario.selecionado = false;
        usuario.logItem = function (item) {
            usuario.itemSelecionado = item;
            usuario.selecionado = true;
        };

        usuario.editar = function () {
            sessionStorage.setItem('userId', usuario.itemSelecionado);
            usuario.redirecionarCadastrar(usuario.itemSelecionado);
        };

        usuario.cadastrar = function () {
            usuario.loading = true;
            if (usuario.id != undefined && usuario.id != "") {
                // $http.put('/transferencia-eletronica-api/api/extranet/pessoas/'+usuario.id,usuario.model).then(
                usuarioService.putUsuario(usuario.id, usuario.model).then(
                    function (data) {
                        console.log('usuário editado com sucesso!');
                        usuario.loading = false;
                        redirecionaListagem();
                        toastService.show('usuário editado com sucesso!', 'success');
                    },
                    function (error) {
                        console.log(error);
                        usuario.loading = false;
                        toastService.show('erro ao editado usuário!', 'error');
                    }
                );
            } else {
                // $http.post('/transferencia-eletronica-api/api/extranet/pessoas',usuario.model).then(
                usuarioService.postUsuario(usuario.model).then(
                    function (data) {
                        console.log('usuário cadastrado com sucesso!');
                        usuario.loading = false;
                        redirecionaListagem();
                        toastService.show('usuário cadastrado com sucesso!', 'success');
                    },
                    function (error) {
                        console.log(error);
                        usuario.loading = false;
                        toastService.show('erro ao cadastrado usuário!', 'error');
                    }
                );
            }
        };

        usuario.redirecionaListagem = function () {
            redirecionaListagem();
        }

        function redirecionaListagem() {
            $location.path("/usuarios");
        }

    }]);

})();

