<#macro creditType action caption>
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
            <label for="percent-input">Процент по кредиту</label>
            <input type="text" id="percent-input" class="form-control" name="percent" required>
        </div>
        <div class="form-group">
            <label for="days-input">Число дней</label>
            <input type="number" id="days-input" class="form-control" name="numberOfDays" required>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-success">${caption}</button>
    </form>
</#macro>