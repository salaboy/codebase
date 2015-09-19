'use strict';

/**
 * @ngdoc service
 * @name codebaseFrontendApp.register
 * @description
 * # register
 * Factory in the codebaseFrontendApp.
 */
angular.module('codebaseFrontendApp')
    .factory('register', ['$http', 'hello', function($http, hello) {
        
        var restFactory = {};

        restFactory.init = function () {
          
        };

        restFactory.login = function () {
             hello('twitter').login();
        };

        restFactory.testall = function () {
            hello('facebook').login().then(function() {
                alert('You are signed in to Facebook');
            }, function(e) {
                alert('Signin error: ' + e.error.message);
            });
            return 'Register';
        };


    return restFactory;
}]);

    