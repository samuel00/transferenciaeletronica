/*
 * Arquivo de seguranca para o PSERVICOS
 */

(function () {
    var app = angular.module("data", ['ngCookies']);
    
    app.service("seguranca",['$rootScope','$timeout','$location','$cookies', function ($rootScope,$timeout,$location,$cookies) {
        var contexto=this;
        
        console.warn('********************************************************');
        console.warn(' ');
        console.warn('ATENÇAO: USUARIO MOCK, mude a referência local do seguranca.js para o SEFA-UI no index.html');
        console.warn(' ');
        console.warn('********************************************************');
        
        this.init = function(){
        	$cookies.put('_const_autenticador_token_', contexto.buscaToken(), {path: '/'});
        	$rootScope.usuario = this.buscaUsuario();
        	console.warn('### Usuario Mock: ' + angular.toJson($rootScope.usuario));
        }

        this.buscaToken = function(){
        	// payload: {"sub": "69685519340", "name": "MOCK", "iat": 1489429898, "exp": 1489516298, "perfis": [{"id": "24", "descricao": "PESSOA FISICA"},{"id": "1","descricao": "QUADRO SOCIETÁRIO"}] }
        	return 'eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE0ODk0Mjk4OTgsImV4cCI6MTQ4OTUxNjI5OCwic3ViIjoiNjk2ODU1MTkzNDAiLCJuYW1lIjoiTU9DSyIsInBlcmZpcyI6W3siaWQiOiIyNCIsImRlc2NyaWNhbyI6IlBFU1NPQSBGSVNJQ0EifSx7ImlkIjoiMSIsImRlc2NyaWNhbyI6IlFVQURSTyBTT0NJRVTDgVJJTyJ9XX0.GMPENcfI6ABdbQjG3ycns0sbRt2XrA4u-9-eFGXF5yc';
        }
        
        this.buscaUsuario = function(){
        	usuario = { login: '69685519340', descricao: 'MOCK'};
        	return usuario;
        }
        
        this.logOut = function(){
       	 	$location.path("/");
        }

        $rootScope.$on('$viewContentLoaded', function(event) {
    		$(".loader").delay(300).fadeOut();
    		$(".animationload").delay(600).fadeOut("slow");
        });

    }]);

})();
