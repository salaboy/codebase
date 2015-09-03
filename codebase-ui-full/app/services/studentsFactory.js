(function () {
    var $students = function ($http) {
        var factory = {};
        //GET STUDENTS
        factory.getAllStudents = function () {
            var allStudents = [{name:"Milburn Elliot", id:0, groupId:1}, {name:"Rilla Crawford", id:1, groupId:0}, {name:"Dory Bishop", id:2, groupId:1}, {name:"Winter Leighton", id:3, groupId:2},  {name:"Wallis Brandon", id:4, groupId:1} ];
            return allStudents;
        }
        //GET STUDENT
        factory.getStudents = function (id) {
           
        }
        //GET STUDENT IN GROUP
        factory.getStudentsInGroup = function (groupId) {
            var someStudents = [{name:"Milburn Elliot", id:0, groupId:1}, {name:"Dory Bishop", id:2, groupId:1}, {name:"Wallis Brandon", id:4, groupId:1} ];
            return someStudents;
        }
        //UPDATE STUDENT
        factory.updateStudent = function (data) {
           
        };
        //NEW STUDENT
        factory.newStudent = function (data) {
           
        };
        //DELETE STUDENT
        factory.deleteStudent = function (id) {
         
        };
        
        return factory;
    };

    $students.$inject = ['$http'];
    angular.module("codebase").factory("$students", $students);

}());