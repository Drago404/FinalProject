function searchFunction(){
    	var action_src = "http://localhost:8080/FinalProject/search/" + document.getElementsByName("txtSearch")[0].value;
    	var form = document.getElementById('searchForm');
    	form.action = action_src ;	
	}

function quantityInput(){
	  return document.getElementById('quantity_input').value;
}