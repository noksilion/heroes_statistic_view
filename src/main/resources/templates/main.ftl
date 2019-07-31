<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Main</title>
</head>
<body>
 <#include "templates/footer.ftl">
 <h1>Main Page</h1>

 <#if message??>
     <h2>${message}</h2>
 </#if>

 <table>
     <tr>
         <th>Add Battle</th>
         <th>View Statistic</th>
         <th>Approve Games</th>
     </tr>
     <tr>
         <td>
             <form action="/view/add_battle" method="get">
                 <button type="submit" >Add Battle</button>
             </form>
         </td>
         <td>
             <form action="/view/statistic" method="get">
                 <button type="submit">Get Statistic</button>
             </form>
         </td>
         <td>
             <form action="/view_unapproved_games" method="get">
                 <button type="submit">Approve Game</button>
             </form>
         </td>
     </tr>
     <tr>

     </tr>

 </table>


</body>
</html>