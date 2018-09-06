(function () {
    var app = angular.module('sefa-ui');

    app.directive('menuAplicacoes', function () {
        return {
            restrict: 'E',
            controller: function ($scope, $rootScope, $element, $templateRequest, $compile, $http, toastService, $timeout){

                $templateRequest("/sefa-ui/v1/commons/directives/menu-extranet.html").then(function(html){
                  $element.append($compile(html)($scope));
                });
                $scope.telaNivelAplicacoes = false;
                $scope.usuario = $rootScope.usuario;

                function carregaModuloUrlAtual () {
                    var array = $location.absUrl().split('/');
                    return array[3].toLowerCase();
                }

                var menuMock = [];
                
                $http.get('/transferencia-eletronica-ui/menuMock.json').then(
                    function(data){
                    	menuMock = data.data;      
                    	$scope.tree = menuMock;
                    	$scope.tree.forEach(function(tr,i) {
                            // Atualiza a arvore com a url do módulo
                            var array = tr.nodes[0].nodes[0].url.split("/");
                            $scope.tree[i].urlModulo = array[1];
                        });
                    	$rootScope.treeModels = $scope.tree;
                    }, 
                    function(error){
                        console.log(error);
                    }
                );
                
                console.warn('### menuMock');
                
                $scope.attNomeModulo = function() {
                    $scope.$emit(config.events().conteudo.nomeModulo);
                }
                $scope.expandeMenu = function(pai) {
                    var id=$scope.tree.indexOf(pai);
                    $scope.tree[id].selected=!$scope.tree[id].selected;
                    for (var i = 0; i < $scope.tree.length; i++) {
                        if (id != i ){
                            $scope.tree[i].selected=false;
                        }
                    }
                    $scope.$emit(config.events().conteudo.closeTelaAplicacoes);
                }

                $scope.goPanel = function(modulo,lockLeft) {
                	console.warn('### goPanel');
                	var send = {};

                    if (lockLeft) {
                        lockLeft();
                        send = {
                            lockLeft,
                            modulo
                        };
                    } else send = modulo;

                    $rootScope.$emit(config.events().conteudo.openTelaAplicacoes, send);

                    //Seleciona menu
                    $scope.tree.forEach(function(tr,i) {tr.selected = false;});
                    $scope.tree[$scope.tree.indexOf(modulo)].selected=true;
                }
                
                $scope.ambiente = "Desenvolvimento";
                $scope.menus = [];
                var timeLoad = 0;
                $scope.LoadItens = function() {
                    timeLoad++;
                    if ($rootScope.treeModels) {
                      var tree = $rootScope.treeModels;
                      var act = false;
                      tree.forEach(function(mod,a) {
                        act = true;
                        
                        mod.nodes.forEach(function(n1,b) {
                            n1.nodes.forEach(function(n2,i) {
                              $scope.menus.push({
                                "descricao": n2.descricao, 
                                "modulo": mod.descricao.toUpperCase(), 
                                "url": n2.url
                              });
                              $scope.$apply();
                            });
                          });
                       
                      });

                      if (!act) {
                          $timeout($scope.LoadItens, 1000);
                      } 
                    } else {
                        $timeout($scope.LoadItens, 200);
                    }
                }

                $scope.LoadItens();

                $scope.querySearch = function(array) {
                    var nArray = [];
                    array.forEach(function(arr,i) {
                      var re = new RegExp( $scope.topoBarSearch,"i");
                      console.log(re);
                      if (arr.descricao.match(re)) {
                        nArray.push(arr);
                      } else {
                        if (arr.modulo.match(re)) {
                            nArray.push(arr);
                        } 
                      }
                      
                    });
                    return nArray;
                }
              
                $scope.cleanInput = function () {
                    $scope.topoBarSearch = "";
                    $scope.showSearchMobile = false;
                }

            },
            link: function(scope) {

                scope.openTelaAplicacoes = function (ev,data) {

                    for (var i = 0; i < scope.tree.length; i++) {
                        for (var j = 0; j < scope.tree[i].nodes.length; j++) {
                            scope.tree[i].nodes[j].selected=false;
                        }
                    }

                    data.selected=true;
                    scope.$emit(config.events().conteudo.openTelaAplicacoes, data.nodes);
                };

            }
        }
    });
})();
