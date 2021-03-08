

// 업로드 이미지 
function uploadImage(data) {
	var name = getFileName(data);
	var Link = getImageLink(data);
	var thumbnail = getThumbnail(data);
	var downOrWindow = downloadOrNewWindow(data);
	var str =
		`<li class="col-xs-3">
		<div>
			<a ${downOrWindow} href="/displyFile?fileLink=${Link}"><img src="${thumbnail}"></a>
			<p><span data-src="${data}" class="glyphicon glyphicon-trash deleteFile" aria-hidden="true"></span>${name}</p>
		</div>
	</li>`;
	return str;
}

// 업로드 파일 목록
function uploadFileList(bno) {
	$.getJSON("/board/getAttach/" + bno, function(result) {
		$(result).each(function() {
			var data = this;
			var str = uploadImage(data);
			$(".uploadList").append(str);
		});

	});
}

// 업로드 이미지 휴지통 삭제 (read)
function readImage(data) {
	var name = getFileName(data);
	var Link = getImageLink(data);
	var thumbnail = getThumbnail(data);
	var downOrWindow = downloadOrNewWindow(data);
	var str =
		`<li class="col-xs-3">
		<div>
			<a ${downOrWindow} href="/displyFile?fileLink=${Link}"><img src="${thumbnail}"></a>
			<p><span data-src="${data}" class="hidden"></span>${name}</p>
		</div>
	</li>`;
	return str;
}

// 업로드 파일 목록(read)
function readFileList(bno) {
	$.getJSON("/board/getAttach/" + bno, function(result) {
		$(result).each(function() {
			var data = this;
			var str = readImage(data);
			$(".uploadList").append(str);
		});

	});
}

// 파일 드랍 레이블과 영역 숨기기
function hiddenInsert(){
	$(".fileInsert").attr("class", "hidden");
}

// 파일 삭제 함수
function deleteFile(that) {
	var sFileLink = that.attr("data-src");
	$.ajax({
		type: 'post',
		url: '/deleteFile',
		data: {
			sFileLink: sFileLink
		},
		dataType: 'text',
		success: function(result) {
			if (result) {
				that.parent().parent().parent().remove();
			} else {
				alert("삭제 실패");
			}
		}
	});
}
// 다운로드와 새창 열기(IO)
function downloadOrNewWindow(data) {
	if (checkImage(data)) {
		return `target="_blink"`
	} else {
		return ``;

	}
}

// 썸네일
function getThumbnail(data) {
	if (!checkImage(data)) {
		return "/resources/upload/gita.png";
	} else {
		return `/resources/upload${data}`;
	}
}
// 이미지파일 여부
function checkImage(data) {
	var type = data.substring(data.lastIndexOf(".") + 1).toUpperCase();
	if (type == "JPG" || type == "JPEG" || type == "PNG" || type == "GIF") {
		return true;
	} else {
		return false;
	}
}

// 업로드 파일 이름
function getFileName(data) {
	var name = data.substring(data.indexOf("_") + 1);
	if (checkImage(data)) {
		name = name.substring(name.indexOf("_") + 1);
	}
	return name;
}

// 원본파일 경로
function getImageLink(data) {
	if (checkImage(data)) {
		var pre = data.substring(0, 12);
		var suf = data.substring(14);
		return pre + suf;
	} else {
		return data;
	}
}