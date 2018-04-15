$(document).ready(function(){
    getData();
  }); 

  function getData(){
    $.ajax({
        type: "GET",
        url: "http://localhost:8282/getUsers",
        complete: function(data) {  
          if(data.status!=200) alert("Error occured.Try again");
          else fillTable(data);
        },
        dataType: "application/json"
    });
}

function fillTable(data){
  const dataObject=JSON.parse(data.responseText);
    for(i=0;i<dataObject.length;i++){;
      $("#table").append("<tr><td>"+dataObject[i].id+"</td><td>"+dataObject[i].userName+"</td><td>"+dataObject[i].firstName+"</td>"
      +"<td>"+dataObject[i].lastName+"</td><td>"+dataObject[i].date+"</td><td>"+dataObject[i].group.name+
      "</td><td><button class='edit' onclick ='editUser($(this))'>edit</button><button class='remove' onclick ='removeUser($(this))'>remove</button></td></tr>");
    }
}

function editUser(chosenRow){
  const cells = chosenRow.closest("tr").children("td"); //get table row
  sessionStorage.setItem('userId',cells.eq(0).text());
  window.location.href="../User/editUser/editUser.html";
}

function removeUser(chosenRow){
  const cells = chosenRow.closest("tr").children("td"); //get table row
  postData(cells.eq(0).text());
}

function postData(userId){
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
        else alert("Error occured.Try again");
    }  ,
    dataType: "application/json"
  });
}

function responseAction(data){
  switch(data.status){
      case 200 :
          alert("Edit group accepted");
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

