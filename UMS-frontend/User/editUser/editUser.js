$(document).ready(function(){
    setDateInput();
    const userId=sessionStorage.getItem('userId');
    getData(userId);
});

function getData(userId){
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
                readyToPost(userId); 
            }
            else getFailed();
        },
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
      postData(user);
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

function setDateInput(){
    $('#datepicker').datepicker({
        uiLibrary: 'bootstrap4'
    });
}

function postData(user){
    $.ajax({
        type: "POST",
        headers : {
            "content-type" : "application/json"
        },
        url: "http://localhost:8282/editUser",
        data: JSON.stringify(user),
        complete: function(data) {
            responseAction(data);
        },
        dataType: "application/json"
    });
}

function showErrorMessage(text){
    $('#errMessage').html(text);
    $('#message-box').css("display","block");
}

function responseAction(data){
    switch(data.status){
        case 200 :
            alert("Edit user successful");
            window.location.href="../mainPage.html";
            break;
        case 500 :
            const err=JSON.parse(data.responseText);
            showErrorMessage(err.message);
            browser.wait(5000);
            window.location.href="editUser.html";
            break;
        default:
            showErrorMessage("Error occured.Try again latter");
            browser.wait(5000);
            window.location.href="editUser.html";
            break;
    }
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
    const dateOfBirth=new Date(parts[2],parts[1]-1,parts[0]);
    if(calculateAge(dateOfBirth)>=18 && calculateAge(dateOfBirth)<=100)  return true;
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