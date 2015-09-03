(function () {

    var loginController = function ($scope, $rootScope, $users, $location, $cookieStore) {
        $scope.submitted = false;
        $rootScope.logged = false;

        $scope.loginUser = function (isValid, user) {


            $scope.submitted = true;

            if (isValid) {

                $users.login(user).success(function (data) {
                    $location.path("/home");
                    $rootScope.logged = true;
                    
                }).error(function (data) {
                    console.log("Error: " + data.error);
                });



            }
        };
    }

    loginController.$inject = ['$scope', '$rootScope', '$users', '$location'];
    angular.module("codebase").controller("loginController", loginController);

}());