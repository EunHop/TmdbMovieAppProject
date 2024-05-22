function wish() {
    $.ajax({
        url: '/details',
        method: "POST",
        data: $('#wish_form').serialize(),
        success: function () {
            location.replace(location.href);
        }
    })
}

$('#review_btn').click(function () {
    if($('#review_form_text').val().replace(/\s|/gi, "").length === 0) {
        alert("내용을 입력해주세요.");
    } else {
        $.ajax({
            url: '/details',
            method: "POST",
            data: $('#review_form').serialize(),
            success: function () {
                location.replace(location.href);
            }
        })
    }
})

$('#update_btn').click(function () {
    if($('#update_enabled').val().replace(/\s|/gi, "").length === 0) {
        alert("내용을 입력해주세요.");
    } else {
        $.ajax({
            url: '/details',
            method: "POST",
            data: $('#review_update_form').serialize(),
            success: function () {
                location.replace(location.href);
            }
        })
    }
})

$('#delete_btn').click(function () {
    if(confirm("정말 삭제 하시겠습니까?") === true) {
        $.ajax({
            url: '/details/delete',
            method: "POST",
            data: $('#review_update_form').serialize(),
            success: function () {
                location.replace(location.href);
            }
        })
    } else {
        return false;
    }
})

if (document.getElementById('update_review_btn') != null) {
    document.getElementById('update_review_btn').addEventListener("click", function () {
        const update_btn = document.getElementById('update_btn');
        const update_enabled = document.getElementById('update_enabled');
        update_btn.style.display = 'inline';
        update_enabled.disabled = false;
        update_enabled.style.borderStyle = 'solid';
        document.getElementById('update_review_btn').style.display = 'none';
    });
}

if (document.getElementById('review_form_text') != null) {
    document.getElementById('review_form_text').addEventListener('keyup', function (event) {
        if (event.key === 'Enter') {
            $.ajax({
                url: '/details',
                method: "POST",
                data: $('#review_form').serialize(),
                success: function () {
                    location.replace(location.href);
                }
            })
        }
    });
}

if (document.getElementById('update_enabled') != null) {
    document.getElementById('update_enabled').addEventListener('keyup', function (event) {
        if (event.key === 'Enter') {
            $.ajax({
                url: '/details',
                method: "POST",
                data: $('#review_update_form').serialize(),
                success: function () {
                    location.replace(location.href);
                }
            })
        }
    });
}