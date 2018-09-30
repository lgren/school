<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css"/>
</head>
<body>
<div class="container">
    <input type="number" id="number" value="1" placeholder="1.爷爷 2.爸爸 3.自己 4.儿子"/>
    <input type="button" onclick="listTest()" value="多重列表测试"/>
</div>
<script src="/webjars/jquery/3.1.0/jquery.min.js"></script>
<script src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="application/javascript">
    function listTest() {
        var url;
        var data;
        var data5 = new Array();
        data5.push({name: "李四"});
        data5.push({name: "张三"});
        var testArr = [null,"1",null,[2,null,3]];
        var data1 = {
            name: "这是爷爷", fatherList: [
                null,
                null,
                {name: "这是爸爸1", list:[
                        {name:"这是我自己", childList:[
                                null,
                                {name: "这是儿子1"},
                                null]},
                        {name:"这是我兄弟1"},
                        {name:"这是我兄弟2"}]},
                {name: "这是爸爸2"}]
        };
        switch ($("#number").val()) {
            default:
            case "1": url = "/listTestGrandFather.do"; data = data1;break;
            case "2": url = "/listTestFather.do"; break;
            case "3": url = "/listTest.do"; break;
            case "4": url = "/listTestChild.do"; break;
            case "5": url = "/listTestChildList.do"; data = data5;break;
            case "6": url = "/json.do"; break;
        }
        $.ajax({
            url : url,
            type : 'POST',
            contentType: "application/json;charset=utf-8",
            data : JSON.stringify(data),
            success: function (result) {
                console.log(result);
            },
            error: function (result) {
                console.log(result);
            }
        });
    }
    var testArr = [null,"1",null,[2,null,3]];
    var json = {
    "crowdJson": "{\"id\":\"7b7917f33be44d459581f4dc3464297d\",\"sbblockList\":[{\"functionType\":1,\"id\":\"d3daa6c6681147fe82dcedaa0042622e\",\"mid\":\"7b7917f33be44d459581f4dc3464297d\",\"conditionList\":[{\"mid\":\"7b7917f33be44d459581f4dc3464297d\",\"msid\":\"d3daa6c6681147fe82dcedaa0042622e\",\"businessType\":\"1\",\"businessId\":\"1\",\"logicJson\":{\"businessType\":\"1\",\"businessId\":\"1\",\"leafId\":[\"1\"]},\"id\":\"cc325d0a5ce84c509d7d64a2ea63dbd4\"}]}]}",
    "crowdMain": {
        "id": "7b7917f33be44d459581f4dc3464297d",
        "sbblockList": [{
            "functionType": 1,
            "id": "d3daa6c6681147fe82dcedaa0042622e",
            "mid": "7b7917f33be44d459581f4dc3464297d",
            "conditionList": [{
                "mid": "7b7917f33be44d459581f4dc3464297d",
                "msid": "d3daa6c6681147fe82dcedaa0042622e",
                "businessType": "1",
                "businessId": "1",
                "logicJson": {
                    "businessType": "1",
                    "businessId": "1",
                    "leafId": ["1","3","2","7"]
                },
                "id": "cc325d0a5ce84c509d7d64a2ea63dbd4"
            }]
        },{
            "functionType": 1,
            "id": "d3daa6c6681147fe82dcedaa0042622e",
            "mid": "7b7917f33be44d459581f4dc3464297d",
            "conditionList": [{
                "mid": "7b7917f33be44d459581f4dc3464297d",
                "msid": "d3daa6c6681147fe82dcedaa0042622e",
                "businessType": "1",
                "businessId": "1",
                "logicJson": {
                    "businessType": "1",
                    "businessId": "1",
                    "leafId": ["1","3","2","7"]
                },
                "id": "cc325d0a5ce84c509d7d64a2ea63dbd4"
            }]
        }]
    },
    "appMonitoringLink": "",
    "appSendHour": 8,
    "appSendMinute": 23,
    "crowdMainId": "7b7917f33be44d459581f4dc3464297d",
    "currPage": 1,
    "id": "1a899ad6050f4aa485fde18fef61af65",
    "pageSize": 20,
    "periodType": 2,
    "sendPeriod": 1,
    "smsMonitoringLink": "",
    "smsSendHour": 8,
    "smsSendMinute": 23,
    "start": 0,
    "taskName": "打撒奥所大所",
    "taskPrice": 123,
    "taskSendReason": "【发送原因】\r\n大萨达萨达萨达撒多撒萨达撒大声地撒多撒大所大所多奥术大师大所大所多撒大所多撒多撒\r\n\r\n【发送价值】",
    "taskType": 2,
    "totalPage": 0,
    "totalRecord": 0,
    "weixinMonitoringLink": "",
    "weixinSendHour": 8,
    "weixinSendMinute": 23
};





    /**
     * 工具js 基本依赖jQuery
     * @create 2018/9/1 11:09
     * @author Lgren
     */


    //region 将数组或对象下 的数组和对象所有的 null值去除
    /**
     * 将数组或对象下 的数组和对象所有的 null值去除
     * @create 2018/8/31 22:09
     * @author Lgren
     * @param obj 数组或对象
     */
    function removeObjectAllNull(obj){
        if (obj instanceof Array) {
            for(var i=0; i<obj.length; i++){
                var item1 = obj[i];
                if(item1 === null || typeof(item1) === "undefined"){
                    obj.splice(i,1);
                    i--;
                } else if (item1 instanceof Object) {
                    removeObjectAllNull(item1);
                }
            }
        } else if (obj instanceof Object) {
            for(var j in obj){
                var item2 = obj[j];
                if(item2 === null || typeof(item2) === "undefined"){
                    delete obj[j];
                } else if (item2 instanceof Object) {
                    removeObjectAllNull(item2);
                }
            }
        }
        return obj;
    }
    //endregion

    //region 将json对象转换数据转换成 form input的形式
    /**
     * 首先! 直接使用 jsonToForm($form, value)即可 此方法不用外边直接调
     * 将json对象转换数据转换成 form input的形式
     * 最大作用是将对象中的数组也转换成可支持后台接收 list的形式
     * 使用如下
     * var $form = $("<form method='post' action="+url+"></form>");
     * var data = {name:"one",hobby:[{name:"游戏"},{name:"睡觉"}]};
     * jsonToForm($form, null, data, "hobby"); 将会排除掉 hobby的解析直接返回成字符串
     * @create 2018/9/1 13:27
     * @author Lgren
     * @param $form 一个jQuery的form对象
     * @param key 直接填写 null (主要是递归使用)
     * @param value json对象数据
     * @param excludeFieldArrOrString 需要排除不解析的字段 可以是String 也可以是Array 例如 "hobby" 或者 ["hobby", "sex"]
     */
    function formGetInput($form, key, value, excludeFieldArrOrString) {
        var input;
        $.each(value,function (k,v) {
            var flag = false;
            if (typeof(excludeFieldArrOrString) === "string") {
                flag = true;
            } else if (excludeFieldArrOrString instanceof Array) {
                flag = true;
                for (var i = 0; (i < excludeFieldArrOrString.length) && flag; i++) {
                    flag = flag && k !== excludeFieldArrOrString[i];
                }
            }
            var name = key == null ? k : !isNaN(k) ? key + "[" + k + "]" : key + "." + k;// input的name命名规则
            if (v instanceof Object && flag) {// 如果value是对象(Array或Object)则开始递归
                formGetInput($form, name, v, excludeFieldArrOrString);
            } else {

                v = flag ? v : JSON.stringify(v);
                input = $("<input type='hidden'>");
                input.attr({"name": name, "value": v});
                $form.append(input);
            }
        });
        return $form;
    }

    //endregion

    //region jsonObject使用form提交方式
    /**
     * 将一个json对象以post方式转换成form提交
     * 注意:依赖 jsonToForm($form, key, value)方法
     * 使用如下
     * var data = {name:"one",hobby:[{name:"游戏"},{name:"睡觉"}]};
     * submitJson("post", "/toTest", data, "hobby"); 将会排除掉 hobby的解析直接返回成字符串
     * @create 2018/9/1 13:27
     * @author Lgren
     * @param method 请求方法支持 get/post/put/delete等
     * @param url 需要响应的地址
     * @param jsonObject json数据对象
     * @param excludeFieldArrOrString 需要排除不解析的字段 可以是String 也可以是Array 例如 "hobby" 或者 ["hobby", "sex"]
     */
    function submitJsonObject(method, url, jsonObject, excludeFieldArrOrString){
        method = method.toLowerCase();
        var $form = $("<form action='"+url+"'></form>");
        if (method !== "get" && method !== "post") {
            var input = $("<input type='hidden' name='_method' value='"+method+"'>");
            $form.append(input);
            method = "post";
        }
        $form.attr("method",method);
        formGetInput($form, null, jsonObject, excludeFieldArrOrString);
        $(document.body).append($form);
        $form.submit();
        document.body.removeChild($form[0]);
    }
    //endregion

    //region 将form表单变为json对象
    /**
     * 将form表单变为json对象
     * @param from 需要转换的form表单
     * @param parseJsonFieldArrOrString 当一个input的value是Json且需要解析成Json对象时 在此栏加上对应的字段名 可以是String 可以是 Array 例如 "hobby" 或者 ["hobby", "sex"]
     * @returns formJsonObj json对象
     */
    function formToJsonObject(from, parseJsonFieldArrOrString) {
        var formJsonObj = {};
        var formArray = $(from).serializeArray();
        for (var i = 0; i < formArray.length; i++) {
            var objVar = formArray[i];
            if (objVar.value == null || objVar.value === "") {
                continue;
            }
            var flag = objVar.name === parseJsonFieldArrOrString;
            if (!flag && parseJsonFieldArrOrString instanceof Array) {
                for (var j = 0; (j < parseJsonFieldArrOrString.length) && !flag; j++) {
                    flag = objVar.name === parseJsonFieldArrOrString[j];
                }
            }
            flag ? objVar.value = eval("("+ objVar.value +")") : null;
            formJsonObj[objVar.name]=objVar.value;
        }
        return formJsonObj
    }
    //endregion
</script>
</body>
</html>
