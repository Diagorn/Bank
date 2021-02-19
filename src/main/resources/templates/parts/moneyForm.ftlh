<#macro money action caption>
    <form action="/${action}" method="post">
        <div class="form-group">
            <label for="name-input">Название валюты</label>
            <input type="text" id="name-input" class="form-control" name="name" required>
        </div>
        <div class="form-group">
            <label for="sum-input">Цена в рублях за единицу</label>
            <input type="number" id="sum-input" class="form-control" name="sum" required>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-success">${caption}</button>
    </form>
</#macro>