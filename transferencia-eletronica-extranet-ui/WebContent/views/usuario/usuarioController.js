(function () {

    var app = angular.module('usuario');

    app.controller('usuarioController', ['$location', '$timeout', '$scope', '$http', '$routeParams', 'usuarioService', 'toastService', function ($location, $timeout, $scope, $http, $routeParams, usuarioService, toastService) {
        var usuario = this;
        usuario.id = $routeParams.id;
        usuario.selected = [];

        //gera um evento que informa o controller de conteudo para modificar o nome da página
        $scope.$emit(config.events().conteudo.mudarTituloPagina, 'Listagem de Usuários');

        usuario.redirecionarCadastrar = function (id) {
            if (id != undefined) {
                id = '/' + id;
            } else {
                id = '';
            }
            $location.path("/usuario" + id);
        };

        usuario.selecionado = false;
        usuario.logItem = function (item) {
            usuario.itemSelecionado = item;
            usuario.selecionado = true;
        };

        usuario.editar = function () {
            sessionStorage.setItem('userSelected', angular.toJson(usuario.itemSelecionado));
            sessionStorage.setItem('userId', usuario.itemSelecionado.id);
            usuario.redirecionarCadastrar(usuario.itemSelecionado.id);
        };
        usuario.voltar = function () {
            $location.path('/paginaUm');
        }

        usuario.avancar = function (usuarioSelecionado) {
            $location.path('/paginaDois')
            sessionStorage.setItem('usuarioSelecionado', angular.toJson(usuarioSelecionado));
        }

        usuario.remover = function () {
            usuario.loading = true;
            usuarioService.deleteUsuario(usuario.itemSelecionado.id).then(
                function (data) {

                    usuario.listagem.grade.splice(usuario.listagem.grade.indexOf(usuario.itemSelecionado), 1);

                    console.log('usuário deletado com sucesso!');
                    console.log(usuario.itemSelecionado);
                    toastService.show('usuário deletado com sucesso!', 'success');
                    usuario.loading = false;
                },
                function (error) {
                    console.log(error);
                    usuario.loading = false;
                    toastService.show('erro ao deletar usuário!', 'error');
                }
            );
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

        usuario.redirecionaListagem = function () {
            redirecionaListagem();
        }

        function redirecionaListagem() {
            $location.path("/usuarios");
        }

        usuario.loading = true;
        function buscarUsuarios() {
            if (usuario.id == undefined || usuario.id == null) {
                usuario.id = "";
            }
            usuario.model.id = usuario.id;

            // $http.get('/transferencia-eletronica-api/api/extranet/pessoas/'+usuario.id).then(
            usuarioService.getBuscaUsuario(usuario.id).then(
                function (res) {
                    if (res.status == 200) {
                        if (angular.isArray(res.data)) {
                            usuario.listagem.grade = res.data;

                            usuario.selected = [];

                            usuario.query = {
                                order: 'id',
                                limit: 9,
                                page: 1,
                                //Alterar o nome da variável ctrl.arrayObj caso necessário
                                total: usuario.listagem.grade.length
                            }
                            usuario.limitOptions = [10, 20, 30, {
                                label: 'Todos',
                                value: function () {
                                    return usuario.query.total
                                }
                            }];

                        } else {
                            usuario.model = res.data;
                        }
                        console.log(usuario.listagem.grade);
                    }
                    else {
                        console.log('erro, httpcode: ' + res.status);
                    }
                    usuario.loading = false;
                },
                function (error) {
                    console.log(error);
                    usuario.loading = false;
                }
            );
        }
        buscarUsuarios();

    }]);

})();

