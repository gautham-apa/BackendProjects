<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Add Movie to Database</title>
  </head>
  <body>
    <h1>Please enter the details below</h1>
    <form method="post">
      <label for="title">Movie Title: </label>
      <input type="text" id="title" name="title" /><br /><br />
      <label for="actor">Lead Actor: </label>
      <input type="text" id="actor" name="actor" /><br /><br />
      <label for="actress">Lead Actress: </label>
      <input type="text" id="actress" name="actress" /><br /><br />
      <label for="genre">Genre: </label>
      <input type="text" id="genre" name="genre" /><br /><br />
      <label for="year">Year: </label>
      <input
        type="number"
        min="1900"
        max="2099"
        step="1"
        value="2023"
        name="year"
      /><br /><br />
      <button type="submit">Add Movie</button>
    </form>
  </body>
</html>
