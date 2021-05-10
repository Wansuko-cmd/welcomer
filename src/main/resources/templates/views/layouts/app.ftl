<#macro content title>

    <head>
        <title>${title}</title>
        <#include "meta.ftl">
    </head>

    <body>
    <div class="app">
        <#include "header.ftl">
        <div class="d-flex" style="height: 100vh">
            <#include "sidebar.ftl">
            <#nested/>
        </div>
        <#include "footer.ftl">
    </div>
    </body>

</#macro>
