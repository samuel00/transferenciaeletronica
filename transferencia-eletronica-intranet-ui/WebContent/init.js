var app = angular.module('transferencia-eletronica-intranet-ui');

app.
config(function ($routeProvider, $locationProvider,$httpProvider, $mdThemingProvider, $sceDelegateProvider, $mdDateLocaleProvider, $mdIconProvider){
    config.routes($routeProvider);

    $sceDelegateProvider.resourceUrlWhitelist([
        // Allow same origin resource loads.
        'self',
        // Allow loading from our assets domain.  Notice the difference between * and **.
        '/sefa-ui/**',
        'http://app-dev.sefa.pa.gov.br/sefa-ui/**',
        'http://app-hom.sefa.pa.gov.br/sefa-ui/**',
        'https://app.sefa.pa.gov.br/sefa-ui/**'
      ]);

    $mdThemingProvider.theme('default')
    .primaryPalette('blue', {'default': '800'})
    .accentPalette('grey');

    $mdDateLocaleProvider.formatDate = function(date) {
      return date ? moment(date).format('DD/MM/YYYY') : null;
    };
    $mdDateLocaleProvider.parseDate = function(dateString) {
      var m = moment(dateString, 'DD/MM/YYYY', true);
      return m.isValid() ? m.toDate() : new Date(NaN);
    };

    $mdIconProvider.defaultIconSet('/sefa-ui/v1/fonts/mdi.svg');
}).
run(['$rootScope', function($rootScope) {
    moment.locale('pt-br');
}]);
