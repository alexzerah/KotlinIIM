<#-- @ftlvariable name="" type="zerah.alexandre.cms.model.Article" -->
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style></style>
</head>
<body>

<h1>New Article</h1>
<form action="/articles/create" method="POST">
    <label>Title</label>
    <input type="text" name="title" value="${title}"/> <br/>

    <label>Text</label>
    <textarea type="text" name="text" value="${text}">Enter body...</textarea> <br/>

    <button type"submit">Done</button>
</form>

<a href="/articles">Back to articles</a>

</body>
</html>
