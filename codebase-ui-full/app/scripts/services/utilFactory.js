"use strict";
(function () {

    var $util = function () {
        var factory = {};

        factory.isEmpty = function (obj) {
            if (obj === null) {
                return true;
            }
            if (obj.length > 0) {
                return false;
            }
            if (obj.length === 0) {
                return true;
            }
            for (var key in obj) {
                if (Object.prototype.hasOwnProperty.call(obj, key)) {
                    return false;
                }
            }
            return true;
        };
        
        factory.transformRequest = function(obj){
            var str = [];
            for (var key in obj) {
                if (obj[key] instanceof Array) {
                    for (var idx in obj[key]) {
                        var subObj = obj[key][idx];
                        for (var subKey in subObj) {
                            str.push(encodeURIComponent(key) + "[" + idx + "][" + encodeURIComponent(subKey) + "]=" + encodeURIComponent(subObj[subKey]));
                        }
                    }
                }
                else {
                    str.push(encodeURIComponent(key) + "=" + encodeURIComponent(obj[key]));
                }
            }
            return str.join("&");
        };
        
        return factory;
    };

    //angular.module("codebase").factory("$util", $util);
    module.exports = $util;

}());


