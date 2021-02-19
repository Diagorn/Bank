<#import "parts/common.ftlh" as c>
<#import "parts/clientForm.ftlh" as cl>

<@c.page>
        <h2 class="red">${middle}</h2>
    <div class="col-md-2">
        <form action="/client" method="get">
            <input type="text" class="form-control" name="filter">
            <button type="submit" class="btn btn-success">Найти</button>
        </form>
    </div>
    <div class="col-md-3">
        <h2>Добавить клиента</h2>
        <@cl.client "client" "Добавить"/>
    </div>
    <div class="col-md-4">
        <table class="table table-striped">
            <thead>
            <tr>
                <td>ID</td>
                <td>Имя</td>
                <td>Отчество</td>
                <td>Фамилия</td>
                <td>Адрес</td>
                <td>Телефон</td>
                <td></td><td></td>
            </tr>
            </thead>
            <#list big as client>
            <tr>
                <td>
                    <p>${client.getId()}</p>
                </td>
                <td>
                    <p>${client.firstName}</p>
                </td>
                <td>
                    <p>${client.patronymic}</p>
                </td>
                <td>
                    <p><b>!</b>${client.lastName}</p>
                </td>
                <td>
                    <p>${client.adress}</p>
                </td>
                <td>
                    <p>${client.phoneNumber}</p>
                </td>
                <td>
                    <a href="/client/edit/${client.id}">Изменить...</a>
                </td>
                <td>
                    <form action="/client/delete/${client.id}" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <button type="submit" class="btn btn-danger">Удалить</button>
                    </form>
                </td>
            </tr>
            </#list>
            <#list low as client>
            <tr>
                <td>
                    <p>${client.getId()}</p>
                </td>
                <td>
                    <p class="red">${client.firstName}</p>
                </td>
                <td>
                    <p>${client.patronymic}</p>
                </td>
                <td>
                    <p>${client.lastName}</p>
                </td>
                <td>
                    <p>${client.adress}</p>
                </td>
                <td>
                    <p>${client.phoneNumber}</p>
                </td>
                <td>
                    <a href="/client/edit/${client.id}">Изменить...</a>
                </td>
                <td>
                    <form action="/client/delete/${client.id}" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <button type="submit" class="btn btn-danger">Удалить</button>
                    </form>
                </td>
            </tr>
            <#else>
        </table>
        <p>No clients added to DB</p>
        </#list>
    </div>
</@c.page>