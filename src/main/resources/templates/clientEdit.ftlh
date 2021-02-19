<#import "parts/common.ftlh" as c>
<#import "parts/clientForm.ftlh" as cl>

<@c.page>
    <@cl.client "client/edit/${client.id}" "Сохранить"/>
    <a href="/client" class="btn btn-danger">Назад</a>
</@c.page>