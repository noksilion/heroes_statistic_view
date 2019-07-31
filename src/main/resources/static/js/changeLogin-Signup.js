function changeToSignup(){
    $( "#textTitle" ).text('').append("Регистрация");
    $( '<input id="Name" type="text" name="name" placeholder="Имя" />' ).insertAfter( "#Email" );
    $('#sendButton').remove();
    $('<button id="sendButton" type="button" onclick="signup()" >Отправить</button>').insertAfter('#Password');
    $('#registrationButton').remove();
    $( '<button id="signupButton" type="button" onclick="changeToLogin()" >Войти</button>' ).insertAfter( "#textOr" );
}

function changeToLogin(){
    $( "#textTitle" ).text('').append("Войти");
    $('#Password').remove();
    $('#signupButton').remove();
    $('#sendButton').remove();
    $('<button id="sendButton" type="button" onclick="login()" >Отправить</button>').insertAfter('#Name');
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
            var tokenObject = JSON.parse(this.responseText);
            document.cookie = "token=" + tokenObject.token;
            window.location.href = appHost + "/";
        }
        if (this.status !== 200) {
            var errorJson = JSON.parse(this.responseText);
            $('#errorText').remove();
            $('<h3 id ="errorText"></h3>').insertAfter('#textTitle');
            var errorMessage = errorJson['debugMessage'];
            $('#errorText').text('').append("Неправильный логин или пароль");
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
            var tokenObject = JSON.parse(this.responseText);
            document.cookie = "token=" + tokenObject.token;
            var url  = new URL(appHost+"/");
            url.searchParams.append('message',"User Sucessfully added!\n Please activate it on your Email");
            window.location.href = url.toString();
        }else{
            var errorJson = JSON.parse(this.responseText);
            $('#errorText').remove();
            $('<h3 id ="errorText"></h3>').insertAfter('#textTitle');
            var errorMessage = errorJson['debugMessage'];
            $('#errorText').text('').append(errorMessage);
        }
    };
    xhr.send(JSON.stringify(credentials));
}