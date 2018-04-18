$(document).ready(function(){
    getData("getGroups");
});

function completeGetBody(data){
    if(data.status!=200) alert("Error occured.Try again");
    else getGroups(data);
}

function getGroups(data){
    if(data.status==200 && JSON.parse(data.responseText).length>0){ //check if at least one group exists
        setSelect(data); 
        readyToPost();    
    }else if(data.status==200){
        showErrorMessage("You have to add group before adding user");
    }else{
        responseAction(data);
    }
}

function readyToPost(){
    $("#addUserForm").submit(function() {
        event.preventDefault();
        const user=getUser();
        if(validation(user)) postData(JSON.stringify(user),"addUser");
    });
}

function completePostBody(data){
    responseAction(data,"../mainPage.html");
}

function getUser(){
    const user={
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

