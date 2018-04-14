$(document).ready(function(){
    setDateInput();
    const userId=localStorage.getItem('userId');
    get_data(userId);
});

function get_data(userId){
    $.ajax({
        type: "POST",
        headers : {
            "content-type" : "application/json"
        },
        url: "http://localhost:8282/getUserById",
        data:userId,
        complete: function(data) {  //received groups
            if(data.status==200){   //check response status
                fillForm(data); 
                readyToPost(userId);    //after get post is avaible
            }
            else getFailed();
        }  ,
        dataType: 'application/json'
    });
}

function fillForm(data){ 
    const group=JSON.parse(data.responseText).userGroups;
    const user=JSON.parse(data.responseText).user;

    $.each(group, function (i, group) {
        $('#selectpicker').append($('<option>', { 
            value: group.id,
            text:  group.name
        }));
    });
        $('#selectpicker option[value="'+user.group.id+'"]').attr("selected",true);
        $("#firstName").val(user.firstName);
        $("#lastName").val(user.lastName);
        $("#userName").val(user.userName);  
        $("#datepicker").val(user.date);     
}

function readyToPost(userId){

    $("#addUserForm").submit(function() {
        event.preventDefault();
        const user={
        id: userId,
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
    $.ajax({
        type: "POST",
        headers : {
            "content-type" : "application/json"
        },
        url: "http://localhost:8282/editUser",
        data: JSON.stringify(user),
        complete: function(data) {
            if(data.status==200) editUserAccepted(); //check response status
            else if(data.status==500) editUserRejected(data);
            else editUserBlankFields();
        }  ,
        dataType: "application/json"
    });
}

function editUserRejected(data){
    var dataObject=JSON.parse(data.responseText);  
    $('#errMessage').html( dataObject.message);
    $('#message-box').css("display","block").css("background","#FFEBE8").css("color","#D52727")
    .css("border","1px solid #D52727");
}

function editUserBlankFields(){
    $('#errMessage').html( "Blank fields");
    $('#message-box').css("display","block").css("background","#FFEBE8").css("color","#D52727")
    .css("border","1px solid #D52727");
}

function editUserAccepted(){
    localStorage.removeItem('userId');
    alert("Edit user sucessful");
    window.location.href="../mainPage.html";
}

function getFailed(){
    $('#errMessage').html( "Server error.Refresh page");
    $('#message-box').css("display","block");
}