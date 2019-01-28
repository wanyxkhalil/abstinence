package io.wanyxkhalil.abstinence.wubmybatis.sample.dao;

import io.wanyxkhalil.abstinence.wubmybatis.sample.domain.DebitOrder;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DebitOrderDAO {

    @Select("select id, user_name, basic_time from debit_order order by id")
    List<DebitOrder> findAll();
}
