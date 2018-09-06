if(config==undefined)var config={};

config.routes=function ($routeProvider) {

    $routeProvider.
        when('/', {
            templateUrl: 'views/home.html',
            caseInsensitiveMatch: true,
            requiresAuthentication: false,
            label: 'Cadastro de Usuários'
        }).
        when('/usuarios', {
            templateUrl: 'views/usuario/listagem.html',
            caseInsensitiveMatch: true,
            requiresAuthentication: true,
            permissions: '/transferencia-eletronica-ui/#/usuarios',
            label: 'Consultar Usuários'
        }).
        when('/usuario/', {
            templateUrl: 'views/usuario/cadastro.html',
            caseInsensitiveMatch: true,
            requiresAuthentication: true,
            permissions: '/transferencia-eletronica-ui/#/usuarios',
            label: 'Cadastrar Usuário'
        }).
        when('/usuario/:id', {
            templateUrl: 'views/usuario/cadastro.html',
            caseInsensitiveMatch: true,
            requiresAuthentication: true,
            permissions: '/transferencia-eletronica-ui/#/usuarios',
            label: 'Editar Usuário'
        }).
        otherwise({
            //templateUrl: 'views/404.html',
            template: "<div>página não encontrada</div>",
        });
        
    //$locationProvider.html5Mode(false);
};
