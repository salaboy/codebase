"use strict";
(function () {
    var mainController = function ($scope, $rootScope, $location) {
        $rootScope.usertype = "admin";
        $rootScope.logged = true;

        $scope.logout = function () {
            $rootScope.logged = false;
            $location.path("/");
        };
    };

    //mainController.$inject = ['$scope', '$rootScope', '$location'];

    module.exports = mainController;

}());