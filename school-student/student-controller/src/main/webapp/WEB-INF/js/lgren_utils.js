/**
 * 工具js 基本依赖jQuery
 * @create 2018/9/1 11:09
 * @author Lgren
 */


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

/**
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
function jsonToForm($form, value, excludeFieldArrOrString, key) {
    var input;
    $.each(value,function (k,v) {
        var flag = k !== excludeFieldArrOrString;
        if (flag && excludeFieldArrOrString instanceof Array) {
            for (var i = 0; (i < excludeFieldArrOrString.length) && flag; i++) {
                flag = k !== excludeFieldArrOrString[i];
            }
        }
        var name = key == null ? k : !isNaN(k) ? key + "[" + k + "]" : key + "." + k;// input的name命名规则
        if (v instanceof Object && flag) {// 如果value是对象(Array或Object)则开始递归
            jsonToForm($form, v, excludeFieldArrOrString, name);
        } else {

            v = flag ? v : JSON.stringify(v);
            input = $("<input type='hidden'>");
            input.attr({"name": name, "value": v});
            $form.append(input);
        }
    });
    return $form;
}

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
function submitJson(method, url, jsonObject, excludeFieldArrOrString){
    method = method.toLowerCase();
    var $form = $("<form action='"+url+"'></form>");
    if (method !== "get" && method !== "post") {
        var input = $("<input type='hidden' name='_method' value='"+method+"'>");
        $form.append(input);
        method = "post";
    }
    $form.attr("method",method);
    jsonToForm($form, jsonObject, excludeFieldArrOrString);
    $(document.body).append($form);
    $form.submit();
    document.body.removeChild($form[0]);
}

/**
 * 将form表单变为json对象
 * @param from 需要转换的form表单
 * @param parseJsonFieldArrOrString 当一个input的value是Json字符串且需要解析成Json对象时 在此栏加上对应的字段名 可以是String 可以是 Array 例如 "hobby" 或者 ["hobby", "sex"]
 * @returns formJsonObj json对象
 */
function formToJson(from, parseJsonFieldArrOrString) {
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
        if (formJsonObj[objVar.name] == null) {
            formJsonObj[objVar.name] = objVar.value;
        } else {
            formJsonObj[objVar.name] = formJsonObj[objVar.name] + "," + objVar.value;
        }
    }
    return formJsonObj
}

/**
 * 获取例如 "测试{name},再次测试{user}"中的 name和 user字段并返回一个 例如["name", "user"]的数组
 * getStrArrBeteenOfTwoStr("测试{name},再次测试{user}","{","}") = ["name", "user"];
 * @create 2018/9/5 13:31
 * @author Lgren
 */
function getStrArrBetweenOfTwoStr(str, start, end) {
    var strArr = [];
    if (isEmpty(str)) {
        return strArr;
    }
    var number = 0;
    var strVar = str;
    var startNum = strVar.indexOf(start);
    var endNum = strVar.indexOf(end);
    while(startNum !== -1 && endNum !== -1) {
        strArr[number] = strVar.substring(strVar.indexOf(start)+1,strVar.indexOf(end));
        number++;
        strVar = strVar.substring(strVar.indexOf(end)+1);
        startNum = strVar.indexOf(start);
        endNum = strVar.indexOf(end);
    }
    return strArr;
}

//判断非空
function isEmpty(value) {
    if (value === undefined || value == null || value === 'null'
        || value === 'undefined' || value.length === 0) {
        return true;
    }
    return false;
}