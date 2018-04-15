$(document).ready(function(){
    setDateInput();
    getData();
});

function getData(){
    $.ajax({
        type: "GET",
        url: "http://localhost:8282/getGroups",
        complete: function(data) { 
            getGroups(data);
        },
        dataType: 'application/json'
    });
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
        const user=getUser();
        if(validation(user)) postData(user);
    });
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

function setDateInput(){
    $('#datepicker').datepicker({
        uiLibrary: "bootstrap4"
    });
}

function postData(user){
    $.ajax({
        type: "POST",
        headers : {
            "content-type" : "application/json"
        },
        url: "http://localhost:8282/addUser",
        data: JSON.stringify(user),
        complete: function(data) {
            responseAction(data);
        }  ,
        dataType: "application/json"
    });
}

function responseAction(data){
    switch(data.status){
        case 200 :
            alert("Add user successful");
            window.location.href="../mainPage.html";
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

function showErrorMessage(text){
    $('#errMessage').html(text);
    $('#message-box').css("display","block");
}

function validation(user){
    if(isValidDate(user.date) && correctAge(user.date) && correctPassword(user.password)
                              && passwordConfirmation(user.password,user.passwordConfirm))
         return true;
    else return false;
}

function isValidDate(date){
    const pattern =/^([0-9]{2})\/([0-9]{2})\/([0-9]{4})$/;

    if(pattern.test(date)) return true;
    else{
        showErrorMessage("Wrong date format ex.DD/MM/YYYY");
        return false;
    }
}

function passwordConfirmation(pwd,pwdConfirm){
    if(pwd===pwdConfirm) return true;
    else{
        showErrorMessage("Passwords are not the same");
        return false;
    }
}

function correctPassword(password){
    const pattern=/(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}/;
    if(pattern.test(password)) return true;
    else{
        showErrorMessage("Password must contain :numer, uppercase letter, lowercase letter and 8-16 characters");
        return false;
    }
}

function correctAge(date){
    const parts=date.split('/');
    const dateOfBirth=new Date(parts[0],parts[1]-1,parts[2]);
    if(calculateAge(dateOfBirth)<18 || calculateAge(dateOfBirth)>100)  return true;
    else {
        showErrorMessage("Incorrect date of birth. You have to be adult.")
        return false;
    }
}

function calculateAge(dateOfBirth){
    const ageDifMs = Date.now() - dateOfBirth.getTime();
    const ageDate = new Date(ageDifMs);
    return Math.abs(ageDate.getUTCFullYear() - 1970);
}