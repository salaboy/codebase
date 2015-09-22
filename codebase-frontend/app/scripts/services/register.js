'use strict';

/**
 * @ngdoc service
 * @name codebaseFrontendApp.register
 * @description
 * # register
 * Factory in the codebaseFrontendApp.
 */
angular.module('codebaseFrontendApp') 
    .factory('register', ['$http', '$rootScope', 'rest', 'hello', function($http, $rootScope, rest, hello) {
        
        var registerFactory = {};
        
        hello.on('auth.login', function(auth) {
            registerFactory.getUser(auth.network);
        });

        registerFactory.post = function (network, data) {
          hello.api(network+':/me/share', 'post', data, function(r){
			if(!r||r.error){
				alert('Whoops! '+r.error.message);
			}
			else{
				alert('Your message has been published to '+ network);
			}
		  });
        };
        
        registerFactory.login = function (network) {
          hello(network).login().then(function() {
                //get network
                
            }, function(e) {
                alert('Signin error: ' + e.error.message);
            });
        };
        
        registerFactory.getUser = function (network) {
            hello(network).api('/me').then(function(r) {
                alert(JSON.stringify(r));
                $rootScope.user = r;
            });
        };
        
        registerFactory.register = function () {
          //api post to server
        };
        
        registerFactory.testall = function () {
            return 'Register';
        };


    return registerFactory;
}]);

    