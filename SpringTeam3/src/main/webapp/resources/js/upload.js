function makeHtmlCode_read(data){
   var img = checkImgType(data)?"/resources/upload/"+data : "/resources/esc.png";
   
   var originalName = getOriginalName(data);
   var imgLink = getImageLink(data);
   
   if(checkImgType(data)){
      var str = `<li class="col-xs-3"><div><a target="_blank" href="/displayFile?filename=${imgLink}"><img src="${img}" style="width:350px; height:350px;"></a><p class="oriName"></p></div></li>`;
      return str;
   }else{
      var str = `<li class="col-xs-3"><div><a href="/displayFile?filename=${imgLink}"><img src="${img}" style="width:350px; height:350px;"></a><p class="oriName"></p></div></li>`;
      return str;
   }
}


function makeHtmlCode(data){
   var img = checkImgType(data)?"/resources/upload/"+data : "/resources/esc.png";
   
   var originalName = getOriginalName(data);
   var imgLink = getImageLink(data);
   
   if(checkImgType(data)){
      var str = `<li class="col-xs-3"><div><a target="_blank" href="/displayFile?filename=${imgLink}"><img src="${img}"></a><p class="oriName"><span data-src='${data}' class="glyphicon glyphicon-trash delbtn" aria-hidden="true"></span> ${originalName}</p></div></li>`;
      return str;
   }else{
      var str = `<li class="col-xs-3"><div><a href="/displayFile?filename=${imgLink}"><img src="${img}"></a><p class="oriName"><span data-src='${data}' class="glyphicon glyphicon-trash delbtn" aria-hidden="true"></span> ${originalName}</p></div></li>`;
      return str;
   }
   
   
}




function getImageLink(data) {
   if (checkImgType(data)) {
      var pre = data.substring(0, 12);
      var suf = data.substring(14);
      
      return pre + suf;
   } else {
      return data;
   }
}

function getOriginalName(data) {
   /* data로부터 원본파일명을 추출하여 반환하는 기능 */
   if (checkImgType(data)) {
      var idx = data.indexOf("_");/*첫 번째 _의 인덱스  */
      idx = data.indexOf("_", idx + 1) + 1;/*첫 번째 _의 인덱스 다음부터 검색했을 때 나오는 _의 인덱스에 _ 다음에 있는 글자의 인덱스  */
      return data.substring(idx);
   } else {
      var idx = data.indexOf("_") + 1;
      return data.substring(idx);
   }
}

function checkImgType(data) {
   var idx = data.lastIndexOf(".") + 1
   var type = data.substring(idx).toUpperCase();
   if (type == "PNG" || type == "JPG" || type == "JPEG" || type == "GIF") {
      return true;
   } else {
      return false;
   }
}
