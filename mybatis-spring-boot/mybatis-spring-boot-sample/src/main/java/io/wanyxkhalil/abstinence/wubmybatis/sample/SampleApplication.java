package io.wanyxkhalil.abstinence.wubmybatis.sample;

import com.github.pagehelper.Page;
import io.wanyxkhalil.abstinence.common.domain.request.PageReq;
import io.wanyxkhalil.abstinence.wubmybatis.sample.dao.DebitOrderDAO;
import io.wanyxkhalil.abstinence.wubmybatis.sample.domain.DebitOrder;
import io.wanyxkhalil.abstinence.wubmybatis.utils.PageHelperUtils;
import io.wanyxkhalil.abstinence.wubmybatis.web.response.PageRespMybatisData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SampleApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }

    @Autowired
    private DebitOrderDAO debitOrderDAO;

    @Override
    public void run(String... args) throws Exception {
        PageReq pageReq = new PageReq();
        pageReq.setPageNum(22);
        pageReq.setPageSize(3);

//        single(pageReq);

        withDoSelect(pageReq);
    }

    private void single(PageReq pageReq) {
        PageHelperUtils.startPage(pageReq);
        List<DebitOrder> list = debitOrderDAO.findAll();

        PageRespMybatisData data = new PageRespMybatisData();
        data.buildPageInfo(list);

        System.out.println(data);
        list.forEach(System.out::println);

//        List<DebitOrder> list2 = debitOrderDAO.findAll();
//        System.out.println(list2.size());
    }

    private void withDoSelect(PageReq pageReq) {
        Page<DebitOrder> page = PageHelperUtils.startPage(pageReq, () -> debitOrderDAO.findAll());

        PageRespMybatisData data = new PageRespMybatisData();
        data.buildPageInfo(page);
        System.out.println(data);

        page.forEach(System.out::println);

//        List<DebitOrder> list2 = debitOrderDAO.findAll();
//        System.out.println(list2.size());
    }
}
