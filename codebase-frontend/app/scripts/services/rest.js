'use strict';

/**
 * @ngdoc service
 * @name codebaseFrontendApp.rest
 * @description
 * # rest
 * Factory in the codebaseFrontendApp.
 */
angular.module('codebaseFrontendApp')
  .factory('rest', ['$http', function($http) {

    var urlBase = '/api/';
    var restFactory = {};

    restFactory.get = function (ext) {
        return $http.get(urlBase + '/' + ext);
    };

    restFactory.post = function (ext, data) {
        return $http.post(urlBase + '/' + ext, data);
    };
      
    restFactory.testall = function () {
        return 'test path';
    };

    return restFactory;
}]);