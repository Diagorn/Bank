<#import "parts/common.ftlh" as c>
<#import "parts/expirationForm.ftlh" as e>

<@c.page>
    <#if message??>
        <h2 class="red">${message}</h2>
    </#if>
    <div class="col-md-3">
        <form action="/expiration" method="get">
            <input type="text" name="filter" class="form-control">
            <button type="submit" class="btn btn-success">Найти</button>
        </form>
    </div>
    <div class="col-md-3">
        <h2>Добавить экспирацию</h2>
        <@e.expiration "expiration" "Добавить"/>
    </div>
    <div class="col-md-4">
        <table class="table table-striped">
            <thead>
            <tr>
                <td>Дата экспирации</td>
                <td>Сумма</td>
                <td>Валюта</td>
                <td>Номер кредитного договора</td>
                <td></td><td></td><td></td>
            </tr>
            </thead>
            <#list expirations as expiration>
            <tr>
                <td>
                    <p>${serviceUtils.getCalendarAsString(expiration.dateOfExpiration)}</p>
                </td>
                <td>
                    <p>${expiration.summ}</p>
                </td>
                <td>
                    ${expiration.money.moneyName}
                </td>
                <td>
                    <p>${expiration.credit.getId()}</p>
                </td>
                <td>
                    <a href="/expiration/edit/${expiration.id}">Изменить...</a>
                </td>

                <td>
                    <form action="/expiration/delete/${expiration.id}" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <button type="submit" class="btn btn-danger">Удалить</button>
                    </form>
                </td>
            </tr>
            <#else>
        </table>
        <p>No expirations added to DB</p>
        </#list>
    </div>
</@c.page>