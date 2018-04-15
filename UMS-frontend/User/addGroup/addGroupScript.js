$(document).ready(function(){
    postData();
 });
 
 
 function postData(){
     $("#addGroupForm").submit(function() {
         event.preventDefault();
         $.ajax({
             type: "POST",
             headers : {
                 "content-type" : "application/json"
             },
             url: "http://localhost:8282/addGroup",
             data: $("#groupName").val(),
             complete: function(data){
                 responseAction(data);
             } ,
             dataType: "application/json"
         });
     });
 }
 
 function responseAction(data){
         switch(data.status){
             case 200 :
                 alert("Add group accepted");
                 window.location.href="../groups/groups.html";
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
 