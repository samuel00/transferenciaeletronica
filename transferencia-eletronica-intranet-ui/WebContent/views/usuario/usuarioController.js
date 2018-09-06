(function () {

    var app = angular.module('usuario');

    app.controller('usuarioController', [ '$location','$timeout','$scope','$http','$routeParams','usuarioService','toastService',  function ($location,$timeout,$scope,$http,$routeParams,usuarioService,toastService) {
        var usuario=this;
        usuario.id=$routeParams.id;
        //gera um evento que informa o controller de conteudo para modificar o nome da página
        $scope.$emit(config.events().conteudo.mudarTituloPagina,'Listagem de Usuários');

        usuario.dataAtual = new Date();

        usuario.redirecionarCadastrar = function(id){
            if(id!=undefined)
                id='/'+id;
            else
                id='';
          $location.path("/usuario"+id);
        };

        usuario.selecionado = false;
        usuario.logItem = function (item) {
            usuario.itemSelecionado = item;
            usuario.selecionado = true;
        };

        usuario.editar = function(){
            sessionStorage.setItem('userId',usuario.itemSelecionado.id);
            usuario.redirecionarCadastrar(usuario.itemSelecionado.id);
        };

        usuario.remover = function(){
          usuario.loading=true;
          usuarioService.deleteUsuario(usuario.itemSelecionado.id).then(
                function(data){

                    usuario.listagem.grade.splice(usuario.listagem.grade.indexOf(usuario.itemSelecionado),1);

                    console.log('usuário deletado com sucesso!');
                    console.log(usuario.itemSelecionado);
                    toastService.show('usuário deletado com sucesso!','success');

                    usuario.loading=false;
                },
                function(error){
                    console.log(error);
                    usuario.loading=false;
                    toastService.show('erro ao deletar usuário!','error');
                }
            );
        };

        usuario.cadastrar = function(){
          usuario.loading=true;
          if(usuario.id!=undefined && usuario.id!=""){
            // $http.put('/transferencia-eletronica-api/api/extranet/pessoas/'+usuario.id,usuario.model).then(
            usuarioService.putUsuario(usuario.id,usuario.model).then(
                function(data){
                    console.log('usuário editado com sucesso!');
                    toastService.show('usuário editado com sucesso!','success');
                    usuario.loading=false;
                    redirecionaListagem();
                },
                function(error){
                    console.log(error);
                    usuario.loading=false;
                    toastService.show('erro ao editar usuário!','error');
                }
            );
          }else{
            // $http.post('/transferencia-eletronica-api/api/extranet/pessoas',usuario.model).then(
            usuarioService.postUsuario(usuario.model).then(
                    function(data){
                        console.log('usuário cadastrado com sucesso!');
                        toastService.show('usuário cadastrado com sucesso!','success');
                        usuario.loading=false;
                        redirecionaListagem();
                    },
                    function(error){
                        console.log(error);
                        usuario.loading=false;                        
                        toastService.show('erro ao cadastrar usuário!','error');
                    }
                );
            }
        };

        //tamanho mínimo do nome (exemplo para mostrar como criar uma validação configurada a partir do controller)
        usuario.nomeMinTam=3;

        usuario.model={
            id:null,
            nome:null,
            idade:null
        };

        usuario.listagem={
            filtro:null,
            grade:[],
        }

        usuario.redirecionaListagem = function(){
            redirecionaListagem();
        }
        
        function redirecionaListagem() {
          $location.path("/usuarios");
        }

        usuario.loading=true;
        function buscarUsuarios(){
            if (usuario.id == undefined || usuario.id == null ){
                usuario.id="";
            }
            usuario.model.id=usuario.id;
             
            // $http.get('/transferencia-eletronica-api/api/extranet/pessoas/'+usuario.id).then(
            usuarioService.getBuscaUsuario(usuario.id).then(
                function(res){
                    if(res.status==200){
                        if (angular.isArray(res.data)){
                            usuario.listagem.grade = res.data;
                        } else {
                            usuario.model = res.data;
                        }
                    }
                    else{
                        console.log('erro, httpcode: '+res.status);
                    }
                    usuario.loading=false;
                },
                function(error){
                    console.log(error);
                    usuario.loading=false;
                }
            );
        }
        buscarUsuarios();

        usuario.selected = [];

        usuario.query = {
            order: 'id',
            limit: 10,
            page: 1,
            //Alterar o nome da variável ctrl.arrayObj caso necessário
            total: usuario.listagem.grade.length
        }

        usuario.limitOptions = [10, 20, 30, {
            label: 'Todos',
            value: function () { return usuario.query.total }
        }];
        
   }]);

})();

