if (config == undefined) var config = {};

config.routes = function ($routeProvider) {

    $routeProvider.
        when('/', {
            templateUrl: 'views/home.html',
            caseInsensitiveMatch: true,
            label: 'Cadastro de Usuários'
        }).
        when('/usuarios', {
            templateUrl: 'views/usuario/listagem.html',
            caseInsensitiveMatch: true,
            label: 'Consultar Usuários'
        }).

        when('/usuario/', {
            templateUrl: 'views/usuario/cadastro.html',
            caseInsensitiveMatch: true,
            label: 'Cadastrar Usuário'
        }).
        when('/usuario/:id', {
            templateUrl: 'views/usuario/cadastro.html',
            caseInsensitiveMatch: true,
            label: 'Editar Usuário'
        }).
        when('/paginaUm', {
            templateUrl: 'views/paginaUm/index.html',
            caseInsensitiveMatch: true,
            label: 'Cadastro'
        }).
        when('/paginaDois', {
            templateUrl: 'views/paginaDois/index.html',
            caseInsensitiveMatch: true,
            label: 'Detalhamento'
        }).
        otherwise({
            //templateUrl: 'views/404.html',
            template: "<div>página não encontrada</div>",
        });
    //$locationProvider.html5Mode(false);
};
