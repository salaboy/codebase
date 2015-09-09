"use strict";
(function () {
    

    var studentsController = function ($scope, $rootScope, $location, $routeParams, $students, $util) {
        $scope.userType = $rootScope.usertype;
        $scope.urlparams = $routeParams;
        $scope.students = $students.getAllStudents();
 
        //ADD TEACHER FORM
        $scope.addSubmitted = false;
        $scope.addStudent = function (isValid) {

            $scope.addSubmitted = true;

            if (isValid) {
                $location.path("/students");
            }
        };
            
        //CHECK URL PARAMETERS
        if (!$util.isEmpty($scope.urlparams)) {
            $scope.currentStudent = $scope.students[$scope.urlparams.id];
        }
        
        //UPDATE TEACHER
        $scope.updateStudent = function (isValid) {

            $scope.updateSubmitted = true;

            if (isValid) {
                $location.path("/students");
            }
        };
    };
    module.exports = studentsController;
//    studentsController.$inject = ['$scope', '$rootScope', '$location', '$routeParams', '$students', '$util'];
//    angular.module( "codebase" ).controller("studentsController", studentsController);
    
}());