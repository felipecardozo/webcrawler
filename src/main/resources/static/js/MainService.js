/* MainService: data access and model management layer 
 * relies on Angular injector to provide:
 *     $http: for use methods get
 */
(function () {
	'use strict';
	
	angular
	    .module('app')
	    .service('MainService', MainService);

	MainService.$inject = ['$http'];

	function MainService($http) {

		var service = {
            getData: getData
        };

		return service;

		function getData(param) {
			console.log(param);
            return $http({ method: 'GET', url: 'http://localhost:8080/crawl?url='+param }).
                success(function( data, status, headers, config) {
                    //console.log(data);
                    return data;
                }).
                error(function(data, status, headers, config) {
                    var error = {};
                    error.status = status;
                    return error;
                });
		}

	}
})();