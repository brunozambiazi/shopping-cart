angular.module('app', [])

	.config(['$httpProvider', function ($httpProvider) {
		$httpProvider.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
		$httpProvider.defaults.transformRequest.unshift(function (data, headersGetter) {
			var key, result = [];

			if (typeof data === "string") {
				return data;
			}

			for (key in data) {
				if (data.hasOwnProperty(key))
					result.push(encodeURIComponent(key) + "=" + encodeURIComponent(data[key]));
				}
			
				return result.join("&");
			}
		);
	}])

	.controller('AppController', ['$timeout', '$scope', '$http', function($timeout, $scope, $http) {
		
		$scope.openCart = function() {
			var elements = new Array();
			
			$scope.cart.items.forEach(function(item) {
				var element = {};
				element.item = item;
				element.product = $scope.products.filter(function(element, index) {
					return element.id === item.product_id;
				})[0];
				elements.push(element);
			});
			
			$scope.cartProducts = elements;
		};
		
		$scope.addToCart = function(product, quantity, callback) {
			if (!quantity) {
				quantity = 1;
			}
			
			$params = {'product_id' : product.id, 'quantity' : quantity};
			$http.post('/api/shoppingcart/items', $params).then(function(response) {
				getShoppingCart(callback);
			});
		};
		
		$scope.removeFromCart = function(product, callback) {
			var item = $scope.cart.items.filter(function(element, index) {
				return element.product_id === product.id;
			})[0];
			
			$http.delete('/api/shoppingcart/items/' + item.id).then(function(response) {
				product.inCart = false;
				getShoppingCart(callback);
			});
		};
		
		var getProducts = function() {
			$http.get('/api/products').then(function(response) {
				$scope.products = response.data;
			});
		};
		
		var getShoppingCart = function(callback) {
			$http.get('/api/shoppingcart').then(function(response) {
				$scope.cart = response.data;
				
				$scope.cart.items.forEach(function(item) {
					var product = ($scope.products || []).filter(function(element, index) {
						return element.id === item.product_id;
					})[0];
					
					if (product) {
						product.inCart = true;
					}
				});
				
				if (callback) {
					$scope.openCart();
				}
			});
		};
		
		$timeout(function() {
			getProducts();
			getShoppingCart();
		});
		
	}])	
	
;