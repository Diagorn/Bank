<#import "parts/common.ftlh" as c>
<#import "parts/depositForm.ftlh" as d>

<@c.page>
    <@d.debet "deposit/edit/${deposit.id}" "Сохранить"/>
    <a href="/deposit" class="btn btn-danger">Назад</a>
</@c.page>