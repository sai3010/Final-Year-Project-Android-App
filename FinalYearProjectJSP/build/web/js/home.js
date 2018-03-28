/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    //document.getElementById("mp").style.marginLeft = "250px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    //document.getElementById("mp").style.marginLeft= "0";
}

$('#mymodal').on('shown.bs.modal', function () {
  $('#addstudent').trigger('focus');
})

function toggle1( id1, id2, id3)
{
    $(id1).css("display","none");
    $(id2).css("display","none");
    $(id3).css("display","table");
}

function toggle2( c1, c2, c3)
{
    $(c1).css("display","none");
    $(c2).css("display","none");
    $(c3).css("display","block");
}

function getvalue(){
    var checks= document.getElementsByClassName('checks');
    var count=0;
    var str="";
    
    for (i=0;i<7;i++){
        if(checks[i].checked===true){
            count ++;
            str+=checks[i].value + " ";
            }
            
 }
 
    if(count==0)
    {
        alert("Choose a branch!");
    }
    else{
        alert(count);
        alert(str);
        }
}