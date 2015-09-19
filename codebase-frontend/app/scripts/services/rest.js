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
    var dataFactory = {};

    dataFactory.get = function (ext) {
        return $http.get(urlBase + '/' + ext);
    };

    dataFactory.post = function (ext, data) {
        return $http.post(urlBase + '/' + ext, data);
    };
      
    dataFactory.testall = function () {
        return 'test path';
    };

    return dataFactory;
}]);