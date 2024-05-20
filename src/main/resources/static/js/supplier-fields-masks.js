$(document).ready(function() {
    $('#zipCode').mask('00.000-000', {reverse: true});

    setDocumentMask($('#type').val());

    $('#type').change(function() {
        setDocumentMask($(this).val());
    });

    function setDocumentMask(type) {
        var mask;
        var placeholder;
        var labelText;
        if(type === 'INDIVIDUAL'){
            mask = '000.000.000-00';
            placeholder = 'ex: 000.000.000-00';
            labelText = 'CPF';
        }else{
            mask = '00.000.000/0000-00';
            placeholder = 'ex: 00.000.000/0000-00';
            labelText = 'CNPJ';
        }
        $('#document-label').text(labelText);
        $('#documentNumber').mask(mask, {reverse: true}).attr('placeholder', placeholder);
    }
});