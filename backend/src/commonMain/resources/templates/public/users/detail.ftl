<#import "../template.ftl" as template>
<@template.page>
    <div class="mb-4">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="/users">Users</a></li>
                <li class="breadcrumb-item active" aria-current="page">${item.email}</li>
            </ol>
        </nav>
    </div>

    <div class="card shadow-sm border-0 mb-4">
        <div class="card-body">
            <div class="d-flex justify-content-between align-items-start mb-3">
                <div>
                    <h2 class="card-title mb-2">${item.email}</h2>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <dl class="row">
                        <dt class="col-sm-4">Email</dt>
                        <dd class="col-sm-8">${item.email}</dd>

                        <dt class="col-sm-4">User ID</dt>
                        <dd class="col-sm-8">${item.id}</dd>

                        <dt class="col-sm-4">Organization</dt>
                        <dd class="col-sm-8">${organization.name}</dd>

                        <dt class="col-sm-4">Organization ID</dt>
                        <dd class="col-sm-8">${item.organizationId}</dd>
                    </dl>
                </div>
            </div>
        </div>
    </div>
</@template.page>
