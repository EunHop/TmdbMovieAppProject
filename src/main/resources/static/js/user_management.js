function user_review_delete_function(review_id) {
    if(confirm("정말 삭제 하시겠습니까?") === true) {
        $.ajax({
            url: '/user/management/delete',
            method:"POST",
            data: {
                id : review_id
            },
            success : function () {
                location.replace(location.href);
            }
        })
    } else {
        return false;
    }
}

function user_authority_change_function(user_id) {
    if(confirm("정말 권한을 변경 하시겠습니까?") === true) {
        $.ajax({
            url: '/user/management/change',
            method:"POST",
            data: {
                id : user_id
            },
            success : function () {
                location.replace(location.href);
            }
        })
    } else {
        return false;
    }
}

function user_reviews_delete_all_function(user_id) {
    if(confirm("정말 모두 삭제 하시겠습니까?") === true) {
        $.ajax({
            url: '/user/management/review/delete/all',
            method:"POST",
            data: {
                id : user_id
            },
            success : function () {
                location.replace('/user/management?pageNo=0');
            }
        })
    } else {
        return false;
    }
}