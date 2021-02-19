<#import "parts/common.ftlh" as c>
<#import "parts/creditForm.ftlh" as cr>

<@c.page>
    <@cr.credit "credit/edit/${credit.id}" "Сохранить"/>
    <a href="/credit" class="btn btn-danger">Назад</a>
</@c.page>