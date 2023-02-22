<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Movie Store</title>
  </head>
  <body>
    <h1>Searching Movies</h1>
    <form method="get">
      <label for="searchTerm">Keyword: </label>
      <input type="text" name="searchTerm" id="searchTerm" required /><br /><br />
      <input type="radio" id="searchTitle" name="searchType" value="title" checked/>
      <label for="searchTitle">Search by title</label><br />
      <input type="radio" id="searchActor" name="searchType" value="actor" />
      <label for="searchActor">Search by actor</label><br />
      <input
        type="radio"
        id="searchActress"
        name="searchType"
        value="actress"
      />
      <label for="searchActress">Search by actress</label><br /><br />
      <button type="submit">Search Movies</button>
      <input type="hidden" id="action" name="action" value="search">
    </form>
  </body>
</html>
