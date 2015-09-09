"use strict";
(function () {

    var navController = function ($scope, $location, $rootScope, $users) {


        $scope.isActive = function (route) {
            return route === $location.path();
        };

        $scope.logoutUser = function () {

            $users.logout().success(function () {
                $location.path("/");
                $rootScope.logged = false;
            }).error(function (data) {
                console.log("Error: " + data.error);
            });




        };

    };

//    navigationController.$inject = ['$scope', '$location', '$rootScope', '$users'];
//    angular.module("codebase").controller("navigationController", navigationController);
    module.exports = navController;
}());
