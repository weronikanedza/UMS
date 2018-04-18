$(document).ready(function(){
    const userId=sessionStorage.getItem('userId');
    getDataByUserId(userId);
});

function getDataByUserId(userId){
    $.ajax({
        type: "POST",
        headers : {
            "content-type" : "application/json"
        },
        url: "http://localhost:8282/getUserById",
        data:userId,
        complete: function(data) {  
            if(data.status==200){   //check response status
                fillForm(data); 
                readyToPost(userId); 
            }
            else responseAction(data);
        },
        dataType: "application/json"
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
    $('#firstName').val(user.firstName);
    $('#lastName').val(user.lastName);
    $('#userName').val(user.userName);  
    $('#datepicker').val(user.date);     
}

function readyToPost(userId){
    $('#addUserForm').submit(function() {
        event.preventDefault();
        const user=getUser(userId);
     if(validation(user))
      postData(JSON.stringify(user),"editUser");
    });
}

function getUser(userId){
    const user={
        id: userId,
        firstName: $('#firstName').val(),
        lastName: $('#lastName').val(),
        userName: $('#userName').val(),
        password : $('#password').val(),
        date : $('#datepicker').val(),
        groupId :$('#selectpicker').val(),
        passwordConfirm : $('#passwordConfirm').val()   
    }
    return user;
}

function completePostBody(data){
    responseAction(data,"../mainPage.html");
}