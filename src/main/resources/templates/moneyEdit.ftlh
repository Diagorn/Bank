<#import "parts/common.ftlh" as c>
<#import "parts/moneyForm.ftlh" as m>

<@c.page>
    <@m.money "money/edit/${money.moneyName}" "Сохранить"/>
    <a href="/money" class="btn btn-danger">Назад</a>
</@c.page>