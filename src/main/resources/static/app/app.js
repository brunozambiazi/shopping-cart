angular.module('app', [])

	.controller('AppController', ['$scope', '$http', function($scope, $http) {
		
		$scope.chart = {'items' : []};
		
		$scope.addToChart = function(product) {
			$scope.products = $scope.products.filter(function(element, index) {
				return element.id !== product.id;
			});
			
			$scope.chart.items.push(product);
		};
		
		$scope.removeFromChart = function(product) {
			$scope.chart.items = $scope.chart.items.filter(function(element, index) {
				return element.id !== product.id;
			});
			
			$scope.products.push(product);
		};
		
		$http.get('/api/products').then(function(response) {
			$scope.products = response.data;
		});
		
	}])	
	
;