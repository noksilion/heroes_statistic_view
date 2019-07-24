function loadStatsHtml2Params(host) {
    var xhttp = new XMLHttpRequest();
    var url  = 'http://localhost:8085/get_stats_html?userId=2&heroId=2';

    // url.searchParams.append('userId', document.getElementById("UserName2Params").value);
    // url.searchParams.append('heroId', document.getElementById("EnemyHero2Params").value);

    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            $( this.responseText ).insertAfter( ".lastTR" );
        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}

