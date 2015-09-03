(function () {
    

    var teachersController = function ($scope, $rootScope, $location, $routeParams, $teachers, $students, $groups) {
        $scope.userType = $rootScope.usertype;
        $scope.urlparams = $routeParams;
        $scope.teachers = $teachers.getAllTeachers();
        $scope.groups= $groups.getAllGroups(); 
        
        //ADD TEACHER FORM
        $scope.addSubmitted = false;
        $scope.addTeacher = function (isValid, teacher) {

            $scope.addSubmitted = true;

            if (isValid) {
                $location.path("/teachers");
            }
        };
            
        //CHECK URL PARAMETERS
        if (!isEmpty($scope.urlparams)) {
            $scope.currentUser = $scope.teachers[$scope.urlparams.id];
        }
        
        //UPDATE TEACHER
        $scope.updateTeacher = function (isValid, teacher) {

            $scope.updateSubmitted = true;

            if (isValid) {
                $location.path("/teachers");
            }
        };
    }
    
    teachersController.$inject = ['$scope', '$rootScope', '$location', '$routeParams', '$teachers', '$students', '$groups'];
    angular.module( "codebase" ).controller("teachersController", teachersController);
    
}());