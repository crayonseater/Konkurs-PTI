<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>



<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<div class="col-md-8 col-md-offset-2">
	<script type="text/javascript">
	function wyslijList(id ,czyAkceptuje) {
		console.log(id, czyAkceptuje);
		$.ajax({	
			url: id + '/post',
			type: 'post',
			dataType: 'json',
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(czyAkceptuje)
		});
		window.alert("Zrobione!");
		return false;
	}
	</script>

	<fieldset class="form-group">
		<label for="tytulListu">W sprawie:</label>
		<textarea class="form-control" id="kto" rows="1" name="kto" disabled>${korespondencja.getPraca().getTemat()}</textarea>
	</fieldset>
	<fieldset class="form-group">
		<label for="tytulListu">Tytuł listu:</label>
		<textarea class="form-control" id="tytulListu" rows="1" name="tytul" disabled>${korespondencja.getTytul()}</textarea>
	</fieldset>
	<fieldset class="form-group">
		<label for="trescListu">Treść listu:</label>
		<textarea class="form-control" id="trescListu" rows="7" name="tresc" disabled>${korespondencja.getTresc()}</textarea>
	</fieldset>

	<button class="btn btn-primary" onclick="wyslijList(${korespondencja.getId()},true)">Akceptuj</button>
	<button class="btn btn-danger" onclick="wyslijList(${korespondencja.getId()},false)">Odrzuć</button>
</div>