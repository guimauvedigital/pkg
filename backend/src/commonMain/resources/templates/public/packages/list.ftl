<#import "../template.ftl" as template>
<@template.page>
    <#list items as package>
        ${package.name}<br/>
    </#list>
</@template.page>
