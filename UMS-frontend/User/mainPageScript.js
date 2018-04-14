$(document).ready(function(){
    get_data();
  }); 


  function get_data(){

    $.ajax({
        type: "GET",
        url: "http://localhost:8282/getUsers",
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
    console.log(i);
    $("#table").append("<tr><td>"+dataObject[i].id+"</td><td>"+dataObject[i].userName+"</td><td>"+dataObject[i].firstName+"</td>"
    +"<td>"+dataObject[i].lastName+"</td><td>"+dataObject[i].date+"</td><td>"+dataObject[i].group.name+
    "</td><td><button class='edit' onclick ='editUser($(this))'>edit</button><button class='remove' onclick ='removeUser($(this))'>remove</button></td></tr>");
  }
}

function editUser(chosenRow){

  var cells = chosenRow.closest("tr").children("td"); //get table row
  localStorage.setItem('userId',cells.eq(0).text());
  window.location.href="../User/editUser/editUser.html";
}

function removeUser(chosenRow){
  var cells = chosenRow.closest("tr").children("td"); //get table row
  post_data(cells.eq(0).text());
}

function post_data(userId){
  $.ajax({
    type: "POST",
    headers : {
        "content-type" : "application/json"
    },
    url: "http://localhost:8282/removeUser",
    data: userId,
    complete: function(data) {
        if(data.status==200){
          alert("User removed"); //check response status
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
