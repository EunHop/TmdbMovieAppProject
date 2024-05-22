function notice_add_function() {
    if(confirm("추가 하시겠습니까?") === true) {
        $.ajax({
            url: '/notice/add/confirm',
            method:"POST",
            data: $('#notice_add').serialize(),
            success : function () {
                location.replace('/notice?pageNo=0');
            }
        })
    } else {
        return false;
    }
}
