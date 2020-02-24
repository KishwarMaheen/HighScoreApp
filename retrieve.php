<?php
	$db_name = "highscoredb";
	$mysql_user = "root";
	$mysql_pass = "mysql";
	$server_name = "localhost";
	$con = mysqli_connect($server_name, $mysql_user, $mysql_pass, $db_name);
	$sql_query = "SELECT name, score FROM score_info;";
	$result = mysqli_query($con, $sql_query);
	$response = array();
	while($row = mysqli_fetch_array($result)){
		array_push($response, array("name"=>$row[0], "score"=>$row[1]));
	}
	echo json_encode(array("server_response"=>$response));
	mysqli_close($con);
?>