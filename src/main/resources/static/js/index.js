// footer 로고 클릭하면 맨 위로 올리기
$('.logo').click( function () {
    $('html, body').animate( {scrollTop:0}, 400);
});

// 모바일 메뉴 움직이기
$('#menu').click(function () {
    if($('#mobile_slide').hasClass('on')) {
        $('#mobile_slide').removeClass('on');
    }
    else {
        $('#mobile_slide').addClass('on');
    }
})