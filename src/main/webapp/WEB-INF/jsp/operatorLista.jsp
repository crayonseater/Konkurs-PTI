<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>



<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<div class="col-md-8 col-md-offset-2">
	
	<div class="container-fluid">
		<a href="/operator/napisz/recenzent/0">
		<button type="button" class="btn btn-info">Napisz na inny temat</button>
		</a>
		<h2>Tematy prac bez recenzenta</h2>
		<div class="row">
			<table class="table table-hover">
				<thead>
					<th>Tytuł</th>
					<th>Akcje</th>
				</thead>
				<c:forEach items="${praceBezRecenzenta}" var="praca">
				<tr>
					<td>${praca.getTemat()}</td>
					<td>
						<a href="/operator/detale/${praca.getId()}">
							<button type="button" class="btn btn-info">Detale</button>
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<h2>Tematy prac z recenzentem</h2>
	<div class="row">
		<table class="table table-hover">
			<thead>
				<th>Tytuł</th>
				<th>Akcje</th>
			</thead>
			<c:forEach items="${praceZRecenzentem}" var="praca">
			<tr>
				<td>${praca.getTemat()}</td>
				<td>
					<a href="/operator/detale/${praca.getId()}">
						<button type="button" class="btn btn-info">Detale</button>
					</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<h2>Listy nie związane z tematami prac</h2>
	<div class="row">
		<table class="table table-hover">
			<thead>
				<th>Temat</th>
				<th>Status</th>
				<th>Akcje</th>
			</thead>
			<c:forEach items="${listyInne}" var="list">
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
</div>
