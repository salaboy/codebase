(function () {
    angular
        .module('teacher.factory', [])
        .factory('teacherFactory', teacherFactory);

    teacherFactory.$inject = ['$http', 'constantsFactory'];

    function teacherFactory( $http, constants) {
        var service = {
            newStudent: newStudent,
            getAllStudents: getAllStudents,
            getStudent: getStudent,
            updateStudent: updateStudent,
            deleteStudent: deleteStudent
        };

        return service;

        function newStudent(student) {
            return $http.post(constants.server + 'schools/teachers/new', student, constants.headers);
        }

        function getAllStudents() {
            return $http.get(constants.server + 'schools/teachers', constants.headers);
        }
        
        function getStudent(id){
            return $http.get(constants.server + 'schools/teachers/'+id, constants.headers);
        }
       
       function updateStudent(student){
           return $http.put(constants.server + 'schools/teachers/'+student.id+'/update', student, constants.headers);
       }
       
       function deleteStudent(id){
           return $http.delete(constants.server + 'schools/teachers/'+id, constants.headers);
       }
    }
})();