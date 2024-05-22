const more = document.getElementsByClassName('see-icon');
const content = document.getElementsByClassName('notice_content');
const modify = document.getElementsByClassName('notice_content_modify');
const notice_delete = document.getElementsByClassName('notice_delete');
for(let i=0; i<more.length; i++) {
    more[i].addEventListener("click", function () {
        content[i].classList.toggle('act');
        modify[i].classList.toggle('modify_act');
        notice_delete[i].classList.toggle('modify_act');
    });
}

const confirm_button = document.getElementsByClassName('notice_content_modify_confirm');
const cancel = document.getElementsByClassName('notice_content_modify_cancel');
const wrapper = document.getElementsByClassName('notice_content_text_wrapper');
const text = document.getElementsByClassName('notice_content_text');
for(let j=0; j<modify.length; j++) {
    modify[j].addEventListener("click", function () {
        wrapper[j].innerHTML="<textarea name='content' style=\"width: 100%;height: 6rem;\">"+text[j].innerHTML.replaceAll("<br>", "\r\n")+"</textarea>";
        modify[j].setAttribute("style", "display: none;");
        confirm_button[j].setAttribute("style", "display: block;");
        cancel[j].setAttribute("style", "display: block;");
    });
    cancel[j].addEventListener("click", function () {
        location.replace(location.href);
    });
}
function notice_modify(notice_id) {
    $.ajax({
        url: '/notice/modify',
        method:"POST",
        data: $('#notice_form'+notice_id).serialize(),
        success : function () {
            location.replace(location.href);
        }
    })
}
function notice_delete_function(notice_id) {
    if(confirm("정말 삭제하시겠습니까? 버튼을 누르면 Disabled 탭에서 삭제된 공지사항들 확인 가능합니다.") === true) {
        $.ajax({
            url: '/notice/delete',
            method:"POST",
            data: $('#notice_form'+notice_id).serialize(),
            success : function () {
                location.replace(location.href);
            }
        })
    } else {
        return false;
    }
}

function notice_restore_function(notice_id) {
    if(confirm("정말 복원 하시겠습니까?") === true) {
        $.ajax({
            url: '/notice/restore',
            method:"POST",
            data: $('#notice_form'+notice_id).serialize(),
            success : function () {
                location.replace(location.href);
            }
        })
    } else {
        return false;
    }
}
