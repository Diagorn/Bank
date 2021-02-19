<#macro credit action caption>
    <form action="/${action}" method="post">
        <div class="form-group">
            <label for="type-input">Тип кредита</label>
            <input type="text" id="type-input" class="form-control" name="creditType" required>
        </div>
        <div class="form-group">
            <label for="summ-input">Сумма кредита</label>
            <input type="number" id="summ-input" class="form-control" name="summ" required>
        </div>
        <div class="form-group">
            <label for="date-input">Дата открытия</label>
            <input type="date" id="date-input" class="form-control" name="date" required>
        </div>
        <div class="form-group">
            <label for="credit-input">Клиент</label>
            <input type="text" id="credit-input" class="form-control" name="client" required>
        </div>
        <div class="form-group">
            <label for="closed-input">Закрыт ли кредит</label>
            <input type="checkbox" id="closed-input" class="form-control" name="closed">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-success">${caption}</button>
    </form>
</#macro>