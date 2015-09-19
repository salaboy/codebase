'use strict';

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
    'ui.router'
  ])
  .config(function ($stateProvider, $urlRouterProvider, helloProvider) {
    helloProvider.init({
        twitter: 'myTwitterToken'
    });

    $urlRouterProvider.otherwise("/");
    $stateProvider
      .state('home', {
        url:'/',
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .state('about', {
        url:'/about',
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl'
      });
  }).run(function ($log, hello) {
        hello.on("auth.login", function (r) {
            $log.log(r.authResponse);
    });
});