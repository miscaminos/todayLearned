var app = new Vue({
    // el은 element의 약어
    // DOM요소를 가리키는 선택자
    // vue.js가 제어하는 범위
    el: '#app-review1',
    // 어플리케이션에서 사용하는 데이터
    data: {
        header:'튜토리얼 리뷰 1',
        topic:'Vue Application Directives: Basics',

        value:0,
        note1:'<u>index.html의 구성<u>',
        note2:'<u>index.js의 구성<u>',
        note3:'<u>v-text<u>',
        note4:'<u>v-html<u>',
        note5:'<u>v-show<u>',
        note6:'<u>v-if & v-else-if & v-else<u>',
        note7:'<u>v-for<u>',
        note8:'<u>v-on<u>',
        note9:'<u>v-bind<u>',
        note10:'<u>v-model<u>',

        htmlLines:[
            'index.html내에서 vue application을 통해 DOM요소를 제어하려면 꼭 포함되어야하는 부분:',
            '<head>',
            '   <!-- vue.js코드를 다운로드-->',
            '   <!-- Vue는 전역 변수로 등록됨 -->',
            '   <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>',
            '</head>',
            '<body>',
            '   <div id="app-review1">',
            '       <!-- Vue어플리케이션은 단일 DOM요소에 연결되어 DOM요소를 완전히 제어함 -->',
            '       <!-- vue application으로 <div id="app-review1"></div>안에 있는 요소만 제어 -->',
            '       <!-- index.js에서 생성된 Vue인스턴스의 데이터 내용을 반영-->',
            '   </div>',
            '   <!-- index.js를 로드 -->',
            '   <script src="index.js"></script>',
            '</body>'
        ],
        jsLines:[
            'index.js에서 꼭 포함되어야하는 부분:',
            'var app = new Vue({',
            'el: "#app-review1",',
            '// 어플리케이션에서 사용하는 데이터',
            'data: {',
            ' ',
            '},',
            '// 어플리케이션에서 사용하는 메소드',
            'methods:{',
            ' ',
            '}',
            '});'
        ],
        d3:['v-text:','text를 렌더링하는 지시자'],
        d4:[
            'v-html:','html 속성을 제어하는 지시자.',
            'html keyword를 렌더링 한다.',
            '(vue에 제어하려는 tag를 쓰면 그대로 반영이된다)'
        ],
        d5:[
            'v-show:','CSS의 display properties를 제어하는 지시자.',
            'v-show가 true인 경우, text가 표시되고, false인경우 텍스트가 표시되지 않는다.'
        ],
        d6:[
            'v-if & v-else-if & v-else:',
            'v-if는 true인 경우에만 렌더링된다.',
            'v-else-if는 v-if와 함께 사용된다. 여러 cases를 다룰때 사용됨 (e.g., radio buttons)',
            'v-else는 v-if 지시자와 함께 사용되며, v-if가 true이면 v-else는 false이다(또는 그 반대).'
        ],
        d7:[
            'v-for:','배열이나 list를 연속해서 보여주는 렌더링 지시자',
            'list내 item들을 iteration을 통해 반복적으로 렌더링하고싶을때 사용할 수 있음.'
        ],
        d8:['v-on:','event를 처리하는 렌더링 지시자'],
        d9:['v-bind:','HTML태그의 속성을 변경하는 지시자'],
        d10:[
            'v-model:','양방향 데이터 바인딩을 지시한다',
            'v-bind는 HTML요소에만 영향을 끼치지만,',
            'v-model은 vue object와 HTML요소 값에 쌍방향으로 영향을 끼친다.',
            '(e.g, HTML값이 변하면 vue obj값도 변함)'
        ],

        demo1: 'v-bind & v-model demonstration:',
        message1:'v-bind의 경우',
        message2:'v-model의 경우',
        resultBind:'입력해주세요',
        resultModel:'입력해주세요',

        demo2:'v-on demonstration:',
        calories: 200,
        out:100,
        in: 2000,
        label1:'운동운동!',
        label2:'냠냠먹자',

        isChecked:false,
        congrats: '<b>수고하셨습니다!<b>'

    },
    methods:{
        exercise: function(){
            this.calories-=this.out;
            if(this.calories<=0){
                this.calories=0;
            }
        },
        eat: function(){
            this.calories+=this.in;
        },
        onClickCheckButton: function (e) {
            this.isChecked = e.target.checked
        }
    }
 });