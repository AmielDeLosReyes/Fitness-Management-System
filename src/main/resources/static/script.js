$(document).ready(function () {
    $("#bmiForm").submit(function (e) {
        e.preventDefault();
        var formData = $(this).serialize();
        $.ajax({
            type: "POST",
            url: "/calculateBMI",
            data: formData,
            success: function (response) {
                $("#result").html(response);
            }
        });
    });
});