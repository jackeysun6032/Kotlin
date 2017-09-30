package com.sxc.kotlin.bean.meizhi;

import android.annotation.SuppressLint;

import com.sxc.kotlin.utils.EncryptionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


/**
 * Created by Alpha on 2016/12/6.
 */

public class GankMeiZhi implements EntityThrans {

    /**
     * error : false
     * results : [{"_id":"58460694421aa939b58d31e3","createdAt":"2016-12-06T08:30:12.824Z","desc":"12-6","publishedAt":"2016-12-06T11:33:36.433Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034jw1fagrnmiqm1j20u011hanr.jpg","used":true,"who":"daimajia
     * "},{"_id":"5844b8dd421aa939befafb03","createdAt":"2016-12-05T08:46:21.259Z","desc":"12-5","publishedAt":"2016-12-05T11:40:51.351Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034gw1fafmi73pomj20u00u0abr.jpg","used":true,"who":"daimajia"},{"_id":"5840bd8a421aa939bb4637e9","createdAt":"2016-12-02T08:17:14.322Z","desc":"12-2","publishedAt":"2016-12-02T12:13:34.224Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034gw1fac4t2zhwsj20sg0izahf.jpg","used":true,"who":"代码家"},{"_id":"583f6a3b421aa939bb4637e1","createdAt":"2016-12-01T08:09:31.936Z","desc":"12-1","publishedAt":"2016-12-01T11:36:13.685Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/610dc034jw1faaywuvd20j20u011gdli.jpg","used":true,"who":"daimajia"},{"_id":"583d96f6421aa939befafacf","createdAt":"2016-11-29T22:55:50.767Z","desc":"11-30","publishedAt":"2016-11-30T11:35:55.916Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/610dc034gw1fa9dca082pj20u00u0wjc.jpg","used":true,"who":"daimajia"},{"_id":"583cc2bf421aa971108b6599","createdAt":"2016-11-29T07:50:23.705Z","desc":"11-29","publishedAt":"2016-11-29T11:38:58.378Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034jw1fa8n634v0vj20u00qx0x4.jpg","used":true,"who":"daimajia"},{"_id":"583b8285421aa9710cf54c3b","createdAt":"2016-11-28T09:04:05.479Z","desc":"11-28","publishedAt":"2016-11-28T11:32:07.534Z","source":"chrome","type":"福利","url":"http://ww3.sinaimg.cn/large/610dc034jw1fa7jol4pgvj20u00u0q51.jpg","used":true,"who":"daimajia"},{"_id":"58378c0f421aa91cade7a57d","createdAt":"2016-11-25T08:55:43.173Z","desc":"11-25","publishedAt":"2016-11-25T11:29:49.832Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034jw1fa42ktmjh4j20u011hn8g.jpg","used":true,"who":"dmj"},{"_id":"58362e82421aa9721eb68ac1","createdAt":"2016-11-24T08:04:18.98Z","desc":"11-24","publishedAt":"2016-11-24T11:40:53.615Z","source":"chrome","type":"福利","url":"http://ww3.sinaimg.cn/large/610dc034jw1fa2vh33em9j20u00zmabz.jpg","used":true,"who":"daimajia
     * "},{"_id":"58350b5e421aa972148296ed","createdAt":"2016-11-23T11:22:06.516Z","desc":"11-23","publishedAt":"2016-11-23T11:27:52.847Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/610dc034gw1fa1vkn6nerj20u011gdjm.jpg","used":true,"who":"代码家"}]
     */

    public boolean error;

    public List<ResultsBean> results;


    @SuppressLint("SimpleDateFormat")
    @Override
    public List<NewslistBean> transform() {
        List<NewslistBean> meiZhis = new ArrayList<>();
        int size = results.size();
        for (int i = 0; i < size; i++) {
            ResultsBean resultsBean = results.get(i);
            NewslistBean newslistBean = new NewslistBean();
            newslistBean.picUrl = resultsBean.url;
            newslistBean.url = resultsBean.source;

            try {

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                df.setTimeZone(TimeZone.getTimeZone("UTC"));
                String time = df.format(df.parse(resultsBean.createdAt));
                newslistBean.ctime = time;
            } catch (Exception e) {
                e.printStackTrace();
            }

            newslistBean.id = EncryptionUtils.md5(newslistBean.picUrl);
            meiZhis.add(newslistBean);
        }
        return meiZhis;
    }


}
