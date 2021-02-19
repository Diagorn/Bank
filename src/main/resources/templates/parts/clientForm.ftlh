<#macro client action caption>
    <form action="/${action}" method="post">
        <div class="form-group">
            <label for="firstname-number-input">Имя</label>
            <input type="text" id="firstname-number-input" class="form-control" name="firstname" required>
        </div>
        <div class="form-group">
            <label for="patronymic-input">Отчество</label>
            <input type="text" id="patronymic-input" class="form-control" name="patronymic" required>
        </div>
        <div class="form-group">
            <label for="lastname-input">Фамилия</label>
            <input type="text" id="lastname-input" class="form-control" name="lastname" required>
        </div>
        <div class="form-group">
            <label for="adress-input">Адрес</label>
            <input type="text" id="adress-input" class="form-control" name="adress" required>
        </div>
        <div class="form-group">
            <label for="telephone-input">Номер телефона</label>
            <input type="tel" id="telephone-input" class="form-control" name="phoneNumber" required>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-success">${caption}</button>
    </form>
</#macro>