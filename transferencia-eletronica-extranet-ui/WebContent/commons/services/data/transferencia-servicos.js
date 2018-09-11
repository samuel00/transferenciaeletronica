angular.module('TrasnferenciaServico',['ngResource'])
.factory('recursoTransferencia',function($resource){

	return $resource('/transferencia-eletronica-api/api/transferencia', {},{
		get : {
		      method: 'GET'
		    }
	})

}).factory('cadastroDeTrasnferencia', function(recursoTransferencia, $q, $rootScope){

	cadastrar = function(foto){
		return $q(function(resolve, reject){
				recursoTransferencia.save(foto,function(){
					$rootScope.$broadcast('fotoCadastrada');
					resolve({
						mensagem : "Transferência cadastrada com sucesso!'",
						inclusao : true
				});
				},function(error){
					console.log(error.data.mensagem);
					reject({
						mensagem : error.data.mensagem,
						inclusao : false
				})
			});
		})
	}

	listar = function(){
		return $q(function(resolve, reject){
			recursoTransferencia.get({},function(retorno){
				resolve({
						mensagem : "Foto recuperadas com sucesso!'",
						transferencias : retorno.transferencias,
						delete : true
				});
				},function(error){
					console.log(error.data.mensagem);
					reject({
						mensagem :error.data.mensagem,
						delete : false
				})
			})	
		});
	}
	return {cadastrar : cadastrar, listar : listar};
})