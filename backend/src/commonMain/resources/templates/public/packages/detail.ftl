<#import "../template.ftl" as template>
<@template.page>
    <div class="mb-4">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="/packages">Packages</a></li>
                <li class="breadcrumb-item active" aria-current="page">${item.name}</li>
            </ol>
        </nav>
    </div>

    <div class="card shadow-sm border-0 mb-4">
        <div class="card-body">
            <div class="d-flex justify-content-between align-items-start mb-3">
                <div>
                    <h2 class="card-title mb-2">${item.name}</h2>
                    <div class="mb-2">
                        <span class="badge ${item.public?then('bg-success', 'bg-danger')} me-2">
                            ${item.public?then('Public', 'Private')}
                        </span>
                        <span class="badge bg-light text-dark">${item.format}</span>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <dl class="row">
                        <dt class="col-sm-4">Created</dt>
                        <dd class="col-sm-8">${item.createdAt}</dd>
                    </dl>
                </div>
            </div>
        </div>
    </div>

    <#if versions?? && (versions?size > 0)>
        <div class="card shadow-sm border-0">
            <div class="card-header bg-white">
                <h4 class="mb-0">Versions</h4>
            </div>
            <div class="list-group list-group-flush">
                <#list versions as version>
                    <div class="list-group-item">
                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <h5 class="mb-1">
                                    ${version.version}
                                    <#if version.yanked>
                                        <span class="badge bg-warning text-dark">Yanked</span>
                                    </#if>
                                </h5>
                                <p class="mb-1 text-muted small">
                                    Published on ${version.publishedAt}
                                </p>
                                <#if version.metadata??>
                                    <p class="mb-0 text-muted small">${version.metadata}</p>
                                </#if>
                            </div>
                            <div>
                                <a href="/packages/${item.id}/versions/${version.id}"
                                   class="btn btn-sm btn-outline-primary">Details</a>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
    <#else>
        <div class="alert alert-info">
            No versions published yet for this package.
        </div>
    </#if>
</@template.page>
