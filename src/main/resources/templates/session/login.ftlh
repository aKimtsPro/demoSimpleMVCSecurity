<#include "../layout.ftlh">
<@layout>

    <h1>Login form</h1>

    <form action="/process_login" method="post">

        <div>
            <label for="username">username</label>
            <input type="text" name="username" id="username">
        </div>
        <div>
            <label for="password">password</label>
            <input type="password" name="password" id="password">
        </div>

        <button type="submit">se connecter</button>

    </form>

</@layout>
