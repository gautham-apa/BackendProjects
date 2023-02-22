<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Movie Browser</title>
  </head>
  <body>
    <h1>Movie List</h1>
    <c:if test = "${movieList.size() == 0}">
        <p>No results found</p>
    </c:if>
    <table>
      <c:forEach items="${movieList}" var="movie">
        <tr>
          <td>Movie Title:</td>
          <td>
            <c:out value="${movie.title}" />
          </td>
        </tr>
        <tr>
          <td>Actor:</td>
          <td><c:out value="${movie.actor}" /></td>
        </tr>
        <tr>
          <td>Actress:</td>
          <td><c:out value="${movie.actress}" /></td>
        </tr>
        <tr>
          <td>Year:</td>
          <td><c:out value="${movie.year}" /></td>
        </tr>
        <td><br /></td>
      </c:forEach>
    </table>
    <br /><br />
    <form method="get">
      <button type="submit">Home Page</button>
    </form>
  </body>
</html>
