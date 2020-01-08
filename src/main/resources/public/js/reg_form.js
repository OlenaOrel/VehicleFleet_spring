angular.module("registration_form", [])
    .controller("AppCtrl", function ($scope, $http) {
        $scope.auth = {};
        let resultMessageEl = document.getElementById('resultMessage');
        let exampleInputNameEl = document.getElementById('exampleInputName');
        let exampleInputLastNameEl = document.getElementById('exampleInputLastName');
        let exampleInputOriginNameEl = document.getElementById('exampleInputOriginName');
        let exampleInputOriginLastNameEl = document.getElementById('exampleInputOriginLastName');
        let exampleInputLoginEl = document.getElementById('exampleInputLogin');
        let exampleInputEmailEl = document.getElementById('exampleInputEmail1');
        let exampleInputPasswordEl = document.getElementById('exampleInputPassword1');
        let exampleInputConfirmPasswordEl = document.getElementById('exampleInputConfirmPassword1');

        let exampleInputNameLabel = document.getElementById('exampleInputNameLabel');
        let exampleInputLastNameLabel = document.getElementById('exampleInputLastNameLabel');
        let exampleInputOriginNameLabel = document.getElementById('exampleInputOriginNameLabel');
        let exampleInputOriginLastNameLabel = document.getElementById('exampleInputOriginLastNameLabel');
        let exampleInputLoginLabel = document.getElementById('exampleInputLoginLabel');
        let exampleInputEmailLabel = document.getElementById('exampleInputEmailLabel');
        let exampleInputPasswordLabel = document.getElementById('exampleInputPasswordLabel');
        let exampleInputConfirmPasswordLabel = document.getElementById('exampleInputConfirmPasswordLabel');
        exampleInputNameEl.addEventListener('input', () => {
            exampleInputNameLabel.style.color = 'black';
            exampleInputLastNameLabel.style.color = 'black';
            exampleInputOriginNameLabel.style.color = 'black';
            exampleInputOriginLastNameLabel.style.color = 'black';
            exampleInputLoginLabel.style.color = 'black';
            exampleInputEmailLabel.style.color = 'black';
            exampleInputPasswordLabel.style.color = 'black';
            exampleInputConfirmPasswordLabel.style.color = 'black';
            $scope.message = '';
        });
        $scope.sendForm = function (auth) {
            $http({
                method: "POST",
                url: "/register",
                data: $.param(auth),
                headers: {"Content-Type": "application/x-www-form-urlencoded"}
            }).then(
                (data) => {
                    // resultMessageEl.style.color = 'green';
                    exampleInputNameEl.value = '';
                    exampleInputLastNameEl.value = '';
                    exampleInputOriginNameEl.value = '';
                    exampleInputOriginLastNameEl.value = '';
                    exampleInputLoginEl.value = '';
                    exampleInputEmailEl.value = '';
                    exampleInputPasswordEl.value = '';
                    exampleInputConfirmPasswordEl.value = '';
                },
                (error) => {
                    // resultMessageEl.style.color = 'red';
                    exampleInputNameEl.value = '';
                    exampleInputLastNameEl.value = '';
                    exampleInputOriginNameEl.value = '';
                    exampleInputOriginLastNameEl.value = '';
                    exampleInputLoginEl.value = '';
                    exampleInputEmailEl.value = '';
                    exampleInputPasswordEl.value = '';
                    exampleInputConfirmPasswordEl.value = '';
                }
            );
        }
    });