<#import "parts/common.ftlh" as c>
<#import "parts/creditTypesForm.ftlh" as cr>

<@c.page>
    <@cr.creditType "credit-types/edit/${creditType.id}" "Сохранить"/>
    <a href="/credit-types" class="btn btn-danger">Назад</a>
</@c.page>