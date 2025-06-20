<#import "../template.ftl" as template>
<@template.page>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h2><@t key="auth_login_title" /></h2>
            <#if error??>
                <div id="alert-error" class="alert alert-danger" role="alert"><@t key=error /></div>
            </#if>
            <form method="post">
                <div class="mb-3">
                    <label for="email" class="form-label"><@t key="auth_field_email" /></label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label"><@t key="auth_field_password" /></label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <button type="submit" class="btn btn-primary"><@t key="auth_field_login" /></button>
            </form>
        </div>
    </div>
</@template.page>
