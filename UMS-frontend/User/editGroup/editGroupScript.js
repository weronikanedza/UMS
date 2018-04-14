$(document).ready(function(){
   const editGroup=JSON.parse(localStorage.getItem('editGroup'));
   const convertedEditGroup={
       id: editGroup.id,
       name: editGroup.name
   }

   alert(convertedEditGroup.id)
   alert(convertedEditGroup.name)
   console.log(convertedEditGroup)
   fillForm(convertedEditGroup.name);
   postData(convertedEditGroup);
});

function fillForm(groupName){ 
        $("#groupName").val(groupName);
}

function postData(convertedEditGroup){
    $("#editGroupForm").submit(function() {
        convertedEditGroup.name=$("#groupName").val();
        event.preventDefault();
        $.ajax({
            type: "POST",
            headers : {
                "content-type" : "application/json"
            },
            url: "http://localhost:8282/editGroup",
            data: JSON.stringify(convertedEditGroup),
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
                alert("Edit group accepted");
                window.location.href="../groups/groups.html";
                break;
            case 500 :
                showErrorMessage("Server error.Try again latter");
                break;
            default:
                showErrorMessage("Error occured.Try again latter");
        }
}

function showErrorMessage(text){
    $('#errMessage').html(text);
    $('#message-box').css("display","block");
}
