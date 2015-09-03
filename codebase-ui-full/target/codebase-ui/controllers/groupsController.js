(function () {
    

    var groupsController = function ($scope, $rootScope, $location, $routeParams, $teachers, $students, $groups)  {
        $scope.userType = $rootScope.usertype;
        $scope.urlparams = $routeParams;
        $scope.groups= $groups.getAllGroups();
        
        $scope.teachers = $teachers.getAllTeachers();
        
        $scope.students = $students.getAllStudents();
        
        
        //ADD TEACHER FORM
        $scope.addSubmitted = false;
        $scope.addGroup = function (isValid, group) {

            $scope.addSubmitted = true;

            if (isValid) {
                $location.path("/groups");
            }
        };
            
        //CHECK URL PARAMETERS
        if (!isEmpty($scope.urlparams)) {
            $scope.currentGroup = $scope.groups[$scope.urlparams.id];
            $scope.currentGroup.students = $students.getStudentsInGroup($scope.urlparams.id);
        }
        
        //UPDATE TEACHER
        $scope.updateTGroup = function (isValid, group) {

            $scope.updateSubmitted = true;

            if (isValid) {
                $location.path("/groups");
            }
        };
    }
    
    groupsController.$inject = ['$scope', '$rootScope', '$location', '$routeParams', '$teachers', '$students', '$groups'];
    angular.module( "codebase" ).controller("groupsController", groupsController);
    
}());