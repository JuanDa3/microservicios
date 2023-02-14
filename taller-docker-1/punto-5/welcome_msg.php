<?php

$nombre = $_POST['nombre'];

$cedula = $_POST['cedula'];
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bienvenida</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            position: absolute;
            width: 100%;
            height: 100%;
        }
        .flex-container {
            display: -ms-flexbox;
            display: -webkit-flex;
            display: flex;
            -ms-flex-align: center;
            -webkit-align-items: center;
            -webkit-box-align: center;
            align-items: center;
            height: 100%;
            background: #f1f2b5;
            background: -webkit-linear-gradient(to right,  #135838, #e5e6dc);
            background: linear-gradient(to right, #135838, #e5e6dc);
        }
        .welcome-message {
            margin: 0 auto;
        }
    </style>
</head>
<body>
<div class="flex-container">
  <div class="welcome-message">
    <h1>Bienvenido <?php echo $nombre; ?> Con cedula: <?php echo $cedula; ?></h1>
  </div>
</div>
</body>
</html>