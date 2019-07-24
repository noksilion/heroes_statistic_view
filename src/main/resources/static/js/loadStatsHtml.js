function loadStatsHtml2Params() {
    var xhttp = new XMLHttpRequest();
    var url  = new URL('http://localhost:8085/get_stats_html');

     url.searchParams.append('userEmail', document.getElementById("Email2Params").value);
     url.searchParams.append('heroId', document.getElementById("EnemyHero2Params").value);

    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            var tableRowsFromServer = this.responseText;
            $('.tr2Params').remove();
            $( tableRowsFromServer ).insertAfter( "#statisticBy2ParamsLastTr" );
            $('.trFromServer').removeClass('trFromServer').addClass('tr2Params');

        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}

function loadStatsHtmlByEmail() {
    var xhttp = new XMLHttpRequest();
    var url  = new URL('http://localhost:8085/get_stats_html');

    url.searchParams.append('userEmail', document.getElementById("EmailParam").value);

    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            var tableRowsFromServer = this.responseText;
            $('.trEmailParam').remove();
            $( tableRowsFromServer ).insertAfter( "#statisticByEmailLastTr" );
            $('.trFromServer').removeClass('trFromServer').addClass('trEmailParam');
        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}

