(function () {
    angular
        .module('profile.factory', [])
        .factory('profileFactory', profileFactory);

    profileFactory.$inject = ['$cookies', 'restFactory', 'hello'];

    function profileFactory($cookies, rest, hello) {
        var service = {
            get: get,
            update: update
        }

        return service;

        function get(id) {
            var data = rest.post('rest/auth2/register', authObj);
        }

        function update(data) {
            var data = rest.authPost('rest/auth2/logout', {});
        }
    }
})();