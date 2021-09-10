const main = {
    init: function () {
        let _this = this;
        // 클릭 시 회원가입
        $('#btn-save').on('click', function () {
            _this.save();
        });

        // 클릭 시 로그인
        $('#btn-login').on('click', function () {
            _this.login();
        });

    },
    save: function () {
        let data = {
            account: $('#account').val(),
            password: $('#password').val(),
            name: $('#name').val(),
            email: $('#email').val()
        };

        $.ajax({
            type: 'POST',
            url: '/tom_api/v1/member/register',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('가입이 완료되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    login: function () {
        let data = {
            account: $('#account').val(),
            password: $('#password').val()
        };

        $.ajax({
            type: 'POST',
            url: '/tom_api/v1/member/login',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (e) {
            alert('로그인 되었습니다. 안녕하세요. '+ e.name + "님!");
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
};

main.init();