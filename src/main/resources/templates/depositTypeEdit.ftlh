<#import "parts/common.ftlh" as c>
<#import "parts/depositTypeForm.ftlh" as d>

<@c.page>
    <@d.depositType "deposit-types/edit/${depositType.id}" "Сохранить"/>
    <a href="/deposit-types" class="btn btn-danger">Назад</a>
</@c.page>