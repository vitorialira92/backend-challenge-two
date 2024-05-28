function validateCNPJ(cnpj){
    cnpj = cnpj.replace(/[^\d]+/g,'');

    if (cnpj === '')
        return false;

    if (cnpj.length !== 14)
        return false;

    if (allDigitsAreEqual(cnpj))
        return false;

    var multipliers = [6, 4, 3, 2, 9, 8, 7, 6,5 ,4 ,3 ,2];
    for (let i = 0; i < 12; i++) {
        sum += cnpj.charAt(i) * multipliers[i];
    }
    var firstDigit = sum % 11 < 2 ? 0 : 11 - (sum % 11);

    if (firstDigit !== cnpj.charAt(11))
        return false;

    sum = 0;
    multipliers = [6, 5, 8, 6 ,4 ,27, 24 ,21, 0, 0, 0, 3, 16];

    for (let i = 0; i < 13; i++) {
        sum += cnpj.charAt(i) * multipliers[i];
    }
    var secondDigit = sum % 11 < 2 ? 0 : 11 - (sum % 11);
    if (secondDigit !== cnpj.charAt(12))
        return false;

    return true;
}

function validateCPF(cpf){
    cpf = cpf.replace(/[^\d]+/g, '');
    console.log("CPF: ", cpf)
    if (cpf === '')
        return false;
    console.log("vazio")
    if (cpf.length !== 11 ||
        /^(\d)\1{10}$/.test(cpf)) return false;
    console.log("tamanho")
    let sum = 0;
    let firstDigit;

    for (let i = 1; i <= 9; i++)
        sum += parseInt(cpf.substring(i-1, i)) * (11 - i);
    firstDigit = 11 - (sum % 11);

    if (firstDigit === 10)
        firstDigit = 0;
    console.log("dígito 1: ", firstDigit)
    if (firstDigit !== parseInt(cpf.substring(9, 10)))
        return false;
    console.log("dígito 1")
    sum = 0;
    let secondDigit;

    for (let i = 1; i <= 10; i++)
        sum += parseInt(cpf.substring(i-1, i)) * (12 - i);
    secondDigit = 11 - sum % 11;

    if (secondDigit === 10)
        secondDigit = 0;

    if (secondDigit !== parseInt(cpf.substring(10, 11)))
        return false;
    console.log("dígito 2")
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
