<#macro debet action caption>
    <form action="/${action}" method="post">
        <div class="form-group">
            <label for="client-name-input">Имя клиента</label>
            <input type="text" id="client-name-input" class="form-control" name="clientName" required>
        </div>
        <div class="form-group">
            <label for="type-input">Тип депозита</label>
            <input type="text" id="type-input" class="form-control" name="depositType" required>
        </div>
        <div class="form-group">
            <label for="open-date-input">Дата открытия</label>
            <input type="date" id="open-date-input" class="form-control" name="openDate" required>
        </div>
        <div class="form-group">
            <label for="close-date-input">Дата закрытия</label>
            <input type="date" id="close-date-input" class="form-control" name="closeDate" required>
        </div>
        <div class="form-group">
            <label for="summ-input">Сумма</label>
            <input type="number" id="summ-input" class="form-control" name="summ" required>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-success">${caption}</button>
    </form>
</#macro>