$(document).ready(function(){
    setDateInput();
    get_data();
});

function get_data(){
    $.ajax({
        type: "GET",
        url: "http://localhost:8181/getGroups",
        complete: function(data) {  //received groups
            if(data.status==200){   //check response status
                setSelect(data); 
                readyToPost();    //after get post is avaible
            }
            else getFailed();
        }  ,
        dataType: 'application/json'
    });
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

function readyToPost(){

    $("#addUserForm").submit(function() {
        event.preventDefault();
        const user={
        firstName: $("#firstName").val(),
        lastName: $("#lastName").val(),
        userName: $("#userName").val(),
        password : $("#password").val(),
        date : $("#datepicker").val(),
        groupId :$("#selectpicker").val(),
        passwordConfirm : $("#passwordConfirm").val()   
        }
    
        post_data(user);
    });
}


function setDateInput(){
    $('#datepicker').datepicker({
        uiLibrary: 'bootstrap4'
    });
}

function post_data(user){
    alert('post')
    
    $.ajax({
        type: "POST",
        headers : {
            "content-type" : "application/json"
        },
        url: "http://localhost:8181/addUser",
        data: JSON.stringify(user),
        complete: function(data) {
            if(data.status==200) addUserAccepted(); //check response status
            else if(data.status==500) addUserRejected(data);
            else addUserBlankFields();
        }  ,
        dataType: "application/json"
    });
}


function addUserRejected(data){
    var dataObject=JSON.parse(data.responseText);  
    $('#errMessage').html( dataObject.message);
    $('#message-box').css("display","block").css("background","#FFEBE8").css("color","#D52727")
    .css("border","1px solid #D52727");
}

function addUserBlankFields(){
    $('#errMessage').html( "Blank fields");
    $('#message-box').css("display","block").css("background","#FFEBE8").css("color","#D52727")
    .css("border","1px solid #D52727");
}

function addUserAccepted(){
    $('#errMessage').html( "Add user sucessful");
    $('#message-box').css("display","block").css("background","#8AE19A").css("color","#085D3C")
    .css("border","1px solid #085D3C");
}
function getFailed(){
    $('#errMessage').html( "Server error.Refresh page");
    $('#message-box').css("display","block");
}