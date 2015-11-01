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
    'rest.factory',
    'constants.factory',
    'login.factory',
    'login.controller',
    'school.factory',
    'school.list.controller',
    'school.new.controller',
    'school.details.controller',
    'school.edit.controller'
  ])
        .config(function ($stateProvider, $urlRouterProvider, helloProvider) {
            helloProvider.init({
                facebook: '163194807355490'
            });

            $urlRouterProvider.otherwise('/');
            $stateProvider
                .state('login', {
                    url: '/',
                    templateUrl: 'scripts/Login/login.html',
                    controller: 'loginController'
                })
                .state('schools', {
                    url: '/schools',
                    templateUrl: 'scripts/Schools/List/list.html',
                    controller: 'schoolListController'
                })
                .state('schools/new', {
                    url: '/schools/new',
                    templateUrl: 'scripts/Schools/New/new.html',
                    controller: 'newSchoolController'
                })
                .state('schools/edit', {
                    url: '/schools/edit',
                    templateUrl: 'scripts/Schools/Edit/edit.html',
                    controller: 'editSchoolController'
                })
                .state('schools/details', {
                    url: '/schools/details',
                    templateUrl: 'scripts/Schools/Details/details.html',
                    controller: 'schoolDetailsController'
                });
        }).run(function () {});
})();