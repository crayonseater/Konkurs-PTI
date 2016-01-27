<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<div class="col-md-8 col-md-offset-2">

	<h3>Dotyczy: ${korespondencja.getPraca().getTemat()}</h3>
	<h3>Adresat: ${korespondencja.getRecenzent().getImie()} ${korespondencja.getRecenzent().getNazwisko()}</h3>

	<fieldset class="form-group">
		<label for="tytulListu">Tytuł listu:</label>
		<textarea class="form-control" id="tytulListu" rows="1" name="tytul" disabled>${korespondencja.getTytul()}</textarea>
	</fieldset>
	<fieldset class="form-group">
		<label for="trescListu">Treść listu:</label>
		<textarea class="form-control" id="trescListu" rows="7" name="tresc" disabled>${korespondencja.getTresc()}</textarea>
	</fieldset>
</div>