$(document).ready(function() {
    var phoneIndex = 0;
    $('#add-phone').click(function() {
        const phoneHtml = `
            <div class="phone-number">
                <input class="phone-input custom-input" type="text" name="phoneNumbers[${phoneIndex}]"/>
                <button type="button" class="remove-phone small-secondary-button">X</button>
            </div>
        `;
        $('#phone-list').append(phoneHtml);
        $('.phone-input').mask("(00) 00000 - 0000");
        phoneIndex++;
    });

    $('#phone-list').on('click', '.remove-phone', function() {
        $(this).parent('.phone-number').remove();
    });
});

