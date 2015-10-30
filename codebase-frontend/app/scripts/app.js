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
    'student.factory',
    'student.list.controller',
    'student.new.controller'
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
                .state('students', {
                    url: '/students',
                    templateUrl: 'scripts/Student/List/list.html',
                    controller: 'studentListController'
                })
                .state('students/new', {
                    url: '/students/new',
                    templateUrl: 'scripts/Student/New/new.html',
                    controller: 'newStudentListController'
                });
        }).run(function () {});
})();