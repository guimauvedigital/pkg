<#import "../template.ftl" as template>
<@template.page>
    <div class="mb-4">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item active" aria-current="page">Users</li>
            </ol>
        </nav>
    </div>

    <#if items?? && (items?size > 0)>
        <div class="card shadow-sm border-0">
            <div class="card-header bg-white d-flex justify-content-between align-items-center">
                <h4 class="mb-0">Organization Users</h4>
            </div>
            <div class="list-group list-group-flush">
                <#list items as user>
                    <div class="list-group-item">
                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <h5 class="mb-1">${user.email}</h5>
                                <p class="mb-0 text-muted small">
                                    User ID: ${user.id}
                                </p>
                            </div>
                            <div>
                                <a href="/users/${user.id}" class="btn btn-sm btn-outline-primary">Details</a>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
    <#else>
        <div class="alert alert-info">
            No users in this organization yet.
        </div>
    </#if>
</@template.page>
