(function () {
    angular
        .module('search.factory', [])
        .factory('searchFactory', searchFactory);

    searchFactory.$inject = ['$cookies', 'restFactory'];

    function searchFactory($cookies, rest) {
        var service = {
            get: get,
        }

        return service;

        function get() {
            var data = rest.post('rest/auth2/register', authObj);
        }
    }
})();