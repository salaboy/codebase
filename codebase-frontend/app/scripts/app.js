(function () {

    /**
     * @ngdoc overview
     * @name codebaseFrontendApp
     * @description
     * # codebaseFrontendApp
     *
     * Main module of the application.
     */

    angular
        .module('codebaseFrontendApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ngHello',
    'ui.router',
    'user.factory',
    'rest.factory',
    'constants.factory',
    'user.controller',
    'about.controller',
    'search.controller',
    'search.factory',
    'profile.controller',
    'profile.factory'
  ])
        .config(function ($stateProvider, $urlRouterProvider, helloProvider) {
            helloProvider.init({
                facebook: '163194807355490'
            });

            $urlRouterProvider.otherwise('/');
            $stateProvider
                .state('home', {
                    url: '/',
                    templateUrl: 'scripts/User/user.html',
                    controller: 'userController'
                })
                .state('about', {
                    url: '/about',
                    templateUrl: 'scripts/About/about.html',
                    controller: 'aboutController'
                });
        }).run(function () {});
})();