(function () {
    'use strict';

    /**
     * @ngdoc service
     * @name codebaseFrontendApp.userService
     * @description
     * # userService
     * Service in the codebaseFrontendApp.
     */
    angular.module('rest.factory', [])
        .factory('restFactory', restFactory);

    restFactory.$inject = ['$http', '$cookies', 'constantsFactory'];

    function restFactory($http, $cookies, constants) {

        var service = {
            get: get,
            post: post,
            postWithAuthentication: postWithAuthentication
        };

        return service;

        ////////////

        function get(ext) {
            return $http.get(constants.server + ext, constants.authHeaders).then(function (result) {
                return result.data;
            }, function (error) {
                console.log(error);
                return null;
            });
        };

        function post(ext, data) {
            return $http.post(constants.server + ext, data).then(function (result) {
                return result.data;
            }, function (error) {
                console.log(error);
                return null;
            });
        };

        function postWithAuthentication(ext, data) {
            $http.post(constants.server, data, constants.authHeaders).then(function (result) {
                return result.data;
            }, function (error) {
                console.log(error);
                return null;
            });
        };
    }
})();