function wish_submit(clicked_id) {
    $.ajax({
        url: '/my_wishlist/post',
        method:"POST",
        data: $('#wish_form'+clicked_id).serialize(),
        success : function () {
            location.replace(location.href);
        }
    })
}

document.getElementById('sort_option').addEventListener("click", function () {
    const sort_option = document.getElementById('sort_option').value;
    if(sort_option==='created_at') {
        document.getElementById('wishList').style.display = 'block';
        document.getElementById('wishListOrderByDate').style.display = 'none';
    }
    else {
        document.getElementById('wishList').style.display = 'none';
        document.getElementById('wishListOrderByDate').style.display = 'block';
    }
});