<#macro page>
    <!DOCTYPE html>
    <html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>PKG</title>

        <!-- Zephyr Bootstrap Theme -->
        <link rel="stylesheet" href="/css/bootstrap.min.css">

        <style>
            body {
                padding-top: 4rem;
            }
        </style>
    </head>
    <body>

    <nav class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top">
        <div class="container">
            <a class="navbar-brand" href="/">PKG</a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item"><a class="nav-link" href="/packages">Packages</a></li>
                </ul>
                <#if user??>
                    <span class="navbar-text">
                        Hello ${user.email} | <a href="/auth/logout" class="text-light">Logout</a>
                    </span>
                <#else>
                    <a href="/auth/login" class="btn btn-secondary my-2 my-sm-0"><@t key="auth_field_login" /></a>
                </#if>
            </div>
        </div>
    </nav>

    <main class="container mt-4">
        <#nested>
    </main>

    <footer class="footer mt-auto py-3 bg-light">
        <div class="container text-center">
            <span class="text-muted">© 2025 Guimauve Digital</span>
        </div>
    </footer>
    </body>
    </html>
</#macro>
