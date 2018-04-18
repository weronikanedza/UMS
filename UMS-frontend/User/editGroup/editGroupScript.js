$(document).ready(function(){
   const editGroup=JSON.parse(sessionStorage.getItem('editGroup'));
   const convertedEditGroup={
       id: editGroup.id,
       name: editGroup.name
   }

   fillForm(convertedEditGroup.name);
   $("#editGroupForm").submit(function() {
       event.preventDefault();
       convertedEditGroup.name=$("#groupName").val();
       postData(JSON.stringify(convertedEditGroup),"editGroup");
   });
});

function fillForm(groupName){ 
        $("#groupName").val(groupName);
}

function completePostBody(data){
    responseAction(data,"../groups/groups.html");
}
