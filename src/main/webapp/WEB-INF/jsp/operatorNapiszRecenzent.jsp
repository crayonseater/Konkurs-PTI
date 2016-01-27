<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<head>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>


</head>
<body>

	
	<div class="col-md-8 col-md-offset-2">
		<h2>Gotowe listy</h2>
		<a href="/operator/napisz/recenzent/1">
			<button class="btn btn-info">Prośba o recenzję</button>
		</a>
		<a href="/operator/napisz/recenzent/2">
			<button class="btn btn-info">Ponaglenie</button>
		</a>
		<a href="/operator/napisz/recenzent/3">
			<button class="btn btn-info">Podziękowanie</button>
		</a>
		<a href="/operator/napisz/recenzent/4">
			<button class="btn btn-info">Zaproszenie</button>
		</a>
	</br>
	<input type="file" id="file-input" />
</br>
<form class="list-form" onsubmit="return wyslijList()">
	<label for="selectRecenzent">Recenzenci:</label>
	<fieldset class="form-group" id="selectRecenzent">
	<c:forEach items="${recenzenci}" var="recenzent">
		<input type="checkbox" value="${recenzent.getId()}">${recenzent.getImie()}, ${recenzent.getNazwisko()}</option>
	</c:forEach>
</fieldset>
<fieldset class="form-group">
	<label for="tytulListu">Tytuł listu:</label>
	<textarea class="form-control" id="tytulListu" rows="1" name="tytul" required>${tytulyListow.get(letterId)}</textarea>
</fieldset>
<fieldset class="form-group">
	<label for="trescListu">Treść listu:</label>
	<textarea class="form-control" id="trescListu" rows="7" name="tresc" required>${tresciListow.get(letterId)}</textarea>
</fieldset>
<button type="submit" class="btn btn-primary">Napisz</button>
</form>

<script type="text/javascript">
function wyslijList() {

	
	var list = {
		tytul: $("#tytulListu").val(),
		tresc: $("#trescListu").val(),
		recenzenci: []
	};
	$(":checked").each(function() {
	  list['recenzenci'].push($(this).val());
	});
	console.log(list);
	$.ajax({	
		url: 'post',
		type: 'post',
		dataType: 'json',
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify(list)
	});
	window.alert("Zrobione!");
	return false;
}

function readSingleFile(e) {
	var file = e.target.files[0];
	if (!file) {
		return;
	}
	var reader = new FileReader();
	reader.onload = function(e) {
		var contents = e.target.result;
		displayContents(contents);
	};
	reader.readAsText(file);

}

function displayContents(contents) {
	var element = document.getElementById('trescListu');
	element.innerHTML = contents;
}

document.getElementById('file-input')
.addEventListener('change', readSingleFile, false);
</script>
</div>
</body>