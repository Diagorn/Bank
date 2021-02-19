<#import "parts/common.ftlh" as c>
<#import "parts/creditTypesForm.ftlh" as ct>

<@c.page>
    <#if message??>
        <h2 class="red">${message}</h2>
    </#if>
    <div class="col-md-3">
        <form action="/credit-types" method="get">
            <input type="text" name="filter" class="form-control">
            <button type="submit" class="btn btn-success">Найти</button>
        </form>
    </div>
    <div class="col-md-4">
        <h2>Добавить тип кредита</h2>
        <@ct.creditType "credit-types" "Добавить"/>
    </div>
    <div class="col-md-4">
        <table class="table table-striped">
            <thead>
            <tr>
                <td>Название</td>
                <td>Условия</td>
                <td>Процент по кредиту</td>
                <td>Число дней</td>
                <td></td><td></td>
            </tr>
            </thead>
            <#list creditTypes as type>
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
                    <p>${type.numberOfDays} дней</p>
                </td>
                <td>
                    <a href="/credit-types/edit/${type.id}">Изменить...</a>
                </td>
                <td>
                    <form action="/credit-types/delete/${type.id}" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <button type="submit" class="btn btn-danger">Удалить</button>
                    </form>
                </td>
            </tr>
            <#else>
        </table>
        <p>No credit types added to DB</p>
        </#list>
    </div>
</@c.page>