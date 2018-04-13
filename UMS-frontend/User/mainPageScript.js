$(document).ready(function(){
   
    
var tableRef = document.getElementById('table').getElementsByTagName('tbody')[0];
var newRow   = tableRef.insertRow(tableRef.rows.length);

for (i = 0; i < tableRef.rows[0].cells.length; i++) {
    var x = newRow.insertCell(0);
    x.innerHTML = "New cell";
}
  }); 

