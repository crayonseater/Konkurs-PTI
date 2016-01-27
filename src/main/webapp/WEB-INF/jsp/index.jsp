<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


<html lang="en">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<body>


	<script type="text/javascript">
	function wybierz() {
		var id = window.prompt("Wybierz recenzenta","4");
		window.location = "/recenzent/korespondencja/lista/" + id;
	}
	</script>
	<div class="col-md-8 col-md-offset-2">

		<p>
			<a href="/operator/lista">
				<button class="btn btn-info">Perspektywa operatora</button>
			</a>
		</p>
		<p>
			<button class="btn btn-info" onclick="wybierz()">Perspektywa recenzenta</button> 
		</p>

		<div class="row">
			<table class="table table-hover">
				<thead>
					<th>Id</th>
					<th>Imie</th>
					<th>Nazwisko</th>
				</thead>
				<c:forEach items="${recenzenci}" var="recenzent">
				<tr>
					<td>${recenzent.getId()}</td>
					<td>${recenzent.getImie()}</td>
					<td>${recenzent.getNazwisko()}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
</body>

</html>