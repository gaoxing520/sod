package com.piesat.dm.core.factory;

import com.piesat.common.utils.DateUtils;
import com.piesat.dm.common.util.TemplateUtil;
import com.piesat.dm.core.constants.Constants;
import com.piesat.dm.core.enums.DatabaseTypesEnum;
import com.piesat.dm.core.model.SelectVo;
import com.piesat.dm.core.template.SqlTemplate;
import com.piesat.util.ResultT;

import java.util.List;
import java.util.Map;

/**
 * @author cwh
 * @date 2020年 12月09日 11:22:51
 */
public class CommData extends Actuator {


    public CommData(DatabaseTypesEnum databaseType, Object con) {
        super(databaseType, con);
    }

    public void countData(SelectVo selectVo, ResultT<String> resultT) {
        String today = DateUtils.getToday();
        String yesterday = DateUtils.getYesterday();
        selectVo.setBeginTime(yesterday);
        selectVo.setTimeColumn(Constants.D_DATETIME);
        selectVo.setEndTime(today);
        selectVo.setColumnStr(Constants.COUNT + Constants.RT);

        String sql = TemplateUtil.rendering(SqlTemplate.QUERY, selectVo);
        Map<String, Object> all = this.exeQueryOne(sql, resultT);
        String all_ct = all == null ? Constants.ZERO : all.get(Constants.RT).toString();

        sql = TemplateUtil.rendering(SqlTemplate.QUERY_BEGIN_END, selectVo);
        Map<String, Object> map = this.exeQueryOne(sql, resultT);
        String ct = map == null ? Constants.ZERO : map.get(Constants.RT).toString();

        this.getPartiInfo(selectVo, resultT, true);
        String min = String.format(Constants.MIN, Constants.D_DATETIME);
        selectVo.setColumnStr(min + Constants.RT);
        sql = TemplateUtil.rendering(SqlTemplate.QUERY, selectVo);
        map = this.exeQueryOne(sql, resultT);
        String min_time = map == null ? null : map.get(Constants.RT).toString();

        this.getPartiInfo(selectVo, resultT, false);
        String max = String.format(Constants.MAX, Constants.D_DATETIME);
        selectVo.setColumnStr(max + Constants.RT);
        sql = TemplateUtil.rendering(SqlTemplate.QUERY, selectVo);
        map = this.exeQueryOne(sql, resultT);
        String max_time = map == null ? null : map.get(Constants.RT).toString();
        System.out.println(all_ct + " " + ct + " " + min_time + " " + max_time);
    }


    public void getPartiInfo(SelectVo selectVo, ResultT<String> resultT, Boolean isAsc) {
        selectVo.setWhere(null);
        if (this.databaseType.equals(DatabaseTypesEnum.XUGU)) {
            String query_parti = TemplateUtil.rendering(SqlTemplate.QUERY_PARTI, selectVo);
            if (!isAsc) {
                query_parti += Constants.DESC;
            }
            List<Map<String, Object>> parti = this.exeQuery(query_parti, resultT);
            if (parti == null) {
                selectVo.setTimeColumn(null);
            } else {
                String where = Constants.D_DATETIME +
                        (isAsc ? Constants.LE : Constants.GT) +
                        (parti.size() > 1 ? parti.get(1).get(Constants.RT) : parti.get(0).get(Constants.RT));
                selectVo.setWhere(where);
            }
        } else {
            selectVo.setTimeColumn(null);
        }
    }
}
