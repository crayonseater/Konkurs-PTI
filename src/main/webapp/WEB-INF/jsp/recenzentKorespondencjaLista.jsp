<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>



<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<div class="col-md-8 col-md-offset-2">

	<h1>${recenzent.getImie()} ${recenzent.getNazwisko()}</h1>

	<h2>Korespondencja oczekująca</h2>
	<div class="row">
		<table class="table table-hover">
			<thead>
				<th>Tytul</th>
				<th>Status</th>
				<th>Akcje</th>
			</thead>
			<c:forEach items="${niePrzeczytane}" var="list">
			<tr>
				<td>${list.getTytul()}</td>
				<td>${list.getStatus()}</td>
				<td>
					<a href="/recenzent/korespondencja/detale/${list.getId()}">
						<button type="button" class="btn btn-info">Detale</button>
					</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
<h2>Korespondencja przeczytana</h2>
<div class="row">
	<table class="table table-hover">
		<thead>
			<th>Tytul</th>
			<th>Status</th>
			<th>Akcje</th>
		</thead>
		<c:forEach items="${przeczytane}" var="list">
		<tr>
			<td>${list.getTytul()}</td>
			<td>${list.getStatus()}</td>
			<td>
				<a href="/korespondencja/detale/${list.getId()}">
					<button type="button" class="btn btn-info">Detale</button>
				</a>
			</td>
		</tr>
	</c:forEach>
</table>
</div>
</div>