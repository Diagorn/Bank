<nav class="navbar navbar-dark">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Управление банком</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/credit">Кредиты</a></li>
            <li><a href="/credit-types">Типы кредитов</a></li>
            <li><a href="/deposit">Вклады</a></li>
            <li><a href="/deposit-types">Типы вкладов</a></li>
            <li><a href="/expiration">Экспирации</a></li>
            <li><a href="/money">Валюты</a></li>
            <li><a href="/client">Клиенты</a></li>
            <li class="float-right">
                <form action="/logout" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button type="submit" class="btn btn-default">Выйти</button>
                </form>
            </li>
        </ul>
    </div>
</nav>