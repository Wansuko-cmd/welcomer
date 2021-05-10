<#macro card title>
    <div class="col-auto m-auto my-5">
        <div class="display-6 mb-3">
            ${title}
        </div>

        <div style="width: 850px">
            <#nested >
        </div>
    </div>
</#macro>
