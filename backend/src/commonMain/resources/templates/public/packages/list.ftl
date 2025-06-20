<#import "../template.ftl" as template>
<#import "../../components/packages/card.ftl" as card>
<@template.page>
    <#list items as package>
        <@card.card package />
    </#list>
</@template.page>
