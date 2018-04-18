$(document).ready(function(){
  getData("getUsers");
}); 

function completeGetBody(data){
  if(data.status!=200) alert("Error occured.Try again");
  else fillTable(data);
}

function completePostBody(data){
    responseAction(data,window.location.href)
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
  postData(cells.eq(0).text(),"removeUser");
}

