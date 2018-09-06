/*
 * TEMPLATE CRUD COMPLETO
 */

(function () {
    var app = angular.module("transferencia-eletronica-intranet-ui");
    app.factory('usuarioService', function($http) {
        _getUsuario = function(){
            return $http.get('/transferencia-eletronica-api/api/intranet/pessoas/');
        };

        _getBuscaUsuario = function(usuarioId){
            return $http.get('/transferencia-eletronica-api/api/intranet/pessoas/'+usuarioId);
        };

        _postUsuario = function(usuarioModel){
            return $http.post('/transferencia-eletronica-api/api/intranet/pessoas/', usuarioModel);
        };

        _putUsuario = function(usuarioId,usuarioModel){
            return $http.put('/transferencia-eletronica-api/api/intranet/pessoas/'+usuarioId,usuarioModel);
        };

        _deleteUsuario = function(itemId){
            return $http.delete('/transferencia-eletronica-api/api/intranet/pessoas/'+itemId);
        };           

        return {
            getUsuario: _getUsuario,
            getBuscaUsuario: _getBuscaUsuario,
            postUsuario: _postUsuario,
            putUsuario: _putUsuario,
            deleteUsuario: _deleteUsuario
        }
    });
})();