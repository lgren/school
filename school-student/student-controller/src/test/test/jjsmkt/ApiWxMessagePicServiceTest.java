package jjsmkt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fang.online.esf.api.service.IApiWxMessagePicService;
import com.fang.online.esf.result.ResultValue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 * @author Lgren
 * @create 2018-08-24 13:48
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class ApiWxMessagePicServiceTest {
    @Autowired
    private IApiWxMessagePicService apiWxMessagePicService;

    Map<String,Object> maps = new HashMap<>();
    @Before
    public void param() {
    }

    /** 获得文章摘要 */
    @Test
    public void getArticleSummary() {

    }

    /** 获得文章总数 */
    @Test
    public void getArticleTotal() {
        String param = "{\"begin_date\": \"2014-12-08\",\"end_date\": \"2014-12-08\"}";
        apiWxMessagePicService.getarticletotal(param);
    }

    /** 获得用户阅读 */
    @Test
    public void getUserRead() {
//        apiWxMessagePicService.getUserRead(param);

    }

    /** 获取用户阅读时间 */
    @Test
    public void getUserReadHour() {

    }

    /** 获得用户分享 */
    @Test
    public void getUserShare() {

    }

    /** 获得用户分享时间 */
    @Test
    public void getUserShareHour() {

    }

    /** 得到材料 */
    @Test
    public void getMaterial() {
        //将封装好的map转换成json格式
        maps.put("type", "image");
        maps.put("offset", "0");
        maps.put("count", "1");
        String jsonStr = JSON.toJSONString(maps);
        ResultValue<JSONObject> resultValue = apiWxMessagePicService.getmaterial(jsonStr);
        System.out.println(resultValue);
    }

    /** 按开放标识发送消息 */
    @Test
    public void sendMessageByOpenId() {

    }

    /** 通过Open Id Test发送消息 可测 */
    @Test
    public void sendMessageByOpenIdTest() {
//        ResultValue<String> x = apiWxMessagePicService.sendMessageByOpenIdTest();


    }

    /** 选择用户电话 可测 */
    @Test
    public void selectByUserPhone() {
        ResultValue<String>  resultValue = apiWxMessagePicService.selectByUserPhone("15073485164");
        System.out.println();
    }
}
