<#import "parts/common.ftlh" as c>
<#import "parts/depositTypeForm.ftlh" as d>

<@c.page>
    <#if message??>
        <h2 class="red">${message}</h2>
    </#if>
    <div class="col-md-3">
        <form action="/deposit-types" method="get">
            <input type="text" name="filter" class="form-control">
            <button type="submit" class="btn btn-success">Найти</button>
        </form>
    </div>
    <div class="col-md-3">
        <h2>Добавить тип вклада</h2>
        <@d.depositType "deposit-types" "Добавить"/>
    </div>
    <div class="col-md-4">
        <table class="table table-striped">
            <thead>
            <tr>
                <td>Название</td>
                <td>Условия</td>
                <td>Процент по депозиту</td>
                <td>Можно ли доложить сумму</td>
                <td>Можно ли снимать деньги</td>
                <td></td><td></td>
            </tr>
            </thead>
            <#list depositTypes as type>
            <tr>
                <td>
                    <p>${type.name}</p>
                </td>
                <td>
                    <p>${type.conditions}</p>
                </td>
                <td>
                    <p>${type.percent}%</p>
                </td>
                <td>
                    <p>${type.canRefill?then('Да', 'Нет')}</p>
                </td>
                <td>
                    <p>${type.canWithdrawal?then('Да', 'Нет')}</p>
                </td>
                <td>
                    <a href="/deposit-types/edit/${type.id}">Изменить...</a>
                </td>
                <td>
                    <form action="/deposit-types/delete/${type.id}" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <button type="submit" class="btn btn-danger">Удалить</button>
                    </form>
                </td>
            </tr>
            <#else>
        </table>
        <p>No deposit types added to DB</p>
        </#list>
    </div>
</@c.page>