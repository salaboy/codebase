(function () {
    angular
        .module('student.factory', [])
        .factory('studentFactory', studentFactory);

    studentFactory.$inject = ['$http', 'constantsFactory'];

    function studentFactory( $http, constants) {
        var service = {
            newStudent: newStudent,
            getAllStudents: getAllStudents,
            getStudent: getStudent,
            updateStudent: updateStudent,
            deleteStudent: deleteStudent
        };

        return service;

        function newStudent(student) {
            return $http.post(constants.server + 'schools/students/new', student, constants.headers);
        }

        function getAllStudents() {
            return $http.get(constants.server + 'schools/students', constants.headers);
        }
        
        function getStudent(id){
            return $http.get(constants.server + 'schools/students/'+id, constants.headers);
        }
       
       function updateStudent(student){
           return $http.put(constants.server + 'schools/students/'+student.id+'/update', student, constants.headers);
       }
       
       function deleteStudent(id){
           return $http.delete(constants.server + 'schools/students/'+id, constants.headers);
       }
    }
})();