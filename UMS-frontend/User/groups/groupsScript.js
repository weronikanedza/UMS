$(document).ready(function(){
  getData("getGroups");
}); 


function completeGetBody(data){
    if(data.status==200) fillTable(data);
    else responseAction(data,"../groups/groups.html");
}

function fillTable(data){
   var dataObject=JSON.parse(data.responseText);
    for(i=0;i<dataObject.length;i++){
        $("#table").append("<tr><td>"+dataObject[i].id+"</td><td>"+dataObject[i].name+
       "</td><td><button class='edit' onclick ='editGroup($(this))'>edit</button><button class='remove' onclick ='removeGroup($(this))'>remove</button></td></tr>");
   }
}

function editGroup(chosenRow){
    var cells = chosenRow.closest("tr").children("td"); //get table row
    const editGroup={
      id:cells.eq(0).text(),
      name:cells.eq(1).text()
    }
    sessionStorage.setItem('editGroup',JSON.stringify(editGroup));
    window.location.href="../../User/editGroup/editGroup.html";
}

function removeGroup(chosenRow){
  var cells = chosenRow.closest("tr").children("td"); //get table row
  postData(cells.eq(0).text(),"removeGroup");
}

function completePostBody(data){
    if(data.status==200){
        alert("Group removed"); //check response status
        window.location.href = window.location.href;
    }
    else alert("Try again");
}