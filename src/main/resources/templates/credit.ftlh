<#import "parts/common.ftlh" as c>
<#import "parts/creditForm.ftlh" as e>

<@c.page>
        <h2 class="red">${message}</h2>
    <div class="col-md-3">
        <form action="/credit" method="get">
            <input type="text" name="filter" class="form-control">
            <button type="submit" class="btn btn-success">Найти</button>
        </form>
    </div>
    <div class="col-md-2">
        <h2>Добавить кредит</h2>
        <@e.credit "credit" "Добавить"/>
    </div>
    <div class="col-md-4">
        <table class="table table-striped">
            <thead>
            <tr>
                <td>Номер договора</td>
                <td>Тип кредита</td>
                <td>Сумма  кредита</td>
                <td>Дата открытия</td>
                <td>Закрыт ли кредит</td>
                <td>Клиент</td>
                <td></td><td>
            </tr>
            </thead>
            <#list credits as credit>
            <tr>
                <td>
                    <p>${credit.id}</p>
                </td>
                <td>
                    <p>${credit.creditType.name}</p>
                </td>
                <td>
                    <p>${credit.summ}</p>
                </td>
                <td>
                    <p>${serviceUtils.getCalendarAsString(credit.startingDate)}</p>
                </td>
                <td>
                    <p>${credit.isClosed()?then('Закрыт', 'Не закрыт')}</p>
                </td>
                <td>
                    <p>${credit.client.getNameWithInitials()}</p>
                </td>
                <td><a href="/credit/edit/${credit.id}">Изменить...</a></td>
                <td>
                    <form action="/credit/delete/${credit.id}" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <button type="submit" class="btn btn-danger">Удалить</button>
                    </form>
                </td>
            </tr>
            <#else>
                <p>No credits added to DB</p>
            </#list>
        </table>

    </div>
</@c.page>