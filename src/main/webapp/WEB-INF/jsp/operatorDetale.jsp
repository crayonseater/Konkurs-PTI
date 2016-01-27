<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<div class="col-md-8 col-md-offset-2">

	<h1>${praca.getTemat()}</h1>
	<h2>Korespondencja dotycząca tematu pracy</h2>
	<div class="row">
		<table class="table table-hover">
			<thead>
				<th>Temat</th>
				<th>Status</th>
				<th>Akcje</th>
			</thead>
			<c:forEach items="${korespondencja}" var="list">
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

<a href="/operator/napisz/recenzent/praca/${praca.getId()}/0">
	<button type="button" class="btn btn-info">Napisz list</button>
</a>
</div>