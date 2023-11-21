/**
 * 
 */

var globalarray = [];
var total_records = 0;
var perpage = 5;

var pagenum = 1;
var currentPageNumber = 1;
var total_pages = 0;
var keyword = "";

var dynamicURL = "";
var methodName = "";
var student = {};
var books = {};
var transaction = {};
var penalty = 0;
var studentId = "";
var bookId = "";




//////////////////////////*****************    Student Page         ************************//////////////////////////////////////////////




$(document).ready(function() {

	fetch_data(pagenum);

	$('#btnstudent').click(function() {

		student.id = $('#txtId').val();
		student.name = $('#name').val();
		student.age = $('#age').val();
		student.gender = $('#gender').val();
		var studentId = student.id;

		if (studentId) {
			//update it
			dynamicURL = "/students/update"
			methodName = "PUT";
		} else {
			//save it
			dynamicURL = "http://localhost:8080/students/create";
			methodName = "POST";
		}
		var studentObj = JSON.stringify(student);
		console.log("Value  : " + studentObj);
		console.log("methodName  : " + methodName);
		$.ajax({
			url: dynamicURL,
			method: methodName,
			data: studentObj,
			contentType: 'application/json; charset=utf-8',
			success: function() {
				$('#myModal').modal('hide');
				alert('Saved successfully');
				fetch_data(currentPageNumber);

				reset();
			},
			error: function(error) {
				alert(error);
			}
		})
	})


	$('.keyword').on('keyup', function(event) { // Fired on 'keyup' event

		keyword = $('#keyword').val();
		fetch_data(pagenum);

	});



	$(document).on("click", "#btnsearch", function() {
		keyword = $('#keyword').val();
		console.log("search keyword  : " + keyword);

		fetch_data(pagenum);
	});

	$(document).on("change", "#entry", function() {
		fetch_data(pagenum);
	});


	$(document).on("change", "#studentname", function() {
		$('.error').css('display', 'none');
	});

})

function studentspagination(pagenum) {
	$("#page_container").html("");
	console.log("total_pages  : " + total_pages);
	if (pagenum == 1) {
		$("#page_container")
			.append(
				"<li class='page-item  disabled previous'><a href='javascript:void(0)'  class='pagenumber'>previous</a></li>");
	} else {
		$("#page_container")
			.append(
				"<li class='page-item' onclick='fetch_data("
				+ (pagenum - 1)
				+ ")' ><a href='javascript:void(0)'  class='pagenumber'>previous</a></li>");
	}

	var i = 0;
	for (i = 0; i <= 2; i++) {
		if (pagenum == (pagenum + i)) {
			$("#page_container")
				.append(
					"<li class='page-item  disabled'><a href='javascript:void(0)'  class='pagenumber'>"
					+ (pagenum + i) + "</a></li>");
		} else {
			if ((pagenum + i) <= total_pages) {
				$("#page_container")
					.append(
						"<li class='page-item' onclick='fetch_data("
						+ (pagenum + i)
						+ ")'><a href='javascript:void(0)'  class='pagenumber'>"
						+ (pagenum + i) + "</a></li>");
			}
		}
	}

	if (pagenum == total_pages) {
		$("#page_container")
			.append(
				"<li class='page-item  disabled'><a href='javascript:void(0)'  class='pagenumber'>next</a></li>");
	} else {
		id = 'pagenumber'
		$("#page_container")
			.append(
				"<li class='page-item next' onclick='fetch_data("
				+ (pagenum + 1)
				+ ")' ><a href='javascript:void(0)'  class='pagenumber'>next</a></li>");
	}

	currentPageNumber = pagenum;
}

function fetch_data(pagenum) {
	var studentPaginate = {};
	var pageSize = $('#entry').val();
	studentPaginate.pageSize = parseInt(pageSize);
	studentPaginate.pageNo = pagenum;
	studentPaginate.keyword = $('#keyword').val();

	var studentObj = JSON.stringify(studentPaginate);
	console.log("studentObj  : " + studentObj);
	$
		.ajax({
			url: '//localhost:8080/students/getStudents',
			method: "post",
			data: studentObj,
			dataType: "json",
			contentType: 'application/json; charset=utf-8',
			success: function(data) {
				globalarray = data.studentList;
				total_records = data.count;
				total_pages = Math.ceil(total_records / studentPaginate.pageSize);
				console.log("total_records  : " + total_records);
				console.log("total_pages  : " + total_pages);
				studentspagination(pagenum);
				console.log("Value  : " + globalarray);
				if (total_records <= 0) {
					var tableBody = $('#dtable tbody');
					tableBody.empty();
					$('.not-found').css('display', 'block');
					$('#page_container').css('display', 'none');
				} else {
					$('.not-found').css('display', 'none');
					$('#page_container').css('display', 'block');
					var tableBody = $('#dtable tbody');
					tableBody.empty();

					$(globalarray)
						.each(
							function(index, element) {
								tableBody
									.append('<tr><td>'
										+ element.id
										+ '</td><td>'
										+ element.name
										+ '</td><td>'
										+ element.age
										+ '</td><td>'
										+ element.gender
										+ '</td><td><button  onclick = "updateStudent('
										+ index
										+ ')" class="btn btn-primary" data-toggle="modal" data-target="#myModal" >Update</button> | <button onclick = "deleteStudent('
										+ element.id
										+ ')">Delete</button></td></tr>');
							})

				}

			},
			error: function() {
				$(".100_list_container").html("error");
			}
		});
}


function deleteStudent(id) {
	$.ajax({
		url: 'http://localhost:8080/students/delete/' + id,
		method: 'DELETE',
		success: function() {
			alert('record has been deleted');
			getAllStudents();
		},
		error: function(error) {
			alert(error);
		}
	})
}

function updateStudent(index) {

	var data = globalarray[index];
	console.log("data"+data);
	$('#txtId').val(data.id);
	$('#name').val(data.name);
	$('#age').val(data.age);
	$('#gender').val(data.gender);

	console.log(data.id);
}

function reset() {

	$('#name').val('');
	$('#age').val('');
	$('#gender').val('');
	$('#txtId').val('');
	$('#keyword').val('');
}

$(document).ready(function() {

	//var student = {};

	$('#btnAddStudent').click(function() {
		student.name = $('#name').val();
		student.age = $('#age').val();
		student.gender = $(".gender:checked").val();
		var studentJSON = JSON.stringify(student);
		$.ajax({
			url: 'http://localhost:8080/students/create',
			method: 'POST',
			data: studentJSON,
			contentType: "application/json; charset=utf-8",
			success: function() {
				alert('Saved successfully!');
			},
			error: function(error) {
				alert(error);
			}
		})
		document.getElementById("formId").reset();
	})
});








/////////////////////////////////********************* Generate Repot page          **************************////////////////////////////////////////////////////// */

$(document).on("click", "#btndailyreport", function() {
	$.ajax({
		url: 'http://localhost:8080/transaction/record',
		method: 'GET',
		success: function() {
			$('.success').css('display', 'block');
			//$('.generatedailyreport').css('display', 'none');

		},
		error: function(error) {
			alert(error);
		}
	})
});

Date.prototype.addDays = function(days) {
	var date = new Date(this.valueOf());
	date.setDate(date.getDate() + days);
	return date;
}
function addDays(theDate, days) {
	return new Date(theDate.getTime() + days * 24 * 60 * 60 * 1000);
}




$(document).ready(
	function() {


		$.ajax({
			type: "GET",
			url: 'http://localhost:8080/students/getAllStudents',
			dataType: "json",
			success: function(data) {

				var s = '<option value="-1"> Select Student</option>';
				for (var i = 0; i < data.length; i++) {
					s += '<option value="' + data[i].id + '">'
						+ data[i].name + '(' + data[i].id + ') </option>';
				}
				$("#studentname").html(s);
			}
		});
	});




$(document).ready(
	function() {
		$.ajax({
			type: "GET",
			url: 'http://localhost:8080/books/getAllBooks',
			dataType: "json",
			success: function(data) {

				var s = '<option value="-1"> Select Book</option>';
				for (var i = 0; i < data.length; i++) {
					s += '<option value="' + data[i].id + '">'
						+ data[i].bookName + '</option>';
				}
				$("#bookname").html(s);
			}
		});
	});





$(document).ready(
	function() {

		$('#lenddate').val(new Date().toISOString().slice(0, 10));
		var newDate = addDays(new Date(), 30);
		var rdate = newDate.toISOString().slice(0, 10);
		$('#returndate').val(rdate);

		$('#rdate').val(new Date().toISOString().slice(0, 10));
	});





$(document).ready(function() {

	getAllTransaction();
	$('#btnissuebook').click(function() {

		student.id = $('#studentname').val();
		books.id = $('#bookname').val();

		for (var i = 0; i < globalarray.length; i++) {
			console.log("fro loop")

			if ((globalarray[i].students.id == student.id) && (globalarray[i].books.id == books.id) && (globalarray[i].bookStatus == "lended")) {

				$('.error').css('display', 'block');
				return;
			}

		}

		transaction.lendDate = $('#lenddate').val();
		transaction.returnDate = $('#returndate').val();

		transaction.bookStatus = "lended";
		transaction.transactionStatus = "none";
		transaction.students = student;
		transaction.books = books;

		var transactionObj = JSON.stringify(transaction);
		$.ajax({
			url: "transaction/create",
			method: 'Post',
			data: transactionObj,
			contentType: 'application/json; charset=utf-8',
			success: function(data) {
				console.log(" data : " + data);
				alert("saved");

				document.getElementById("issueformId").reset();
			},
			error: function(error) {
				alert(error);
			}
		})
	})
})






function calculatePenalty(s, b) {

	var id = 0;
	student.id = parseInt(s);
	books.id = parseInt(b);

	var pcount = 0;
	var ncount = 0
	for (var i = 0; i < globalarray.length; i++) {
		console.log("fro loop " + globalarray.length);

		if ((globalarray[i].students.id == student.id) && (globalarray[i].books.id == books.id)) {
			console.log("fro loop");

			student.id = globalarray[i].students.id;


			pcount++;

		} else {

			ncount++;
		}

	}
	if (pcount > 0) {
		$.ajax({
			url: "transaction/penalty/" + student.id,
			method: 'Get',
			contentType: 'application/json; charset=utf-8',
			success: function(data) {
				penalty = data;
				console.log("penalty : " + penalty);

				$('.error').css('display', 'none');

				var s = '<output name="result" for="btnpenalty"> Penalty : ' + penalty + '</output>';

				$("#op").html(s);



			},
			error: function(error) {
				alert(error);
			}

		})

	} else {
		$('.error').css('display', 'block');
	}


}



function updateTransaction(transaction) {

	var transactionObj = JSON.stringify(transaction);
	console.log("transactionObj  : " + transactionObj)
	$.ajax({



		url: '/transaction/update',
		method: 'PUT',
		data: transactionObj,
		contentType: 'application/json; charset=utf-8',
		success: function() {

			alert('Saved successfully');

		},
		error: function(error) {
			alert(error);
		}




	})

}


function returnBook(s, b, r) {
	var id = 0;
	var lenddate = '';
	var returndate = '';
	var transactionStatus = '';

	student.id = parseInt(s);
	books.id = parseInt(b);
	returndate = r;
	console.log("returndate " + returndate);
	var pcount = 0;
	var ncount = 0
	for (var i = 0; i < globalarray.length; i++) {
		console.log("fro loop " + globalarray.length);

		if ((globalarray[i].students.id == student.id) && (globalarray[i].books.id == books.id)) {
			console.log("fro loop");

			student.id = globalarray[i].students.id;

			id = globalarray[i].id;

			lenddate = globalarray[i].lendDate;
			bookStatus = globalarray[i].bookStatus;
			transactionStatus = globalarray[i].transactionStatus;

			pcount++;

		} else {

			ncount++;
		}

	}
	if (pcount > 0) {
		$.ajax({
			url: "transaction/penalty/" + student.id,
			method: 'Get',
			contentType: 'application/json; charset=utf-8',
			success: function(data) {
				penalty = data;
				console.log("penalty : " + penalty);


				transaction.id = id;
				transaction.bookStatus = "returned";
				transaction.lendDate = lenddate;
				console.log("student.id  : " + student)

				transaction.penalty = penalty;

				transaction.returnDate = returndate;

				transaction.transactionStatus = transactionStatus;


				transaction.students = student;
				transaction.books = books;
				$('.error').css('display', 'none');

				updateTransaction(transaction);
			},
			error: function(error) {
				alert(error);
			}

		})

	} else {
		$('.error').css('display', 'block');
	}

}




$(document).ready(function() {
	getAllTransaction();
	$('#btnreturnbook').click(function() {
		var s = $('#studentname').val();
		var b = $('#bookname').val();
		var r = $('#rdate').val();

		returnBook(s, b, r);

	})
})



$(document).ready(function() {
	getAllTransaction();
	$('#btnpenalty').click(function() {
		var s = $('#studentname').val();
		var b = $('#bookname').val();

		calculatePenalty(s, b);

	})
})



function getAllTransaction() {
	$.ajax({
		url: "transaction/getAllTransaction",
		method: 'Get',
		contentType: 'application/json; charset=utf-8',
		success: function(data) {
			globalarray = data;
		},
		error: function(error) {
			alert(error);
		}
	})
}




/*async function uploadFile() {
	let formData = new FormData();
	formData.append("file", fileupload.files[0]);
	console.log("formData  :" + fileupload.files[0])
	let response = await fetch('transaction/upload', {
		method: "POST",
		body: formData
	});
	console.log("formData  :" + formData)
	if (response.status == 200) {
		alert("File successfully uploaded.");
	}
}

*/


$(document).on("click", "#btndailyreportdownload", function() {
	var fi = document.getElementById('fileupload'); // GET THE FILE INPUT AS VARIABLE.
	console.log(" fi :" + fi.files.length)
	var totalFileSize = 0;
	var name = '';


	// VALIDATE OR CHECK IF ANY FILE IS SELECTED.
	if (fi.files.length > 0) {
		// RUN A LOOP TO CHECK EACH SELECTED FILE.
		for (var i = 0; i <= fi.files.length - 1; i++) {
			//ACCESS THE SIZE PROPERTY OF THE ITEM OBJECT IN FILES COLLECTION. IN THIS WAY ALSO GET OTHER PROPERTIES LIKE FILENAME AND FILETYPE
			name = fi.files.item(i).name;

		}
		downloadFile(name);
	} else {
		$('.error').css('display', 'block');
	}

});




async function downloadFile(name) {
	let response = await fetch('transaction/download/' + name, {
		method: "GET",

	});
	var url = response.url;
	window.open(url, '_blank');
	console.log("urlll :   " + url);

}







////////////////////*********************   BOOK Page      ***************************************////////////////////////////////////////// */



$(document).ready(function() {

	var book = {};
	var genre = {};
	$('#btnAddBook').click(function() {
		book.bookName = $('#bookname').val();
		book.authorName = $('#authorname').val();
		book.isbn = $('#isbn').val();
		genre.id = $('#genre').val();
		book.genre = genre;
		console.log(book);

		var studentJSON = JSON.stringify(book);
		$.ajax({
			url: 'http://localhost:8080/books/create',
			method: 'POST',
			data: studentJSON,
			contentType: "application/json; charset=utf-8",
			success: function() {
				alert('Saved successfully!');
			},
			error: function(error) {
				alert(error);
			}
		})
		document.getElementById("formId").reset();
	})
});

$(document).ready(
	function() {
		$.ajax({
			type: "GET",
			url: 'http://localhost:8080/genre/getAllGenre',
			dataType: "json",
			success: function(data) {
				var s = '<option value="-1"> Select Genre</option>';
				for (var i = 0; i < data.length; i++) {
					s += '<option value="' + data[i].id + '">'
						+ data[i].genreName + '</option>';
				}
				$("#genre").html(s);
			}
		});
	});


var globalarray = [];
var genreList = {};
$(document).ready(function() {
	$.ajax({
		type: "GET",
		url: 'http://localhost:8080/genre/getAllGenre',
		dataType: "json",
		success: function(data) {
			genreList = data;
			var s = '<option value="-1"> Select Genre</option>';
			for (var i = 0; i < data.length; i++) {
				s += '<option value="' + data[i].id + '">'
					+ data[i].genreName + '</option>';
			}
			$("#genre").html(s);
		}
	});


	var book = {};
	var genre = {};
	var dynamicURL = "";
	var methodName = "";
	getAllBooks();

	$('#btnAddBook').click(function() {
		book.id = $('#txtId').val();
		book.bookName = $('#bookName').val();
		book.authorName = $('#authorName').val();
		book.isbn = $('#isbn').val();
		genre.id = $('#genre').val();
		book.genre = genre;


		console.log("xxxxxxxxxxxxxxx " + $('#genre').val());
		var bookId = book.id;
		if (bookId) {
			//update it
			dynamicURL = "http://localhost:8080/books/update"
			methodName = "PUT";
		} else {
			//save it
			dynamicURL = "http://localhost:8080/books/create";
			methodName = "POST";
		}
		var bookObj = JSON.stringify(book);
		console.log("Value  : " + bookObj);

		$.ajax({
			url: dynamicURL,
			method: methodName,
			data: bookObj,
			contentType: 'application/json; charset=utf-8',
			success: function() {
				alert('Saved successfully');
				getAllBooks();
				reset();
			},
			error: function(error) {
				alert(error);
			}
		})
	})
})

function getAllBooks() {

	$.ajax({
		url: "http://localhost:8080/books/getAllBooks",
		method: "GET",
		dataType: "json",
		success: function(data) {
			globalarray = data;
			var tableBody = $('#tblBook tbody');
			tableBody.empty();
			$(data).each(function(index, element) {
				//console.log("Genre : "+element.genre.genreName);
				tableBody.append('<tr><td>' + element.id + '</td><td>' + element.bookName + '</td><td>' + element.authorName + '</td><td>' + element.isbn + '</td><td>' + element.genre.genreName + '</td><td><button onclick = "update(' + index + ')">Update</button> | <button onclick = "deleteBook(' + element.id + ')">Delete</button></td></tr>');
			})
		},
		error: function(error) {
			alert(error);
		}
	})
}

function deleteBook(id) {
	$.ajax({
		url: 'http://localhost:8080/books/delete/' + id,
		method: 'DELETE',
		success: function() {
			alert('record has been deleted');
			getAllBooks();
		},
		error: function(error) {
			alert(error);
		}
	})
}

function update(index) {

	var data = globalarray[index];

	console.log(genreList);

	$('#txtId').val(data.id);
	$('#bookName').val(data.bookName);
	$('#authorName').val(data.authorName);
	$('#isbn').val(data.isbn);
	// $('#genre').val(data.genre.genreName);

	// var s = '<option value="-1"> Select Genre</option>';



	var s = '';
	s = '<option value="-1"> Select Genre</option>';
	for (var i = 0; i < genreList.length; i++) {
		console.log("data.genre.genreName  :   " + data.genre.genreName);
		console.log("genreList.genreName  : " + genreList[i].genreName);


		if (data.genre.genreName == genreList[i].genreName) {
			console.log("data.genre.genreName2  :   " + data.genre.genreName);

			s += '<option value="' + genreList[i].id + '"  selected>' + genreList[i].genreName + '</option>';
			//var s= '<option value="' + data.genre.genreName + '"  selected>'
		}

		else {
			s += '<option value="' + genreList[i].id + '" >' + genreList[i].genreName + '</option>';
		}
	}
	$("#genre").html(s);

}

function reset() {
	$('#bookName').val('');
	$('#authorName').val('');
	$('#isbn').val('');
	$('#genre').val('');
	$('#txtId').val('');
}



