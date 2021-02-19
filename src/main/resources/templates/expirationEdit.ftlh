<#import "parts/common.ftlh" as c>
<#import "parts/expirationForm.ftlh" as e>

<@c.page>
    <@e.expiration "expiration/edit/${expiration.id}" "Сохранить"/>
    <a href="/money" class="btn btn-danger">Назад</a>
</@c.page>