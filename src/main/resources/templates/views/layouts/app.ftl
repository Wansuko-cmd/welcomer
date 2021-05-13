<#macro content title>

    <head>
        <title>${title}</title>
        <#include "meta.ftl">
    </head>

    <body>
    <div class="app">
        <div class="d-flex">
            <#include "sidebar.ftl">
            <#nested/>
        </div>
    </div>
    </body>

</#macro>
