'use strict';

/**
 * @ngdoc service
 * @name codebaseFrontendApp.register
 * @description
 * # register
 * Factory in the codebaseFrontendApp.
 */
angular.module('codebaseFrontendApp')
    .factory('register', ['$http', function($http, hello) {
        
        var restFactory = {};

        restFactory.init = function () {
          
        };

        restFactory.login = function () {
            hello('facebook').login().then(function() {
                alert('You are signed in to Facebook');
            }, function(e) {
                alert('Signin error: ' + e.error.message);
            });
        };

        restFactory.testall = function () {
            return 'Register';
        };

    return restFactory;
}]);

    