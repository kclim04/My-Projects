function validatecode(){
    alert("Attendance has been successfully been submitted.")
}

function validatefile(){
    alert("Your assignment was uploaded successfully.")
}

function addassignment(){
    subject = document.getElementById("subject").value;
    assignmentname = document.getElementById("name").value;
    date = document.getElementById("date").value;
    alert("Assignment added for subject "+ subject + "\nAssignment Name: " + assignmentname + "\nDue Date: " + date);
}

function generatecode(){
    course = document.getElementById("course").value;
    code = Math.floor((Math.random() * 99999) + 10000); 
    var session = document.querySelector('input[type=radio][name=class]:checked');
    var day = document.querySelector('input[type=radio][name=day]:checked');
    time = document.getElementById("time").value;
    alert("Attendance Code for class "+ course + ":\n\n           " + code + "\n\nClass Session: " + session.value + "\nDay: " + day.value + "\nTime: " + time);
}

function editTimetable(){
row = window.prompt("Input the Row number\n0 = Time\n1 = Monday\n2 = Tuesday\n3 = Wednesday\n4 = Thursday\n5 = Friday", "0");
column = window.prompt("Input the Column number\n0 = Time\n1 = 8:00AM-10:00AM\n2 = 10:00AM-12:00PM\n3 = 12:00PM-2:00PM\n4 = 2:00PM-4:00PM\n5 = 4:00PM-6:00PM\n6 = 6:00PM-8:00PM","0");
content = window.prompt("Input the Cell content");  
var x=document.getElementById('lec_timetable').rows[parseInt(row,10)].cells;
x[parseInt(column,10)].innerHTML=content;
}

function submit1(){
    var x=document.getElementById('assignment_table').rows[parseInt(1,10)].cells;
    x[parseInt(3,10)].innerHTML="Submitted";
}
function submit2(){
    var x=document.getElementById('assignment_table').rows[parseInt(2,10)].cells;
    x[parseInt(3,10)].innerHTML="Submitted";
}
function submit3(){
    var x=document.getElementById('assignment_table').rows[parseInt(3,10)].cells;
    x[parseInt(3,10)].innerHTML="Submitted";
}
function unsubmit1(){
    var x=document.getElementById('assignment_table').rows[parseInt(1,10)].cells;
    x[parseInt(3,10)].innerHTML="Pending";
}
function unsubmit2(){
    var x=document.getElementById('assignment_table').rows[parseInt(2,10)].cells;
    x[parseInt(3,10)].innerHTML="Pending";
}
function unsubmit3(){
    var x=document.getElementById('assignment_table').rows[parseInt(3,10)].cells;
    x[parseInt(3,10)].innerHTML="Pending";
}

function changeattendanceP1(){
    var x=document.getElementById('lec_attendance_table').rows[parseInt(1,10)].cells;
    x[parseInt(6,10)].innerHTML="Present";
}
function changeattendanceP2(){
    var x=document.getElementById('lec_attendance_table').rows[parseInt(2,10)].cells;
    x[parseInt(6,10)].innerHTML="Present";
}
function changeattendanceP3(){
    var x=document.getElementById('lec_attendance_table').rows[parseInt(3,10)].cells;
    x[parseInt(6,10)].innerHTML="Present";
}
function changeattendanceP4(){
    var x=document.getElementById('lec_attendance_table').rows[parseInt(4,10)].cells;
    x[parseInt(6,10)].innerHTML="Present";
}
function changeattendanceA1(){
    var x=document.getElementById('lec_attendance_table').rows[parseInt(1,10)].cells;
    x[parseInt(6,10)].innerHTML="Absent";
}
function changeattendanceA2(){
    var x=document.getElementById('lec_attendance_table').rows[parseInt(2,10)].cells;
    x[parseInt(6,10)].innerHTML="Absent";
}
function changeattendanceA3(){
    var x=document.getElementById('lec_attendance_table').rows[parseInt(3,10)].cells;
    x[parseInt(6,10)].innerHTML="Absent";
}
function changeattendanceA4(){
    var x=document.getElementById('lec_attendance_table').rows[parseInt(4,10)].cells;
    x[parseInt(6,10)].innerHTML="Absent";
}

function gradeP1(){
    var x=document.getElementById('lec_assignment_table').rows[parseInt(1,10)].cells;
    x[parseInt(6,10)].innerHTML="Pass";
}
function gradeP2(){
    var x=document.getElementById('lec_assignment_table').rows[parseInt(2,10)].cells;
    x[parseInt(6,10)].innerHTML="Pass";
}
function gradeP3(){
    var x=document.getElementById('lec_assignment_table').rows[parseInt(3,10)].cells;
    x[parseInt(6,10)].innerHTML="Pass";
}
function gradeP4(){
    var x=document.getElementById('lec_assignment_table').rows[parseInt(4,10)].cells;
    x[parseInt(6,10)].innerHTML="Pass";
}
function gradeF1(){
    var x=document.getElementById('lec_assignment_table').rows[parseInt(1,10)].cells;
    x[parseInt(6,10)].innerHTML="Fail";
}
function gradeF2(){
    var x=document.getElementById('lec_assignment_table').rows[parseInt(2,10)].cells;
    x[parseInt(6,10)].innerHTML="Fail";
}
function gradeF3(){
    var x=document.getElementById('lec_assignment_table').rows[parseInt(3,10)].cells;
    x[parseInt(6,10)].innerHTML="Fail";
}
function gradeF4(){
    var x=document.getElementById('lec_assignment_table').rows[parseInt(4,10)].cells;
    x[parseInt(6,10)].innerHTML="Fail";
}

const container = document.getElementById('container');
const registerBtn = document.getElementById('register');
const loginBtn = document.getElementById('login');

registerBtn.addEventListener('click', () => {
    container.classList.add("active");
});

loginBtn.addEventListener('click', () => {
    container.classList.remove("active");
});

function login(){
    username = document.getElementById("username").value;
    alert("Welcome back!\nYou have logged in successfully.")
}

function register(){
    username = document.getElementById("username").value;
    alert("Welcome "+ username + "!\nYou have registered an account successfully.")
}

function validaterequest(){
    alert("Your request has been received successfully.")
}
