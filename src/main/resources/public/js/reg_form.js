angular.module("registration_form", [])
    .controller("AppCtrl", function ($scope, $http) {
        $scope.auth = {};
        let resultMessageEl = document.getElementById('resultMessage');
        let exampleInputNameEl = document.getElementById('exampleInputName');
        let exampleInputLastNameEl = document.getElementById('exampleInputLastName');
        let exampleInputNativeNameEl = document.getElementById('exampleInputNativeName');
        let exampleInputNativeLastNameEl = document.getElementById('exampleInputNativeLastName');
        let exampleInputLoginEl = document.getElementById('exampleInputLogin');
        let exampleInputEmailEl = document.getElementById('exampleInputEmail1');
        let exampleInputPasswordEl = document.getElementById('exampleInputPassword1');
        let exampleInputConfirmPasswordEl = document.getElementById('exampleInputConfirmPassword1');

        let inputNameLabel = document.getElementById('inputNameLabel');
        let inputLastNameLabel = document.getElementById('inputLastNameLabel');
        let inputNativeNameLabel = document.getElementById('inputNativeNameLabel');
        let inputNativeLastNameLabel = document.getElementById('inputNativeLastNameLabel');
        let inputLoginLabel = document.getElementById('inputLoginLabel');
        let inputEmailLabel = document.getElementById('inputEmailLabel');
        let inputPasswordLabel = document.getElementById('inputPasswordLabel');
        let inputConfirmPasswordLabel = document.getElementById('inputConfirmPasswordLabel');
        exampleInputNameEl.addEventListener('input', () => {
            inputNameLabel.style.color = 'black';
            inputLastNameLabel.style.color = 'black';
            inputNativeNameLabel.style.color = 'black';
            inputNativeLastNameLabel.style.color = 'black';
            inputLoginLabel.style.color = 'black';
            inputEmailLabel.style.color = 'black';
            inputPasswordLabel.style.color = 'black';
            inputConfirmPasswordLabel.style.color = 'black';
            $scope.message = '';
        });
        $scope.sendForm = function (auth) {
            $http({
                method: "Get",
                url: "/register",
                data: $.param(auth),
                headers: {"Content-Type": "application/x-www-form-urlencoded"}
            }).then(
                (data) => {
                    resultMessageEl.style.color = 'green';
                    $scope.message = 'Успешно зарегистрирован';
                    exampleInputNameEl.value = '';
                    exampleInputLastNameEl.value = '';
                    exampleInputNativeNameEl.value = '';
                    exampleInputNativeLastNameEl.value = '';
                    exampleInputLoginEl.value = '';
                    exampleInputEmailEl.value = '';
                    exampleInputPasswordEl.value = '';
                    exampleInputConfirmPasswordEl.value = '';
                    $scope.message = 'Регистрация успешна';
                },
                (error) => {
                    resultMessageEl.style.color = 'red';
                    inputNameLabel.style.color = 'red';
                    inputLoginLabel.style.color = 'red';
                    exampleInputNameEl.value = '';
                    exampleInputLoginEl.value = '';
                    exampleInputEmailEl.value = '';
                    exampleInputPasswordEl.value = '';
                    exampleInputConfirmPasswordEl.value = '';
                    $scope.message = 'При регистрации произошла ошибка';
                }
            );
        }
    });