<#import "parts/common.ftlh" as c>
<#import "parts/depositForm.ftlh" as d>

<@c.page>
    <#if message??>
        <h2 class="red">${message}</h2>
    </#if>
    <div class="col-md-3">
        <form action="/deposit" method="get">
            <input type="text" name="filter" class="form-control">
            <button type="submit" class="btn btn-success">Найти</button>
        </form>
    </div>
    <div class="col-md-3">
        <h2>Добавить вклад</h2>
        <@d.debet "deposit" "Добавить"/>
    </div>
    <div class="col-md-4">
        <table class="table table-striped">
            <thead>
            <tr>
                <td>Имя клиента</td>
                <td>Дата открытия</td>
                <td>Дата закрытия</td>
                <td>Тип вклада</td>
                <td>Сумма</td>
                <td></td><td></td>
            </tr>
            </thead>
            <#list deposits as deposit>
            <tr>
                <td>
                    <p>${deposit.client.getNameWithInitials()}</p>
                </td>
                <td>
                    <p>${serviceUtils.getCalendarAsString(deposit.getOpenDate())}</p>
                </td>
                <td>
                    <#if deposit.getCloseDate()??>
                        <p>${serviceUtils.getCalendarAsString(deposit.getCloseDate())}</p>
                    </#if>
                </td>
                <td>
                    <p>${deposit.getType().name}</p>
                </td>
                <td>
                    <p>${deposit.getSum()}</p>
                </td>
                <td><a href="/deposit/edit/${deposit.id}">Изменить...</a></td>
                <td>
                    <form action="/deposit/delete/${deposit.id}" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <button type="submit" class="btn btn-danger">Удалить</button>
                    </form>
                </td>
            </tr>
        </table>
        <#else>
            <p>No deposits added to DB</p>
        </#list>
    </div>
</@c.page>