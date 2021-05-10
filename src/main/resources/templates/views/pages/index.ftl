<#-- @ftlvariable name="sentMessages" type="kotlin.collections.List<com.wsr.model.h2.entities.SentMessage>" -->

<#assign title="KoTo-Home">

<#import "../layouts/app.ftl" as app/>
<#import "../layouts/component.ftl" as component/>

<@app.content title=title>
    <div class="container">
        <div class="row">

            <@component.card title="メッセージ送信数">
                <canvas id="myLineChart"></canvas>
            </@component.card>

            <@component.card title="送信履歴">
                <table class="table table-striped table-bordered" style="width: 850px;">
                    <thead class="table-dark">
                    <tr>
                        <th scope="col">アクション</th>
                        <th scope="col">返答</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list sentMessages as sentMessage>
                        <tr>
                            <td>${sentMessage.comingMessage}</td>
                            <td>${sentMessage.reply}</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </@component.card>
        </div>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>
    <script>

        // const dayToCount = {
        //     10: 3,
        //     11: 2,
        //     12: 2,
        //     13: 3,
        //     14: 5
        // }

        let json

        $.getJSON("/api/message/dayToCount", (json) => {

            let value = 0
            let dayToCount = []

            for(value of json){
                dayToCount[Object.keys(value)] = Object.values(value)
            }

            const dayLabels = Object.keys(dayToCount)
            const countData = Object.values(dayToCount)

            const ctx = document.getElementById("myLineChart");
            new Chart(ctx, {
                type: 'line',
                data: {
                    labels: dayLabels,
                    datasets: [{
                        label: '送信されたメッセージ数',
                        data: countData,
                        borderColor: 'rgba(255, 100, 100, 1)'
                    }]
                },
                options: {
                    scales: {
                        yAxes: [{
                            ticks: {
                                min: 0
                            }
                        }]
                    }
                }
            });
        })
    </script>
</@app.content>
