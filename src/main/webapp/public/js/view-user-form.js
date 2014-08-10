// Add validation for the user details form
var addVerification = function () {
}

(function () {
    "use strict";
    
    $('#user-details-form').validate({
        rules: {
        	id: {
                required: false,
        		min: 1,
        		max: 9999999
        	},
        	version: {
                required: false,
        		min: 1,
        		max: 9999999
        	},
            name: {
                minlength: 3,
                maxlength: 80,
                required: true
            },
            username: {
                minlength: 6,
                maxlength: 20,
                required: true
            },
            password: {
                minlength: 8,
                maxlength: 20,
                required: false
            },
            confirm: {
                minlength: 8,
                maxlength: 20,
                required: false,
                equalTo: "#inputPassword"
            }
        },
        highlight: function(label) {
            $(label).closest('.form-group').removeClass('success error valid');
            $(label).closest('.form-group').addClass('error');
        },
        success: function(label) {
            $(label).closest('.form-group').removeClass('success error valid');
            $(label).closest('.form-group').addClass('success');
            label.remove();
        }
   });
    
}());