function changeToSignup(){
    $('#errorText').remove();
    $('#Email').css('margin-top',"40px");
    $( "#textTitle" ).text('').append("Пройдите регистрацию");
    $( '<input id="Name" type="text" name="name" placeholder="Имя" />' ).insertAfter( "#Email" );
    $('#sendButton').remove();
    $('<button id="sendButton" type="button" onclick="signup()" >Регистрация</button>').insertAfter('#Password');
    $('#registrationButton').remove();
    $( '<button id="signupButton" type="button" onclick="changeToLogin()" >Войти</button>' ).insertAfter( "#textOr" );
}

function changeToLogin(){
    $('#errorText').remove();
    $('#Email').css('margin-top',"40px");
    $( "#textTitle" ).text('').append("Войти");
    $('#Name').remove();
    $('#signupButton').remove();
    $('#sendButton').remove();
    $('<button id="sendButton" type="button" onclick="login()" >Войти</button>').insertAfter('#Password');
    $( '<button id="registrationButton" type="button" onclick="changeToSignup()">Регистрация</button>' ).insertAfter( "#textOr" );
}

function login(){
    var restAppHost = document.getElementById("restHost").value;
    var appHost = document.getElementById("appHost").value;
    var xhr = new XMLHttpRequest();

    var url  = new URL(restAppHost+"/login");
    var credentials = {};
    credentials.email = document.getElementById("Email").value;
    credentials.password = document.getElementById("Password").value;

    xhr.withCredentials = true;
    xhr.open("POST", url.toString(), true);
    xhr.onreadystatechange = function() {
        if (this.status === 200) {
            addCookie(JSON.parse(this.responseText));
            window.location.href = appHost + "/";
        }
        if (this.status !== 200) {
            var errorJson = JSON.parse(this.responseText);
            $('#errorText').remove();
            $('<h3 id ="errorText"></h3>').insertAfter('#textTitle');
            $('#errorText').text('').append("Неправильный логин или пароль");
            $('#Email').css('margin-top',"13px");
        }
    };
    xhr.send(JSON.stringify(credentials));
}

function signup(){
    var restAppHost = document.getElementById("restHost").value;
    var appHost = document.getElementById("appHost").value;
    var xhr = new XMLHttpRequest();

    var url  = new URL(restAppHost+"/singup");
    var credentials = {};
    credentials.email = document.getElementById("Email").value;
    credentials.password = document.getElementById("Password").value;
    credentials.userName = document.getElementById("Name").value;

    xhr.withCredentials = true;

    xhr.open("POST", url.toString(), true);
    xhr.onreadystatechange = function() {
        if (this.status === 200) {
            addCookie(JSON.parse(this.responseText));
            var url  = new URL(appHost+"/");
            url.searchParams.append('message',"User Sucessfully added!\n Please activate it on your Email");
            window.location.href = url.toString();
        }else{
            var errorJson = JSON.parse(this.responseText);
            $('#errorText').remove();
            $('<h3 id ="errorText"></h3>').insertAfter('#textTitle');
            var errorMessage = errorJson['debugMessage'];
            $('#errorText').text('').append(errorMessage);
            $('#Email').css('margin-top',"13px");
        }
    };
    xhr.send(JSON.stringify(credentials));
}

function addCookie(tokenObject){
    var d = new Date();
    var days=5;
    d.setTime(d.getTime() + (days*24*60*60*1000));
    var expires = ""+d.toUTCString();

    document.cookie =
        'token=' + tokenObject.token +
        '; expires=' + expires +
        '; path=/';
}