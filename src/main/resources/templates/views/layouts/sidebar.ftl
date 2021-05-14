<#import "./sidebar_component.ftl" as sidebar_component>

<div class="d-none d-lg-block sidebar">

    <div class="mt-3">
        <img src="/src/icon.png" alt="表示できませんでした" style="width: 200px; margin-left: 75px"/>
    </div>

    <@sidebar_component.card url="/" title="ホーム" id="home"/>

    <@sidebar_component.card url="/message" title="メッセージ" id="message"/>

    <@sidebar_component.card url="/introduction" title="紹介文" id="introduction"/>

    <div>
        <img src="/src/whatIsKoTo.png" alt="表示できませんでした" style="width: 300px; margin-left: 25px; bottom: 30px; position: absolute">
    </div>
</div>

<script>
    let selectedSidebar

    switch (location.pathname){
        case '/': selectedSidebar = $('#home'); break;
        case '/message': selectedSidebar = $('#message'); break;
        case '/introduction': selectedSidebar = $('#introduction'); break;
    }
    selectedSidebar.addClass('sidebar_component_active')
</script>
