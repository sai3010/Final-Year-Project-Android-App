/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
   // document.getElementById("main").style.marginLeft = "250px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
   // document.getElementById("main").style.marginLeft= "0";
}

$('#mymodal').on('shown.bs.modal', function () {
  $('#addstudent').trigger('focus')
})
function toggle( id1, id2, id3)
{
    $(id1).css("display","none");
    $(id2).css("display","none");
    $(id3).css("display","table");
}