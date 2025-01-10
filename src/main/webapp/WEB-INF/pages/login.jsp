<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:pageTemplate pageTitle="Login">
    <c:if test="${message != nul}">
       <div class="alert alert-warning" role="alert">
              ${message}
    </c:if>
   <form class="form-signin" action="j_security_check" method="POST">
        <h2 class="form-signin-heading">Sign in</h2>
        <label for="username" class="sr-only">Username</label>
        <input type="text" id="username" name="j_username" class="form-control" placeholder="Username" required autofocus>
        <label for="password" class="sr-only">Password</label>
        <input type="password" id="password" name="j_password" class="form-control" placeholder="Password" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>
</t:pageTemplate>
<script>
    const form = document.querySelector('#loginForm');
    form.addEventListener('submit', async (e) => {
        e.preventDefault();
        const username = document.querySelector('#username').value;
        const password = document.querySelector('#password').value;

        console.log('Submitting:', { username, password });

        const response = await fetch(form.action, {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: new URLSearchParams({ username, password }),
        });

        if (response.ok) {
            window.location.href = '/dashboard';
        } else {
            alert('Login failed');
        }
    });

</script>