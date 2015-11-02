(function () {
    angular
        .module('course.factory', [])
        .factory('courseFactory', courseFactory);

    courseFactory.$inject = ['$http', 'constantsFactory'];

    function courseFactory( $http, constants) {
        var service = {
            newCourse: newCourse,
            getAllCoursesBySchool: getAllCoursesBySchool,
            getCourse: getCourse,
            updateCourse: updateCourse,
            deleteCourse: deleteCourse
        };

        return service;

        function newCourse(course) {
            return $http.post(constants.server + 'schools/courses/new', course, constants.headers);
        }

        function getAllCoursesBySchool(schoolId) {
            return $http.get(constants.server + 'schools/'+schoolId+'/courses', constants.headers);
        }
        
        function getCourse(id){
            return $http.get(constants.server + 'schools/courses/'+id, constants.headers);
        }
       
       function updateCourse(course){
           return $http.put(constants.server + 'schools/courses/'+course.id+'/update', course, constants.headers);
       }
       
       function deleteCourse(id){
           return $http.delete(constants.server + 'schools/courses/'+id, constants.headers);
       }
    }
})();