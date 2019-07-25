function loadStatsHtml2Params(link) {
    var xhttp = new XMLHttpRequest();
    var url  = new URL(link);

     url.searchParams.append('userId', document.getElementById("Email2Params").value);
     url.searchParams.append('heroId', document.getElementById("EnemyHero2Params").value);

    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            $( "#statisticBy2ParamsError" ).text('');
            var tableRowsFromServer = this.responseText;
            $('.tr2Params').remove();
            $( tableRowsFromServer ).insertAfter( "#statisticBy2ParamsLastTr" );
            $('.trFromServer').removeClass('trFromServer').addClass('tr2Params');

        }else if(this.readyState === 4 && this.status === 400 ){
            var errorFromServer = JSON.parse( this.responseText);
            var errorMessage = errorFromServer['debugMessage'];
            $( "#statisticBy2ParamsError" ).text('').append( errorMessage );
        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}

function loadStatsHtmlByEmail(link) {
    var xhttp = new XMLHttpRequest();
    var url  = new URL(link);

    url.searchParams.append('userId', document.getElementById("EmailParam").value);

    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            $( "#statisticByEmailError" ).text('');
            var tableRowsFromServer = this.responseText;
            $('.trEmailParam').remove();
            $( tableRowsFromServer ).insertAfter( "#statisticByEmailLastTr" );
            $('.trFromServer').removeClass('trFromServer').addClass('trEmailParam');
        }
        else if(this.readyState === 4 && this.status === 400){
            var errorFromServer = JSON.parse( this.responseText);
            var errorMessage = errorFromServer['debugMessage'];
            $( "#statisticByEmailError" ).text('').append( errorMessage );
        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}

function loadStatsHtmlByHero(link) {
    var xhttp = new XMLHttpRequest();
    var url  = new URL(link);

    url.searchParams.append('heroId', document.getElementById("YouHeroParam").value);

    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            $( "#statisticByHeroError" ).text('');
            var tableRowsFromServer = this.responseText;
            $('.trHeroParam').remove();
            $( tableRowsFromServer ).insertAfter( "#statisticByHeroLastTr" );
            $('.trFromServer').removeClass('trFromServer').addClass('trHeroParam');
        }
        else if(this.readyState === 4 && this.status === 400){
            var errorFromServer = JSON.parse( this.responseText);
            var errorMessage = errorFromServer['debugMessage'];
            $( "#statisticByHeroError" ).text('').append( errorMessage );
        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}

function loadStatsHtmlGlobalUserWinRate(link) {
    var xhttp = new XMLHttpRequest();
    var url  = new URL(link);

    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            $( "#globalWinRateError" ).text('');
            var tableRowsFromServer = this.responseText;
            $('.trGlobalWinRate').remove();
            $( tableRowsFromServer ).insertAfter( "#globalWinRateTr" );
            $('.trFromServer').removeClass('trFromServer').addClass('trGlobalWinRate');
        }
        else if(this.readyState === 4 && this.status === 400){
            var errorFromServer = JSON.parse( this.responseText);
            var errorMessage = errorFromServer['debugMessage'];
            $( "#globalWinRateError" ).text('').append( errorMessage );
        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}

