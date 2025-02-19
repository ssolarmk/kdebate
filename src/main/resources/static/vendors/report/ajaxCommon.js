function fn_egov_link_page(pageNo){
	document.bbsViewFrm.pageIndex.value = pageNo;
   	document.bbsViewFrm.submit();
}

/*
 * 기본 아작스 호출 함수
 * @param target : 호출 URL
 * @param form : 넘기는 값(기본 쿼리스트링)
 * @param callback : 실행후 호출 함수
 */
function callAjax(target, form, callback) {
	jQuery.ajax({
		type : "POST",
		url : target,
		data : form,
		dataType : "json",
		cache: false,
		success : function(data) {
			if(data.resultCode == "F001") location.replace("/loginPage.ke");

			if(data.resultCode == "S000"){
				callback(data);
			} else {
				swal("실패", data.resultMsg , "error");
			}
		},
		error : function(e) {
			var str = e.responseJSON.path;
			if(str.indexOf('authFalse')) {
				swal("실패", "권한이 없습니다.", "error");
			} else {
				swal("실패", "작업수행에 실패하였습니다.", "error");
			}
		}, timeout:100000
	});
}
/*
 * 코드 아작스 호출 함수
 * @param target : 호출 URL
 * @param form : 넘기는 값(기본 쿼리스트링)
 */
function callAjaxCode(target, form) {
	jQuery.ajax({
		type : "POST",
		url : target,
		data : form,
		cache: false,
		dataType : "json",
		success : function(data) {
			if(data.resultCode == "F001") location.replace("/loginPage.ke");
			if(data.resultCode == "S000"){
				return data;
			} else {
				swal("실패", data.resultMsg , "error");
			}
		},
		error : function(e) {
			swal("실패", "작업수행에 실패하였습니다.", "error");
		}, timeout:100000
	});
}
/*
 * 콤보박스 아작스 호출 함수
 * @param target : 호출 URL
 * @param data : 넘기는 값(기본 쿼리스트링)
 * @param combo : 대상 select, 멀티 호출 시 예)#aa,#bb 단건일 경우 기존과 동일  val과 갯수 맞출것
 * @param type : 1 - 선택, 2 - 전체, 3 - 무조건 선택
 * @param val : 넘기는 값(기본 쿼리스트링), 멀티 호출 시 예)#aa,#bb 단건일 경우 기존과 동일 combo와 갯수 맞출것
 * @multiYn 멀티셀렉트 여부
 */
function callAjaxCombo(target, data, combo, type, val, multiYn){
	jQuery.ajax({
		type : "POST",
		url : target,
		data : data,
		cache: false,
		dataType : "json",
		success : function(data) {
			if(data.resultCode == "F001") location.replace("/loginPage.ke");
			data = data.data;
			var cnt = data.length;
			var comboArray = combo.split(',');
			for ( var k in comboArray) {
				if(cnt > 0){
					if(type == "1"){
						$(comboArray[k]).children().remove();
						$(comboArray[k]).append("<option value=\"\">선 택</option>");
					} else if(type == "2"){
						$(comboArray[k]).children().remove();
						$(comboArray[k]).append("<option value=\"\">전 체</option>");
					}
					for (var i=0; i < cnt; i++){
						var valArray = val.split(',');
						for ( var l in valArray) {
							if(valArray[l] == data[i].code){
								var appendText = "<option value='"+data[i].code+"' selected=\"selected\">"+data[i].codeNm+"</option>";
							} else {
								var appendText = "<option value='"+data[i].code+"'>"+data[i].codeNm+"</option>";
							}
							$(comboArray[k]).append(appendText);
						}
					}

					if(multiYn == 'Y'){
						$(comboArray[k]).attr('multiple', 'multiple');
						if(type == "1"){
							$(comboArray[k]).attr('data-placeholder', '선 택');
						} else if(type == "2"){
							$(comboArray[k]).attr('data-placeholder', '전 체');
						}
						$(comboArray[k]).chosen({});
						$(".search-choice-close").click();
					}

				} else {
					$(comboArray[k]).children().remove();
					$(comboArray[k]).append("<option value=\"\">데이터가 없습니다</option>");
				}
			}
		},
		error : function(e) {
			swal("실패", "작업수행에 실패하였습니다.", "error");
		}
	});
}

/*
 * 라디오, 체크박스 아작스 호출 함수
 * @param target : 호출 URL
 * @param data : 넘기는 값(기본 쿼리스트링)
 * @param combo : 대상 DIV(감싸는 DIV), 멀티 호출 시 예)#aa,#bb 단건일 경우 기존과 동일  val과 갯수 맞출것
 * @param type : radio, check
 * @param val : 넘기는 값(기본 쿼리스트링), 멀티 호출 시 예)#aa,#bb 단건일 경우 기존과 동일 combo와 갯수 맞출것
 * @multiYn 멀티셀렉트 여부
 */
function callAjaxCheckCode(target, data, combo, type){
	jQuery.ajax({
		type : "POST",
		url : target,
		data : data,
		cache: false,
		dataType : "json",
		success : function(data) {
			if(data.resultCode == "F001") location.replace("/loginPage.ke");
			var cnt = data.length;
			var comboArray = combo.split(',');
			for ( var k in comboArray) {
				if(cnt > 0){
					if(type=="radio"){
						for (var i=0; i < cnt; i++){
							var appendText = "<label class='checkbox-inline i-checks'><input type='radio' value='"+data[i].code+"' name='"+$(comboArray[k]).attr("id")+"' id='"+$(comboArray[k]).attr("id")+"'> "+data[i].codeNm+"</label>";
							$(comboArray[k]).append(appendText);
						}
					} else if(type=="check"){
						for (var i=0; i < cnt; i++){
							var appendText = "<label class='checkbox-inline i-checks'><input type='checkbox' value='"+data[i].code+"' name='"+$(comboArray[k]).attr("id")+"' id='"+$(comboArray[k]).attr("id")+"'> "+data[i].codeNm+"</label>";
							$(comboArray[k]).append(appendText);
						}
					}
					$('.i-checks').iCheck({
					    checkboxClass: 'icheckbox_square-green',
					    radioClass: 'iradio_square-green',
					});

				} else {
					$(comboArray[k]).append("데이터가 없습니다");
				}
			}
		},
		error : function(e) {
			swal("실패", "작업수행에 실패하였습니다.", "error");
		}
	});
}


/*
 * 코드 콤보 아작스 호출 함수
 * @param target : 호출 URL
 * @param data : 넘기는 값(기본 쿼리스트링)
 * @param combo : 대상 select, 멀티 호출 시 예)#aa,#bb 단건일 경우 기존과 동일  val과 갯수 맞출것
 * @param type : 1 - 선택, 2 - 전체, 3 - 무조건 선택
 * @param val : 넘기는 값(기본 쿼리스트링) - 없으면 값을 넘기지 않아도 됨, 멀티 호출 시 예)#aa,#bb 단건일 경우 기존과 동일 combo와 갯수 맞출것
 * @param condition : 이외 조건값처리(기본 쿼리스트링) - 없으면 값을 넘기지 않아도 됨 (단, val없이 condition이 존재할 수는 없음)
 */
function callAjaxComboCode(data, combo, type, val, condition){
	var param = "";
	if (condition != null && condition != '') {
		param = "codeId="+data+"&"+condition;
	} else {
		param = "codeId="+data;
	}
	jQuery.ajax({
		type : "POST",
		url : "/comm/retrieveCodeList.do",
		data : param,
		dataType : "json",
		cache: false,
		success : function(data) {
			var cnt = data.length;
			var comboArray = combo.split(',');
			for ( var k in comboArray) {
				if(cnt > 0){
					if(type == "1"){
						$(comboArray[k]).children().remove();
						$(comboArray[k]).append("<option value=\"\">선 택</option>");
					} else if(type == "2"){
						$(comboArray[k]).children().remove();
						$(comboArray[k]).append("<option value=\"\">전 체</option>");
					}
					for (var i=0; i < cnt; i++){
						var valArray = val.split(',');
						for ( var l in valArray) {
							if(valArray[l] == data[i].code){
								var appendText = "<option value='"+data[i].code+"' selected=\"selected\">"+data[i].codeNm+"</option>";
							} else {
								var appendText = "<option value='"+data[i].code+"'>"+data[i].codeNm+"</option>";
							}
							$(comboArray[k]).append(appendText);
						}
					}
				} else {
					$(comboArray[k]).children().remove();
					$(comboArray[k]).append("<option value=\"\">데이터가 없습니다</option>");
				}
			}
		},
		error : function(e) {
			swal("실패", "작업수행에 실패하였습니다.", "error");
		}
	});
}

function ConfirmdialogToAjax(text, target, form, callback) {
	if (text == "create") {
		text = "등록하시겠습니까?";
	} else if (text == "update") {
		text = "수정하시겠습니까?";
	} else if (text == "delete") {
		text = "삭제하시겠습니까?";
	} else if (text == "studentdelete") {
		text = "원생정보를 삭제하면 복구 할 수 없습니다. 그래도 삭제하시겠습니까?";
	}
	swal({
		title: text,
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "예",
        cancelButtonText: "아니요",
        closeOnConfirm: true
    }, function (isConfirm) {
        if (!isConfirm) return;
        jQuery.ajax({
    		type : "POST",
    		url : target,
    		data : form,
    		cache: false,
    		dataType : "json",
    		success : function(data) {
    			if(data.resultCode == "F001") location.replace("/loginPage.ke");
    			if(data.resultCode == "S000"){
    				callback(data);
    			} else {
    				swal("실패", data.resultMsg , "error");
    			}
    		},
    		error : function(e) {
    			swal("실패", "작업수행에 실패하였습니다.", "error");
    		}, timeout:100000
    	});
        window.onkeydown = null;
        window.onfocus = null;
    });
}
function bookClubTrans(myData) {
	if(myData.length > 0){
		$.each(myData, function(index, item){
			item.aca_code = "oter";
			item.auth_key = "1b3eae31a6cdbfa37c2c4903d42825b0";
			$.ajax({
	    		type : "POST",
	    		url : "http://bookclubs.co.kr/certify_member_up_oter_test.asp",
	    		contentType: 'application/x-www-form-urlencoded; charset=euc-kr',
	    		data : $.param(item),
	    		cache: false,
	    		dataType : "json",
	    		timeout:100000
	    	});
		});
	}
}


//json ajax
function JsonToAjax(text, target, form, callback) {
	if(!!text){
		if (text == "CREATE") {
			text = "등록하시겠습니까?";
		} else if (text == "UPDATE") {
			text = "수정하시겠습니까?";
		} else if (text == "DELETE") {
			text = "삭제하시겠습니까?";
		}
		swal({
			title: text,
	        type: "warning",
	        showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        confirmButtonText: "예",
	        cancelButtonText: "아니요",
	        closeOnConfirm: true
	    }, function (isConfirm) {
	        if (!isConfirm) return;
	        jQuery.ajax({
	    		type : "POST",
	    		url : target,
	    		data : form,
	    		traditional: true,
	    		cache: false,
	    		dataType : "json",
	    		contentType: "application/json",
	    		success : function(data) {
	    			if(data.resultCode == "F001") location.replace("/loginPage.ke");
	    			if(data.resultCode == "S000"){
	    				callback(data);
	    			}
	    		},
	    		error : function(e) {
	    			swal("실패", "작업수행에 실패하였습니다.", "error");
	    		}, timeout:100000
	    	});
	        window.onkeydown = null;
	        window.onfocus = null;
	    });
	}else{
	        jQuery.ajax({
	    		type : "POST",
	    		url : target,
	    		data : form,
	    		traditional: true,
	    		cache: false,
	    		dataType : "json",
	    		contentType: "application/json",
	    		success : function(data) {
	    			if(data.resultCode == "F001") location.replace("/loginPage.ke");
	    			if(data.resultCode == "S000"){
	    				callback(data);
	    			}
	    		},
	    		error : function(e) {
	    			swal("실패", "작업수행에 실패하였습니다.", "error");
	    		}, timeout:100000
	    	});
	        window.onkeydown = null;
	        window.onfocus = null;


	}






}

