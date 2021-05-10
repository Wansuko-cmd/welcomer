<#macro content title>

    <head>
        <title>${title}</title>
        <#include "meta.ftl">
    </head>

    <body>
        <#include "header.ftl">
        <div class="d-flex">
            <#include "sidebar.ftl">
            <#nested/>
        </div>
        <#include "footer.ftl">
    </body>

</#macro>
