angular.module('transferencia-eletronica').controller('TransferenciaController', function($scope,cadastroDeTrasnferencia,$routeParams){
	$scope.foto = {};
	
	$scope.transferencias = [];
	
	$scope.foto.dataTransferencia = new Date();

	  $scope.minDate = new Date(
	      $scope.foto.dataTransferencia.getFullYear(),
	      $scope.foto.dataTransferencia.getMonth(),
	      $scope.foto.dataTransferencia.getDate());

	$scope.mensagem = '';
	
	$scope.listar = function (){
		cadastroDeTrasnferencia.listar().then(function(retorno){
			$scope.transferencias = retorno.transferencias;
			$scope.tableParams = new NgTableParams({}, { dataset: $scope.transferencias});
		})
		.catch(function(retorno){
			$scope.mensagem = retorno.mensagem;
		});
	}

	$scope.submeter = function(){
		$scope.foto.dataTransferencia = $scope.formatarData($scope.foto.dataTransferencia)
		if($scope.formulario.$valid){
			cadastroDeTrasnferencia.cadastrar($scope.foto)
			.then(function(retorno){
				$scope.mensagem = retorno.mensagem;
				if(retorno.inclusao) $scope.foto = {};
				$scope.$broadcast('fotoCadastrada');
			})
			.catch(function(retorno){
				$scope.mensagem = retorno.mensagem;
			});
		}
	};
	
	
	$scope.formatarData = function (data) {
		var d = new Date(data),
		mes = '' + (d.getMonth() + 1),
		dia = '' + d.getDate(),
		ano = d.getFullYear();

		if (mes.length < 2) mes = '0' + mes;
		if (dia.length < 2) dia = '0' + dia;

		return [dia, mes, ano].join('/');
};

	$scope.listar();
	
});



/*$http.get('v1/fotos/' + $routeParams.fotoId)
		.success(function(foto){
			$scope.foto = foto;
		})
		.error(function(erro){
			console.log(erro);
			$scope.mensagem ='Nao foi possivel obter foto';
		});*/



/*if($scope.foto._id){
			recursoFoto.update({fotoId : $scope.foto._id}, $scope.foto, function(){
				$scope.mensagem ='Foto alterada com sucesso!';
			},function(error){
				$scope.mensagem ='Nao foi possivel alterar foto';
			});
			$http.put('v1/fotos/' + $scope.foto._id, $scope.foto)
			.success(function(){
				$scope.mensagem ='Foto alterada com sucesso!';
			})
			.error(function(erro){
				console.log(erro);
				$scope.mensagem ='Nao foi possivel alterar foto';
			});
		}else{
			if($scope.formulario.$valid){
				recursoFoto.save($scope.foto,function(){
					$scope.foto = {};
					$scope.mensagem ='Foto cadastrada com sucesso!';
			},function(error){
				$scope.mensagem ='Nao foi possivel cadastrar foto';
			});
			/*$http.post('v1/fotos',$scope.foto).success(function(response){
				$scope.foto = [];
				$scope.mensagem ='Foto cadastrada com sucesso!';
			}).error(function(error){
				$scope.mensagem =error;
			})
		}*/