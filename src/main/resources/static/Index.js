//form.addEventListener('submit', e => {
//    e.preventDefault();
//    validateInputs();
//});
//
//var fileInput = document.getElementById('fileUpload');
//
//const setError = (element, message) => {
//    const inputControl = element.parentElement;
//    const errorDisplay = inputControl.querySelector('.error');
//
//    errorDisplay.innerText = message;
//    inputControl.classList.add('error');
//};
//
//const setSuccess = element => {
//    const inputControl = element.parentElement;
//    const errorDisplay = inputControl.querySelector('.error');
//
//    errorDisplay.innerText = '';
//    inputControl.classList.remove('error');
//};
//
//const validateInputs = () => {
//
//    if (fileInput === '') {
//        setError(fullUpload, 'Full Name is required');
//    } else {
//        setSuccess(fileUpload);
//    }



//function uploadFile() {
//  var formData = new FormData();
//  formData.append('fileUpload', $('#fileUpload')[0].files[0]);
//
//  $.ajax({
//    url: '/uploadFile',
//    type: 'POST',
//    data: formData,
//    processData: false,
//    contentType: false,
//    success: function(data) {
//      // Update HTML elements with the returned data
//      $('#ListErrorsLength').text(data.ListErrorsLength);
//      $('#excelListLength').text(data.excelListLength);
//      $('#errors').text(data.errors);
//      $('#ListErrors').text(data.ListErrors);
//      $('#totalRecords').text(data.totalRecords);
//    },
//    error: function() {
//      // Handle error case
//      console.log('Error occurred during AJAX call.');
//    }
//  });
//}

//    $(document).ready(function() {
//        $('#form').submit(function(event) {
//            event.preventDefault();
//
//            var form = $(this);
//            var url = form.attr('action');
//            var formData = new FormData(form[0]);
//
//            $.ajax({
//                type: 'POST',
//                url: url,
//                data: formData,
//                processData: false,
//                contentType: false,
//                success: function(response) {
//                    // Display errors
//                    if (response.errors) {
//                        var errorMessages = response.errors;
//                        var errorContainer = $('#errorMessages');
//                        errorContainer.empty();
//
//                        errorMessages.forEach(function(errorMessage) {
//                            errorContainer.append('<p>' + errorMessage + '</p>');
//                        });
//                    }
//
//                    // Display Excel errors
//                    if (response.listErrors) {
//                        var excelErrors = response.listErrors;
//                        var excelErrorContainer = $('#excelErrors');
//                        excelErrorContainer.empty();
//
//                        excelErrors.forEach(function(excelError) {
//                            var excelErrorRow = '<tr>';
//                            excelErrorRow += '<td>' + excelError.Id + '</td>';
//                            excelErrorRow += '<td>' + excelError.name + '</td>';
//                            excelErrorRow += '<td>' + excelError.position + '</td>';
//                            excelErrorRow += '<td>' + excelError.branch + '</td>';
//                            excelErrorRow += '<td>' + excelError.yearClass + '</td>';
//                            excelErrorRow += '<td>' + excelError.email + '</td>';
//                            excelErrorRow += '<td>' + excelError.mobile + '</td>';
//                            excelErrorRow += '</tr>';
//
//                            excelErrorContainer.append(excelErrorRow);
//                        });
//                    }
//                },
//                error: function() {
//                    console.log('Error occurred during the AJAX request.');
//                }
//            });
//        });
//    });

//
//$(document).ready(function() {
//  $('#form').on('submit', function(e) {
//    e.preventDefault();
//
//    var form = $(this);
//    var formData = new FormData(form[0]);
//
//    $.ajax({
//      url: form.attr('action'),
//      type: 'POST',
//      data: formData,
//      processData: false,
//      contentType: 'multipart/form-data',
//      success: function(response) {
//        if (response.validationErrors.length > 0) {
//          // Construct the error messages as a string
//          var errorMessages = response.validationErrors.join(';');
//
//          // Encode the error messages for URL parameter
//          var encodedErrorMessages = encodeURIComponent(errorMessages);
//          //console.log(errorMessages);
//
//          // Redirect to the other page with the error messages as URL parameter
//          window.location.href = 'success.html?errors=' + encodedErrorMessages;
//        } else {
//          window.location.href = 'success.html';
//        }
//      },
//      error: function(xhr, status, error) {
//        console.log(error);
//      }
//    });
//  });
//});
