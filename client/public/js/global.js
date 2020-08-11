SERVER_URL = 'http://localhost:8080/';

var laptops = ['Dell G7 7790 (G777161S2NDW-62G)',
    'Dell Inspiron 3582 (3582N54H1IHD_LBK)',
    'Dell Inspiron 5593 (I5593F58S2ND230L-10PS)',
    'Dell XPS 13 (9380) (X3716S3NIW-83S)',
    'Dell Inspiron 3793 (I3758S2DDL-70S)',
    'Dell Alienware m15 (AM15FI78H1H1DW-8S)',
    'ASUS X509MA-EJ070 (90NB0Q32-M02920)',
    'ASUS M509DJ-BQ070 (90NB0P21-M00800)',
    'Apple MacBook Pro A1989',
    'Apple MacBook Air A2179',
    'Apple MacBook Air MVFH2',
    'Apple MacBook Air MQD32'];

$(function () {
    $("#search").autocomplete({
        source: laptops
    });
});




function getLaptops() {
    let url = SERVER_URL + 'parser?name=' + document.getElementById('search').value;
    $.ajax({
        url: url,
        method: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        complete: function (response) {
            $("#laptop-table tr").remove();
            $.each(response.responseJSON, function (key, value) {
                if (value == null) {

                } else {
                    $('#laptop-table').append(
                        `
 <tr>
                                <td>
                                    <a href="${value.url}"><img src="${value.shopImage}" alt="" width="100" height="50"></a>
                                </td>
                                <td >
                                    <h6>${value.name}</h6>
                                    <a href="${value.shopUrl}">${value.shopUrl}</a>
                                </td>
                                <td>
                                    <h3><span class="badge badge-secondary">${value.price} ${value.currency}</span></h3>
                                </td>
                                <td>
                                    <h3><span class="badge badge-success"><a href="${value.url}" style=""><font color="white">Купити</font></a></span></h3>
                                </td>
                            </tr>
                            `
                    )
                }
            });
        }
    });
}
