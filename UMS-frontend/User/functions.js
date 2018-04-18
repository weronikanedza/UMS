function getData(urlEnd){
    $.ajax({
        type: "GET",
        cache: false,
        url: "http://localhost:8282/"+urlEnd,
        complete: function(data) {  
         completeGetBody(data);
        },
        dataType: "application/json"
    });
  }

  function postData(data,urlEnd){
    $.ajax({
      type: "POST",
      headers : {
          "content-type" : "application/json"
      },
      url: "http://localhost:8282/"+urlEnd,
      data: data,
      complete: function(data) {
          completePostBody(data);
      }  ,
      dataType: "application/json"
    });
  }

  function responseAction(data,location){
    switch(data.status){
        case 200 :
            alert("Request accepted");
            window.location.href=location;
            break;
        case 500 :
            const err=JSON.parse(data.responseText);
            showErrorMessage(err.message);
            break;
        default:
            showErrorMessage("Error occured.Try again latter");
            break;
    }
  }
  
  function setSelect(data){
    const dataObject=JSON.parse(data.responseText);
    $.each(dataObject, function (i, dataObject) {
        $('#selectpicker').append($('<option>', { 
            value: dataObject.id,
            text: dataObject.name
        }));
    });
}

function showErrorMessage(text){
    $('#errMessage').html(text);
    $('#message-box').css("display","block");
}