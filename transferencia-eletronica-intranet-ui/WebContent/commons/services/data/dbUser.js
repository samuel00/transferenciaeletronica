/*
 * Arquivo de seguranca para o eSEFA
 */

(function () {
    var app = angular.module("data");
    
    app.service("dbUser",['$rootScope','$timeout','$location','$http', function ($rootScope,$timeout,$location,$http) {
        var contexto=this;
        
        console.warn('********************************************************');
        console.warn(' ');
        console.warn('ATENÇAO: USUARIO MOCK, mude a referência local do dbUser.js para o SEFA-UI no index.html');
        console.warn(' ');
        console.warn('********************************************************');
        
        this.init = function(){
        	$http.defaults.headers.common['Authorization'] = 'Bearer ' + contexto.buscaToken();
        	$rootScope.usuario = this.buscaUsuario();
        	console.warn('### Usuario Mock: ' + angular.toJson($rootScope.usuario));
        }
        
        this.buscaToken = function(){
        	// payload: {"sub": "9999999001", "name": "MOCK", "iat": 1489410176, "exp": 1489431776, "refreshTime": 1489428176752}
        	return 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE0ODk0MTAxNzYsImV4cCI6MTQ4OTQzMTc3NiwicmVmcmVzaFRpbWUiOjE0ODk0MjgxNzY3NTIsInN1YiI6Ijk5OTk5OTkwMDEiLCJuYW1lIjoiTU9DSyJ9.2OreDhkI5Gc4MuOtkvScaPBT7OsaSw5k-garJ6i-xBo';
        }
        
        this.buscaUsuario = function(){
        	var usuario = { matricula: '9999999001', descricao: 'MOCK'};
        	return usuario;
        }

        this.logOut = function(){
        	$location.path("/");
        }
        
        $rootScope.$on('$viewContentLoaded', function(event) {
        	$timeout(function() {
        		$('#splash-esefa').addClass("closed");
            }, 500);
        });
        
    }]);    
})();