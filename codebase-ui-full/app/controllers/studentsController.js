(function () {
    

    var studentsController = function ($scope, $rootScope, $location, $routeParams, $teachers, $students, $groups) {
        $scope.userType = $rootScope.usertype;
        $scope.urlparams = $routeParams;
        $scope.students = $students.getAllStudents();
 
        //ADD TEACHER FORM
        $scope.addSubmitted = false;
        $scope.addStudent = function (isValid, teacher) {

            $scope.addSubmitted = true;

            if (isValid) {
                $location.path("/students");
            }
        };
            
        //CHECK URL PARAMETERS
        if (!isEmpty($scope.urlparams)) {
            $scope.currentStudent = $scope.students[$scope.urlparams.id];
        }
        
        //UPDATE TEACHER
        $scope.updateStudent = function (isValid, teacher) {

            $scope.updateSubmitted = true;

            if (isValid) {
                $location.path("/students");
            }
        };
    }
    
    studentsController.$inject = ['$scope', '$rootScope', '$location', '$routeParams', '$teachers', '$students', '$groups'];
    angular.module( "codebase" ).controller("studentsController", studentsController);
    
}());