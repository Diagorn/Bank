<#macro depositType action caption>
    <form action="/${action}" method="post">
        <div class="form-group">
            <label for="name-input">Название</label>
            <input type="text" id="name-input" class="form-control" name="name" required>
        </div>
        <div class="form-group">
            <label for="conditions-input">Условия</label>
            <input type="text" id="conditions-input" class="form-control" name="conditions" required>
        </div>
        <div class="form-group">
            <label for="percent-input">Процент по депозиту</label>
            <input type="text" id="percent-input" class="form-control" name="percent" required>
        </div>
        <div class="form-group">
            <label for="refill-input">Возможно пополнить счёт</label>
            <input type="checkbox" id="refill-input" class="form-control" name="refill">
        </div>
        <div class="form-group">
            <label for="withdraw-input">Возможно снять деньги без закрытия счёта</label>
            <input type="checkbox" id="withdraw-input" class="form-control" name="withdraw">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-success">${caption}</button>
    </form>
</#macro>