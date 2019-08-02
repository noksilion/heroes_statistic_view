<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Main</title>
    <link rel="stylesheet" href="/css/main.css">
</head>
<body>

<div id="externalDiv">
    <div id ="hommLogo">
        <img src="/img/homm.png" alt="image" height="95" width="285"/>
    </div>

    <div id = middleDiv>
        <div id = "angelGif">
            <img src="/img/archangel.gif"  alt="archangel">
        </div>
        <div id="menu">
            <form action="/view/add_battle" method="get">
                <button type="submit" >Add Battle</button>
            </form>
            <form action="/view/statistic" method="get">
                <button type="submit">Get Statistic</button>
            </form>
            <form action="/view_unapproved_games" method="get">
                <button type="submit">Approve Game</button>
            </form>
        </div>
        <div id="dragonGif">
            <img src="/img/dragon.gif" alt="dragon">
        </div>
    </div>

    <div id="goToMainPage">
        <button type="submit">На Главную</button>
    </div>
</div>
</body>
</html>