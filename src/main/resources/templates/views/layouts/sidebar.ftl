<#import "./sidebar_component.ftl" as sidebar_component>

<div class="border border-black-50 d-none d-lg-block sidebar bg-dark">

    <@sidebar_component.card url="/" title="ホーム" id="home"/>

    <@sidebar_component.card url="/message" title="メッセージ" id="message"/>

    <@sidebar_component.card url="/introduction" title="紹介文" id="introduction"/>

</div>

<script>
    let selectedSidebar

    switch (location.pathname){
        case '/': selectedSidebar = $('#home'); break;
        case '/message': selectedSidebar = $('#message'); break;
        case '/introduction': selectedSidebar = $('#introduction'); break;
    }
    selectedSidebar.css('font-size', 'xx-large')
</script>
