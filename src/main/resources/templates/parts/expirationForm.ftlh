<#macro expiration action caption>
    <form action="/${action}" method="post">
        <div class="form-group">
            <label for="name-input">Название валюты</label>
            <input type="text" id="name-input" class="form-control" name="moneyName" required>
        </div>
        <div class="form-group">
            <label for="sum-input">Сумма</label>
            <input type="number" id="sum-input" class="form-control" name="sum" required>
        </div>
        <div class="form-group">
            <label for="date-input">Дата экспирации</label>
            <input type="date" id="date-input" class="form-control" name="date" required>
        </div>
        <div class="form-group">
            <label for="credit-input">Номер кредитного договора</label>
            <input type="number" id="credit-input" class="form-control" name="creditNumber" required>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-success">${caption}</button>
    </form>
</#macro>