<#macro content title>

    <head>
        <title>${title}</title>
        <#include "meta.ftl">
    </head>


    <body>
        <#include "header.ftl">
        <#nested/>
        <#include "footer.ftl">
    </body>

</#macro>
