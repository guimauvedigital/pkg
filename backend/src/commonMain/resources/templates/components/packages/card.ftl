<#macro card pkg>
    <div class="card mb-3 shadow-sm border-0">
        <div class="card-body d-flex justify-content-between align-items-start">
            <div>
                <h5 class="card-title mb-1">${pkg.name}
                    <span class="badge ${pkg.public?then('bg-success', 'bg-danger')} me-2">
                        ${pkg.public?then('Public', 'Private')}
                    </span>
                </h5>
                <p class="card-text text-muted small mb-1">${pkg.description!pkg.name}</p>
                <small class="text-muted">
                    Published on ${pkg.createdAt}
                </small>
            </div>
            <div class="text-end">
                <span class="badge bg-light text-dark">${pkg.format}</span><br>
                <a href="/packages/${pkg.id}" class="btn btn-sm btn-outline-primary mt-2">More</a>
            </div>
        </div>
    </div>
</#macro>
