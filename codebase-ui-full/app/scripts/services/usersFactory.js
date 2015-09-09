"use strict";
(function () {
    var $users = function ($http, $cookieStore, $util, appConstants) {
        var factory = {};
        //SIGNUP
        factory.create = function (email, password, firstname, lastname, gender, birthday) {
            return $http({
                method: 'POST',
                url: appConstants.server + appConstants.context + 'rest/auth/create',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                transformRequest: $util.transformRequest,
                data: {email: email, password: password, firstname: firstname, 
                    lastname: lastname, gender: gender, birthday: birthday}
            });
        };
        //LOGOUT
        factory.logout = function () {
            return $http({
                method: 'POST',
                url: appConstants.server + appConstants.context + 'rest/auth/logout',
                headers: {'Content-Type': 'application/x-www-form-urlencoded', service_key: 'webkey:' + $cookieStore.get('email'), auth_token: $cookieStore.get('auth_token')},
            });
        };
        //LOGIN
        factory.login = function (user) {
            return $http({
                method: 'POST',
                url: appConstants.server + appConstants.context + 'rest/auth/login',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                transformRequest: $util.transformRequest,
                data: {email: user.email, password: user.password}
            });
        };
        
        return factory;
    };

    $users.$inject = ['$http', '$cookieStore', '$util', 'appConstants'];
   // angular.module("codebase").factory("$users", $users);
    module.exports = $users;

}());