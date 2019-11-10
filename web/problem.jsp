<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Exception</title>
</head>
<body>
<div class="error">
    <h3>Wow! How did you get here?</h3>
    <img src="img/error.jpg" alt="Error"/>
</div>

<style>
    body {
        margin: 0;
    }
    .error{
        min-height: 100vh;
        width: 100vw;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
    }
    .error img {
        width: 80%;
        max-width: 500px;
    }
    .error h3 {
        margin-top: 20px;
        font-size: 2rem;
        color: #333;
    }
</style>

</body>
</html>