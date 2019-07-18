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
             <button type="submit" formaction="/view/add_battle" formmethod="get">Add Battle</button>
         </td>
         <td>
             <button type="submit" formaction="/view/statistic" formmethod="get">Get Statistic</button>
         </td>
         <td>
             <button type="submit" formmethod="get" formaction="/view_unapproved_games">Get Statistic</button>
         </td>
     </tr>
     <tr>

     </tr>

 </table>


</body>
</html>