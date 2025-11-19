<#import "../../template.ftl" as template>
<@template.page>
    <div class="mb-4">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="/packages">Packages</a></li>
                <li class="breadcrumb-item"><a href="/packages/${package.id}">${package.name}</a></li>
                <li class="breadcrumb-item active" aria-current="page">${item.version}</li>
            </ol>
        </nav>
    </div>

    <div class="card shadow-sm border-0 mb-4">
        <div class="card-body">
            <div class="d-flex justify-content-between align-items-start mb-3">
                <div>
                    <h2 class="card-title mb-2">
                        ${package.name} <span class="text-muted">v${item.version}</span>
                    </h2>
                    <div class="mb-2">
                        <span class="badge ${package.public?then('bg-success', 'bg-danger')} me-2">
                            ${package.public?then('Public', 'Private')}
                        </span>
                        <span class="badge bg-light text-dark">${package.format}</span>
                        <#if item.yanked>
                            <span class="badge bg-warning text-dark">Yanked</span>
                        </#if>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <dl class="row">
                        <dt class="col-sm-4">Version</dt>
                        <dd class="col-sm-8">${item.version}</dd>

                        <dt class="col-sm-4">Published</dt>
                        <dd class="col-sm-8">${item.publishedAt}</dd>

                        <#if item.metadata??>
                            <dt class="col-sm-4">Metadata</dt>
                            <dd class="col-sm-8">${item.metadata}</dd>
                        </#if>
                    </dl>
                </div>
            </div>
        </div>
    </div>

    <#if files?? && (files?size > 0)>
        <div class="card shadow-sm border-0">
            <div class="card-header bg-white">
                <h4 class="mb-0">Files</h4>
            </div>
            <div class="list-group list-group-flush">
                <#list files as file>
                    <div class="list-group-item">
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="flex-grow-1">
                                <h5 class="mb-1">
                                    <i class="bi bi-file-earmark"></i> ${file.name}
                                </h5>
                                <p class="mb-0 text-muted small">
                                    ${file.contentType}
                                    <#if file.size gt 0>
                                        • ${(file.size / 1024)?string["0.##"]} KB
                                    </#if>
                                </p>
                            </div>
                            <div>
                                <a href="${file.path}" class="btn btn-sm btn-outline-primary" download>
                                    <i class="bi bi-download"></i> Download
                                </a>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
    <#else>
        <div class="alert alert-info">
            No files available for this version.
        </div>
    </#if>
</@template.page>
