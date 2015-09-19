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
    'ngHello' 
  ])
  .config(function ($routeProvider, helloProvider) {
  
    helloProvider.init({
        twitter: 'KEY'
    });
    
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
