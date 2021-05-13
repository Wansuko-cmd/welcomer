<#macro card title>
    <div class="col-auto m-auto my-5">
        <div class="d-flex mb-3">
            <div class="rounded" style="background-color: #1A1A1A; width: 8px; height: 50px"></div>
            <div class="h1 mb-3 ps-3">
                ${title}
            </div>
        </div>

        <div style="width: 850px">
            <#nested >
        </div>
    </div>
</#macro>
