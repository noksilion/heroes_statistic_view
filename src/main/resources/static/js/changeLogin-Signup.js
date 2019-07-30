function changeToSignup(){
    $( "#textTitle" ).text('').append("Регистрация");
    $( '<input id="Password" type="password" name="password" placeholder="Пароль" />' ).insertAfter( "#Email" );
    $('<button id="sendButton" type="button" onclick="login()" >Отправить</button>')
    $('#registrationButton').remove();
    $( '<button id="signupButton" type="button" onclick="changeToLogin()" >Войти</button>' ).insertAfter( "#textOr" );
}

function changeToLogin(){
    $( "#textTitle" ).text('').append("Войти");
    $('#Password').remove();
    $('#signupButton').remove();
    $( '<button id="registrationButton" type="button" onclick="changeToSignup()">Регистрация</button>' ).insertAfter( "#textOr" );
}

function register(restHost){
    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;

    var credentialsJson = {};
    credentialsJson.hero_id = parseInt(document.getElementById("enemy"+i+"HeroName").value);

    xhr.open("POST", restHost+"/login", true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.send(credentialsJson);

    xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            $( "#Message" ).text('').append("Game successfully added");

        }else if(this.readyState === 4  ){
            var errorFromServer = JSON.parse( xhr.responseText);
            var errorMessage = errorFromServer['debugMessage'];
            $( "#Message" ).text('').append( errorMessage );
        }
    };
}

function signup(){

}