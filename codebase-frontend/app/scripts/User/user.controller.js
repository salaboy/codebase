'use strict';

/**
 * @ngdoc function
 * @name codebaseFrontendApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the codebaseFrontendApp
 */
angular.module('user.controller', [])
    .controller('userController', ['$scope', '$rootScope', 'userFactory', function ($scope, $rootScope, userFactory) {
        // register.login('facebook');
        // user.login({email:'grogdj@gmail.com', password:'asdasd'});
        $scope.username = 'asdasad';
}]);