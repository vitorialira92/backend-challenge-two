function validateCNPJ(cnpj){
    cnpj = cnpj.replace(/[^\d]+/g,'');

    if (cnpj === '')
        return false;

    if (cnpj.length !== 14)
        return false;

    if (allDigitsAreEqual(cnpj))
        return false;

    let sum = 0;

    var multipliers = [5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2];
    for (let i = 0; i < 12; i++) {
        sum += cnpj.charAt(i) * multipliers[i];
    }
    var firstDigit = sum % 11 < 2 ? 0 : 11 - (sum % 11);

    if (firstDigit !== parseInt(cnpj.charAt(12), 10))
        return false;

    sum = 0;
    multipliers = [6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2];

    for (let i = 0; i < 13; i++) {
        sum += parseInt(cnpj.charAt(i), 10) * multipliers[i];
    }
    var secondDigit = sum % 11 < 2 ? 0 : 11 - (sum % 11);
    if (secondDigit !== parseInt(cnpj.charAt(13), 10))
        return false;

    return true;
}

function validateCPF(cpf){
    cpf = cpf.replace(/[^\d]+/g, '');

    if (cpf === '')
        return false;

    if (cpf.length !== 11) return false;

    if (allDigitsAreEqual(cpf))
        return false;

    let sum = 0;
    let firstDigit;

    for (let i = 1; i <= 9; i++)
        sum += parseInt(cpf.substring(i-1, i)) * (11 - i);
    firstDigit = 11 - (sum % 11);

    if (firstDigit === 10)
        firstDigit = 0;
    if (firstDigit !== parseInt(cpf.substring(9, 10)))
        return false;
    sum = 0;
    let secondDigit;

    for (let i = 1; i <= 10; i++)
        sum += parseInt(cpf.substring(i-1, i)) * (12 - i);
    secondDigit = 11 - sum % 11;

    if (secondDigit === 10)
        secondDigit = 0;

    if (secondDigit !== parseInt(cpf.substring(10, 11)))
        return false;
    return true;
}

function allDigitsAreEqual(str){

    const firstChar = str[0];

    for (let i = 1; i < str.length; i++) {
        if (str[i] !== firstChar) {
            return false;
        }
    }

    return true;
}
