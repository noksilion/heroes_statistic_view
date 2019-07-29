function getEnemyHtml(applicationHost) {
    var xhttp = new XMLHttpRequest();
    var url  = new URL(applicationHost+"/get_enemy_html");

    var enemiesQuantity  = parseInt(document.getElementById("enemiesQuantity").value);
    var increasedEnemiesQuantity = enemiesQuantity +1;

    url.searchParams.append('enemiesQuantity',enemiesQuantity.toString());

    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            $( "#Error" ).text('');
            var tableRowsFromServer = this.responseText;
            if(!$('#reduceEnemy').length && increasedEnemiesQuantity>1) {
                $('<button id = "reduceEnemy" type="button" onclick="reduceEnemy()">Reduce Enemy</button>').insertAfter("#addEnemy");
            }
            if(increasedEnemiesQuantity>7){
                $( "#addEnemy" ).remove();
            }
            $("#enemiesQuantityVisual").html("Enemies Quantity  - ".concat(increasedEnemiesQuantity.toString()));
            $("#enemiesQuantity").val(increasedEnemiesQuantity.toString());
            if($('.enemy').length){
                var enemyTrId = "#enemy"+enemiesQuantity.toString()+"Tr";
                $( tableRowsFromServer ).insertAfter( enemyTrId );
            }else{
                $(tableRowsFromServer).insertAfter( "#youTr" );
            }
        }
        else if(this.readyState === 4 && this.status === 400){
            var errorFromServer = JSON.parse( this.responseText);
            var errorMessage = errorFromServer['debugMessage'];
            $( "#Error" ).text('').append( errorMessage );
        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}

function reduceEnemy(){
    var enemiesQuantity  = parseInt(document.getElementById("enemiesQuantity").value);
    var reducedEnemiesQuantity = enemiesQuantity -1;

    $("#enemiesQuantityVisual").html("Enemies Quantity  - ".concat(reducedEnemiesQuantity.toString()));
    $("#enemiesQuantity").val(reducedEnemiesQuantity.toString());

    if(!$('#addEnemy').length && reducedEnemiesQuantity<8){
        $('<button id = "addEnemy" type="button" onclick="getEnemyHtml()">Add new Enemy</button>').insertBefore("#reduceEnemy");
    }
    if(reducedEnemiesQuantity<2){
        $( "#reduceEnemy" ).remove();
    }
    $( ".enemy"+enemiesQuantity+"Tr" ).remove();
}

function addBattle(restHost){

    var playersArray = [];
    var enemiesQuantity  = parseInt(document.getElementById("enemiesQuantity").value);
    var loggedPlayer = {};
    loggedPlayer.hero_id = parseInt(document.getElementById("youHero").value);
    loggedPlayer.result = document.getElementById("youResult").value;
    loggedPlayer.user_id = parseInt(document.getElementById("youId").value);
    playersArray.push(loggedPlayer);
    for (var i=0;i<enemiesQuantity;){
         i++;
         var player = {};
         player.hero_id = parseInt(document.getElementById("enemy"+i+"HeroName").value);
         player.result = document.getElementById("enemy"+i+"Result").value;
         player.user_id = parseInt(document.getElementById("enemy"+i+"EmailParam").value);
         playersArray.push(player);

    }

    var gameDtoForPost = {players:playersArray};

    var gameJson =JSON.stringify(gameDtoForPost);

    var xhr = new XMLHttpRequest();
    xhr.withCredentials = true;


    //xhr.responseType = "json";
    xhr.open("POST", restHost+"/games", true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.send(gameJson);

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