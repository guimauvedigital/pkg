<#import "../template.ftl" as template>
<#import "../../components/packages/card.ftl" as card>
<@template.page>
    <div class="mb-4">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item active" aria-current="page">Packages</li>
            </ol>
        </nav>
    </div>

    <#list items as package>
        <@card.card package />
    </#list>
</@template.page>
