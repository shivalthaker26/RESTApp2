
'use strict';
reservationAppCtrls
    .controller('EditReservationCtrl', ['$http','$scope','$modal','$location','$routeParams', function($http, $scope, $modal, $location, $routeParams) {



         $scope.user = {};
         
         
	        $scope.date = new Date();

	        $scope.open = {

	            date: false
	        }
	        $scope.items = {
	        		"1"  : "US +1",
	                "91" : "India +91",
	                "01" : "CANADA +1",
	                "44" : "UNITED KINGDOM +44"
	            }

      


         $scope.dateOptions = {
             showWeeks: false,
             startingDay: 1
         };

         $scope.timeOptions = {
             readonlyInput: true,
             showMeridian: false
         };

         $scope.openCalendar = function(e, date) {
             e.preventDefault();
             e.stopPropagation();

             $scope.open[date] = true;
         };

        $scope.getCustomer = function()
        {
   

                $http({
                    method:'GET',
                    url:'api/customer/get/' + $routeParams.reservationNumber
                }).success(function(response){
                   
                    $scope.user = response.payload;
                    
                    $scope.user.date = new Date(response.payload.date*1000);
                }).error(function(error){
                    console.log(error);
                });


        }
        $scope.getCustomer();



        $scope.updateCust = function()
        {


        	$scope.getDate = new Date($scope.user.date);

        	
        	$scope.user.date = $scope.getDate.getTime()/1000;

            $scope.confirmationsDetails = {};

            $scope.openConfirmationModal = function(size, response)
            {
                var modalInstance = $modal.open({
                    animation: true,
                    templateUrl: 'myModalContent.html',
                    controller: 'ConfirmationDetailModal',
                    size: size,
                    resolve: {
                        confirmationDetails: function () {
                            return response;
                        }
                    }
                })
            };
        	$http({
        		method:'POST',
        	    url:'api/customer/update',
        	    data:$scope.user
        	}).success(function(response)
        	{
                $scope.openConfirmationModal('lg',response.payload);
                $scope.user = '';

            }).error(function(error)
        	{
        		console.log(error);
        	})
        }








    }]);/**
 * Created by shivalthaker26 on 8/24/15.
 */
