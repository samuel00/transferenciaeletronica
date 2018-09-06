angular.module('transferencia-eletronica-intranet-ui', [
    //modulos principais
    'ngRoute', 'ngMessages', 'ngAnimate', 'ngMaterial', 'sefa-ui',
    'md.data.table', 'ngMask', 'ui.utils.masks',

    //serviços
    //-------------
    'data', 'utilService', 'recurso',

    //filtros
    //-------------
    'defaultFilters',

    //diretivas
    //-------------

    //sub-modulos
    'usuario'

]);

//filtros
angular.module('defaultFilters',[]);

//Serviços
angular.module('utilService', []);
angular.module('data', []);
angular.module('recurso', []);

//sub-modulos
angular.module('usuario', []);
angular.module('sefa-ui', []);
