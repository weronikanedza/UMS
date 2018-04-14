$(document).ready(function(){
  get_data();
}); 


function get_data(){

  $.ajax({
      type: "GET",
      url: "http://localhost:8282/getGroups",
      complete: function(data) {  
          if(data.status==200) fillTable(data);
          else alert("failed");
      }  ,
      dataType: "application/json"
  });
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
localStorage.setItem('editGroup',JSON.stringify(editGroup));
window.location.href="../../User/editGroup/editGroup.html";
}

function removeGroup(chosenRow){
var cells = chosenRow.closest("tr").children("td"); //get table row
post_data(cells.eq(0).text());
}

function post_data(groupId){
$.ajax({
  type: "POST",
  headers : {
      "content-type" : "application/json"
  },
  url: "http://localhost:8282/removeGroup",
  data: groupId,
  complete: function(data) {
      if(data.status==200){
        alert("Group removed"); //check response status
        window.location.href = window.location.href;
      }
      else alert("Try again");
  }  ,
  dataType: "application/json"
});
}
// function getFailed(){
//   alert("fails");
// }
