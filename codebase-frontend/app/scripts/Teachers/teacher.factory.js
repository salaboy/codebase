(function () {
    angular
        .module('teacher.factory', [])
        .factory('teacherFactory', teacherFactory);

    teacherFactory.$inject = ['$http', 'constantsFactory'];

    function teacherFactory( $http, constants) {
        var service = {
            newTeacher: newTeacher,
            getAllTeachers: getAllTeachers,
            getTeacher: getTeacher,
            updateTeacher: updateTeacher,
            deleteTeacher: deleteTeacher
        };

        return service;

        function newTeacher(teacher) {
            return $http.post(constants.server + 'schools/teachers/new', teacher, constants.headers);
        }

        function getAllTeachers() {
            return $http.get(constants.server + 'schools/teachers', constants.headers);
        }
        
        function getTeacher(id){
            return $http.get(constants.server + 'schools/teachers/'+id, constants.headers);
        }
       
       function updateTeacher(teacher){
           return $http.put(constants.server + 'schools/teachers/'+teacher.id+'/update', teacher, constants.headers);
       }
       
       function deleteTeacher(id){
           return $http.delete(constants.server + 'schools/teachers/'+id, constants.headers);
       }
    }
})();