$(document).ready(function() {

    $('#type').change(function() {
        var fieldMask;
        var placeholder;
        var labelText;
        var type = $(this).val();

        if(type === 'INDIVIDUAL'){
            $('#documentNumber').prop('disabled', false);
            $('#documentNumber').prop('required', true);
            fieldMask = '000.000.000-00';
            placeholder = 'ex: 000.000.000-00';
            labelText = 'CPF';
            $('#documentNumber').mask(fieldMask)
        }else if(type === 'LEGAL_ENTITY'){
            $('#documentNumber').prop('disabled', false);
            $('#documentNumber').prop('required', true);
            fieldMask = '00.000.000/0000-00';
            placeholder = 'ex: 00.000.000/0000-00';
            labelText = 'CNPJ';
            $('#documentNumber').mask(fieldMask)
        }else{
            $('#documentNumber').prop('disabled', true);
            $('#documentNumber').prop('required', false);
            $('#documentNumber').unmask
        }
        $('#document-label').text(labelText);
        $('#documentNumber').attr('placeholder', placeholder);
    });
});