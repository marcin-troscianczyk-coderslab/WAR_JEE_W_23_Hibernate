<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Search book</title>

    <style>
        .flex-container {
            display: flex;
            flex-direction: column;
        }
    </style>
</head>
<body>
<h1>Search book</h1>
<form>
    <div class="flex-container">
        <span>By title:<input type="text" name="title"/></span>
    </div>
    <input type="submit" value="Search book">
</form>
<form>
    <div class="flex-container">
        <span>By category:<input type="text" name="id"/></span>
    </div>
    <input type="submit" value="Search book">
</form>
</body>
</html>
